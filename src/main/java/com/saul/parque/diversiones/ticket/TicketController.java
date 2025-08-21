package com.saul.parque.diversiones.ticket;

import com.saul.parque.diversiones.dto.ticket.SaleTicketResponse;
import com.saul.parque.diversiones.dto.ticket.TicketResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TicketController {

    private static final Logger log = LoggerFactory.getLogger(TicketController.class);

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<List<TicketResponse>> add(@RequestBody Map<Long, Integer> request) {
        log.info("add {} tickets", request.size());
        return ResponseEntity.ok(ticketService.add(request));
    }

    @GetMapping("/between/{start}/{end}")
    public ResponseEntity<SaleTicketResponse> getSaleBetween(@PathVariable LocalDate start, @PathVariable LocalDate end) {
        log.info("Get sale between {} and {}", start, end);
        return ResponseEntity.ok(ticketService.getSaleBetween(start, end));
    }
}
