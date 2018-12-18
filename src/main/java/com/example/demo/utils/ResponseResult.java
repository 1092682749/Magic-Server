package com.example.demo.utils;

public class ResponseResult {
    int code = 0; // 响应吗
    String message; // 响应信息
    Object data; // 返回数据
    public ResponseResult() {
        this.code = 200; this.message = "成功";
    }
    public ResponseResult(Object data) {
        this.data = data;
    }
    public ResponseResult(String message) {
        this.message = message;
    }
    public ResponseResult(int code, String message, Object data) {
        this(data);
        this.code = code; this.message = message;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
