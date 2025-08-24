package com.saul.parque.diversiones.game.ticket;

import com.saul.parque.diversiones.dto.game.ticket.GameTicketResponse;
import com.saul.parque.diversiones.game.Game;
import com.saul.parque.diversiones.game.GameRepository;
import com.saul.parque.diversiones.game.ticket.price.GamePrice;
import com.saul.parque.diversiones.game.ticket.price.GamePriceRepository;
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
import java.util.*;

@Service
@RequiredArgsConstructor
public class GameTicketServiceImpl implements GameTicketService {

    private static final Logger log = LoggerFactory.getLogger(GameTicketServiceImpl.class);

    private final GameTicketRepository gameTicketRepository;

    private final GameRepository gameRepository;

    private final GamePriceRepository gamePriceRepository;

    @Transactional
    @Override
    public List<GameTicketResponse> add(Map<Long, Integer> request) {

        Map<Game, GamePrice> games = new LinkedHashMap<>(request.size());

        request.forEach((key, value) -> {
            Game game = gameRepository
                    .findByIdAndActiveIsTrue(key)
                    .orElseThrow(() -> new EntityNotFoundException("no sé encontró el juego con id: " + key));

            GamePrice gamePrice = gamePriceRepository.findByIsCurrentIsTrueAndGameId(key)
                    .orElseThrow(() -> new EntityNotFoundException("no sé encontró el precio del juego con id: " + key));

            games.put(game, gamePrice);
        });

        List<GameTicketResponse> response = new ArrayList<>();

        games.forEach((game, price) -> {

            for (int i = 0; i < request.get(game.getId()); i++) {
                LocalDateTime now = LocalDateTime.now();

                GameTicket gameTicket = gameTicketRepository.save(GameTicket.builder()
                        .saleDate(now)
                        .price(price)
                        .validity(LocalDateTime.of(LocalDate.now(), game.getClosingHours()))
                        .redeemed(false)
                        .build());

                gameTicket.setTicketNumber(generateTicket(now, gameTicket.getId()));

                response.add(fromEntity(gameTicketRepository.save(gameTicket)));
            }
        });

        return response;
    }

    @Override
    public List<GameTicketResponse> getAll() {
        return gameTicketRepository.findAll().stream().map(this::fromEntity).toList();
    }

    @Override
    public void redeem(String numberTicket) {

        gameTicketRepository.findByTicketNumber(numberTicket)
                .ifPresentOrElse(gameTicket -> {
                    gameTicket.setRedeemed(true);
                    gameTicketRepository.save(gameTicket);
                    log.info("ticket with number {} redeemed", numberTicket);
                }, () -> {
                    throw new EntityNotFoundException("no se encontro el ticket con número: " + numberTicket);
                });
    }

    @Override
    public Map<String, Boolean> validSchedule(Long idGame) {

        Game game = gameRepository.findById(idGame)
                .orElseThrow(() -> new EntityNotFoundException("no sé encontró el juego con id: " + idGame));

        return Collections.singletonMap("valid", LocalTime.now().isBefore(game.getClosingHours()));
    }

    private String generateTicket(LocalDateTime date, Long id) {
        return String.format("TKT-%s-%d",
                date.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss-SSS")),
                id);
    }

    private GameTicketResponse fromEntity(GameTicket entity) {
        return new GameTicketResponse(entity.getId(),
                entity.getPrice().getPrice(),
                entity.getSaleDate(),
                entity.getTicketNumber(),
                entity.getValidity(),
                entity.isRedeemed());
    }
}
