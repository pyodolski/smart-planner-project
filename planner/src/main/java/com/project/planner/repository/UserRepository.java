package com.project.planner.repository;

import com.project.planner.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    // Email 통하여 User Entity 접근
}
