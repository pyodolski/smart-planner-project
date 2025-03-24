package com.project.planner.controller;

import com.project.planner.domain.Event;
import com.project.planner.repository.UserRepository;
import com.project.planner.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/myPlanner")
public class MyPlannerController {
    private final EventService eventService;
    private final UserRepository userRepository;
    // 좋아요 위치 표시 서비스
}
