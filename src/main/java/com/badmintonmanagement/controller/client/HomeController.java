package com.badmintonmanagement.controller.client;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.badmintonmanagement.entity.Article;
import com.badmintonmanagement.service.ArticleService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
    @GetMapping()
    public String index() {
        return "client/index";
    }
}
