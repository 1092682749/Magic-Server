package com.example.demo.service;

import com.example.demo.model.ArticleNotify;

import java.util.List;

public interface ArticleNotifyService {
    List<ArticleNotify> findNotify();
    int save(ArticleNotify articleNotify);
    ArticleNotify findById(Integer id);
}
