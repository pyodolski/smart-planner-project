package com.project.planner.controller;

import com.project.planner.dto.UserDto;
import com.project.planner.service.UserService;
import com.project.planner.domain.User; // User 클래스 import
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class RegisterController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입 페이지를 반환하는 메서드
     * @param model Thymeleaf에서 사용할 데이터를 담는 객체
     * @return user/register.html 템플릿 반환
     */
    @GetMapping("/registry")
    public String registryForm(Model model) {
        if (!model.containsAttribute("userDTO")) {
            model.addAttribute("userDTO", new UserDto());
        }
        return "user/register";
    }

    @PostMapping("/registry")
    public String registry(@ModelAttribute @Valid UserDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userDTO", userDto);
            return "user/register";
        }

        if (!userDto.getPassword().equals(userDto.getPassword_confirm())) {
            model.addAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
            model.addAttribute("userDTO", userDto);
            return "user/register";
        }

        try {
            User user = userService.createUser(userDto, passwordEncoder);
            userService.saveUser(user);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("userDTO", userDto);
            return "user/register";
        }

        return "redirect:/login?status=success";
    }

}

 // RegisterController 구현 Ok
