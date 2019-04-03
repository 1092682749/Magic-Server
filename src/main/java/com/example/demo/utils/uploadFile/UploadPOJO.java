package com.example.demo.utils.uploadFile;

public class UploadPOJO {
    String fileName;
    String fileType;
    String decodeType;
    String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getDecodeType() {
        return decodeType;
    }

    public void setDecodeType(String decodeType) {
        this.decodeType = decodeType;
    }
}
