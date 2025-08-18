package com.saul.parque.diversiones.dto.ticket;

import com.saul.parque.diversiones.ticket.price.TicketType;

import java.time.LocalDateTime;

public record TicketResponse(Long id, double price, TicketType type, LocalDateTime saleDate, String ticketNumber) {
}
