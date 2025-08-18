package com.saul.parque.diversiones.ticket.price;

import com.saul.parque.diversiones.base.GeneralPrice;
import com.saul.parque.diversiones.util.Builder;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "ticket_price")
public class TicketPrice extends GeneralPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TicketType type;

    public TicketPrice(){
        super();
    }

    private TicketPrice(Long id, TicketType type, Double price, LocalDateTime dateUpdate, boolean isCurrent) {
        super(price, dateUpdate, isCurrent);
        this.id = id;
        this.type = type;
    }

    public static TicketPriceBuilder builder() {
        return new TicketPriceBuilder();
    }

    public static class TicketPriceBuilder implements Builder<TicketPrice> {
        private Long id;

        private TicketType type;

        private Double price;

        private LocalDateTime dateUpdate;

        private boolean isCurrent;

        public TicketPriceBuilder() {}

        public TicketPriceBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public TicketPriceBuilder type(TicketType type) {
            this.type = type;
            return this;
        }

        public TicketPriceBuilder price(Double price) {
            this.price = price;
            return this;
        }

        public TicketPriceBuilder dateUpdate(LocalDateTime dateUpdate) {
            this.dateUpdate = dateUpdate;
            return this;
        }

        public TicketPriceBuilder current(boolean current) {
            isCurrent = current;
            return this;
        }

        @Override
        public TicketPrice build() {
            LocalDateTime now = LocalDateTime.now();

            if (this.dateUpdate.isBefore(now)) this.dateUpdate = now;

            return new TicketPrice(id, type, price, dateUpdate, isCurrent);
        }
    }
}
