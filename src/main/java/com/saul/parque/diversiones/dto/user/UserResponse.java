package com.saul.parque.diversiones.dto.user;

import com.saul.parque.diversiones.user.Role;

import java.time.LocalDateTime;

public record UserResponse
        (Long id, String username, Role role, boolean isEnable, String firstName, String lastName, LocalDateTime dateRegister)
{}
