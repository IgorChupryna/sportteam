package com.sportteam.spring.service;


import com.sportteam.spring.model.Comment;
import com.sportteam.spring.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository<Comment> commentRepository;

    @Transactional
    public List<Comment> getAllComments() {
        return (List<Comment>) commentRepository.findAll();
    }

    @Transactional
    public List<Comment> findByName(String name) {
        return commentRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public Comment getById(Long id) {
        return  commentRepository.findOne(id);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.delete(commentId);
    }

    @Transactional
    public boolean addComment(Comment comment) {
        return commentRepository.save(comment) != null;
    }

    @Transactional
    public boolean updateComment(Comment comment) {
        return commentRepository.save(comment) != null;
    }
}
