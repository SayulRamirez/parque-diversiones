package com.saul.parque.diversiones.assignments;

import com.saul.parque.diversiones.dto.assign.AssignmentRequest;
import com.saul.parque.diversiones.dto.assign.AssignmentResponse;

import java.util.List;

public interface AssignmentService {

    AssignmentResponse add(AssignmentRequest request);

    AssignmentResponse getById(Long id);

    List<AssignmentResponse> getAll();
}
