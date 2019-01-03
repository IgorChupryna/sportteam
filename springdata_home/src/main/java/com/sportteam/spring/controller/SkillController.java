package com.sportteam.spring.controller;

import com.sportteam.spring.model.Security;
import com.sportteam.spring.model.Skill;
import com.sportteam.spring.service.SecurityService;
import com.sportteam.spring.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.sportteam.spring.controller.SecurityController.getLoginName;

@Controller
public class SkillController {
    @Autowired
    SecurityService securityService;
    @Autowired
    SkillService skillService;

    @RequestMapping(value = "/skill/{id}", method = RequestMethod.GET)
     @ResponseBody
    public String getSkillById(Model model,@PathVariable Long id) {
        model.addAttribute("login", getLoginName());
        List<Skill> skills = null;
        String errorString = null;

        skills.add(skillService.getById(id));

        model.addAttribute("skillList",skills);
        model.addAttribute("errorString", errorString);

        return "skills";
    }

    @RequestMapping(value = "/skillByName/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String getSkillByName(Model model,@PathVariable String name) {
        model.addAttribute("login", getLoginName());
        List<Skill> skills = null;
        String errorString = null;

        skills = skillService.findByName(name);

        model.addAttribute("skillList",skills);
        model.addAttribute("errorString", errorString);

        return "skills";
    }

    @RequestMapping(value = "/skill", method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("login", getLoginName());

        List<Skill> skills = null;
        String errorString = null;

        skills = skillService.getAllSkills();

        model.addAttribute("skillList",skills);
        model.addAttribute("errorString", errorString);

        return "skills";
    }



    @RequestMapping(value = "/skill/delete", method = RequestMethod.POST)
    public String delete(Model model, @RequestParam(required = false) Long id, @RequestParam(required = false) String name) {
        model.addAttribute("login", getLoginName());

        List<Skill> skills = null;
        String errorString = null;

        skillService.deleteSkill(id);

        skills = skillService.getAllSkills();

        model.addAttribute("skillList",skills);
        model.addAttribute("errorString", errorString);

        return "skills";
    }

    @RequestMapping(value = "/skill/update", method = RequestMethod.POST)
    public String update(Model model, @RequestParam(required = false) Long id, @RequestParam(required = false) String name) {
        model.addAttribute("login", getLoginName());

        List<Skill> skills = null;
        String errorString = null;

        Skill skill = skillService.getById(id);
        skill.setName(name);

        skillService.updateSkill(skill);


        skills = skillService.getAllSkills();

        model.addAttribute("skillList",skills);
        model.addAttribute("errorString", errorString);

        return "skills";
    }

    @RequestMapping(value = "/skill/insert", method = RequestMethod.POST)
    public String insert(Model model, @RequestParam(required = false) Long id,
                         @RequestParam(required = false) String name0) {
        model.addAttribute("login", getLoginName());


        String errorString = null;

        List<Skill> skills = null;



        Skill skill = new Skill();
        skill.setName(name0);
        skillService.addSkill(skill);

        skills = skillService.getAllSkills();

        model.addAttribute("skillList",skills);
        model.addAttribute("errorString", errorString);

        return "skills";
    }
    
}
