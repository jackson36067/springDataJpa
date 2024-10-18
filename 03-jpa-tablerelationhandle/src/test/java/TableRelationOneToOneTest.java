
import com.jackson.dao.ArticleDao;
import com.jackson.domain.Article;
import com.jackson.domain.ArticleData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-jpa.xml")
public class TableRelationOneToOneTest {
    @Autowired
    private ArticleDao articleDao;

    /**
     * 测试新增
     */
    @Test
    public void testTableRelationOneToOneOperationSave() {

        // 创建文章对象
        Article article = new Article();
        article.setAuthor("jacksonlt");
        article.setTitle("前端学习之路");

        // 创建文章内容对象
        article.setCreateTime(LocalDateTime.now());
        ArticleData articleData = new ArticleData();
        articleData.setContent("前端学习的路很漫长,需要认真学");

        // 建立两个表之间的关系
        article.setArticleData(articleData);
        articleData.setArticle(article);

        articleDao.save(article);
    }
}
