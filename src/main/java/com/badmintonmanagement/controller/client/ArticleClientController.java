package com.badmintonmanagement.controller.client;

import com.badmintonmanagement.entity.Article;
import com.badmintonmanagement.entity.Detail;
import com.badmintonmanagement.exception.ArticleNotFoundException;
import com.badmintonmanagement.service.ArticleService;
import com.badmintonmanagement.service.DetailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleClientController {
    private final ArticleService articleService;
    private final DetailService detailService;
    public ArticleClientController(ArticleService articleService, DetailService detailService) {
        this.articleService = articleService;
        this.detailService = detailService;
    }
    @GetMapping()
    public String articles(Model model) {
        List<Article> articles = articleService.getAllArticles();
        String message = "";
        if (articles.isEmpty()) {
            message = "Không có tin tức mới nhất";
        }
        model.addAttribute("message", message);
        model.addAttribute("articles", articles);
        return "client/article/articles";
    }
    @GetMapping("/detail/{articleId}")
    public String detail(@PathVariable Long articleId, Model model) {
        try {
            Article article = articleService.getArticleById(articleId);
            List<Detail> contents = detailService.getAllByArticle(article, "content");
            List<Detail> images = detailService.getAllByArticle(article, "image");
            int index = contents.size() / 2;
            List<Detail> contents1 = contents.subList(0, index);
            List<Detail> contents2 = contents.subList(index, contents.size());
            model.addAttribute("article", article);
            model.addAttribute("contents1", contents1);
            model.addAttribute("contents2", contents2);
            model.addAttribute("images", images);
            return "client/article/detail_article";
        } catch(ArticleNotFoundException ex) {
            model.addAttribute("message", ex.getMessage());
            return "error/500";
        }

    }
}
