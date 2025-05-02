package com.commitme.commit_me.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.commitme.commit_me.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer>{
    
    Event findByName(String name);
    Event findByDate(Date date);
    Optional<Event> findByTitle(String title);
    Optional<Event> findByDescription(String title);

    @Query("SELECT e FROM Event e WHERE e.category.type = :type")
    List<Event> findByCategoryType(@Param("Type") String type);

}
