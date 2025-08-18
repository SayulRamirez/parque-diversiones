package com.saul.parque.diversiones.ticket.price;

import com.saul.parque.diversiones.dto.ticket.price.TicketPriceRequest;
import com.saul.parque.diversiones.dto.ticket.price.TicketPriceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketPriceServiceImpl implements TicketPriceService {

    private final TicketPriceRepository ticketPriceRepository;

    @Transactional
    @Override
    public TicketPriceResponse addPrice(TicketPriceRequest request) {

        Optional<TicketPrice> currentPrice = ticketPriceRepository.findByIsCurrentIsTrueAndType(request.type());

        return currentPrice.map(ticket -> {
            ticket.setCurrent(false);
            ticketPriceRepository.save(ticket);

            TicketPrice ticketCurrent = ticketPriceRepository.save(fromRequest(request));
            return fromEntity(ticketCurrent);

        }).orElseGet(() -> {
            TicketPrice ticketCurrent = ticketPriceRepository.save(fromRequest(request));
            return fromEntity(ticketCurrent);
        });
    }

    @Override
    public List<TicketPriceResponse> getAll() {
        return ticketPriceRepository.findAll().stream()
                .sorted(Comparator.comparing(TicketPrice::getDateUpdate).reversed())
                .map(this::fromEntity)
                .toList();
    }

    @Override
    public List<TicketPriceResponse> getAllIsCurrent() {
        return ticketPriceRepository.findAllByIsCurrentIsTrue()
                .stream().map(this::fromEntity)
                .toList();
    }

    private TicketPriceResponse fromEntity(TicketPrice entity) {

        return new TicketPriceResponse(
                entity.getId(),
                entity.getType(),
                entity.getPrice(),
                entity.getDateUpdate(),
                entity.isCurrent()
        );
    }

    private TicketPrice fromRequest(TicketPriceRequest request) {

        return TicketPrice.builder()
                .type(request.type())
                .price(request.price())
                .dateUpdate(LocalDateTime.now())
                .current(true)
                .build();
    }
}
