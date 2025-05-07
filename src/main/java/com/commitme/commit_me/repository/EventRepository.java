package com.commitme.commit_me.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.commitme.commit_me.model.Category;
import com.commitme.commit_me.model.Event;
import com.commitme.commit_me.model.User;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer>{
    
    List<Event> findByUser(User user);
    Optional<Event> findByDescription(String title);
    Optional<Event> findByDate(String date);
    List<Event> findByCategory(Category category);
    List<Event> findByCategoryType(String type);
    List<Event> findByTitle(String title);
    List<Event> findByTitleContainingIgnoreCase(String keyword);

}
