package com.commitme.commit_me.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.commitme.commit_me.model.Event;
import com.commitme.commit_me.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/events")

public class EventController {
    
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<Object> createEvent (@Valid @RequestBody Event event) {
        return eventService.createEvent(event);
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
