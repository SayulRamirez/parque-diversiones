package com.saul.parque.diversiones.game;

import com.saul.parque.diversiones.dto.game.GameRequest;
import com.saul.parque.diversiones.dto.game.GameResponse;

import java.util.List;

public interface GameService {

    GameResponse getById(Long id);

    List<GameResponse> getAll();

    GameResponse add(GameRequest request);

    GameResponse update(GameRequest request, long id);

    void activate(Long id, boolean active);

}
