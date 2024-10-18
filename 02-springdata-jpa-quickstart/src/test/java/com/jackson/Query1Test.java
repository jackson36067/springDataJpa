package com.jackson;

import com.jackson.dao.ArticleDao;
import com.jackson.domain.Article;
import net.bytebuddy.TypeCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-jpa.xml")
public class Query1Test {
    @Autowired
    private ArticleDao articleDao;

    /**
     * 测试通过id查询
     */
    @Test
    public void testFindById() {
        // 通过id查询一个文章
        Optional<Article> optional = articleDao.findById(12);
        System.out.println(optional.get());

        // 通过id查询多个文章 执行一条sql语句
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 12, 15, 16);
        List<Article> articles = articleDao.findAllById(list);
        System.out.println(articles);
    }

    /**
     * 测试查询全部
     */
    @Test
    public void testFindAll() {
        List<Article> articles = articleDao.findAll();
        System.out.println(articles);
    }

    /**
     * 测试查询全部且排序
     */
    @Test
    public void testFindAllWithSort() {
        Sort sort = Sort.by(Sort.Order.desc("aid"));
        List<Article> articles = articleDao.findAll(sort);
        articles.forEach(System.out::println);
    }

    /**
     * 测试分页查询
     */
    @Test
    public void testFindAllWithPageable() {
        // 通过PageRequest中的of方法创建Pageable对象
        // 其中pageNumber从0开始表示第一页
        Pageable pageable = PageRequest.of(0, 2);
        Page<Article> page = articleDao.findAll(pageable);
        // 总记录数 总页数 每页记录数
        System.out.println("总记录数: "+page.getTotalElements());
        System.out.println("总页数: "+page.getTotalPages());
        System.out.println("每页记录数: "+page.getSize());
        // 当前页元素
        List<Article> pageContent = page.getContent();
        pageContent.forEach(System.out::println);
    }

    /**
     * 查询分页且排序
     */
    @Test
    public void testFindWithSortAndPage() {
        Pageable pageable = PageRequest.of(0, 2, Sort.by(Sort.Order.desc("aid")));
        Page<Article> page = articleDao.findAll(pageable);
        // 当前页元素
        List<Article> pageContent = page.getContent();
        pageContent.forEach(System.out::println);
    }
}

