package com.project.planner.controller;

import com.project.planner.domain.Event;
import com.project.planner.domain.User;
import com.project.planner.dto.EventDto;
import com.project.planner.dto.LikedPlaceDto;
import com.project.planner.dto.LikedPlaceXYDto;
import com.project.planner.repository.UserRepository;
import com.project.planner.service.EventService;
import com.project.planner.service.LikedPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/myPlanner")
public class MyPlannerController {
    private final EventService eventService;
    private final UserRepository userRepository;
    private final LikedPlaceService likedPlaceService;
    // 좋아요 위치 표시 서비스

    @GetMapping("api/events/vacation")
    @ResponseBody
    public List<EventDto> listAllVacationEvents(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByEmail(username);
        return eventService.listAllEventByEventType(user, "vacation");
    }

    @GetMapping("api/events/diet")
    @ResponseBody
    public List<EventDto> listAllDietEvents(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByEmail(username);
        return eventService.listAllEventByEventType(user, "diet");
    }

    @GetMapping("api/events/shopping")
    @ResponseBody
    public List<EventDto> listAllShoppingEvents(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByEmail(username);
        return eventService.listAllEventByEventType(user, "shopping");
    }

    @GetMapping("api/events/place")
    @ResponseBody
    public List<EventDto> listAllPlaceEvents(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByEmail(username);
        return eventService.listAllEventByEventType(user, "place");
    }

    @GetMapping("api/events/text")
    @ResponseBody
    public List<EventDto> listAllTextEvents(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByEmail(username);
        return eventService.listAllEventByEventType(user, "text");
    }
    // 휴가 , 식단, 쇼핑, 장소, 텍스트 이벤트 정보 불러오기

    @GetMapping("getLikedPlace")
    @ResponseBody
    public List<LikedPlaceDto> getLikedPlace(Principal principal) {

        String username = principal.getName();
        User user = userRepository.findByEmail(username);
        return likedPlaceService.listAllLikedPlaces(user);
    }
    // 즐겨찾기 장소 정보 불러오기

    @GetMapping("getLikedPlace/{placeName}/{roadAddress}") // 주소 변경
    @ResponseBody
    public LikedPlaceXYDto getLikedPlace(@PathVariable String placeName, @PathVariable String roadAddress, Principal principal) {

        String username = principal.getName();
        User user = userRepository.findByEmail(username);
        return likedPlaceService.findLikedPlaceXY(user, placeName, roadAddress);
    }
    @GetMapping("/getLikedPlaceXY/{placeName}/{roadAddress}")
    @ResponseBody
    public LikedPlaceXYDto getLikedPlaceXY(@PathVariable String placeName, @PathVariable String roadAddress, Principal principal) throws IOException {
        String username = principal.getName();
        User user = userRepository.findByEmail(username);

        return likedPlaceService.findLikedPlaceXY(user, placeName, roadAddress);

    }


    @DeleteMapping("/deleteLikedPlace/{placeName}/{roadAddress}") // 주소 변경
    @ResponseBody
    public void deleteLikedPlace(@PathVariable String placeName, @PathVariable String roadAddress, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByEmail(username);
        likedPlaceService.deleteLikedPlace(user, placeName, roadAddress);
    }
    // 즐겨찾기 장소 삭제
    @GetMapping
    public String myPlanner() {
        return "myPlanner/myPlanner";
    }

    @DeleteMapping(value = "delete")
    @ResponseBody
    public void deleteEvent(@RequestBody EventDto eventDto, Principal principal) {
        Long id = eventDto.getId();
        eventService.deleteEvent(id);
    } // 이벤트 삭제

    // 생필품 정보 API 호출 코드 [ 예정 ]

    @PostMapping(value = "create_text")
    @ResponseBody
    public void addEvent_text(@RequestBody EventDto eventTextDto, Principal principal) throws Exception {
        String eventType = eventTextDto.getEventType();

        String title = eventTextDto.getTitle();

        String start = eventTextDto.getStart();
        String end = eventTextDto.getEnd();
        boolean allDay = eventTextDto.isAllDay();

        String username = principal.getName();
        User user = userRepository.findByEmail(username);

        Event eventText = EventDto.builder()
                .eventType(eventType)
                .title(title)
                .start(start)
                .end(end)
                .allDay(allDay)
                .user(user)
                .build().toEntity_text();
        eventService.saveEvent(eventText);

    }
    // 텍스트 이벤트 생성

    @PostMapping(value = "create_diet")
    @ResponseBody
    public void addEvent_diet(@RequestBody EventDto eventDietDto, Principal principal) throws Exception {
        String eventType = eventDietDto.getEventType();

        String title = eventDietDto.getTitle();
        String food_name = eventDietDto.getFood_name();
        String kcal = eventDietDto.getKcal();
        String carbohydrate = eventDietDto.getCarbohydrate();
        String protein = eventDietDto.getProtein();
        String fat = eventDietDto.getFat();
        String sugar = eventDietDto.getSugar();
        String salt = eventDietDto.getSalt();
        String cholesterol = eventDietDto.getCholesterol();
        String saturatedFattyAcids = eventDietDto.getSaturatedFattyAcids();
        String transFattyAcids = eventDietDto.getTransFattyAcids();

        String start = eventDietDto.getStart();
        String startDate = eventDietDto.getStartDate();
        String end = eventDietDto.getEnd();
        boolean allDay = eventDietDto.isAllDay();

        String username = principal.getName();
        User user = userRepository.findByEmail(username);

        Event eventDiet = EventDto.builder()
                .eventType(eventType)
                .title(title)
                .food_name(food_name)
                .kcal(kcal)
                .carbohydrate(carbohydrate)
                .protein(protein)
                .fat(fat)
                .sugar(sugar)
                .salt(salt)
                .cholesterol(cholesterol)
                .saturatedFattyAcids(saturatedFattyAcids)
                .transFattyAcids(transFattyAcids)
                .start(start)
                .startDate(startDate)
                .end(end)
                .allDay(allDay)
                .user(user)
                .build().toEntity_diet();
        eventService.saveEvent(eventDiet);
    }
    // 식단 이벤트 생성

    @PostMapping(value = "create_shopping")
    @ResponseBody
    public void addEvent_shopping(@RequestBody EventDto eventShoppingDto, Principal principal) throws Exception {
        String eventType = eventShoppingDto.getEventType();

        String title = eventShoppingDto.getTitle();
        String shopping_name = eventShoppingDto.getShopping_name();
        String totalPrice = eventShoppingDto.getTotal_price();

        String start = eventShoppingDto.getStart();
        String startDate = eventShoppingDto.getStartDate();
        String startMonth = eventShoppingDto.getStartMonth();
        String end = eventShoppingDto.getEnd();
        boolean allDay = eventShoppingDto.isAllDay();

        String username = principal.getName();
        User user = userRepository.findByEmail(username);

        Event eventShopping = EventDto.builder()
                .eventType(eventType)
                .title(title)
                .shopping_name(shopping_name)
                .total_price(totalPrice)
                .start(start)
                .startDate(startDate)
                .startMonth(startMonth)
                .end(end)
                .allDay(allDay)
                .user(user)
                .build().toEntity_shopping();
        eventService.saveEvent(eventShopping);
    }
    // 쇼핑 이벤트 생성

    @PostMapping(value = "create_place")
    @ResponseBody
    public void addEvent_place(@RequestBody EventDto eventPlaceDto, Principal principal) throws Exception {
        String eventType = eventPlaceDto.getEventType();

        String title = eventPlaceDto.getTitle();
        String placeName = eventPlaceDto.getPlaceName();
        String roadAddress = eventPlaceDto.getAddress();
        String x = eventPlaceDto.getX();
        String y = eventPlaceDto.getY();

        String start = eventPlaceDto.getStart();
        String startDate = eventPlaceDto.getStartDate();
        String end = eventPlaceDto.getEnd();
        boolean allDay = eventPlaceDto.isAllDay();

        String username = principal.getName();
        User user = userRepository.findByEmail(username);

        Event eventPlace = EventDto.builder()
                .eventType(eventType)
                .title(title)
                .placeName(placeName)
                .address(roadAddress)
                .x(x)
                .y(y)
                .start(start)
                .startDate(startDate)
                .end(end)
                .allDay(allDay)
                .user(user)
                .build().toEntity_place();
        eventService.saveEvent(eventPlace);
    }
    // 장소 이벤트 생성


    @GetMapping("getDietApi/{numOfRows}/{pageNo}")
    @ResponseBody
    public String getDietApi(@PathVariable Long numOfRows, @PathVariable Long pageNo) throws IOException {

        String base = "http://apis.data.go.kr/";
        String service = "1471000/FoodNtrCpntDbInfo02";
        String serviceKey = "CH%2FJx5uRVc0rOLBa5MkIkCdgg2vCmy0p96GHAjiVL0MAM%2B3x%2BAfgLKiAhO8lyx1acH7FRpJtaGdw9NErQZnD2g%3D%3D";
        String type = "json";

        // URL을 형식에 맞게 구성
        String string_url = base + service + "/getFoodNtrCpntDbInq02?" + "ServiceKey=" + serviceKey + "&" +
                "pageNo=" + pageNo + "&" +
                "numOfRows=" + numOfRows + "&" +
                "type=" + type;

        // URL 객체 생성
        URL url = new URL(string_url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // HTTP 요청 방식 설정 (GET)
        conn.setRequestMethod("GET");

        // 응답 처리
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        // 응답을 반환
        return sb.toString();
    }

    @GetMapping("getDietApi/{numOfRows}/{pageNo}/{desc_kor}")
    @ResponseBody
    public String getDietApiByName(@PathVariable Long numOfRows, @PathVariable Long pageNo, @PathVariable String desc_kor) throws IOException {

        String base = "http://apis.data.go.kr/";
        String service = "1471000/FoodNtrCpntDbInfo02";
        String serviceKey = "CH%2FJx5uRVc0rOLBa5MkIkCdgg2vCmy0p96GHAjiVL0MAM%2B3x%2BAfgLKiAhO8lyx1acH7FRpJtaGdw9NErQZnD2g%3D%3D";
        String type = "json";
        String searchParam = URLEncoder.encode(desc_kor, "UTF-8");
        String string_url = base + service + "/getFoodNtrCpntDbInq02?" + "ServiceKey=" + serviceKey + "&" +
                "pageNo=" + pageNo + "&" +
                "numOfRows=" + numOfRows + "&" +
                "type=" + type + "&" +
                "FOOD_NM_KR=" + URLEncoder.encode(desc_kor, "UTF-8");


        URL url = new URL(string_url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        BufferedReader rd;

        // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        return sb.toString();

    }
    // 식품 영양 정보 API
    // 조회 방식의 차이
    // 1. 페이지 단위로 조회하는 기본 API 호출 방식
    // 2. 식품 이름으로 영양 정보를 검색하는 기능

}


