package com.jackson.dao;

import com.jackson.domain.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

// 遵循springdatajpa的规范,让到层接口继承JpaRepository<实体类,表字段中主键的类型>以及JpaSpecificationExecutor<实体类>,
// 最高继承接口Repositories是一个标记接口(没有抽象方法),作用是子接口的实现类可以自动被spring IOC 容器所示别,以及可以在该接口中定义一些指定的规范的方法
public interface ArticleDao extends JpaRepository<Article, Integer>, JpaSpecificationExecutor<Article> {
    /*
    根据规范自定义查询方法
     */
    // 根据标题查询
    public List<Article> findByTitle(String title);

    // 根据标题模糊查询
    public List<Article> findByTitleLike(String title);

    // 根据标题和作者查询
    public List<Article> findByTitleAndAuthor(String title, String author);

    // 根据id范围查询1
    public List<Article> findByAidBetween(Integer begin, Integer end);

    // 根据id范围查询2
    public List<Article> findByAidIsLessThan(Integer aid);

    // 根据id范围查询3
    public List<Article> findByAidIn(List<Integer> aids);

    // 根据创建时间之后查询
    public List<Article> findByCreateTimeAfter(LocalDateTime createTime);



    /*
    使用jpql(java persistence query language)语句自定义语句
    类似于sql语句,但是要使用实体类代替表名,使用属性名代替字段名[面向对象查询]
     */

    // 展示位置参数绑定 [根据title以及author查询]
    // ?表示占位符,从1开始
    @Query("from Article a where a.title = ?1 and a.author = ?2")
    List<Article> findByCondition1(String title, String author);
    // 展示名字参数绑定
    // 其中:参数名表示占位符与@param中写入的名字相联系
    @Query("from Article a where a.title = :title and a.author = :authors")
    List<Article> findByCondition2(@Param("title") String title,@Param("authors") String author);
    // 展示like模糊查询
    @Query("from Article a where a.title like %:title%")
    List<Article> findByCondition3(@Param("title") String title);
    // 展示排序查询
    @Query("from Article a where a.title like %:title% order by a.aid desc ")
    List<Article> findByCondition4(@Param("title") String title);
    // 展示分页查询
    @Query("from Article a where a.title like %:title%")
    List<Article> findByCondition5(Pageable pageable, @Param("title") String title);
    // 展示传入集合参数查询
    @Query("from Article a where a.aid in :aids")
    List<Article> findByCondition6(@Param("aids") List<Integer> aids);
    // 展示传入bean进行查询(SPEL表达式查询,参数为一个对象)
    @Query("from Article a where a.title = :#{#article.title} and a.author = :#{#article.author}")
    List<Article> findByCondition7(@Param("article")Article article);
}
