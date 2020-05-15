package com.rsyb.fydao.article;

import com.rsyb.fydomain.article.FyArticle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDao {
    public void insertArticle(FyArticle fyArticle); // 插入一篇文章
    public List<FyArticle> selectArticle(String fyTitle,String fyUid); // 根据用户id和文章标题某一篇文章
    public FyArticle selectOneArticle(String fyTid); // 根据文章id查询某一篇文章
    public void UpdateArticle(FyArticle fyArticle); // 修改文章
    public void deleteArticle(String fyTid); // 根据id删除文章
    public Integer selectCountOfSort(@Param(value = "fySort")String fySort);  // 查询总条数
    public List<FyArticle> pageBeanSelect(@Param(value = "start") Integer start, @Param(value = "size")Integer size,@Param(value = "fySort")String fySort);//查询所有的文章
    public List<FyArticle> conditionSelectArticles(FyArticle fyArticle);
}
