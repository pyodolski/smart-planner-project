package com.project.planner.service;

import com.project.planner.domain.Event;
import com.project.planner.domain.User;
import com.project.planner.dto.EventDto;
import com.project.planner.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EventService {
    private final EventRepository eventRepository;

    public List<EventDto> listAllEventByEventType(User user, String eventType) {
        List<Event> list = eventRepository.findAllByUserAndEventType(user, eventType);
        List<EventDto> eventsDto = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            EventDto eventDto = new EventDto();

            eventDto.setId(list.get(i).getId());
            eventDto.setEventType(list.get(i).getEventType());
            eventDto.setTitle(list.get(i).getTitle());

            eventDto.setFood_name(list.get(i).getFood_name());
            eventDto.setKcal(list.get(i).getKcal());
            eventDto.setCarbohydrate(list.get(i).getCarbohydrate());
            eventDto.setProtein(list.get(i).getProtein());
            eventDto.setFat(list.get(i).getFat());
            eventDto.setSugar(list.get(i).getSugar());
            eventDto.setSalt(list.get(i).getSalt());
            eventDto.setCholesterol(list.get(i).getCholesterol());
            eventDto.setSaturatedFattyAcids(list.get(i).getSaturatedFattyAcids());
            eventDto.setTransFattyAcids(list.get(i).getTransFattyAcids());

            eventDto.setShopping_name(list.get(i).getShopping_name());
            eventDto.setTotal_price(list.get(i).getTotal_price());

            eventDto.setPlaceName(list.get(i).getPlaceName());
            eventDto.setAddress(list.get(i).getAddress());
            eventDto.setX(list.get(i).getX());
            eventDto.setY(list.get(i).getY());

            eventDto.setStart(list.get(i).getStart().toString());
            eventDto.setEnd(list.get(i).getEnd().toString());
            eventDto.setAllDay(list.get(i).isAllDay());
            eventsDto.add(eventDto);
        }
        return eventsDto;
    }

    public List<EventDto> findYesterdayDiets(User user, String yesterdayDate) {
        List<Event> list = eventRepository.findAllByUserAndEventTypeAndStartDate(user, "diet", yesterdayDate);

        List<EventDto> yesterdayDietsDto = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            EventDto dietDte = new EventDto();

            dietDte.setId(list.get(i).getId());
            dietDte.setTitle(list.get(i).getTitle());
            dietDte.setFood_name(list.get(i).getFood_name().toString());
            dietDte.setKcal(list.get(i).getKcal().toString());
            dietDte.setCarbohydrate(list.get(i).getCarbohydrate().toString());
            dietDte.setProtein(list.get(i).getProtein().toString());
            dietDte.setFat(list.get(i).getFat().toString());
            dietDte.setSugar(list.get(i).getSugar().toString());
            dietDte.setSalt(list.get(i).getSalt().toString());
            dietDte.setCholesterol(list.get(i).getCholesterol().toString());
            dietDte.setSaturatedFattyAcids(list.get(i).getSaturatedFattyAcids().toString());
            dietDte.setTransFattyAcids(list.get(i).getTransFattyAcids().toString());

            yesterdayDietsDto.add(dietDte);

        }
        return yesterdayDietsDto;
    }

    public List<EventDto> findDayShopping(User user, String date) {
        List<Event> list = eventRepository.findAllByUserAndEventTypeAndStartDate(user, "shopping", date);

        List<EventDto> shoppingDto = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            EventDto shoppingDte = new EventDto();

            shoppingDte.setId(list.get(i).getId());
            shoppingDte.setTitle(list.get(i).getTitle());
            shoppingDte.setShopping_name(list.get(i).getShopping_name().toString());
            shoppingDte.setTotal_price(list.get(i).getTotal_price().toString());

            shoppingDto.add(shoppingDte);
        }
        return shoppingDto;
    }

    public List<EventDto> findMonthShopping(User user, String month) {
        List<Event> list = eventRepository.findAllByUserAndEventTypeAndMonth(user, "shopping", month);

        List<EventDto> shoppingDto = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            EventDto shoppingDte = new EventDto();

            shoppingDte.setId(list.get(i).getId());
            shoppingDte.setTitle(list.get(i).getTitle());
            shoppingDte.setShopping_name(list.get(i).getShopping_name().toString());
            shoppingDte.setTotal_price(list.get(i).getTotal_price().toString());

            shoppingDto.add(shoppingDte);
        }
        return shoppingDto;
    }

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
