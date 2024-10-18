import com.jackson.dao.ArticleCommentDao;
import com.jackson.dao.ArticleDao;
import com.jackson.domain.Article;
import com.jackson.domain.ArticleComment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.HashSet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-jpa.xml")
public class TableRelationOneToManyTest {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ArticleCommentDao articleCommentDao;

    @Test
    public void testTableRelationOneToMany() {
        Article article = new Article();
        article.setTitle("jackson自传");
        article.setAuthor("jackson");
        article.setCreateTime(LocalDateTime.now());
        ArticleComment comment1 = new ArticleComment();
        comment1.setComment("it is a very long comment");
        ArticleComment comment2 = new ArticleComment();
        comment2.setComment("it is a very good comment");
        comment1.setArticle(article);
        comment2.setArticle(article);
        HashSet<ArticleComment> articleComments = new HashSet<>();
        articleComments.add(comment1);
        articleComments.add(comment2);
        article.setArticleComments(articleComments);
        articleDao.save(article);
        articleCommentDao.save(comment1);
        articleCommentDao.save(comment2);
    }
}
