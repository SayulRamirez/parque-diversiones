package com.saul.parque.diversiones.exception;

import lombok.Getter;

@Getter
public enum Errors {

    ENTITY_NOT_FOUND("entidad no encontrada"),

    ILLEGAL_ARGUMENT_EXCEPTION("argumentos malformados");

    private final String value;

    Errors(String value) {
        this.value = value;
    }
}
