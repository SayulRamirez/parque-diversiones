package com.saul.parque.diversiones.dto.game.price;

import java.time.LocalDateTime;

public record GamePriceResponse(

        long id,

        double price,

        LocalDateTime dateUpdate,

        long idGame,

        boolean current
) {
}
