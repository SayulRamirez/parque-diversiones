package com.saul.parque.diversiones.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
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

        log.warn("Error validation in {}", request.getRequestURI());

        return response;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiError handleEntityNotFoundException(EntityNotFoundException e, HttpServletRequest request) {

        log.warn("{} in {}", e.getMessage(), request.getRequestURI());

        return new ApiError(
                HttpStatus.NOT_FOUND.value(),
                Errors.ENTITY_NOT_FOUND.getValue(),
                e.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiError handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
        log.warn("{} in {}", e.getMessage(), request.getRequestURI());

        return new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                Errors.ILLEGAL_ARGUMENT_EXCEPTION.getValue(),
                e.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now());
    }

    @ExceptionHandler(DateTimeParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiError handleDateTimeParseException(DateTimeParseException e, HttpServletRequest request) {
        log.warn("{} in {}", e.getMessage(), request.getRequestURI());

        return new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                Errors.DATE_INVALID_EXCEPTION.getValue(),
                "formato esperado HH:mm",
                request.getRequestURI(),
                LocalDateTime.now());
    }

    @ExceptionHandler(DateInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiError handleDateInvalidException(DateInvalidException e, HttpServletRequest request) {
        log.warn("{} in {}", e.getMessage(), request.getRequestURI());

        return new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                Errors.DATE_INVALID_EXCEPTION.getValue(),
                e.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now());
    }
}
