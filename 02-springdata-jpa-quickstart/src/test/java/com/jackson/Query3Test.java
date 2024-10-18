package com.jackson;

import com.jackson.dao.ArticleDao;
import com.jackson.domain.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public class Query3Test {
    @Autowired
    private ArticleDao articleDao;

    /**
     * 测试通过jpql语句自定义的查询方法根据title以及author查询文章
     */
    @Test
    public void testFindByCondition1() {
        List<Article> articles = articleDao.findByCondition1("java1", "jackson1");
        articles.forEach(System.out::println);
    }

    /**
     * 测试通过jpql语句自定义的查询方法根据title以及author查询文章
     */
    @Test
    public void testFindByCondition2() {
        List<Article> articles = articleDao.findByCondition2("java1", "jackson1");
        articles.forEach(System.out::println);
    }

    /**
     * 测试通过jpql语句自定义的查询方法根据title模糊查询文章
     */
    @Test
    public void testFindByCondition3() {
        List<Article> articles = articleDao.findByCondition3("java");
        articles.forEach(System.out::println);
    }

    /**
     * 测试通过jpql语句自定义的查询方法根据title模糊查询文章且根据id倒序排序
     */
    @Test
    public void testFindByCondition4() {
        List<Article> articles = articleDao.findByCondition4("java");
        articles.forEach(System.out::println);
    }

    /**
     * 测试通过jpql语句自定义的查询方法根据title模糊分页查询文章
     */
    @Test
    public void testFindByCondition5() {
        Pageable pageable = PageRequest.of(0, 2);
        List<Article> articles = articleDao.findByCondition5(pageable, "java");
        articles.forEach(System.out::println);
    }

    /**
     * 测试通过jpql语句自定义的查询方法根据id查询
     */
    @Test
    public void testFindByCondition6() {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 12, 15);
        List<Article> articles = articleDao.findByCondition6(list);
        articles.forEach(System.out::println);
    }

    @Test
    public void testFindByCondition7() {
        Article article = new Article();
        article.setTitle("java1");
        article.setAuthor("jackson1");
        List<Article> articles = articleDao.findByCondition7(article);
        articles.forEach(System.out::println);
    }
}

