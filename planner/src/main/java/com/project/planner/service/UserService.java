package com.project.planner.service;

import com.project.planner.domain.User;
import com.project.planner.dto.UserDto;
import com.project.planner.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public static User createUser(UserDto userDTO, PasswordEncoder passwordEncoder) {
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encodedPassword);
        return userDTO.toEntity();
    }

    public User saveUser(User user) {
        validateDuplicateUser(user);
        return userRepository.save(user);
    }

    private void validateDuplicateUser(User user) { // 중복 가입 방지
        User findUser = userRepository.findByEmail(user.getEmail());
        if(findUser != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException(email);
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().toString())
                .build();
    }
}