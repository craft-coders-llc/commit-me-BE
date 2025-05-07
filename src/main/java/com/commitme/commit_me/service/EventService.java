package com.commitme.commit_me.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.commitme.commit_me.exceptions.EventDescriptionAlreadyExistsException;
import com.commitme.commit_me.exceptions.EventTitleAlreadyExistsException;
import com.commitme.commit_me.model.Category;
import com.commitme.commit_me.model.Event;
import com.commitme.commit_me.model.User;
import com.commitme.commit_me.repository.CategoryRepository;
import com.commitme.commit_me.repository.EventRepository;
import com.commitme.commit_me.repository.UserRepository;

@Service
public class EventService {
    
    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public EventService(EventRepository eventRepository, CategoryRepository categoryRepository, UserRepository userRepository){
        this.eventRepository = eventRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<Object> createEvent(Event event, Integer categoryId) {
        //Optional<User> userOptional = userRepository.getUserById(userId);
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);

        if (event.getTitle() != null && eventRepository.findUpdtByTitle(event.getTitle()).isPresent()) {
            throw new EventTitleAlreadyExistsException("(!) ERROR: ya existe un evento con el mismo título");
        }

        if (event.getDescription() != null && eventRepository.findByDescription(event.getDescription()).isPresent()){
            throw new EventDescriptionAlreadyExistsException("(!) ERROR: ya existe un evento con la misma descripción");
        }

        //event.setUser(userOptional.get());
        event.setCategory(categoryOptional.get());

        return new ResponseEntity<>(eventRepository.save(event), HttpStatus.CREATED);
    }

    public List<Event> getAllEvents() {
        return this.eventRepository.findAll();
    }

    public List<Event> getEventsByCategoryType(String type){
        return eventRepository.findByCategoryType(type);
    }

    public List<Event> searchEventsByTitle(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("(!) ERROR: El parámetro 'keyword' no puede estar vacío");
        }

        List<Event> result = eventRepository.findByTitleContainingIgnoreCase(keyword);
        if (result.isEmpty()) {
            throw new NoSuchElementException("(!) ERROR: No se encontraron eventos que coincidan con: " + keyword);
        }

        return result;
    }

    public Optional<Event> getEventsbyDate(String date){
        return eventRepository.findByDate(date);
    }

    public Optional<List<Event>> getEventsbyUserCreator(String username){
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return Optional.empty();
        }
        List<Event> events = eventRepository.findByUser(user);
        return Optional.of(events);
    }

    //public ResponseEntity<Object> updateEvent(Integer id, String title, String description, String date, String time, String venue, Event updateEvent){
    //    Optional<Event> eventOptional = eventRepository.findUpdtByTitle(title);

        //if(!eventOptional.isPresent()){
        //    return ResponseEntity.notFound().build();
        //}
        //Event existingEvent = eventOptional.get();

        //existingEvent.setTitle(updateEvent.getTitle());
        //existingEvent.setDescription(updateEvent.getDescription());
        //existingEvent.setTime(time);
        //existingEvent.setDate(date);
        //existingEvent.setVenue(venue);
        //eventRepository.save(existingEvent);
        
        //return ResponseEntity.ok(existingEvent);
    //}

    public ResponseEntity<Object> updateEvent(Integer id, Event updateEvent) {
        Optional<Event> eventOptional = eventRepository.findById(id);
    
        if (!eventOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
    
        Event existingEvent = eventOptional.get();
    
        if (updateEvent.getTitle() != null) existingEvent.setTitle(updateEvent.getTitle());
        if (updateEvent.getDescription() != null) existingEvent.setDescription(updateEvent.getDescription());
        if (updateEvent.getDate() != null) existingEvent.setDate(updateEvent.getDate());
        if (updateEvent.getTime() != null) existingEvent.setTime(updateEvent.getTime());
        if (updateEvent.getVenue() != null) existingEvent.setVenue(updateEvent.getVenue());
    
        eventRepository.save(existingEvent);
        return ResponseEntity.ok(existingEvent);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<Object> deleteEvent(Integer id) {
        Optional<Event> eventOptional = eventRepository.findById(id);

        if(!eventOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Event event = eventOptional.get();
        eventRepository.deleteById(event.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
