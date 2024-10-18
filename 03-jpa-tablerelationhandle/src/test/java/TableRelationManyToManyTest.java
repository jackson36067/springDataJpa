import com.jackson.dao.ArticleDao;
import com.jackson.dao.ArticleTypeDao;
import com.jackson.domain.Article;
import com.jackson.domain.ArticleType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.HashSet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-jpa.xml")
public class TableRelationManyToManyTest {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ArticleTypeDao articleTypeDao;

    @Test
    public void testTableRelationOneToMany() {
        Article article1 = new Article();
        article1.setTitle("jackson自传1");
        article1.setAuthor("jackson1");
        article1.setCreateTime(LocalDateTime.now());

        Article article2 = new Article();
        article2.setTitle("jackson学习之路2");
        article2.setAuthor("jackson2");
        article2.setCreateTime(LocalDateTime.now());

        ArticleType articleType1 = new ArticleType();
        articleType1.setName("自传类");
        ArticleType articleType2 = new ArticleType();
        articleType2.setName("学习类");
        HashSet<Article> articles = new HashSet<>();
        articles.add(article2);
        articles.add(article1);
        articleType2.setArticle(articles);
        articleType1.setArticle(articles);
        HashSet<ArticleType> articleTypes = new HashSet<>();
        articleTypes.add(articleType1);
        articleTypes.add(articleType2);
        articleDao.save(article1);
        articleDao.save(article2);
        articleTypeDao.save(articleType1);
        articleTypeDao.save(articleType2);
    }
}
