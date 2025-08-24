package com.saul.parque.diversiones.game.ticket.price;

import com.saul.parque.diversiones.base.GeneralPrice;
import com.saul.parque.diversiones.game.Game;
import com.saul.parque.diversiones.util.Builder;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "game_price")
public class GamePrice extends GeneralPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Game game;

    public GamePrice() {
        super();
    }

    private GamePrice(Long id, Game game, Double price, LocalDateTime dateUpdate, boolean isCurrent) {
        super(price, dateUpdate, isCurrent);
        this.id = id;
        this.game = game;
    }

    public static GamePriceBuilder builder() {
        return new GamePriceBuilder();
    }

    public static class GamePriceBuilder implements Builder<GamePrice> {
        private Long id;

        private Game game;

        private Double price;

        private LocalDateTime dateUpdate;

        private boolean isCurrent;

        public GamePriceBuilder() {}

        public GamePriceBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public GamePriceBuilder game(Game game) {
            this.game = game;
            return this;
        }

        public GamePriceBuilder price(Double price) {
            this.price = price;
            return this;
        }

        public GamePriceBuilder dateUpdate(LocalDateTime dateUpdate) {
            this.dateUpdate = dateUpdate;
            return this;
        }

        public GamePriceBuilder current(boolean current) {
            isCurrent = current;
            return this;
        }

        @Override
        public GamePrice build() {
            return new GamePrice(this.id, this.game, this.price, this.dateUpdate, this.isCurrent);
        }
    }
}