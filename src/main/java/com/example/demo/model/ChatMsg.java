package com.example.demo.model;

import java.util.Date;

public class ChatMsg implements Comparable<ChatMsg> {
    private Integer id;

    private Integer sendid;

    private Integer reviceid;

    private Date addtime;

    private Integer type;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSendid() {
        return sendid;
    }

    public void setSendid(Integer sendid) {
        this.sendid = sendid;
    }

    public Integer getReviceid() {
        return reviceid;
    }

    public void setReviceid(Integer reviceid) {
        this.reviceid = reviceid;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public int compareTo(ChatMsg o) {
        if (this.addtime.getTime() > o.addtime.getTime()) {
            return 1;
        }
        if (this.addtime.getTime() < o.addtime.getTime()) {
            return -1;
        }
        return 0;
    }
}