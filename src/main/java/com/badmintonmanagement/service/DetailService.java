package com.badmintonmanagement.service;

import com.badmintonmanagement.entity.Article;
import com.badmintonmanagement.entity.Detail;
import com.badmintonmanagement.entity.User;
import com.badmintonmanagement.repository.DetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailService {
    private final DetailRepository detailRepository;
    public DetailService(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }

    public List<Detail> getAllByArticle(Article article, String name) {
        return detailRepository.findAllByArticleAndName(article, name);
    }
    public List<Detail> getAllByUser(User user, String type) {
        return detailRepository.findAllByUser(user, type);
    }
}
