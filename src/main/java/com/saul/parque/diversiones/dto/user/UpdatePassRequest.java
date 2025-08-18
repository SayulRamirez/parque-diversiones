package com.saul.parque.diversiones.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public record UpdatePassRequest(

        @NotNull(message = "el campo no debe de ser nulo")
        @Positive(message = "el campo debe de ser mayor a cero")
        Long id,

        @Schema(example = "C0ntrasen4$egura")
        @NotBlank(message = "el campo no debe de estar vacío o nulo")
        @Size(message = "el campo debe de tener minímo 8 caracteres y maximo 20", min = 8, max = 16)
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
                message = "La contraseña debe tener al menos 8 caracteres, incluyendo una mayúscula, una minúscula, un número y un símbolo especial (@$!%*?&).")
        String password
){}
