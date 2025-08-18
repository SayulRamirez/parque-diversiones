package com.saul.parque.diversiones.ticket.price;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketPriceRepository extends JpaRepository<TicketPrice, Long> {

    Optional<TicketPrice> findByIsCurrentIsTrueAndType(TicketType type);

    List<TicketPrice> findAllByIsCurrentIsTrue();
}
