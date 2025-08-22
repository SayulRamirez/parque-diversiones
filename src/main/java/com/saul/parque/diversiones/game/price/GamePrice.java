package com.saul.parque.diversiones.game.price;

import com.saul.parque.diversiones.base.GeneralPrice;
import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "game_price")
public class GamePrice extends GeneralPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer game;
}
