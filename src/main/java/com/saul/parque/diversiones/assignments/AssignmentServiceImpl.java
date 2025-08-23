package com.saul.parque.diversiones.assignments;

import com.saul.parque.diversiones.dto.assign.AssignmentRequest;
import com.saul.parque.diversiones.dto.assign.AssignmentResponse;

import com.saul.parque.diversiones.game.Game;
import com.saul.parque.diversiones.game.GameRepository;
import com.saul.parque.diversiones.user.User;
import com.saul.parque.diversiones.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {

    private static final Logger log = LoggerFactory.getLogger(AssignmentServiceImpl.class);

    private final AssignmentRepository assignmentRepository;

    private final GameRepository gameRepository;

    private final UserRepository userRepository;

    @Override
    public AssignmentResponse add(AssignmentRequest request) {

        Optional<Assignment> find = assignmentRepository.findByEmployeeIdAndGameIdAndActiveIsTrue(request.idEmployee(), request.idGame());

        LocalDateTime assignmentDate = LocalDateTime.now();

        return find.map(assignment -> {

            Game game = gameRepository.findByIdAndActiveIsTrue(request.idGame())
                    .orElseThrow(() -> new EntityNotFoundException("no sé encontro el juego a asignar con id: " + request.idGame() + " o está desactivado"));

            assignment.setActive(false);

            assignmentRepository.save(assignment);

            Assignment save = assignmentRepository.save(Assignment.builder()
                    .employee(assignment.getEmployee())
                    .game(game)
                    .active(request.active())
                    .assignmentDate(assignmentDate)
                    .build());

            log.info("The assignment of employee {} was updated with game {}", request.idEmployee(), request.idGame());

            return fromEntity(save);
        }).orElseGet(() -> {
            Game game = gameRepository.findByIdAndActiveIsTrue(request.idGame())
                    .orElseThrow(() -> new EntityNotFoundException("no sé encontro el juego a asignar con id: " + request.idGame() + " o está desactivado"));

            User user = userRepository.findByIdAndIsEnabledIsTrue(request.idEmployee())
                    .orElseThrow(() -> new EntityNotFoundException("no sé encontro el empleado a asignar con id: " + request.idEmployee() + " o está desactivado"));

            Assignment save = assignmentRepository.save(Assignment.builder()
                    .employee(user)
                    .game(game)
                    .active(request.active())
                    .assignmentDate(assignmentDate)
                    .build());

            log.info("A new assignment was added for employee {} with game {}", request.idEmployee(), request.idGame());

            return fromEntity(save);
        });
    }

    @Override
    public AssignmentResponse getById(Long id) {
        return fromEntity(findById(id));
    }

    @Override
    public List<AssignmentResponse> getAll() {
        return assignmentRepository.findAll().stream().map(this::fromEntity).toList();
    }

    private Assignment findById(Long id) {
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No sé encontró la asignación con id: " + id));
    }

    private AssignmentResponse fromEntity(Assignment entity) {
        return new AssignmentResponse(
                entity.getId(),
                entity.getEmployee().getId(),
                entity.getGame().getId(),
                entity.isActive(),
                entity.getAssignmentDate()
        );
    }
}
