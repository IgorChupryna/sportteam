package com.sportteam.spring.repository;

import com.sportteam.spring.model.Comment;
import com.sportteam.spring.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findById(Integer id);
}
