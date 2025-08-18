package com.saul.parque.diversiones.dto.user;

import com.saul.parque.diversiones.user.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequest(

        @Schema(example = "C0ntrasen4$egura")
        @NotBlank(message = "el campo no debe de estar vacío o nulo")
        @Size(message = "el campo debe de tener minímo 8 caracteres y maximo 20", min = 8, max = 16)
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
                message = "La contraseña debe tener al menos 8 caracteres, incluyendo una mayúscula, una minúscula, un número y un símbolo especial (@$!%*?&).")
        String password,

        @NotNull(message = "el campo no debe de ser nulo")
        Role role,

        @Schema(example = "José Andres")
        @NotBlank(message = "el campo no debe de estar vacío o nulo")
        @Size(message = "el campo debe de tener minímo 3 y maximo 40 caracteres", min = 3, max = 40)
        @Pattern(
                regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ]+( [A-Za-zÁÉÍÓÚáéíóúÑñ]+)*$",
                message = "La contraseña debe tener al menos 8 caracteres, incluyendo una mayúscula, una minúscula, un número y un símbolo especial (@$!%*?&).")
        String firstName,

        @Schema(example = "Pérez Zandoval")
        @NotBlank(message = "el campo no debe de estar vacío o nulo")
        @Size(message = "el campo debe de tener minímo 3 caracteres y maxiomo 50 caracteres", min = 3, max = 50)
        @Pattern(
                regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ]+( [A-Za-zÁÉÍÓÚáéíóúÑñ]+)*$",
                message = "La contraseña debe tener al menos 8 caracteres, incluyendo una mayúscula, una minúscula, un número y un símbolo especial (@$!%*?&).")
        String lastName
        ){
}
