package org.problem2.data;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.problem2.model.entity.User;
import org.problem2.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final UserRepository userRepository;

    @PostConstruct
    public void insertInitialData() {
        for (int i = 0; i < 5; i++) {
            User user = User.builder()
                    .name(String.format("Kim%d", i+1))
                    .build();
            userRepository.save(user);
        }
    }
}
