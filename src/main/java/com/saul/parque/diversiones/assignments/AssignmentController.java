package com.saul.parque.diversiones.assignments;

import com.saul.parque.diversiones.dto.assign.AssignmentRequest;
import com.saul.parque.diversiones.dto.assign.AssignmentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignment")
@RequiredArgsConstructor
public class AssignmentController {

    private static final Logger log = LoggerFactory.getLogger(AssignmentController.class);

    private final AssignmentService assignmentService;

    @PostMapping
    public ResponseEntity<AssignmentResponse> add(@Valid @RequestBody AssignmentRequest request) {
        log.info("Adding assignment");
        return new ResponseEntity<>(assignmentService.add(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssignmentResponse> getById(@PathVariable Long id) {
        log.info("Get assignment by id: {}", id);
        return ResponseEntity.ok(assignmentService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<AssignmentResponse>> getAll() {
        log.info("Get all assignments");
        return ResponseEntity.ok(assignmentService.getAll());
    }
}
