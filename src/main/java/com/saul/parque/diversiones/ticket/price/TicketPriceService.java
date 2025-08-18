package com.saul.parque.diversiones.ticket.price;

import com.saul.parque.diversiones.dto.ticket.price.TicketPriceRequest;
import com.saul.parque.diversiones.dto.ticket.price.TicketPriceResponse;

import java.util.List;

public interface TicketPriceService {

    TicketPriceResponse addPrice(TicketPriceRequest request);

    List<TicketPriceResponse> getAll();

    List<TicketPriceResponse> getAllIsCurrent();
}
