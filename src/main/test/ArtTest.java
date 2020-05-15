import com.rsyb.fydomain.article.FyArticle;
import com.rsyb.fyservice.article.ArticleServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.UUID;

//帮我们创建容器
@RunWith(SpringJUnit4ClassRunner.class)
//指定创建容器使用的配置文件
@ContextConfiguration("classpath:ApplicationContext.xml")

public class ArtTest {
    @Autowired
    private ArticleServer articleServer;

    @Test
    public void test01(){
        for (int i=10;i<20;i++){
            FyArticle fyArticle = new FyArticle();
            String fyTid = UUID.randomUUID().toString().replaceAll("-","");
            fyArticle.setFyTid(fyTid);
            fyArticle.setFyGdate(new Date());
            fyArticle.setFyUid("03c1a508aecb4065a4f2f783fef863ba");
            fyArticle.setFyAbstract("文章摘要"+i+i+i+i);
            fyArticle.setFySort("PY");
            fyArticle.setFyContent("文章内容"+i+i+i+i);
            fyArticle.setFyTdate("2020-01-02");
            fyArticle.setFyTitle("文章标题"+i+i+i+i);
            articleServer.addArticle(fyArticle);
        }

    }
}
