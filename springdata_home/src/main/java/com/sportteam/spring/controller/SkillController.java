package com.sportteam.spring.controller;

import com.sportteam.spring.model.Skill;
import com.sportteam.spring.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SkillController {
    
    @Autowired
    SkillService skillService;

    @RequestMapping(value = "/skill/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Skill getAllUsers(@PathVariable Long id) {
        return skillService.getById(id);
    }

    @RequestMapping(value = "/skillByName/{name}", method = RequestMethod.GET)
    public List<Skill> getSkilleByName(@PathVariable String name) {
        return skillService.findByName(name);
    }

    @RequestMapping(value = "/skill", method = RequestMethod.GET)
    public String getAll(Model model) {
       // model.addAttribute("login", SecurityController.getLoginName());
        List<Skill> skills = null;
        String errorString = null;

        skills = skillService.getAllSkills();

        model.addAttribute("skillList",skills);
        model.addAttribute("errorString", errorString);

        return "skills";
    }

    @RequestMapping(value = "/skill/{id}", method = RequestMethod.DELETE)
    public HttpStatus deletePersnone(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return HttpStatus.NO_CONTENT;
    }

    @RequestMapping(value = "/skill", method = RequestMethod.POST)
    public HttpStatus insertSkille(@RequestBody Skill skill) {
        return skillService.addSkill(skill) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
    }

    @RequestMapping(value = "/skill", method = RequestMethod.PUT)
    public HttpStatus updateSkill(@RequestBody Skill skill) {
        return skillService.updateSkill(skill) ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST;
    }
    
    
    
}
