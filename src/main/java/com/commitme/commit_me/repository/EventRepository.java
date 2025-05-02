package com.commitme.commit_me.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.commitme.commit_me.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer>{
    
    Event findByName(String name);
    Event findByDate(Date date);
    Optional<Event> findByTitle(String title);
    Optional<Event> findByDescription(String title);

}
