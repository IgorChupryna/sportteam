package com.sportteam.spring.controller;

import com.sportteam.spring.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.sportteam.spring.controller.SecurityController.getLoginName;


@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private SecurityService securityService;

    @RequestMapping
    public String loginPage(Model model) {
        model.addAttribute("login", getLoginName());
        return "home";
    }




}
