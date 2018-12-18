package com.sportteam.spring.controller;

import com.sportteam.spring.model.User;
import com.sportteam.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;


    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public @ResponseBody
    User getAllUsers(@PathVariable Long id) {
        return userService.getById(id);
    }

    @RequestMapping(value = "/userByName/{name}", method = RequestMethod.GET)
    public List<User> getUsereByName(@PathVariable String name) {
        return userService.findByName(name);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getAll(Model model) {
        List<User> users = null;
        String errorString = null;

        users = userService.getAllUsers();

        model.addAttribute("userList",users);
        model.addAttribute("errorString", errorString);

        return "users";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public HttpStatus deletePersnone(@PathVariable Long id) {
        userService.deleteUser(id);
        return HttpStatus.NO_CONTENT;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public HttpStatus insertUsere(@RequestBody User user) {
        return userService.addUser(user) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public HttpStatus updateUser(@RequestBody User user) {
        return userService.updateUser(user) ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST;
    }

}
