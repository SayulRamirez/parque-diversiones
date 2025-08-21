package com.saul.parque.diversiones.ticket.price;

import com.saul.parque.diversiones.dto.ticket.price.TicketPriceRequest;
import com.saul.parque.diversiones.dto.ticket.price.TicketPriceResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/price-ticket")
@RequiredArgsConstructor
public class TicketPriceController {

    private static final Logger log = LoggerFactory.getLogger(TicketPriceController.class);

    private final TicketPriceService ticketPriceService;

    @PostMapping
    public ResponseEntity<TicketPriceResponse> add(@Valid @RequestBody TicketPriceRequest request) {
        log.info("Add ticket price");
        return new ResponseEntity<>(ticketPriceService.addPrice(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TicketPriceResponse>> getAll() {
        log.info("Get all ticket price");
        return ResponseEntity.ok(ticketPriceService.getAll());
    }

    @GetMapping("/current")
    public ResponseEntity<List<TicketPriceResponse>> getAllIsCurrent() {
        log.info("Get all price current");
        return ResponseEntity.ok(ticketPriceService.getAllIsCurrent());
    }
}
