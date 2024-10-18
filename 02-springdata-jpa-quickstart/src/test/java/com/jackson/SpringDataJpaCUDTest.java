package com.jackson;

import com.jackson.dao.ArticleDao;
import com.jackson.domain.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-jpa.xml")
public class SpringDataJpaCUDTest {
    @Autowired
    private ArticleDao articleDao;

    /**
     * 测试保存多个
     */
    @Test
    public void testSaveAll() {
        List<Article> list = new ArrayList<Article>();
        Article article1 = new Article();
        article1.setAuthor("jackson4");
        article1.setTitle("java4");
        article1.setCreateTime(LocalDateTime.now());
        Article article2 = new Article();
        article2.setAuthor("jackson2");
        article2.setTitle("java2");
        article2.setCreateTime(LocalDateTime.now());
        Article article3 = new Article();
        article3.setAuthor("jackson3");
        article3.setTitle("java3");
        article3.setCreateTime(LocalDateTime.now());
        Collections.addAll(list, article1, article2, article3);
        articleDao.saveAll(list);
    }

    /**
     * 测试删除一个
     */
    @Test
    public void testDeleteOne() {
        // deleteById() 根据id删除 先查询在删除
//        articleDao.deleteById(5);
        // delete(S s)通过实体类删除, 实体类必须要有主键值 直接删除
        Article article = new Article();
        article.setAid(9);
        articleDao.delete(article);
    }

    /**
     * 测试删除全部
     */
    @Test
    public void testDeleteAll() {
        // deleteAll() 先查询,再一条一条删
//        articleDao.deleteAll();
        // deleteAllInBatch() 直接清空表
        articleDao.deleteAllInBatch();
    }

    /**
     * 测试删除多个
     */
    @Test
    public void testDeleteOneMore() {
        List<Article> list = new ArrayList<>();
        Article article1 = new Article();
        article1.setAid(13);
        Article article2 = new Article();
        article2.setAid(14);
        Collections.addAll(list, article1, article2);
        // 直接删除多个
//        articleDao.deleteAllInBatch(list);
        // 先查询, 再一个一个删除
        articleDao.deleteAll(list);
    }
}
