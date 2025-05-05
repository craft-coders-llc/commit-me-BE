package com.commitme.commit_me.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.commitme.commit_me.model.Category;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

    Category findByType(String type);
    Optional<Category> findById(Integer id);

}
