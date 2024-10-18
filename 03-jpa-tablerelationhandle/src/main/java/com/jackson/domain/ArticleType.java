package com.jackson.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "article_type")
public class ArticleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tid;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "article_relation_type",
            // 中间表的外键对应当前表的主键的名称
            joinColumns = {@JoinColumn(name = "tid", referencedColumnName = "tid")},
            // 中间表的外键对应对方表的主键的名称
            inverseJoinColumns = {@JoinColumn(name = "aid",referencedColumnName = "aid")}
    )
    private Set<Article> article = new HashSet<Article>(0);

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Article> getArticle() {
        return article;
    }

    public void setArticle(Set<Article> article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "ArticleType{" +
                "tid=" + tid +
                ", name='" + name + '\'' +
                ", article=" + article +
                '}';
    }
}
