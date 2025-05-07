package com.commitme.commit_me.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.commitme.commit_me.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User  findByEmail(String email);
    User findByUsername(String username);
    Optional<User> getUserById(Integer id);

    Optional<User> findByUsernameAndPassword(String username, String password);
} 