package com.saul.parque.diversiones.dto.game;

import java.time.LocalTime;

public record GameResponse(
        long id,

        String name,

        int capacity,

        LocalTime openingHours,

        LocalTime closingHours,

        boolean active
) {
}
