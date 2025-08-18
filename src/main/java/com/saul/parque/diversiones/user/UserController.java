package com.saul.parque.diversiones.user;

import com.saul.parque.diversiones.dto.user.UpdatePassRequest;
import com.saul.parque.diversiones.dto.user.UserRequest;
import com.saul.parque.diversiones.dto.user.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest request) {
        return new ResponseEntity<>(userService.create(request), HttpStatus.CREATED);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles() {
        return ResponseEntity.ok(userService.getRoles());
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/enabled")
    public ResponseEntity<List<UserResponse>> getAllEnabled() {
        return ResponseEntity.ok(userService.getAllEnabled());
    }

    @PatchMapping
    public ResponseEntity<UserResponse> updatePassword(@Valid @RequestBody UpdatePassRequest request) {
        return ResponseEntity.ok(userService.updatePassword(request));
    }

    @PatchMapping("/{id}/enabled/{enabled}")
    public ResponseEntity<Void> enabledUser(@PathVariable Long id, @PathVariable boolean enabled) {
        userService.enbledUser(id, enabled);
        return ResponseEntity.ok().build();
    }

}
