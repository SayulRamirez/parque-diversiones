package com.saul.parque.diversiones.assignments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    Optional<Assignment> findByEmployeeIdAndGameIdAndActiveIsTrue(long idEmployee, long idGame);
}
