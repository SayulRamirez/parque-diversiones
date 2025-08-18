package com.saul.parque.diversiones.ticket;

import com.saul.parque.diversiones.base.GeneralTicket;
import com.saul.parque.diversiones.ticket.price.TicketPrice;
import com.saul.parque.diversiones.util.Builder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "ticket")
public class Ticket extends GeneralTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private TicketPrice price;

    public Ticket() {
        super();
    }

    private Ticket(Long id, TicketPrice price, LocalDateTime saleDate, String ticketNumber) {
        super(saleDate, ticketNumber);
        this.id = id;
        this.price = price;
    }

    public static TicketBuilder builder() {
        return new TicketBuilder();
    }

    public static class TicketBuilder implements Builder<Ticket> {

        private Long id;

        private TicketPrice price;

        private LocalDateTime saleDate;

        private String ticketNumber;

        public TicketBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public TicketBuilder price(TicketPrice price) {
            this.price = price;
            return this;
        }

        public TicketBuilder saleDate(LocalDateTime saleDate) {
            this.saleDate = saleDate;
            return this;
        }

        public TicketBuilder ticketNumber(String ticketNumber) {
            this.ticketNumber = ticketNumber;
            return this;
        }

        @Override
        public Ticket build() {
            return new Ticket(id, price, saleDate, ticketNumber);
        }
    }
}
