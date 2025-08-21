package com.saul.parque.diversiones.user;

import com.saul.parque.diversiones.dto.user.UpdatePassRequest;
import com.saul.parque.diversiones.dto.user.UserRequest;
import com.saul.parque.diversiones.dto.user.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest request) {
        log.info("Register new user");
        return new ResponseEntity<>(userService.create(request), HttpStatus.CREATED);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles() {
        log.info("Get roles");
        return ResponseEntity.ok(userService.getRoles());
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        log.info("Get all users");
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/enabled")
    public ResponseEntity<List<UserResponse>> getAllEnabled() {
        log.info("Get all users enabled");
        return ResponseEntity.ok(userService.getAllEnabled());
    }

    @PatchMapping
    public ResponseEntity<UserResponse> updatePassword(@Valid @RequestBody UpdatePassRequest request) {
        log.info("Updating password");
        return ResponseEntity.ok(userService.updatePassword(request));
    }

    @PatchMapping("/{id}/enabled/{enabled}")
    public ResponseEntity<Void> enabledUser(@PathVariable Long id, @PathVariable boolean enabled) {
        userService.enbledUser(id, enabled);
        log.info("Updating enabled user {} to {}", id, enabled);
        return ResponseEntity.ok().build();
    }

}
