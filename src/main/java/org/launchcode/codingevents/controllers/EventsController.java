package org.launchcode.codingevents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventsController {
    private static List<String> events = new ArrayList<>();

    @GetMapping
    public String displayAllEvents(Model model){
        model.addAttribute("events", events);
        return "events/events/index";
    }

    //lives at /events/create
    @GetMapping("create")
    public String renderCreateEventForm(){
        return "events/events/create";
    }

    //lives at /events/create. it can be names the same as the method about it because they handle different types of request
    @PostMapping("create")
    public String createEvent(@RequestParam String eventName )
    {
    events.add(eventName);
    //redirects people to the root path of the specific controler
    return "redirect:";
    }






}
