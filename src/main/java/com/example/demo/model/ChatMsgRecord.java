package com.example.demo.model;

import org.springframework.data.annotation.Transient;

import java.util.Date;

public class ChatMsgRecord implements Comparable<ChatMsgRecord> {
    private Integer id;

    private String receivename;

    private String sendname;

    private Integer type;

    private Date addtime;

    private String content;

    private Integer msgtype;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReceivename() {
        return receivename;
    }

    public void setReceivename(String receivename) {
        this.receivename = receivename == null ? null : receivename.trim();
    }

    public String getSendname() {
        return sendname;
    }

    public void setSendname(String sendname) {
        this.sendname = sendname == null ? null : sendname.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Integer getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(Integer msgtype) {
        this.msgtype = msgtype;
    }

    @Transient
    private String attachmentChannelType;

    public String getAttachmentChannelType() {
        return attachmentChannelType;
    }

    public void setAttachmentChannelType(String attachmentChannelType) {
        this.attachmentChannelType = attachmentChannelType;
    }


    @Override
    public int compareTo(ChatMsgRecord o) {
        if (this.addtime != null && o.addtime != null){
            if (this.addtime.getTime() > o.addtime.getTime()) {
                return 1;
            }
            if (this.addtime.getTime() < o.addtime.getTime()) {
                return -1;
            }
        } else if (this.id != null && o.id != null) {
            if (this.id > o.id) {
                return 1;
            }
            if (this.id < o.id) {
                return -1;
            }
        }
        return 0;
    }

}