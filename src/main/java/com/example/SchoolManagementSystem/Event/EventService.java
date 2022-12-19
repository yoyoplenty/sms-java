package com.example.SchoolManagementSystem.Event;

import com.example.SchoolManagementSystem.Event.Dto.NewEventDto;
import com.example.SchoolManagementSystem.Event.Dto.UpdateEventDto;
import com.example.SchoolManagementSystem.School.School;
import com.example.SchoolManagementSystem.School.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    SchoolService schoolService;

    public Event createEvent(NewEventDto newEventDto) {
        School school = schoolService.findSchoolById(newEventDto.getSchoolId());

        Event event = Event.builder()
                .name(newEventDto.getName())
                .amount(newEventDto.getAmount())
                .eventType(newEventDto.getEventType())
                .date(newEventDto.getDate())
                .school(school)
                .build();

        return eventRepository.save(event);
    }

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    public Event getEvent(UUID id) {
        return findEventById(id);
    }

    public List<Event> getEventsBySchoolId(UUID id) {
        schoolService.findSchoolById(id);

        return eventRepository.findEventBySchoolId(id);
    }

    public Event findEventById(UUID id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("event not found"));
    }

    public Event updateEvent(UpdateEventDto updateEventDto, UUID id) {
        Event event = findEventById(id);

        event.setEventType(updateEventDto.getEventType());
        event.setName(updateEventDto.getName());
        event.setAmount(updateEventDto.getAmount());
        event.setDate(updateEventDto.getDate());

        return eventRepository.save(event);
    }

    public String deleteEvent(UUID id) {
        Event event = findEventById(id);
        eventRepository.delete(event);

        return "deleted successfully";
    }
}
