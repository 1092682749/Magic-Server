package com.example.demo.controller;

import com.example.demo.model.Article;
import com.example.demo.model.Review;
import com.example.demo.service.ArticleNotifyService;
import com.example.demo.service.ArticleService;
import com.example.demo.service.ReviewService;
import com.example.demo.service.UserService;
import com.example.demo.utils.ResponseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleNotifyService articleNotifyService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    UserService userService;

    public String articleList() {
        return "articlesList";
    }

    @RequestMapping("articleList")
    public ModelAndView list(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "") String condition) {
        PageHelper.startPage(pageNum, 5);
        List<Article> articleList = articleService.findArticleByCondition(condition);
        PageInfo pageInfo = new PageInfo(articleList, 3);
        ModelAndView mav = new ModelAndView("articlesList");
        mav.addObject("notifyList", articleNotifyService.findNotify());
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
    @RequestMapping("/ok/getArticle")
    public @ResponseBody ResponseResult getArticle(Integer id) {
        Article article = articleService.findById(id);
        List<Review> reviewList = reviewService.findById(id);
        for (Review review : reviewList) {
            List<Review> reviewList1 = reviewService.findSecondLevel(review.getId());
            review.setReviews(reviewList1);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("article", article);
        map.put("reviewList", reviewList);
        return new ResponseResult(map);
    }
    @RequestMapping("/publishReview")
    public @ResponseBody ResponseResult publishReview(@RequestBody Review review) throws Exception {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer id = userService.findByUsername(user.getUsername()).getId();
        review.setFromUserId(id);
        review.setAddtime(new Date());
        review.setLevel(review.getToUserId() == 0 ? Review.LEVEL_TO_ARTICLE : Review.LEVEL_TO_USER);
        review.setFromUserName(userService.findById(review.getFromUserId()).getNickName());
        if (!(review.getToUserId() == 0)) {
            review.setToUserName(userService.findById(review.getToUserId()).getNickName());
        } else {

        }
        if (0 < reviewService.save(review)) {
            return new ResponseResult().setMessage("评论成功");
        }
        return new ResponseResult().setMessage("评论失败");
    }
}
