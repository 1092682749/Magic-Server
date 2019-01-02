package com.example.demo.server.impl;

import com.example.demo.dao.ArticleNotifyMapper;
import com.example.demo.model.ArticleNotify;
import com.example.demo.server.ArticleNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ArticleNotifyImpl implements ArticleNotifyService {
    @Autowired
    ArticleNotifyMapper articleNotifyMapper;
    @Override
    public List<ArticleNotify> findNotify() {
        return articleNotifyMapper.findNotify();
    }

    @Override
    public int save(ArticleNotify articleNotify) {
        return articleNotifyMapper.save(articleNotify);
    }

    @Override
    public ArticleNotify findById(Integer id) {
        return articleNotifyMapper.findById(id);
    }
}
