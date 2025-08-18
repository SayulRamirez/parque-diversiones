package com.saul.parque.diversiones.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class GeneralTicket {

    @Column(name = "sale_date", nullable = false)
    private LocalDateTime saleDate;

    @Column(name = "ticket_number", unique = true, nullable = false)
    private String ticketNumber;

    public GeneralTicket () {}

    protected GeneralTicket(LocalDateTime saleDate, String ticketNumber) {
        this.saleDate = saleDate;
        this.ticketNumber = ticketNumber;
    }
}
