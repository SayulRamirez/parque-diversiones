package com.saul.parque.diversiones.dto.ticket;

import com.saul.parque.diversiones.ticket.price.TicketType;

import java.util.List;
import java.util.Map;

public record SaleTicketResponse(

        double amount,

        Map<TicketType, DetailsType> detailsType,

        List<TicketResponse> details
) {
}
