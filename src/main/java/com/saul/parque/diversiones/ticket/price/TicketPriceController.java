package com.saul.parque.diversiones.ticket.price;

import com.saul.parque.diversiones.dto.ticket.price.TicketPriceRequest;
import com.saul.parque.diversiones.dto.ticket.price.TicketPriceResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket/price")
@RequiredArgsConstructor
public class TicketPriceController {

    private final TicketPriceService ticketPriceService;

    @PostMapping
    public ResponseEntity<TicketPriceResponse> add(@Valid @RequestBody TicketPriceRequest request) {
        return new ResponseEntity<>(ticketPriceService.addPrice(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TicketPriceResponse>> getAll() {
        return ResponseEntity.ok(ticketPriceService.getAll());
    }

    @GetMapping("/current")
    public ResponseEntity<List<TicketPriceResponse>> getAllIsCurrent() {
        return ResponseEntity.ok(ticketPriceService.getAllIsCurrent());
    }
}
