package com.example.demo.model;


import org.springframework.data.annotation.Transient;

import java.util.Date;
import java.util.List;

public class Review {
    public static Integer LEVEL_TO_ARTICLE = 1;
    public static Integer LEVEL_TO_USER = 2;
    private Integer id;

    private Integer aid;

    private Integer fromUserId;

    private Integer toUserId;

    private Integer level;

    private Date addtime;

    private String content;

    private String fromUserName;

    private String toUserName;

    private Integer toReviewId;

    public Integer getToReviewId() {
        return toReviewId;
    }

    public void setToReviewId(Integer toReviewId) {
        this.toReviewId = toReviewId;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    @Transient
    private List<Review> reviews;

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}