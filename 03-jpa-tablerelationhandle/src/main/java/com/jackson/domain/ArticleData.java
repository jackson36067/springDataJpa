package com.jackson.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "article_data")
public class ArticleData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "content")
    private String content;
    @OneToOne()
    // @JoinColumn
    // name 指定该表中的逻辑外键字段名
    // referencedColumnName 指定对方表的主键名
    @JoinColumn(name = "article_id", referencedColumnName = "aid", unique = true)
    private Article article;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article articleData) {
        this.article = articleData;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        return "ArticleData{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}