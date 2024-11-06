package com.badmintonmanagement.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

@Controller
@RequiredArgsConstructor
public class HomeController {
    @GetMapping()
    public String index(Model model) {
        LocalDate nextSunday = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        LocalDateTime endTime = LocalDateTime.of(nextSunday, LocalTime.of(14,0));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        model.addAttribute("endTime", endTime.format(formatter));
        return "client/index";
    }
}
