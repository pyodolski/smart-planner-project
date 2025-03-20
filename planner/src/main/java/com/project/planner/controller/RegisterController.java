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
        model.addAttribute("userDTO", new UserDto()); // 빈 UserDto 객체를 모델에 추가
        return "user/register"; // 회원가입 폼을 반환
    }

    /**
     * 회원가입 요청을 처리하는 메서드
     * @param userDto 회원가입 폼에서 입력받은 데이터를 담은 DTO 객체
     * @param bindingResult 유효성 검사 결과를 담는 객체
     * @param model Thymeleaf에서 사용할 데이터를 담는 객체
     * @return 회원가입 성공 시 로그인 페이지로 리다이렉트, 실패 시 다시 회원가입 페이지 반환
     */
    @PostMapping("/registry") // @Valid를 사용하여 userDto의 유효성을 검증
    public String registry(@ModelAttribute @Valid UserDto userDto, BindingResult bindingResult, Model model) {
        // 유효성 검사에 실패한 경우, 다시 회원가입 페이지를 반환
        if (bindingResult.hasErrors()) {
            return "user/register";
        }

        // 비밀번호와 비밀번호 확인이 일치하지 않는 경우
        if (!userDto.getPassword().equals(userDto.getPassword_confirm())) {
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "user/register";
        }

        try {
            // User 객체를 생성하고 비밀번호를 암호화하여 저장
            User user = userService.createUser(userDto, passwordEncoder);
            userService.saveUser(user);
        } catch (IllegalStateException e) {
            // 중복된 이메일 등의 이유로 회원가입이 실패한 경우 에러 메시지를 모델에 추가
            model.addAttribute("error", e.getMessage());
            return "user/register";
        }

        // 회원가입 성공 후 로그인 페이지로 리다이렉트
        return "redirect:/login?status=success";
    }
}

 // RegisterController 구현 Ok
