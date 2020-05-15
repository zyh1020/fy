package com.rsyb.fyservice.article;

import com.rsyb.fydao.article.ArticleDao;
import com.rsyb.fydomain.PageBean;
import com.rsyb.fydomain.article.FyArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServerImpl implements ArticleServer {
    @Autowired
    private ArticleDao arcitleDao;

    public String addArticle(FyArticle fyArticle) {
        arcitleDao.insertArticle(fyArticle);
        return "";
    }
    public List<FyArticle> findUpdateArticle(String fyTitle, String fyUid) {
        return arcitleDao.selectArticle(fyTitle,fyUid);
    }

    public FyArticle findOneArticleByFyTid(String fyTid) {
        return arcitleDao.selectOneArticle(fyTid);
    }

    public String UpdateArticle(FyArticle fyArticle) {
        arcitleDao.UpdateArticle(fyArticle);
        return null;
    }

    public String deleteArticle(String fyTid) {
        arcitleDao.deleteArticle(fyTid);
        return null;
    }

    public PageBean<FyArticle> findPageBean(Integer currentPage,String fySort) {
        PageBean<FyArticle> pageBean = new PageBean<FyArticle>();
        //设置当前页数
        pageBean.setCurrPage(currentPage);
        //查询获取总记录数
        //Integer totalCount = arcitleDao.selectCount();
        Integer totalCount = arcitleDao.selectCountOfSort(fySort);
        pageBean.setTotalCount(totalCount);

        //每页显示的数据
        Integer pageSize = 9;
        pageBean.setPageSize(pageSize);

        //根据总记录数和每页显示的数据计算总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());

        //根据下面信息查询
        Integer strat = (currentPage-1)*pageSize; // 从多少第几条数据开始查询
        Integer size = pageBean.getPageSize(); // 查询多少条数据
        System.out.println(strat+"到"+size+",,,,,,,,,,,,,,,,,,,,,,");
        List<FyArticle> fyArticles = arcitleDao.pageBeanSelect(strat, size,fySort);
        pageBean.setLists(fyArticles);

        return pageBean;
    }

    public List<FyArticle> conditionSelectArticles(FyArticle fyArticle) {
        return arcitleDao.conditionSelectArticles(fyArticle);
    }
}
