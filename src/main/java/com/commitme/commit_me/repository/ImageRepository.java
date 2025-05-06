package com.commitme.commit_me.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.commitme.commit_me.model.Image;


public interface ImageRepository extends JpaRepository<Image, Integer> {
Optional<Image> findByName(String name);
}
