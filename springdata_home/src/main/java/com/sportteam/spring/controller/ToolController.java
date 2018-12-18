package com.sportteam.spring.controller;

import com.sportteam.spring.model.Tool;
import com.sportteam.spring.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ToolController {
    @Autowired
    ToolService toolService;


    @RequestMapping(value = "/tool/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Tool getAllUsers(@PathVariable Long id) {
        return toolService.getById(id);
    }

    @RequestMapping(value = "/toolByName/{name}", method = RequestMethod.GET)
    public List<Tool> getTooleByName(@PathVariable String name) {
        return toolService.findByName(name);
    }

    @RequestMapping(value = "/tool", method = RequestMethod.GET)
    public String getAll(Model model) {
        List<Tool> tools = null;
        String errorString = null;

        tools = toolService.getAllTools();

        model.addAttribute("toolList",tools);
        model.addAttribute("errorString", errorString);

        return "tools";
    }

    @RequestMapping(value = "/tool/{id}", method = RequestMethod.DELETE)
    public HttpStatus deletePersnone(@PathVariable Long id) {
        toolService.deleteTool(id);
        return HttpStatus.NO_CONTENT;
    }

    @RequestMapping(value = "/tool", method = RequestMethod.POST)
    public HttpStatus insertToole(@RequestBody Tool tool) {
        return toolService.addTool(tool) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
    }

    @RequestMapping(value = "/tool", method = RequestMethod.PUT)
    public HttpStatus updateTool(@RequestBody Tool tool) {
        return toolService.updateTool(tool) ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST;
    }


}
