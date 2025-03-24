package com.project.planner.dto;

import com.project.planner.domain.LikedPlace;
import com.project.planner.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikedPlaceDto {

    private Long id;
    private String placeName;
    private String roadAddress;
    private User user;
    private String x;
    private String y;

    public LikedPlace toEntity() {
        return LikedPlace.builder()
                .id(id)
                .placeName(placeName)
                .roadAddress(roadAddress)
                .x(x)
                .y(y)
                .user(user)
                .build();
    }
}
