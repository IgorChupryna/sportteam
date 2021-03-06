package com.sportteam.spring.controller;

import com.sportteam.spring.model.Security;
import com.sportteam.spring.model.UserRole;
import com.sportteam.spring.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class SecurityController {
    @Autowired
    SecurityService securityService;


    @RequestMapping(value = { "/home","/login" }, method = RequestMethod.GET)
    public String homePage(Model model) {
        model.addAttribute("login", getLoginName());
        return "home";
    }



    @RequestMapping(value = {  "/other" }, method = RequestMethod.GET)
    public String otherPage(Model model) {
        //model.addAttribute("login", getLoginName());
        return "other";
    }


    @RequestMapping("/unauthorized")
    public String unauthorized(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       // model.addAttribute("login", user.getUsername());
        return "unauthorized";
    }


    @RequestMapping(value = "/update")
    public String update(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();

        Security dbUser = securityService.getUserByLogin(login);

        model.addAttribute("login", login);
        model.addAttribute("roles", user.getAuthorities());
        model.addAttribute("email", dbUser.getEmail());
        model.addAttribute("phone", dbUser.getPhone());

        return "update";
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam(required = false) String email, @RequestParam(required = false) String phone) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();

        Security dbUser = securityService.getUserByLogin(login);
        dbUser.setEmail(email);
        dbUser.setPhone(phone);

        securityService.updateSecurity(dbUser);

        return "redirect:/";
    }


    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
    public String update(@RequestParam String login,
                         @RequestParam String password,
                         @RequestParam(required = false) String email,
                         @RequestParam(required = false) String phone,
                         Model model) {
        if (securityService.existsByLogin(login)) {
            model.addAttribute("exists", true);
            return "register";
        }

        ShaPasswordEncoder encoder = new ShaPasswordEncoder();
        String passHash = encoder.encodePassword(password, null);

        Security security = new Security(login, passHash, UserRole.USER, email, phone);
        securityService.addSecurity(security);

        return "redirect:/";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }



    public static String getLoginName(){
        String login = null;
        Object sc = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = null;
        if (sc instanceof User) {
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            login = user.getUsername();
        }
        return login;
    }



}
