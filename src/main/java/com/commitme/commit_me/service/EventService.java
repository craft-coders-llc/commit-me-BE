package com.commitme.commit_me.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public ResponseEntity<Object> createEvent(Event event, Integer userId, Integer categoryId) { //MIRAR QUE ESTÉ BIEN EL METODO FINDBYID DE USER
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);

        if (event.getTitle() != null && eventRepository.findByTitle(event.getTitle()).isPresent()) {
            throw new EventTitleAlreadyExistsException("(!) ERROR: ya existe un evento con el mismo título");
        }

        if (event.getDescription() != null && eventRepository.findByDescription(event.getDescription()).isPresent()){
            throw new EventDescriptionAlreadyExistsException("(!) ERROR: ya existe un evento con la misma descripción");
        }

        return new ResponseEntity<>(eventRepository.save(event), HttpStatus.CREATED);
    }

    public List<Event> getAllEvents() {
        return this.eventRepository.findAll();
    }

    

}
