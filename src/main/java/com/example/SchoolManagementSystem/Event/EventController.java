package com.example.SchoolManagementSystem.Event;


import com.example.SchoolManagementSystem.Event.Dto.NewEventDto;
import com.example.SchoolManagementSystem.Event.Dto.UpdateEventDto;
import com.example.SchoolManagementSystem.School.Annotation.SchoolNotPresent;
import com.example.SchoolManagementSystem.Utils.Handlers.Responses.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/events")
public class EventController {

    @Autowired
    EventService eventService;

    @PostMapping()
    public ResponseEntity<Object> createEvent(@RequestBody @Valid NewEventDto newEventDto) {
        try {
            Event newEvent = eventService.createEvent(newEventDto);

            return ResponseHandler.generateResponse("Successfully created Event", HttpStatus.OK, newEvent);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<Object> getEvents() {
        try {
            List<Event> events = eventService.getEvents();

            return ResponseHandler.generateResponse("Successfully fetched data", HttpStatus.OK, events);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEvent(@PathVariable UUID id) {
        try {
            Event event = eventService.getEvent(id);

            return ResponseHandler.generateResponse("Successfully fetched data", HttpStatus.OK, event);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @GetMapping("/schools/{school_id}")
    public ResponseEntity<Object> getSchoolEvents(@PathVariable @SchoolNotPresent UUID school_id) {
        try {
            List<Event> events = eventService.getEventsBySchoolId(school_id);

            return ResponseHandler.generateResponse("Successfully fetched school Events", HttpStatus.OK, events);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateEvent(@RequestBody UpdateEventDto updateEventDto, @PathVariable UUID id) {
        try {
            Object Event = eventService.updateEvent(updateEventDto, id);

            return ResponseHandler.generateResponse("Successfully updated data", HttpStatus.OK, Event);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEvent(@PathVariable UUID id) {
        try {
            Object Event = eventService.deleteEvent(id);

            return ResponseHandler.generateResponse("Successfully deleted data", HttpStatus.OK, Event);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }
}
