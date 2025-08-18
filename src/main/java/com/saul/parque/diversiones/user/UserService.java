package com.saul.parque.diversiones.user;

import com.saul.parque.diversiones.dto.user.UpdatePassRequest;
import com.saul.parque.diversiones.dto.user.UserRequest;
import com.saul.parque.diversiones.dto.user.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse create(UserRequest request);

    List<Role> getRoles();

    List<UserResponse> getAll();

    List<UserResponse> getAllEnabled();

    UserResponse updatePassword(UpdatePassRequest request);

    void enbledUser(Long id, boolean enabled);
}
