package com.badmintonmanagement.controller.client;

import com.badmintonmanagement.entity.Content;
import com.badmintonmanagement.service.ContentService;
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
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ContentService contentService;
    @GetMapping()
    public String index(Model model) {
        List<Content> contents = contentService.getListContents();
        LocalDate nextSunday = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        LocalDateTime endTime = LocalDateTime.of(nextSunday, LocalTime.of(14,0));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        model.addAttribute("contents", contents);
        model.addAttribute("endTime", endTime.format(formatter));
        return "client/index";
    }
}
