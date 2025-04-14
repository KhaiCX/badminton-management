package com.badmintonmanagement.controller.client;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.badmintonmanagement.entity.Content;
import com.badmintonmanagement.service.ContentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ContentService contentService;
    @GetMapping()
    public String index(Model model) {
        List<Content> contents = contentService.getListContents();
        model.addAttribute("contents", contents);
        return "client/index";
    }
}
