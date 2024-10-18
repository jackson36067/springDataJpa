package com.jackson;

import com.jackson.dao.ArticleDao;
import com.jackson.domain.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-jpa.xml")
public class Query2Test {
    @Autowired
    private ArticleDao articleDao;

    /**
     * 测试通过自定义的查询方法根据title查询文章
     */
    @Test
    public void testFindByTitle() {
        List<Article> articleList = articleDao.findByTitle("java1");
        articleList.forEach(System.out::println);
    }

    /**
     * 测试通过自定义的查询方法根据title模糊查询文章
     */
    @Test
    public void testFindByTitleLike() {
        List<Article> articleList = articleDao.findByTitleLike("%java%");
        articleList.forEach(System.out::println);
    }

    /**
     * 测试通过自定义的查询方法根据title以及author查询文章
     */
    @Test
    public void testFindByTitleAndAuthor() {
        List<Article> articles = articleDao.findByTitleAndAuthor("java1", "jackson1");
        articles.forEach(System.out::println);
    }

    /**
     * 测试通过自定义的查询方法根据aid范围查询文章
     */
    @Test
    public void testFindByAidBetween() {
        List<Article> articleList = articleDao.findByAidBetween(1, 16);
        articleList.forEach(System.out::println);
    }

    /**
     * 测试通过自定义的查询方法根据aid范围查询文章
     */
    @Test
    public void testFindByAidLessThan() {
        List<Article> articleList = articleDao.findByAidIsLessThan(15); // 不包含本身
        articleList.forEach(System.out::println);
    }

    /**
     * 测试通过自定义的查询方法根据aid范围查询文章
     */
    @Test
    public void testFindByAidIn() {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 12, 15, 16);
        List<Article> articleList = articleDao.findByAidIn(list); // 不包含本身
        articleList.forEach(System.out::println);
    }

    /**
     * 测试通过自定义的查询方法查询创建时间时间在指定时间之后的文章
     */
    @Test
    public void testFindByCreateTimeAfter() {
        List<Article> articles = articleDao.findByCreateTimeAfter(LocalDateTime.of(LocalDate.now(), LocalTime.MIN));
        articles.forEach(System.out::println);
    }
}

