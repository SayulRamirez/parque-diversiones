package com.saul.parque.diversiones.dto.ticket.price;

import com.saul.parque.diversiones.ticket.price.TicketType;

import java.time.LocalDateTime;

public record TicketPriceResponse (
        long id,

        TicketType type,

        double price,

        LocalDateTime dateUpdate,

        boolean isCurrent
) {
}
