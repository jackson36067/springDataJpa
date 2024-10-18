package com.jackson.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "article_comment")
public class ArticleComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;
    @Column(name = "comment")
    private String comment;

    @ManyToOne()
    @JoinColumn(name = "article_id", referencedColumnName = "aid", unique = false)
    private Article article;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
