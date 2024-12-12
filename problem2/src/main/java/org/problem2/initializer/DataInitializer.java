package org.problem2.initializer;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.problem2.entity.User;
import org.problem2.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final UserRepository userRepository;

    @PostConstruct
    public void insertInitialData() throws Exception {
        try {
            if (userRepository.count() > 0) {
                return;
            }

            List<User> userList = createUserList();
            userRepository.saveAll(userList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<User> createUserList() {
        return List.of(
                User.builder().name("Kim1").build(),
                User.builder().name("Kim2").build(),
                User.builder().name("Kim3").build(),
                User.builder().name("Kim4").build(),
                User.builder().name("Kim5").build()
        );
    }
}
