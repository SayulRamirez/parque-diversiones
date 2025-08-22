package com.saul.parque.diversiones.game.price;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamePriceRepository extends JpaRepository<GamePrice, Long> {
    GamePrice findByIsCurrentTrueAndGame(int game);
}
