package com.saul.parque.diversiones.ticket;

import com.saul.parque.diversiones.dto.ticket.DetailsType;
import com.saul.parque.diversiones.dto.ticket.SaleTicketResponse;
import com.saul.parque.diversiones.dto.ticket.TicketResponse;
import com.saul.parque.diversiones.ticket.price.TicketPrice;
import com.saul.parque.diversiones.ticket.price.TicketPriceRepository;
import com.saul.parque.diversiones.ticket.price.TicketType;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final Logger log = LoggerFactory.getLogger(TicketServiceImpl.class);

    private final TicketRepository ticketRepository;

    private final TicketPriceRepository priceRepository;

    @Transactional
    @Override
    public List<TicketResponse> add(Map<Long, Integer> request) {

        List<TicketPrice> ticketPrices = new ArrayList<>();

        request.forEach((key, integer) -> ticketPrices.add(priceRepository.findByIdAndIsCurrentIsTrue(key)
                .orElseThrow(() -> new EntityNotFoundException("El precio con id: " +  key + " no existe o no está activo"))));

        List<TicketResponse> response = new ArrayList<>();

        ticketPrices.forEach(ticketPrice -> {

            Long key = ticketPrice.getId();

            for (int i = 0; i < request.get(key); i++) {
                LocalDateTime now = LocalDateTime.now();

                Ticket ticket = Ticket.builder()
                        .price(ticketPrice)
                        .saleDate(now)
                        .ticketNumber(generateTicket(now, key))
                        .build();

                Ticket save = ticketRepository.save(ticket);

                log.info("Add ticket type {} with id {}", ticket.getPrice().getType(), ticket.getId());

                response.add(fromEntity(save));
            }
        });

        return response;
    }

    @Override
    public SaleTicketResponse getSaleBetween(LocalDate startRequest, LocalDate endRequest) {

        if (endRequest.isBefore(startRequest)) throw new IllegalArgumentException("la fecha de inicio no debe de ser despues de la fecha final");

        LocalDateTime start = LocalDateTime.of(startRequest, LocalTime.MIDNIGHT);
        LocalDateTime end = LocalDateTime.of(endRequest, LocalTime.MAX);

        List<Ticket> salesDate = ticketRepository.findAllBySaleDateBetween(start, end);

        Map<TicketType, DetailsType> detailsType = new LinkedHashMap<>();
        List<TicketResponse> details = new ArrayList<>();

        salesDate.forEach(ticket -> {

            TicketType key = ticket.getPrice().getType();

            detailsType.putIfAbsent(key, new DetailsType(ticket.getPrice().getPrice()));
            detailsType.get(key).sum();

            details.add(fromEntity(ticket));
        });

        double amount = 0;

        for (Map.Entry<TicketType, DetailsType> entry : detailsType.entrySet()) {
            DetailsType value = entry.getValue();
            value.amount();
            amount += value.getAmount();
        }

        return new SaleTicketResponse(amount, detailsType, details);
    }

    private String generateTicket(LocalDateTime date, Long id) {
        return String.format("TKT-%s-%d",
                date.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss-SSS")),
                id);
    }

    private TicketResponse fromEntity(Ticket entity) {
        TicketPrice ticketPrice = entity.getPrice();

        return new TicketResponse(entity.getId(),
                ticketPrice.getPrice(),
                ticketPrice.getType(),
                entity.getSaleDate(),
                entity.getTicketNumber());
    }
}
