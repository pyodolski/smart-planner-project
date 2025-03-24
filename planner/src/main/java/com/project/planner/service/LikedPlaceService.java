package com.project.planner.service;

import com.project.planner.domain.LikedPlace;
import com.project.planner.domain.User;
import com.project.planner.dto.LikedPlaceDto;
import com.project.planner.dto.LikedPlaceXYDto;
import com.project.planner.repository.LikedPlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LikedPlaceService {

    private final LikedPlaceRepository likedPlaceRepository;

    public List<LikedPlaceDto> listAllLikedPlaces(User user) {
        List<LikedPlace> list = likedPlaceRepository.findAllByUser(user);

        List<LikedPlaceDto> likedPlaceDto = new ArrayList<>();

        for (LikedPlace likedPlace : list) {
            LikedPlaceDto likedPlaceDte = new LikedPlaceDto();

            likedPlaceDte.setId(likedPlace.getId());
            likedPlaceDte.setPlaceName(likedPlace.getPlaceName());
            likedPlaceDte.setRoadAddress(likedPlace.getRoadAddress());
            likedPlaceDte.setX(likedPlace.getX());
            likedPlaceDte.setY(likedPlace.getY());

            likedPlaceDto.add(likedPlaceDte);
        }

        return likedPlaceDto;
    }

    public LikedPlaceXYDto findLikedPlaceXY(User user, String placeName, String roadAddress) {
    LikedPlace likedPlace = likedPlaceRepository.findByUserAndPlaceNameAndRoadAddress(user, placeName, roadAddress);

    LikedPlaceXYDto likedPlaceXYDto = new LikedPlaceXYDto(likedPlace.getX(), likedPlace.getY());

    return likedPlaceXYDto;
    }

    public LikedPlace saveLikedPlace(LikedPlace likedPlace){
        return likedPlaceRepository.save(likedPlace);
    }

    public void deleteLikedPlace(User user, String placeName, String roadAddress) {
        likedPlaceRepository.deleteByUserAndPlaceNameAndRoadAddress(user, placeName, roadAddress);
    }
}
