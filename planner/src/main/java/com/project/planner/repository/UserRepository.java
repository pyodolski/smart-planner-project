package com.project.planner.repository;

import com.project.planner.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByPhone(String phone);

    User findByEmailOrPhone(String email, String phone);

    User findByEmailAndPassword(String email, String password);

    User findByPhoneAndPassword(String phone, String password);

}
