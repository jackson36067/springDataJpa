package com.jackson;

import com.jackson.domain.Article;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class JpaTest {

    /**
     * 测试使用jpa保存文章对象数据到数据库中
     */
    @Test
    public void testSave() {
        Article article = new Article();
        article.setAuthor("jackson");
        article.setTitle("jpa");
        article.setCreateTime(LocalDateTime.now());

        // 使用jpa将对象保存到数据库中
        // 1. 创建持久化管理器工厂
        String PersistenceUnitName = "jpa01"; // 根据名称读取数据库配置
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PersistenceUnitName);
        // 2.创建持久化管理器 (这个API是最重要的api,我们可以基于此API完成获取事务以及对数据库的CRUD的操作)
        EntityManager entityManager = factory.createEntityManager();
        // 3.得到事务,开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        // 4.操作 新增文章对象数据
        entityManager.persist(article);
        // 5.提交事务
        transaction.commit();
        // 6.释放资源
        entityManager.close();
        // 工厂不需要关闭
//        factory.close();
    }

    /**
     * 测试使用jpa获取数据库中的文章数据
     */
    @Test
    public void testFind() {
        String PersistenceUnitName = "jpa01";
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PersistenceUnitName);
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        // 4.操作 查询文章数据库
        Article article = entityManager.find(Article.class, 1);
        System.out.println(article);
        transaction.commit();
        entityManager.close();
        // 工厂不需要关闭
//        factory.close();
    }

    @Test
    public void testUpdate() {
        String PersistenceUnitName = "jpa01"; // 根据名称读取数据库配置
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PersistenceUnitName);
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        // 4.操作 修改文章数据 必须先查询,然后再修改
        Article article = entityManager.find(Article.class, 1);
        article.setTitle("jpa-update");
        article.setAuthor("curry");
        article.setCreateTime(LocalDateTime.now());
        transaction.commit();
        entityManager.close();
        // 工厂不需要关闭
//        factory.close();
    }

    @Test
    public void testDelete() {
        // jpa常见API
        // Persistence 通过读取持久化单元名称, 根据配置创建持久化管理器工厂
        // EntityManagerFactory 这是一个工厂类, 目的是为了创建EntityManager, 对于这种工厂类, 他的创建以及销毁是很耗费资源的, 在一个工程中一般维持一个就好(单例)
        // EntityManager 基于此API可以完成对数据库表的CRUD操作
        // EntityTransaction 进行事务管理 begin commit rollback

        String PersistenceUnitName = "jpa01"; // 根据名称读取数据库配置
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PersistenceUnitName);
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        // 4.操作 删除文章数据库中的数据 必须先查询,再删除
        Article article = entityManager.find(Article.class, 1);
        entityManager.remove(article);
        transaction.commit();
        entityManager.close();
        // 工厂不需要关闭
//        factory.close();
    }
}
