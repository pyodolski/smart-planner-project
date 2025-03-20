package com.project.planner.dto;

import com.project.planner.domain.Event;
import com.project.planner.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {

    private Long id;

    private String eventType;

    private String title;

    //  식단 -----------------------
    private String food_name;
    private String kcal;
    private String carbohydrate;
    private String protein;
    private String fat;
    private String sugar;
    private String salt;
    private String cholesterol;
    private String saturatedFattyAcids;
    private String transFattyAcids;

    //  소비 기록 --------------------
    private String shopping_name;
    private String total_price;

    //  장소 --------------------
    private String placeName;
    private String address;
    private String x;
    private String y;

    private String start;
    private String startDate;
    private String startMonth;
    private String end;

    private User user;

    private boolean allDay;

    public Event toEntity_text() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.KOREA);

        return Event.builder()
                .eventType(eventType)  // 이벤트 유형 설정
                .title(title)  // 제목 설정
                .start(LocalDateTime.parse(start, dateTimeFormatter)) // 문자열 start를 LocalDateTime으로 변환
                .end(LocalDateTime.parse(end, dateTimeFormatter)) // 문자열 end를 LocalDateTime으로 변환
                .allDay(allDay)  // 하루 종일 일정 여부 설정
                .user(user)  // 해당 이벤트를 생성한 사용자 설정
                .build();
    }

    public Event toEntity_diet() {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.KOREA);

        return Event.builder()
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
                .start(LocalDateTime.parse(start, dateTimeFormatter))
                .startDate(startDate)
                .end(LocalDateTime.parse(end, dateTimeFormatter))
                .allDay(allDay)
                .user(user)
                .build();

    }

    public Event toEntity_shopping() {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.KOREA);

        return Event.builder()
                .eventType(eventType)
                .title(title)
                .shopping_name(shopping_name)
                .total_price(total_price)
                .start(LocalDateTime.parse(start, dateTimeFormatter))
                .startDate(startDate)
                .startMonth(startMonth)
                .end(LocalDateTime.parse(end, dateTimeFormatter))
                .allDay(allDay)
                .user(user)
                .build();

    }

    public Event toEntity_place() {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.KOREA);

        return Event.builder()
                .eventType(eventType)
                .title(title)
                .placeName(placeName)
                .address(address)
                .x(x)
                .y(y)
                .start(LocalDateTime.parse(start, dateTimeFormatter))
                .startDate(startDate)
                .end(LocalDateTime.parse(end, dateTimeFormatter))
                .allDay(allDay)
                .user(user)
                .build();

    }
    // text, diet, shopping, place 빌더 생성


}
