package com.example.demo.server;

import com.example.demo.model.Article;

import java.util.List;

public interface ArticleService {
    List<Article> findAll();
    public int save(Article article);
}
