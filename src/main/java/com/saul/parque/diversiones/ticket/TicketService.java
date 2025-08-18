package com.saul.parque.diversiones.ticket;

import com.saul.parque.diversiones.dto.ticket.SaleTicketResponse;
import com.saul.parque.diversiones.dto.ticket.TicketResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface TicketService {

    List<TicketResponse> add(Map<Long, Integer> request);

    SaleTicketResponse getSaleBetween(LocalDate start, LocalDate end);
}
