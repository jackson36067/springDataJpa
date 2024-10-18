package com.jackson;

import com.jackson.dao.ArticleDao;
import com.jackson.domain.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-jpa.xml")
public class SpringDataJpaTest {
    @Autowired
    private ArticleDao articleDao;

    /**
     * 测试新增
     */
    @Test
    public void testSave() {
        Article article = new Article();
        article.setAuthor("jackson1");
        article.setTitle("java1");
        article.setCreateTime(LocalDateTime.now());
        articleDao.save(article);
    }

    /**
     * 测试根据id查询
     */
    @Test
    public void testFindById() {
        Optional<Article> optional = articleDao.findById(1);
        System.out.println(optional.get());
    }

    /**
     * 测试查询全部
     */
    @Test
    public void testFindAll() {
        List<Article> all = articleDao.findAll();
        System.out.println(all);
    }

    /**
     * 测试修改
     */
    @Test
    public void testUpdate() {
        /**
         * 新增以及修改操作都是save方法,当实体类有主键值时,为修改操作,当实体类没有主键值时,为新增操作
         */
        Article article = new Article();
        article.setAid(2);
        article.setAuthor("jackson2");
        article.setTitle("java2");
        articleDao.save(article);
    }

    /**
     * 测试根据id删除
     */
    @Test
    public void testDelete() {
        articleDao.deleteById(2);
    }
}
