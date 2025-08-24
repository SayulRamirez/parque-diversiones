package com.saul.parque.diversiones.game.ticket.price;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GamePriceRepository extends JpaRepository<GamePrice, Long> {

    Optional<GamePrice> findByIsCurrentIsTrueAndGameId(long game);

    List<GamePrice> findAllByIsCurrentIsTrueOrderByDateUpdateDesc();
}
