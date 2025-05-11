package com.project.planner.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

import static org.apache.catalina.util.ConcurrentDateFormat.GMT;

/**
 * Event 엔티티 클래스
 * - 사용자의 일정, 식단, 소비 기록, 장소 정보를 저장하는 모델
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "event")
@Data
public class Event {

    // 기본 키 (PK)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    // 이벤트 유형 (예: 일정, 식단, 소비, 장소 등)
    private String eventType;

    // 이벤트 제목
    @Column(nullable = false)
    private String title;

    // **식단 관련 필드**
    private String food_name;        // 음식 이름
    private String kcal;             // 칼로리 정보
    private String carbohydrate;     // 탄수화물 정보
    private String protein;          // 단백질 정보
    private String fat;              // 지방 정보
    private String sugar;            // 당 정보
    private String salt;             // 나트륨 정보
    private String cholesterol;      // 콜레스테롤 정보
    private String saturatedFattyAcids; // 포화지방산 정보
    private String transFattyAcids;  // 트랜스지방 정보

    // **소비 기록 관련 필드**
    private String shopping_name;    // 구매한 상품명
    private String total_price;      // 총 가격

    // **장소 관련 필드**
    private String placeName;        // 장소 이름
    private String address;          // 장소 주소
    private String x;                // 장소 좌표 (경도)
    private String y;                // 장소 좌표 (위도)

    // **일정 관련 날짜 및 시간 정보**

    // 일정 시작 시간 (년-월-일 시:분 형식)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+9")
    private LocalDateTime start;

    // 일정 시작 날짜 (년-월-일 형식) - 소비, 식단, 장소용
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+9")
    private String startDate;

    // 월별 소비 데이터 저장 (년-월 형식)
    @DateTimeFormat(pattern = "yyyy-MM")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+9")
    private String startMonth;

    // 일정 종료 시간 (년-월-일 시:분 형식)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+9")
    private LocalDateTime end;

    // 하루 종일 일정 여부
    private boolean allDay;

    // 사용자와 연관된 이벤트 (다대일 관계)
    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getKcal() {
        return kcal;
    }

    public void setKcal(String kcal) {
        this.kcal = kcal;
    }

    public String getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(String carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(String cholesterol) {
        this.cholesterol = cholesterol;
    }

    public String getSaturatedFattyAcids() {
        return saturatedFattyAcids;
    }

    public void setSaturatedFattyAcids(String saturatedFattyAcids) {
        this.saturatedFattyAcids = saturatedFattyAcids;
    }

    public String getTransFattyAcids() {
        return transFattyAcids;
    }

    public void setTransFattyAcids(String transFattyAcids) {
        this.transFattyAcids = transFattyAcids;
    }

    public String getShopping_name() {
        return shopping_name;
    }

    public void setShopping_name(String shopping_name) {
        this.shopping_name = shopping_name;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public boolean isAllDay() {
        return allDay;
    }

    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
