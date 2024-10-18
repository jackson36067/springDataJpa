package com.jackson.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity // 告诉jpa这是一个实体类对象,需要把它与数据库表进行映射
@Table(name = "article")
public class Article {
    @Id // 标识这个字段是主键字段
    @Column(name = "aid")
    // 指定主键id的生成策略,GenerationType.IDENTITY对应mysql中的主键自增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer aid;
    @Column(name = "author")
    private String author;
    @Column(name = "create_time")
    private LocalDateTime createTime;
    @Column(name = "title")
    private String title;
    // 让这个实体维护关系
    // 先声明类的关系 article - article_data 表为一对一关系
    // 声明主动放弃关系维护 mapperBy = "当前实体在对方类中的属性名"
    // cascade = CascadeType.ALL 操作article表时同时操作article_data表
    @OneToOne(mappedBy = "article", cascade = CascadeType.ALL) // 声明表之间的关系
    private ArticleData articleData;

    @OneToMany(mappedBy = "article")
    private Set<ArticleComment> articleComments = new HashSet<ArticleComment>(0);
    @ManyToMany(mappedBy = "article")
    private Set<ArticleType> articleTypes = new HashSet<ArticleType>(0);

    public Set<ArticleComment> getArticleComments() {
        return articleComments;
    }

    public void setArticleComments(Set<ArticleComment> articleComments) {
        this.articleComments = articleComments;
    }

    public ArticleData getArticleData() {
        return articleData;
    }

    public void setArticleData(ArticleData articleData) {
        this.articleData = articleData;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<ArticleType> getArticleTypes() {
        return articleTypes;
    }

    public void setArticleTypes(Set<ArticleType> articleTypes) {
        this.articleTypes = articleTypes;
    }

    @Override
    public String toString() {
        return "Article{" +
                "aid=" + aid +
                ", author='" + author + '\'' +
                ", createTime=" + createTime +
                ", title='" + title + '\'' +
                '}';
    }
}
