package com.saul.parque.diversiones.dto.assign;

import jakarta.validation.constraints.Positive;

public record AssignmentRequest (

        @Positive(message = "{field.positive}")
        long idEmployee,

        @Positive(message = "{field.positive}")
        long idGame,

        boolean active
) {
}
