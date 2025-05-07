package com.project.planner.controller;

import com.project.planner.domain.LikedPlace;
import com.project.planner.domain.User;
import com.project.planner.dto.LikedPlaceDto;
import com.project.planner.repository.UserRepository;
import com.project.planner.service.LikedPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/map")
public class MapController {

    @GetMapping("searchView")
    public String searchView() {
        return "map/searchView";
    }
    private final LikedPlaceService likedPlaceService;
    private final UserRepository userRepository;

    @PostMapping(value = "place_like")
    @ResponseBody
    public void place_like(@RequestBody LikedPlaceDto likedPlaceDto, Principal principal) throws Exception{

        String placeName = likedPlaceDto.getPlaceName();
        String roadAddress = likedPlaceDto.getRoadAddress();
        String x = likedPlaceDto.getX();
        String y = likedPlaceDto.getY();

        String username = principal.getName();
        User user = userRepository.findByEmail(username);

        LikedPlace likedPlace = LikedPlace.builder()
                .placeName(placeName)
                .roadAddress(roadAddress)
                .x(x)
                .y(y)
                .user(user)
                .build();

        likedPlaceService.saveLikedPlace(likedPlace);

    }
}
