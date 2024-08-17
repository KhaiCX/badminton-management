package com.badmintonmanagement.controller.client;

import com.badmintonmanagement.entity.Article;
import com.badmintonmanagement.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final ArticleService articleService;
    public HomeController(ArticleService articleService) {
        this.articleService = articleService;
    }
    @GetMapping()
    public String index(Model model) {
        List<Article> articles = articleService.getAllArticles();
        if (articles.isEmpty()) {
            model.addAttribute("message", "Không có tin tức mới nhất");
        }
        model.addAttribute("articles", articles);
        return "client/index";
    }
    @GetMapping("/detail")
    public String detail() {
        return "client/detail_new";
    }
}
