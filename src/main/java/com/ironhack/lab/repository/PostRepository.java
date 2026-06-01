package com.ironhack.lab.repository;

import com.ironhack.lab.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
