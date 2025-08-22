package com.saul.parque.diversiones.game;

import java.util.List;

public interface GamePriceService<T, R> {

    List<?> getAllPricesCurrents();

    List<?> getAll();

    R addPrice(T request);
}
