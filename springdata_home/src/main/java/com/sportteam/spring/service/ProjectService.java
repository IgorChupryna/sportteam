package com.sportteam.spring.service;

import com.sportteam.spring.model.Project;
import com.sportteam.spring.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository<Project> projectRepository;

    @Transactional
    public List<Project> getAllProjects() {
        return (List<Project>) projectRepository.findAll();
    }

    @Transactional
    public List<Project> findByName(String name) {
        return projectRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public Project getById(Long id) {
        return  projectRepository.findOne(id);
    }

    @Transactional
    public void deleteProject(Long projectId) {
        projectRepository.delete(projectId);
    }

    @Transactional
    public boolean addProject(Project project) {
        return projectRepository.save(project) != null;
    }

    @Transactional
    public boolean updateProject(Project project) {
        return projectRepository.save(project) != null;
    }
}
