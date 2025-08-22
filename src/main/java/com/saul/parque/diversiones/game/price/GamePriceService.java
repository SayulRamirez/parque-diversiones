package com.saul.parque.diversiones.game.price;

import java.util.List;

public interface GamePriceService<T, R> {

    List<?> getAllPricesCurrents();

    List<?> getAll();

    R addPrice(T request);
}
