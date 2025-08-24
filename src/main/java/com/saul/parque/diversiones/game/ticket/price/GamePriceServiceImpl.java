package com.saul.parque.diversiones.game.ticket.price;

import com.saul.parque.diversiones.dto.game.price.GamePriceRequest;
import com.saul.parque.diversiones.dto.game.price.GamePriceResponse;
import com.saul.parque.diversiones.game.Game;
import com.saul.parque.diversiones.game.GameRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GamePriceServiceImpl implements GamePriceService {

    private static final Logger log = LoggerFactory.getLogger(GamePriceServiceImpl.class);

    public static final double MIN_PRICE = 20.5;

    private final GamePriceRepository gamePriceRepository;

    private final GameRepository gameRepository;

    @Transactional
    @Override
    public GamePriceResponse add(GamePriceRequest request) {

        if (request.price() < MIN_PRICE)
            throw new IllegalArgumentException("el precio no puede ser menor a: " + MIN_PRICE);

        Game game = gameRepository.findById(request.idGame())
                .orElseThrow(() -> new EntityNotFoundException("no se encontro el juego con id: " + request.idGame()));

        gamePriceRepository.findByIsCurrentIsTrueAndGameId(request.idGame())
                .ifPresent(gamePriceCurrent -> {
                    gamePriceCurrent.setCurrent(false);
                    gamePriceRepository.save(gamePriceCurrent);
                    log.info("The price of game {} was deactivated", gamePriceCurrent.getId());
                });

        GamePrice gamePrice = gamePriceRepository.save(GamePrice.builder()
                .game(game)
                .price(request.price())
                .dateUpdate(LocalDateTime.now())
                .current(true)
                .build());

        log.info("new game price {} in game with id: {}", gamePrice.getPrice(), gamePrice.getId());

        return fromEntity(gamePrice);
    }

    @Override
    public List<GamePriceResponse> getAll() {
        return gamePriceRepository.findAll().stream().map(this::fromEntity).toList();
    }

    @Override
    public List<GamePriceResponse> getAllPricesCurrents() {
        return gamePriceRepository.findAllByIsCurrentIsTrueOrderByDateUpdateDesc().stream()
                .map(this::fromEntity).toList();
    }

    private GamePriceResponse fromEntity(GamePrice entity) {
        return new GamePriceResponse(entity.getId(),
                entity.getPrice(),
                entity.getDateUpdate(),
                entity.getGame().getId(),
                entity.isCurrent());
    }
}
