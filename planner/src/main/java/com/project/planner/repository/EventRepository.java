package com.project.planner.repository;

import com.project.planner.domain.Event;
import com.project.planner.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByUserAndEventType(User user, String eventType);
    // User, EventType 으로 조회
    List<Event> findAllByUserAndEventTypeAndStartDate(User user, String eventType, String date);
    // User, EventType, StartDate 으로 조회
    List<Event> findAllByUserAndEventTypeAndMonth(User user, String eventType, String month);
    // User, EventType, Month 으로 조회

    // List<Event> findAllByUser(User user);

    void deleteById(Long id); // 해당아이디 삭제

}
