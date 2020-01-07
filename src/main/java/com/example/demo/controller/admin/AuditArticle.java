package com.example.demo.controller.admin;

import com.example.demo.model.Article;
import com.example.demo.service.ArticleService;
import com.example.demo.utils.PageBean;
import com.example.demo.utils.ResponseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Stream;

@Controller
@RequestMapping("/ok/admin")
public class AuditArticle {
    @Autowired
    ArticleService articleService;
    @RequestMapping("/findByAdminCondition")
    public @ResponseBody
    ResponseResult findByAdminCondition(@RequestBody PageBean<Article> bean) {
        PageHelper.startPage(bean.getPageNum(), bean.getPageSize());
        List<Article> articleList = articleService.findByAdminCondition(bean.getCondition());
        PageInfo pageInfo = new PageInfo(articleList, bean.getNavigatePages());
        return new ResponseResult(pageInfo);
    }
    @RequestMapping("/changeAuditState")
    public @ResponseBody ResponseResult changeAuditState(Integer aid, Integer state) {
        if (articleService.changeAuditState(state, aid) > 0) {
            return new ResponseResult().setMessage("成功");
        }
        return new ResponseResult().setMessage("失败");
    }

    @RequestMapping("/find")
    @ResponseBody
    public List<Article> find(String title) {
        return articleService.findByTitle(title);
    }

}
