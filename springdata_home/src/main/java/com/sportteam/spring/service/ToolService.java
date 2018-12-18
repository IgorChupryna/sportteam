package com.sportteam.spring.service;

import com.sportteam.spring.model.Tool;
import com.sportteam.spring.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ToolService {
    @Autowired
    private ToolRepository toolRepository;

    @Transactional
    public List<Tool> getAllTools() {
        return (List<Tool>) toolRepository.findAll();
    }

    @Transactional
    public List<Tool> findByName(String name) {
        return toolRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public Tool getById(Long id) {
        return  toolRepository.findOne(id);
    }

    @Transactional
    public void deleteTool(Long toolId) {
        toolRepository.delete(toolId);
    }

    @Transactional
    public boolean addTool(Tool tool) {
        return toolRepository.save(tool) != null;
    }

    @Transactional
    public boolean updateTool(Tool tool) {
        return toolRepository.save(tool) != null;
    }
}
