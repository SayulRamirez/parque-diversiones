package com.saul.parque.diversiones.dto.ticket.price;

import com.saul.parque.diversiones.ticket.price.TicketType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TicketPriceRequest(

        @NotNull(message = "{field.not.null}")
        TicketType type,

        @NotNull(message = "{field.not.null}")
        @Positive(message = "{field.positive}")
        double price
) {
}
