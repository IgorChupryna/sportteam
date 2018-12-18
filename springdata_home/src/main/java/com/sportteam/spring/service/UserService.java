package com.sportteam.spring.service;

import com.sportteam.spring.model.User;
import com.sportteam.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Transactional
    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public User getById(Long id) {
        return  userRepository.findOne(id);
    }

    @Transactional
    public void deleteUser(Long userId) {
        userRepository.delete(userId);
    }

    @Transactional
    public boolean addUser(User user) {
        return userRepository.save(user) != null;
    }

    @Transactional
    public boolean updateUser(User user) {
        return userRepository.save(user) != null;
    }
}
