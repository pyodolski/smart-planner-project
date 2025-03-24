package com.project.planner.repository;

import com.project.planner.domain.LikedPlace;
import com.project.planner.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikedPlaceRepository extends JpaRepository<LikedPlace, Long> {
    List<LikedPlace> findAllByUser(User user);

    LikedPlace findByUserAndPlaceNameAndRoadAddress(User user, String placeName, String roadAddress);

    void deleteById(Long id);

    void deleteByUserAndPlaceNameAndRoadAddress(User user, String placeName, String roadAddress);
}
