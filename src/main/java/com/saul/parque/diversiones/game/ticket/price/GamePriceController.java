package com.saul.parque.diversiones.game.ticket.price;

import com.saul.parque.diversiones.dto.game.ticket.price.GamePriceRequest;
import com.saul.parque.diversiones.dto.game.ticket.price.GamePriceResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game-price")
@RequiredArgsConstructor
public class GamePriceController {

    private static final Logger log = LoggerFactory.getLogger(GamePriceController.class);

    private final GamePriceService gamePriceService;

    @PostMapping
    public ResponseEntity<GamePriceResponse> add(@Valid @RequestBody GamePriceRequest request) {
        log.info("Add new game price");
        return ResponseEntity.ok(gamePriceService.add(request));
    }

    @GetMapping
    public ResponseEntity<List<GamePriceResponse>> getAll() {
        return ResponseEntity.ok(gamePriceService.getAll());
    }

    @GetMapping("/currents")
    public ResponseEntity<List<GamePriceResponse>> getAllPricesCurrents() {
        return ResponseEntity.ok(gamePriceService.getAllPricesCurrents());
    }
}
