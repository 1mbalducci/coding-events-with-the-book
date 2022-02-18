package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.Models.Event;
import org.launchcode.codingevents.data.EventData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventsController {

    @GetMapping
    public String displayAllEvents(Model model){
        model.addAttribute("title","All Events");
        model.addAttribute("events", EventData.getAll());
        return "events/events/index";
    }

    //lives at /events/create
    @GetMapping("create")
    public String displayCreateEventForm(Model model){
        model.addAttribute("title","Create Event");
        model.addAttribute(new Event());
        return "events/events/create";
    }

    //lives at /events/create. it can be names the same as the method about it because they handle different types of request
    @PostMapping("create")
    //@ModelAttribute allows you to not have to put in tons of @RequestParam's
    //instead you can just reference the models/event file and it will take those parameters
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model)
    {
        if (errors.hasErros()) {
            model.addAttribute("title","Create Event");
            return "events/events/create";


        }
    EventData.add(newEvent);
    //redirects people to the root path of the specific controler
    return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm (Model model){
        model.addAttribute("title","Delete Events");
        model.addAttribute("events",EventData.getAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {

        if (eventIds != null) {
            for (int id : eventIds) {
                EventData.remove(id);
            }
        }
        return "redirect:";
    }
}
