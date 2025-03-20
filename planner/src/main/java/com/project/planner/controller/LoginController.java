package com.project.planner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // 로그인 성공
public class LoginController {

    @GetMapping("/login")
    public String loginForm(@RequestParam(value = "status", required = false) String status, Model model) {
        if (status != null && !status.isEmpty()) {
            if (status.equals("success")) {
                model.addAttribute("Success", "로그인에 성공하였습니다.");
            }
        }
        return "user/login";
    }

    @GetMapping(value = "/login/error") // 로그인 실패
    public String loginError(Model model) {
        model.addAttribute("error", "아이디 또는 비밀번호가 일치하지 않습니다.");
        return "user/login";
    }
}

// 로그인 Controller 구현 Ok
