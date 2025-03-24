package com.project.planner.controller;

import com.project.planner.domain.User;
import com.project.planner.dto.EventDto;
import com.project.planner.repository.UserRepository;
import com.project.planner.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final EventService eventService;
    private final UserRepository userRepository;
    @GetMapping("/user/home")
    public String userHome() { return "userHome/userHome"; }

    @GetMapping("/user/getWeatherApi/{stnId}/{tmFc}")
    @ResponseBody
    public String getWeatherApi(@PathVariable String stnId, @PathVariable String tmFc) throws IOException {
        // 기본 URL 설정
        String baseurl = "http://apis.data.go.kr/";
        String service = "1360000/MidFcstInfoService/getMidFcst";
        String serviceKey = "CH%2FJx5uRVc0rOLBa5MkIkCdgg2vCmy0p96GHAjiVL0MAM%2B3x%2BAfgLKiAhO8lyx1acH7FRpJtaGdw9NErQZnD2g%3D%3D";
        String pageNp = "1";
        String dataType = "json";
        String string_url = baseurl + service + "?" + "serviceKey=" + serviceKey + "&" + "pageNo=" + pageNp + "&"
                + "dataType=" + dataType + "&" + "stnId=" + stnId + "&" + "tmFc=" + tmFc;
        URL url = new URL(string_url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        BufferedReader rd;

        // 서비스코드가 정상이면 200-300사이 숫자
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        } StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        return sb.toString();
    }
    @GetMapping("/user/getYesterdayDiet/{yesterdayDate}")
    @ResponseBody
    public List<EventDto> getYesterdayDiet(@PathVariable String yesterdayDate, Principal principal) throws IOException {
        // Principal principal은 현재 로그인한 사용자 정보를 가져올 수 있게 해주는 Spring Security의 기능
        String userName = principal.getName();
        User user = userRepository.findByEmail(userName);
        return eventService.findYesterdayDiets(user, yesterdayDate);
        // 현재 로그인한 유저의 정보 접근 가능 어제 식단 호출
    }

    @GetMapping("user/getShoppingByDate/{date}")
    @ResponseBody
    public List<EventDto> getShoppingByDate(@PathVariable String date, Principal principal) throws IOException {
        String username = principal.getName();
        User user = userRepository.findByEmail(username);

        return eventService.findDayShopping(user, date);
    }

    @GetMapping("user/getShoppingByMonth/{month}")
    @ResponseBody
    public List<EventDto> getShoppingByMonth(@PathVariable String month, Principal principal) throws IOException {
        String username = principal.getName();
        User user = userRepository.findByEmail(username);

        return eventService.findMonthShopping(user, month);
    }
}
