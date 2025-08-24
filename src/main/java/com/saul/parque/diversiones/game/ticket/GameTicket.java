package com.saul.parque.diversiones.game.ticket;

import com.saul.parque.diversiones.base.GeneralTicket;
import com.saul.parque.diversiones.dto.game.ticket.GameTicketResponse;
import com.saul.parque.diversiones.game.ticket.price.GamePrice;
import com.saul.parque.diversiones.util.Builder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "game_tickets")
public class GameTicket extends GeneralTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private GamePrice price;

    private LocalDateTime validity;

    private boolean redeemed;

    public GameTicket() {
        super();
    }

    private GameTicket(Long id, GamePrice price, LocalDateTime validity, boolean redeemed, LocalDateTime saleDate, String ticketNumber) {
        super(saleDate, ticketNumber);
        this.id = id;
        this.price = price;
        this.validity = validity;
        this.redeemed = redeemed;
    }

    public static GameTicketBuilder builder() {
        return new GameTicketBuilder();
    }

    public static class GameTicketBuilder implements Builder<GameTicket> {
        private LocalDateTime saleDate;
        private String ticketNumber;
        private Long id;
        private GamePrice price;
        private LocalDateTime validity;
        private boolean redeemed;

        public GameTicketBuilder() {}

        public GameTicketBuilder saleDate(LocalDateTime saleDate) {
            this.saleDate = saleDate;
            return this;
        }

        public GameTicketBuilder ticketNumber(String ticketNumber) {
            this.ticketNumber = ticketNumber;
            return this;
        }

        public GameTicketBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public GameTicketBuilder price(GamePrice price) {
            this.price = price;
            return this;
        }

        public GameTicketBuilder validity(LocalDateTime validity) {
            this.validity = validity;
            return this;
        }

        public GameTicketBuilder redeemed(boolean redeemed) {
            this.redeemed = redeemed;
            return this;
        }

        @Override
        public GameTicket build() {
            return new GameTicket(this.id, this.price, this.validity, this.redeemed, this.saleDate, this.ticketNumber);
        }
    }
}
