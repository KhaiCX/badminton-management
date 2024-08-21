package com.badmintonmanagement.service;

import com.badmintonmanagement.entity.Article;
import com.badmintonmanagement.entity.Detail;
import com.badmintonmanagement.entity.User;
import com.badmintonmanagement.exception.DetailNotFoundException;
import com.badmintonmanagement.repository.DetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Detail getDetailById(Long detailId) throws DetailNotFoundException {
        Optional<Detail> detailOption = detailRepository.findById(detailId);
        if (detailOption.isPresent()) {
            return detailOption.get();
        }
        else {
            throw new DetailNotFoundException("Không tìm thấy detail");
        }
    }

    public void save(Detail detail) {
        detailRepository.save(detail);
    }
}
