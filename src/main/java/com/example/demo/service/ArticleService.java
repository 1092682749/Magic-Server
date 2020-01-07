package com.example.demo.service;

import com.example.demo.model.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleService {
    List<Article> findAll();
    public int save(Article article);
    List<Article> findMatch(String condition);
    List<Article> findArticleByCondition(String condition);
    Article findById(Integer id);
    List<Article> findPass();
    List<Article> findByAdminCondition(Article article);
    int changeAuditState(Integer state, Integer aid);
    List<Article> findByTitle(String title);
}
