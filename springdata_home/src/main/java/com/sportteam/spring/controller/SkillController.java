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

import java.util.List;

import static com.sportteam.spring.controller.SecurityController.getLoginName;

@Controller
public class SkillController {
    @Autowired
    SecurityService securityService;
    @Autowired
    SkillService skillService;

    @RequestMapping(value = "/skill/{id}", method = RequestMethod.GET)
    public @ResponseBody
    String getSkillById(Model model,@PathVariable Long id) {
        model.addAttribute("login", getLoginName());
        List<Skill> skills = null;
        String errorString = null;

        skills.add(skillService.getById(id));

        model.addAttribute("skillList",skills);
        model.addAttribute("errorString", errorString);

        return "skills";
    }

    @RequestMapping(value = "/skillByName/{name}", method = RequestMethod.GET)
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

    @RequestMapping(value = "/skill/{id}", method = RequestMethod.DELETE)
    public HttpStatus deletePersnone(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return HttpStatus.NO_CONTENT;
    }

    @RequestMapping(value = "/skill", method = RequestMethod.POST)
    public String update(@RequestParam(required = false) String email, @RequestParam(required = false) String phone) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();
        Security securityUser = securityService.getUserByLogin(login);
        securityUser.setEmail(email);
        securityUser.setPhone(phone);
        return "redirect:/";
    }

    @RequestMapping(value = "/skill", method = RequestMethod.PUT)
    public HttpStatus updateSkill(@RequestBody Skill skill) {
        return skillService.updateSkill(skill) ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST;
    }
    
    
    
}
