package com.badmintonmanagement.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "details")
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detailId;
    private String name;
    @Lob
    private String value;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    public Detail(String name, String value, Article article) {
        this.name = name;
        this.value = value;
        this.article = article;
    }
    public Detail() {}

    public Long getId() {
        return detailId;
    }

    public void setId(Long detailId) {
        this.detailId = detailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
