package com.saul.parque.diversiones.game.ticket;

import com.saul.parque.diversiones.dto.game.ticket.GameTicketResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/game-ticket")
@RequiredArgsConstructor
public class GameTicketController {

    private static final Logger log = LoggerFactory.getLogger(GameTicketController.class);

    private final GameTicketService gameTicketService;

    @PostMapping
    public ResponseEntity<List<GameTicketResponse>> add(@Valid @NotNull(message = "{field.not.null}") @RequestBody Map<Long, Integer> request) {
        log.info("Adding game tickets");
        return ResponseEntity.ok(gameTicketService.add(request));
    }

    @GetMapping
    public ResponseEntity<List<GameTicketResponse>> getAll() {
        return ResponseEntity.ok(gameTicketService.getAll());
    }

    @GetMapping("/valid-schedule/{idGame}")
    public ResponseEntity<Map<String, Boolean>> validSchedule(@PathVariable Long idGame) {
        log.info("Validating sale date of the game with id {}", idGame);
        return ResponseEntity.ok(gameTicketService.validSchedule(idGame));
    }

    @PatchMapping("/{numberTicket}")
    public ResponseEntity<Void> redeem(@PathVariable String numberTicket) {
        log.info("Redeeming ticket {}", numberTicket);
        gameTicketService.redeem(numberTicket);
        return ResponseEntity.ok().build();
    }
}

