package com.saul.parque.diversiones.game;

import com.saul.parque.diversiones.dto.game.GameRequest;
import com.saul.parque.diversiones.dto.game.GameResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {


    private static final Logger log = LoggerFactory.getLogger(GameController.class);

    private final GameService gameService;

    @GetMapping("/{id}")
    public ResponseEntity<GameResponse> getById(@PathVariable Long id) {
        log.info("Get game by id: {}", id);
        return ResponseEntity.ok(gameService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<GameResponse>> getAll() {
        log.info("Get all game");
        return ResponseEntity.ok(gameService.getAll());
    }

    @PostMapping
    public ResponseEntity<GameResponse> add(@Valid @RequestBody GameRequest request) {
        log.info("Add new game {}", request.name());

        GameResponse response = gameService.add(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameResponse> update(@Valid @RequestBody GameRequest request, @PathVariable Long id) {
        log.info("Update game {}", id);

        GameResponse response = gameService.update(request, id);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/active/{active}")
    public ResponseEntity<Void> activate(@PathVariable Long id, @PathVariable boolean active) {
        log.info("Changed activate game {}", id);
        gameService.activate(id, active);
        return ResponseEntity.ok().build();
    }
}
