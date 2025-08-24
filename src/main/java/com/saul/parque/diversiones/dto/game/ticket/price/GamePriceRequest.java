package com.saul.parque.diversiones.dto.game.ticket.price;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record GamePriceRequest(

        @NotNull(message = "{field.not.null}")
        @Positive(message = "{field.positive}")
        double price,

        @NotNull(message = "{field.not.null}")
        @Positive(message = "{field.positive}")
        long idGame
) {
}
