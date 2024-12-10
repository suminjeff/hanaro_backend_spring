package org.problem2;

import org.junit.jupiter.api.Test;
import org.problem2.model.entity.User;
import org.problem2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryTests {
	@Autowired
	private UserRepository userRepository;

	@Test
	void testInsertKim() {
		for (int i = 0; i < 5; i++) {
			User user = User.builder()
					.name(String.format("Kim%d", i+1))
					.build();
			userRepository.save(user);
		}
	}

	@Test
	void testClearUser() {
		userRepository.deleteAll();
	}

}
