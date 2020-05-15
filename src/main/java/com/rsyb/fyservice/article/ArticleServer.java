package com.rsyb.fyservice.article;

import com.rsyb.fydomain.PageBean;
import com.rsyb.fydomain.article.FyArticle;

import java.util.List;

public interface ArticleServer {
    public String addArticle(FyArticle fyArticle);
    public List<FyArticle> findUpdateArticle(String fyTitle,String fyUid);
    public FyArticle findOneArticleByFyTid(String fyTid);
    public String UpdateArticle(FyArticle fyArticle);
    public String deleteArticle(String fyTid);
    public PageBean<FyArticle> findPageBean(Integer currentPage,String fySort);
    public List<FyArticle> conditionSelectArticles(FyArticle fyArticle);
}
