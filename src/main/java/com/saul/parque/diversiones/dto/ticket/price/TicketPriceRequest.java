package com.saul.parque.diversiones.dto.ticket.price;

import com.saul.parque.diversiones.ticket.price.TicketType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TicketPriceRequest(

        @NotNull(message = "el campo no debe de estar vacío o nulo")
        TicketType type,

        @NotNull(message = "el campo no debe de estar vacío")
        @Positive(message = "el campo debe de ser mayor a cero")
        double price
) {
}
