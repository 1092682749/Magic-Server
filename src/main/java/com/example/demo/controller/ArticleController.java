package com.example.demo.controller;

import com.example.demo.model.Article;
import com.example.demo.server.ArticleService;
import com.example.demo.utils.ResponseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ArticleController {
    @Autowired
    ArticleService articleService;

    public String articleList() {
        return "articlesList";
    }

    @RequestMapping("articleList")
    public ModelAndView list(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "") String condition) {
        PageHelper.startPage(pageNum, 5);
        List<Article> articleList = articleService.findArticleByCondition(condition);
        PageInfo pageInfo = new PageInfo(articleList, 3);
        ModelAndView mav = new ModelAndView("articlesList");
        mav.addObject("pageInfo", pageInfo);
        return mav;
    }
    @RequestMapping("/writeArticle")
    public ModelAndView writeArticle() {
        return new ModelAndView("writeArticle");
    }
    @RequestMapping("/ok/uploadArticle")
    public @ResponseBody
    ResponseResult uploadArticle(@RequestBody Article article) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:MM:SS");
        article.setAddtime(sdf.format(new Date()));
        if (articleService.save(article) > 0) {
            return new ResponseResult("保存成功");
        }
        return new ResponseResult("保存失败");
    }
}
