package com.example.demo.server.impl;

import com.example.demo.dao.ArticleMapper;
import com.example.demo.model.Article;
import com.example.demo.server.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<Article> findMatch(String condition) {
        return articleMapper.findMatch(condition);
    }

    @Override
    public List<Article> findArticleByCondition(String condition) {
        List<Article> articles;
        if ("".equals(condition)) {
            articles = articleMapper.findAll();
        } else {
            articles = articleMapper.findMatch(condition);
        }
        return articles;
    }
}
