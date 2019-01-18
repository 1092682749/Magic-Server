package com.example.demo.controller;

import com.example.demo.model.ArticleNotify;
import com.example.demo.service.ArticleNotifyService;
import com.example.demo.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ArticleNotifyController {
    @Autowired
    ArticleNotifyService articleNotifyService;
    @RequestMapping("/getNotify")
    public @ResponseBody
    List<ArticleNotify> getNotify() {
        return articleNotifyService.findNotify();
    }
    @RequestMapping("/uploadNotify")
    public @ResponseBody
    ResponseResult uploadNotify(@RequestBody ArticleNotify articleNotify) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        articleNotify.setAddtime(simpleDateFormat.format(new Date()));
        if (articleNotifyService.save(articleNotify) > 0) {
            return new ResponseResult("保存成功");
        }
        return new ResponseResult("保存失败");
    }
    @RequestMapping("/ok/getNotifyById")
    public @ResponseBody ArticleNotify getNotifyById(Integer id) {
        return articleNotifyService.findById(id);
    }
}
