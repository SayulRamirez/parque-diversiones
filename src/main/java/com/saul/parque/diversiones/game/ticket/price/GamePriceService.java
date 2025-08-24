package com.saul.parque.diversiones.game.ticket.price;

import com.saul.parque.diversiones.dto.game.ticket.price.GamePriceRequest;
import com.saul.parque.diversiones.dto.game.ticket.price.GamePriceResponse;

import java.util.List;

public interface GamePriceService {

    GamePriceResponse add(GamePriceRequest request);

    List<GamePriceResponse> getAll();

    List<GamePriceResponse> getAllPricesCurrents();
}
