package com.commitme.commit_me.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.commitme.commit_me.exceptions.EventDescriptionAlreadyExistsException;
import com.commitme.commit_me.exceptions.EventNotFoundException;
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

    public ResponseEntity<Object> createEvent(Event event, String title, String description, LocalDate date, LocalTime time, String venue, Integer userId, Integer categoryId) { //MIRAR QUE ESTÉ BIEN EL METODO FINDBYID DE USER
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);

        if (event.getTitle() != null && eventRepository.findByTitle(event.getTitle()).isPresent()) {
            throw new EventTitleAlreadyExistsException("(!) ERROR: ya existe un evento con el mismo título");
        }

        if (event.getDescription() != null && eventRepository.findByDescription(event.getDescription()).isPresent()){
            throw new EventDescriptionAlreadyExistsException("(!) ERROR: ya existe un evento con la misma descripción");
        }

        event.setUser(userOptional.get());
        event.setCategory(categoryOptional.get());
        return new ResponseEntity<>(eventRepository.save(event), HttpStatus.CREATED);
    }

    public List<Event> getAllEvents() {
        return this.eventRepository.findAll();
    }

    // public ResponseEntity<Object> getEventByCategoryType(String type) {
    //     Optional<Category> categoryOptional = categoryRepository.findByCategoryType(type);
    //     if(!categoryOptional.isPresent()) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     Category category = categoryOptional.get();
    //     return ResponseEntity.ok(category);
    // }

    public List<Event> getEventsByCategoryType(String type){
        return eventRepository.findByCategoryType(type);
    }

    public Optional<Event> findbyTitle(String title){
        return eventRepository.findByTitle(title);
    }

    public ResponseEntity<Object> updateEvent(Integer id, String title, String description, LocalDate date, LocalTime time, String venue, Event updateEvent){
        Optional<Event> eventOptional = Optional.ofNullable(eventRepository.findByTitle(title)
            .orElseThrow(() -> new EventNotFoundException("(!) ERROR: no se ha encontrado ningún evento con el título")));

        if(!eventOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Event existingEvent = eventOptional.get();

        existingEvent.setTitle(updateEvent.getTitle());
        existingEvent.setDescription(updateEvent.getDescription());
        existingEvent.setTime(time);
        existingEvent.setDate(date);
        existingEvent.setVenue(venue);
        eventRepository.save(existingEvent);
        
        return ResponseEntity.ok(existingEvent);

    }

}
