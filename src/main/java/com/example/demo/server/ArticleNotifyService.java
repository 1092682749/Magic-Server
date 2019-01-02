package com.example.demo.server;

import com.example.demo.model.Article;
import com.example.demo.model.ArticleNotify;

import java.util.List;

public interface ArticleNotifyService {
    List<ArticleNotify> findNotify();
    int save(ArticleNotify articleNotify);
    ArticleNotify findById(Integer id);
}
