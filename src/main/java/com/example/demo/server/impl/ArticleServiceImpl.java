package com.example.demo.server.impl;

import com.example.demo.dao.ArticleMapper;
import com.example.demo.model.Article;
import com.example.demo.server.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;
    @Override
    public List<Article> findAll() {
        return articleMapper.findAll();
    }

    @Override
    public int save(Article article) {
        return articleMapper.save(article);
    }
}
