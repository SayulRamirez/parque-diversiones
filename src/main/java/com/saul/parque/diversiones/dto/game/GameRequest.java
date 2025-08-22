package com.saul.parque.diversiones.dto.game;

import jakarta.validation.constraints.*;

import java.time.LocalTime;

public record GameRequest(

        @NotBlank(message = "{field.not.blank}")
        @Size(message = "{field.size}", min = 3, max = 100)
        String name,

        @NotNull(message = "{field.not.null}")
        @Positive(message = "{field.positive}")
        Integer capacity,

        @NotNull(message = "{field.not.null}")
        LocalTime openingHours,

        @NotNull(message = "{field.not.null}")
        LocalTime closingHours,

        boolean active
) {
}
