package org.problem2.repository;

import org.problem2.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User getUserByName(String kim1);
}
