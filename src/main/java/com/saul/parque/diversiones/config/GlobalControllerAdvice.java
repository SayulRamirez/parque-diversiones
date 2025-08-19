package com.saul.parque.diversiones.config;

import com.saul.parque.diversiones.exception.ApiError;
import com.saul.parque.diversiones.exception.Errors;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidation(MethodArgumentNotValidException e, HttpServletRequest request) {

        Map<String, Object> response = new LinkedHashMap<>();

        e.getBindingResult().getAllErrors().forEach((error) -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            response.put(field, message);
        });

        response.put("path", request.getRequestURI());
        response.put("timestamp", ZonedDateTime.now(ZoneId.of("America/Chicago")));
        response.put("status", HttpStatus.BAD_REQUEST);
        return response;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiError handleEntityNotFoundException(EntityNotFoundException e, HttpServletRequest request) {

        return new ApiError(
                HttpStatus.NOT_FOUND.value(),
                Errors.ENTITY_NOT_FOUND.getValue(),
                e.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now());
    }
}
