package com.jackson;

import com.jackson.dao.ArticleDao;
import com.jackson.domain.Article;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-jpa.xml")
public class Query4Test {
    @Autowired
    private ArticleDao articleDao;

    /**
     * 测试jap动态查询
     */
    @Test
    public void findAllDynamic() {
        List<Article> articles = articleDao.findAll(new Specification<Article>() {
            String title = "";
            String author = "";

            /**
             *
             * @param root 实体类对象,我们可以通过他获取属性值
             * @param cq 用于生成sql语句
             * @param cb 用于拼接查询条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                // 使用cb拼接条件
                List<Predicate> list = new ArrayList<>();
                // 不是空就把条件加入到list集合中
                if (!title.isEmpty()) {
                    Predicate predicate = cb.equal(root.get("title").as(String.class), title);
                    list.add(predicate);
                }
                if (!author.isEmpty()) {
                    Predicate predicate = cb.equal(root.get("author").as(String.class), author);
                    list.add(predicate);
                }
                // 通过and连接两个条件
                return cb.and(list.toArray(new Predicate[]{}));
            }
        });
        articles.forEach(System.out::println);
    }

    /**
     * 测试jap动态分页查询
     */
    @Test
    public void findAllDynamicWithPage() {
        Page<Article> articles = articleDao.findAll(new Specification<Article>() {
            String title = "";
            String author = "";

            /**
             *
             * @param root 实体类对象,我们可以通过他获取属性值
             * @param cq 用于生成sql语句
             * @param cb 用于拼接查询条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                // 使用cb拼接条件
                List<Predicate> list = new ArrayList<>();
                // 不是空就把条件加入到list集合中
                if (!title.isEmpty()) {
                    Predicate predicate = cb.equal(root.get("title").as(String.class), title);
                    list.add(predicate);
                }
                if (!author.isEmpty()) {
                    Predicate predicate = cb.equal(root.get("author").as(String.class), author);
                    list.add(predicate);
                }
                // 通过and连接两个条件
                return cb.and(list.toArray(new Predicate[]{}));
            }
        }, PageRequest.of(0, 2, Sort.by(Sort.Order.desc("aid"))));
        articles.forEach(System.out::println);
    }

    /**
     * 测试jap动态分页排序查询
     */
    @Test
    public void findAllDynamicWithPageAndSort() {
        Page<Article> articles = articleDao.findAll(new Specification<Article>() {
            String title = "";
            String author = "";

            /**
             *
             * @param root 实体类对象,我们可以通过他获取属性值
             * @param cq 用于生成sql语句
             * @param cb 用于拼接查询条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                // 使用cb拼接条件
                List<Predicate> list = new ArrayList<>();
                // 不是空就把条件加入到list集合中
                if (!title.isEmpty()) {
                    Predicate predicate = cb.equal(root.get("title").as(String.class), title);
                    list.add(predicate);
                }
                if (!author.isEmpty()) {
                    Predicate predicate = cb.equal(root.get("author").as(String.class), author);
                    list.add(predicate);
                }
                // 通过and连接两个条件
                return cb.and(list.toArray(new Predicate[]{}));
            }
        }, PageRequest.of(0, 2, Sort.by(Sort.Order.desc("aid"))));
        articles.forEach(System.out::println);
    }
}

