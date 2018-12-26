package com.example.demo.controller;

import com.example.demo.model.ChatMsgRecord;
import com.example.demo.utils.ResponseResult;
import com.example.demo.utils.annotation.TestProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

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
        String[] strs = record.getContent().split(";");
        // 网页base64数据处理
        if (strs[0]!= null && record.getContent().split(";")[0].equals("data:image/jpeg")) {
            String src = record.getContent().substring(27);
            BASE64Decoder decoder = new BASE64Decoder();
            System.out.println(decoder.decodeBuffer(record.getContent()));
            byte[] b = decoder.decodeBuffer(record.getContent().split(",")[1]);
            bos.write(b);
        } else {
            // android数据
            bos.write(Base64.getDecoder().decode(record.getContent()));
        }
        bos.flush();
        bos.close();
        record.setContent("../uploads/images/" + file.getName());
        return new ResponseResult(record);
    }
    @TestProxy("qq")
    @RequestMapping("/ok/proxy")
    public @ResponseBody String proxy() {
        System.out.println("proxy");
        return "asd";
    }
}
