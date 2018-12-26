package com.example.demo.model;

import java.util.List;

public class Article {
    private Integer aid;

    private String atitle;

    private String uid;

    private String addtime;

    private Integer readnumber;

    private Integer state;

    private Integer isbanner;

    private Integer category;

    private String source;

    private String keyword;

    private String author;

    private String acontent;

    private String brief;

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getAcontent() {
        return acontent;
    }

    public void setAcontent(String acontent) {
        this.acontent = acontent;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAtitle() {
        return atitle;
    }

    public void setAtitle(String atitle) {
        this.atitle = atitle == null ? null : atitle.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime == null ? null : addtime.trim();
    }

    public Integer getReadnumber() {
        return readnumber;
    }

    public void setReadnumber(Integer readnumber) {
        this.readnumber = readnumber;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getIsbanner() {
        return isbanner;
    }

    public void setIsbanner(Integer isbanner) {
        this.isbanner = isbanner;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }
}