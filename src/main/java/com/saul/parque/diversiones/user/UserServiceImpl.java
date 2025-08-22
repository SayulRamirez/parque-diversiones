package com.saul.parque.diversiones.user;

import com.saul.parque.diversiones.dto.user.UpdatePassRequest;
import com.saul.parque.diversiones.dto.user.UserRequest;
import com.saul.parque.diversiones.dto.user.UserResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserResponse create(UserRequest request) {
        LocalDateTime now = LocalDateTime.now();

        User user = userRepository.save(User.builder()
                .password(request.password())
                .role(request.role())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .dateRegister(now)
                .isEnabled(true)
                .build());

        String username = generateUsername(request, user.getId());

        user.setUsername(username);

        log.info("User create with id: {}", user.getId());

        return fromEntity(userRepository.save(user));
    }

    @Override
    public List<Role> getRoles() {
        return List.of(Role.values());
    }

    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll().stream().map(this::fromEntity).toList();
    }

    @Override
    public List<UserResponse> getAllEnabled() {
        return userRepository.findAllByIsEnabledIsTrue().stream()
                .map(this::fromEntity).toList();
    }

    @Transactional
    @Override
    public UserResponse updatePassword(UpdatePassRequest request) {

        User user = userRepository.findById(request.id())
                .orElseThrow(() -> new EntityNotFoundException("no se encontro el usuario con id: " + request.id()));

        user.setPassword(request.password());

        log.info("Update password with id: {}", user.getId());

        return fromEntity(userRepository.save(user));
    }

    @Transactional
    @Override
    public void enbledUser(Long id, boolean enabled) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("no se encontro el usuario con id: " + id));

        user.setEnabled(enabled);

        log.info("Update enabled user with id: {}", user.getId());

        userRepository.save(user);
    }

    private String generateUsername(UserRequest request, long id) {

        String[] split = new String[]{
                getFirst(request.firstName()),
                getFirst(request.lastName())
        };

        StringBuilder username = new StringBuilder();

        for (String name : split) {
            username.append(name.charAt(0));
            username.append(name.charAt(1));
        }

        return removeAccents(username.toString()) + id;
    }

    private String getFirst(String names) {
        return names.split(" ")[0].toUpperCase();
    }

    public String removeAccents(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }

    private UserResponse fromEntity(User user) {
        return new UserResponse(user.getId(),
                user.getUsername(),
                user.getRole(),
                user.isEnabled(),
                user.getFirstName(),
                user.getLastName(),
                user.getDateRegister());
    }
}

