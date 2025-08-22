package com.saul.parque.diversiones.game;

import com.saul.parque.diversiones.dto.game.GameRequest;
import com.saul.parque.diversiones.dto.game.GameResponse;
import com.saul.parque.diversiones.exception.DateInvalidException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private static final Logger log = LoggerFactory.getLogger(GameServiceImpl.class);

    private final GameRepository gameRepository;

    @Override
    public GameResponse getById(Long id) {

        Game game = getGame(id);

        log.info("Getting game {} {}", game.getId(), game.getName());

        return fromEntity(game);
    }

    @Override
    public List<GameResponse> getAll() {
        return gameRepository.findAll().stream().map(this::fromEntity).toList();
    }

    @Transactional
    @Override
    public GameResponse add(GameRequest request) {

        isTimeOpeningValid(request.openingHours(), request.closingHours());

        Game game = gameRepository.save(Game.builder()
                .name(request.name())
                .capacity(request.capacity())
                .openingHours(request.openingHours())
                .closingHours(request.closingHours())
                .active(request.active())
                .build());

        log.info("New game {} {}", game.getId(), game.getName());

        return fromEntity(game);
    }

    @Transactional
    @Override
    public GameResponse update(GameRequest request, long id) {

        isTimeOpeningValid(request.openingHours(), request.closingHours());

        Game game = getGame(id);

        game.setActive(request.active());
        game.setName(request.name());
        game.setCapacity(request.capacity());
        game.setClosingHours(request.closingHours());
        game.setOpeningHours(request.openingHours());

        log.info("Update game {}", game.getId());

        return fromEntity(gameRepository.save(game));
    }

    @Transactional
    @Override
    public void activate(Long id, boolean active) {
        Game game = getGame(id);

        log.info("Changed active game {} to {} with id: {}", game.isActive(), active, game.getId());
        game.setActive(active);

        gameRepository.save(game);
    }

    private Game getGame(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("juego no encontrado con id: " + id));
    }

    private static void isTimeOpeningValid(LocalTime openingHours, LocalTime closingHours) {
        if (closingHours.isBefore(openingHours))
            throw new DateInvalidException("la hora de cierre no debe de ser menor a la hora de apertura");
    }

    private GameResponse fromEntity(Game entity) {
        return new GameResponse(
                entity.getId(),
                entity.getName(),
                entity.getCapacity(),
                entity.getOpeningHours(),
                entity.getClosingHours(),
                entity.isActive()
        );
    }
}