package org.springsecuritynew.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AppController {

    @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
    public ModelAndView homePage(ModelMap model) {
        model.addAttribute("greeting", "Hi, Welcome to mysite. ");
        ModelAndView modelAndView = new ModelAndView("welcome");
        return modelAndView;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        ModelAndView modelAndView = new ModelAndView("admin");
        return modelAndView;
    }

    @RequestMapping(value = "/db", method = RequestMethod.GET)
    public ModelAndView dbaPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        ModelAndView modelAndView = new ModelAndView("dba");
        return modelAndView;
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ModelAndView logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        ModelAndView modelAndView = new ModelAndView("welcome");
        return modelAndView;
    }

    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public ModelAndView accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        ModelAndView modelAndView = new ModelAndView("accessDenied");
        return modelAndView;
    }

    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}



