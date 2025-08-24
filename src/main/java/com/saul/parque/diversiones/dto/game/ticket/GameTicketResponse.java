package com.saul.parque.diversiones.dto.game.ticket;

import java.time.LocalDateTime;

public record GameTicketResponse (
        long id,

        double price,

        LocalDateTime saleDate,

        String ticketNumber,

        LocalDateTime validity,

        boolean redeemed
){
}
