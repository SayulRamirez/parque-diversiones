package com.saul.parque.diversiones.assignments;

import com.saul.parque.diversiones.game.Game;
import com.saul.parque.diversiones.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "assignments")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User employee;

    @ManyToOne
    private Game game;

    private boolean active;

    @Column(name = "assignment_date", nullable = false)
    private LocalDateTime assignmentDate;
}
