package com.jackson.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity // 告诉jpa这是一个实体类对象,需要把它与数据库表进行映射
@Table(name="article")
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

    public Article() {
    }

    public Article(Integer aid, String author, LocalDateTime createTime, String title) {
        this.aid = aid;
        this.author = author;
        this.createTime = createTime;
        this.title = title;
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
