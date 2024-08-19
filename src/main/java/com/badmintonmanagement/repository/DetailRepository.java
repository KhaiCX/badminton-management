package com.badmintonmanagement.repository;

import com.badmintonmanagement.entity.Article;
import com.badmintonmanagement.entity.Detail;
import com.badmintonmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Long> {
    @Query("select d from Detail d where d.article = :article and d.name = :name")
    List<Detail> findAllByArticleAndName(Article article, String name);
    @Query("select d from Detail d where d.user = :user and d.type = :type")
    List<Detail> findAllByUser(User user, String type);
}
