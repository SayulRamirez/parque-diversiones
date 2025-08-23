package com.saul.parque.diversiones.dto.assign;

import java.time.LocalDateTime;

public record AssignmentResponse (
        long id,

        long idEmployee,

        long idGame,

        boolean active,

        LocalDateTime assignmentDate
) {
}
