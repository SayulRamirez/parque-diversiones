package com.saul.parque.diversiones.game.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameTicketRepository extends JpaRepository<GameTicket, Long> {

    Optional<GameTicket> findByTicketNumber(String ticketNumber);
}
