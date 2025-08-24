package com.saul.parque.diversiones.game.ticket;

import com.saul.parque.diversiones.dto.game.ticket.GameTicketResponse;

import java.util.List;
import java.util.Map;

public interface GameTicketService {

    List<GameTicketResponse> add(Map<Long, Integer> request);

    List<GameTicketResponse> getAll();

    void redeem(String numberTicket);

    Map<String, Boolean> validSchedule(Long idGame);
}
