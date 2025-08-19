package com.saul.parque.diversiones.dto.user;

import com.saul.parque.diversiones.user.Role;
import com.saul.parque.diversiones.util.RegexPatteners;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequest(

        @Schema(example = "C0ntrasen4$egura")
        @NotBlank(message = "{field.not.blank}")
        @Size(min = 8, max = 16, message = "{field.size}")
        @Pattern(regexp = RegexPatteners.PASSWORD_VALID, message = "{password.pattern}")
        String password,

        @NotNull(message = "{field.not.null}")
        Role role,

        @Schema(example = "José Andres")
        @NotBlank(message = "{field.not.blank}")
        @Size(message = "{field.size}", min = 3, max = 40)
        @Pattern(regexp = RegexPatteners.NAMES_VALID, message = "{name.pattern}")
        String firstName,

        @Schema(example = "Pérez Zandoval")
        @NotBlank(message = "{field.not.blank}")
        @Size(message = "{field.size}", min = 3, max = 50)
        @Pattern(regexp = RegexPatteners.NAMES_VALID, message = "{name.pattern}")
        String lastName
        ){
}
