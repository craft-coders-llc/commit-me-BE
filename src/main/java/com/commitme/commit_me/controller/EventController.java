package com.commitme.commit_me.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.commitme.commit_me.model.Event;
import com.commitme.commit_me.service.EventService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api/v1/events")

public class EventController {
    
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<Object> createEvent (@PathVariable Integer userId, @Valid @RequestBody Event event) {
        Integer categoryId = event.getCategory().getId();
        return eventService.createEvent(event, userId, categoryId);
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{type}")
    public List<Event> getEventsByCategory(@PathVariable String type) {
        return eventService.getEventsByCategoryType(type);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Event>> searchEventsByTitle(@RequestParam("keyword") String keyword) {
        List<Event> events = eventService.searchEventsByTitle(keyword);
        return ResponseEntity.ok(events);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateEvent(@PathVariable Integer id, @RequestBody Event updatedEvent){
        return this.eventService.updateEvent(id, null, null, null, null, null, updatedEvent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEvent(@PathVariable Integer id) {
        return this.eventService.deleteEvent(id);
    }

}
