package com.saul.parque.diversiones.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class GeneralPrice {

    @Column(nullable = false)
    private Double price;

    @Column(name = "date_update", nullable = false)
    private LocalDateTime dateUpdate;

    @Column(name = "is_current", nullable = false)
    private boolean isCurrent;

    public GeneralPrice(){}

    protected GeneralPrice(Double price, LocalDateTime dateUpdate, boolean isCurrent) {
        this.price = price;
        this.dateUpdate = dateUpdate;
        this.isCurrent = isCurrent;
    }
}
