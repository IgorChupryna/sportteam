package com.sportteam.spring.controller;

import com.sportteam.spring.model.Project;
import com.sportteam.spring.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProjectController {
    @Autowired
    ProjectService projectService;


    @RequestMapping(value = "/project/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Project getAllUsers(@PathVariable Long id) {
        return projectService.getById(id);
    }

    @RequestMapping(value = "/projectByName/{name}", method = RequestMethod.GET)
    public List<Project> getProjecteByName(@PathVariable String name) {
        return projectService.findByName(name);
    }

    @RequestMapping(value = "/project", method = RequestMethod.GET)
    public String getAll(Model model) {
        List<Project> projects = null;
        String errorString = null;

        projects = projectService.getAllProjects();

        model.addAttribute("projectList",projects);
        model.addAttribute("errorString", errorString);

        return "projects";
    }

    @RequestMapping(value = "/project/{id}", method = RequestMethod.DELETE)
    public HttpStatus deletePersnone(@PathVariable Long id) {
        projectService.deleteProject(id);
        return HttpStatus.NO_CONTENT;
    }

    @RequestMapping(value = "/project", method = RequestMethod.POST)
    public HttpStatus insertProjecte(@RequestBody Project project) {
        return projectService.addProject(project) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
    }

    @RequestMapping(value = "/project", method = RequestMethod.PUT)
    public HttpStatus updateProject(@RequestBody Project project) {
        return projectService.updateProject(project) ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST;
    }

}
