package com.badmintonmanagement.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/schedules")
public class SchedulesController {
    @GetMapping
    public String schedules() {
        return "client/schedule/schedules";
    }
}
