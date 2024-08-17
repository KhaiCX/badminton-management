package com.badmintonmanagement.service;

import com.badmintonmanagement.exception.ArticleNotFoundException;
import com.badmintonmanagement.entity.Article;
import com.badmintonmanagement.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article save(Article article) {
        return articleRepository.save(article);
    }
    public Article getArticleById(Long articleId) throws ArticleNotFoundException {
        Optional<Article> article = articleRepository.findById(articleId);
        if (article.isPresent()) {
            return article.get();
        } else {
            throw new ArticleNotFoundException("Không tìm thấy bài đăng nào!!!");
        }
    }
}
