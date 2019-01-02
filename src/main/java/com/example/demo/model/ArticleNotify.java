package com.example.demo.model;

public class ArticleNotify {
    private Integer id;

    private String atitle;

    private String addtime;

    private Integer readnumber;

    private Integer state;

    private Integer isbanner;

    private String uid;

    private String author;

    private String sourceFrom;

    private String brief;

    private String acontent;

    private String coverimg;

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

    public String getCoverimg() {
        return coverimg;
    }

    public void setCoverimg(String coverimg) {
        this.coverimg = coverimg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAtitle() {
        return atitle;
    }

    public void setAtitle(String atitle) {
        this.atitle = atitle == null ? null : atitle.trim();
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getSourceFrom() {
        return sourceFrom;
    }

    public void setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom == null ? null : sourceFrom.trim();
    }
}