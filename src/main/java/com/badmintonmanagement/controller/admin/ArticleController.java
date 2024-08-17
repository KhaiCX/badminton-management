package com.badmintonmanagement.controller.admin;

import com.badmintonmanagement.common.FileUploadUtil;
import com.badmintonmanagement.entity.Article;
import com.badmintonmanagement.entity.User;
import com.badmintonmanagement.service.ArticleService;
import com.badmintonmanagement.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
//@RequestMapping("/admin/articles")
public class ArticleController {
//    public static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);
//    private ArticleService articleService;
//    public ArticleController(ArticleService articleService) {
//        this.articleService = articleService;
//    }
//    @GetMapping()
//    public String getAllArticles(Model model) {
//        List<Article> articles = articleService.getAllArticles();
//        model.addAttribute("articles", articles);
//        return "admin/articles";
//    }
//    @GetMapping("/add-article")
//    public String addNewArticle(Model model) {
//        model.addAttribute("article", new Article());
//        return "admin/add_article";
//    }
//    @PostMapping("/add-article")
//    public String saveArticle(@ModelAttribute("article") Article article,
//                              BindingResult bindingResult,
//                              RedirectAttributes ra,
//                              @RequestParam("image") MultipartFile multipartFile) throws IOException {
//        if (bindingResult.hasErrors()) {
//            return "admin/articles";
//        }
//
//        if (!multipartFile.isEmpty()) {
//            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//            article.setImage(fileName);
//            Article savedArticle = articleService.save(article);
//            String uploadDir = "article-images/" + savedArticle.getId();
//            FileUploadUtil.cleanDir(uploadDir);
//            FileUploadUtil.savedFile(uploadDir, fileName, multipartFile);
//        } else {
//            if (article.getImage() == null || article.getImage().isEmpty()) {
//                article.setImage(null);
//            }
//            articleService.save(article);
//        }
//
//        ra.addFlashAttribute("message", "Bài viết đã được lưu thành công!");
//        return "redirect:/articles";
//    }
}
