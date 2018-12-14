package com.sportteam.spring.repository;

import com.sportteam.spring.model.Comment;
import com.sportteam.spring.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository<P> extends JpaRepository<Comment,Long> {
    List<Comment> findByName(String name);
}
