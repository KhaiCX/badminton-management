package com.badmintonmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "details")
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detailId;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String value;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String type;

    public Detail(String name, String value, Article article, String type) {
        this.name = name;
        this.value = value;
        this.article = article;
        this.type = type;
    }
    public Detail(String name, String value, User user, String type) {
        this.name = name;
        this.value = value;
        this.user = user;
        this.type = type;
    }
    public Detail() {}

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

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
