package com.example.demo.controller;

import com.example.demo.model.ChatMsgRecord;
import com.example.demo.utils.ResponseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.Base64;

@Controller
public class ImageFileController {
    @RequestMapping("/android/uploadImage")
    public @ResponseBody
    ResponseResult uploadImage(@RequestBody ChatMsgRecord record) throws IOException {
        String imageStr = record.getContent();
        File path = new File("../uploads/images");
        if (!path.exists()) {
            path.mkdirs();
        }
        File file = new File(path + "/" + System.currentTimeMillis() + ".png");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        bos.write(Base64.getDecoder().decode(record.getContent()));
        bos.flush();
        bos.close();
        record.setContent("../uploads/images" + file.getName());
        return new ResponseResult(record);
    }
}
