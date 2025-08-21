package com.saul.parque.diversiones.dto.user;

import com.saul.parque.diversiones.util.RegexPatterns;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public record UpdatePassRequest(

        @NotNull(message = "{field.not.null}")
        @Positive(message = "{field.positive}")
        Long id,

        @Schema(example = "C0ntrasen4$egura")
        @NotBlank(message = "{field.not.blank}")
        @Size(message = "{field.size}", min = 8, max = 16)
        @Pattern(regexp = RegexPatterns.PASSWORD_VALID, message = "{password.pattern}")
        String password
){}
