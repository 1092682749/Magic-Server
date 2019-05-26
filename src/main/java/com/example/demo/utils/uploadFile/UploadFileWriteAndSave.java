package com.example.demo.utils.uploadFile;

import com.example.demo.configration.diskIO.TaskExecutor;
import com.example.demo.utils.ResponseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.exceptions.TemplateInputException;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.Base64;
import java.util.logging.Logger;

@Controller
public class UploadFileWriteAndSave {
    static File outFile;
    public static String uploadPDF(UploadPOJO file) throws IOException {
        if (file.getFileType().equals("pdf")) {
            File filePath = new File("../uploads/pdf/");
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            Base64.Decoder decoder = Base64.getDecoder();
            byte bytes[] = decoder.decode(file.getData());
            outFile = new File(filePath + file.getFileName() + ".pdf");
            FileOutputStream fos = new FileOutputStream(outFile);
            fos.write(bytes, 0, bytes.length);
            fos.flush();
            fos.close();
        }
        return outFile.getPath();
    }
    @RequestMapping("/ok/all/uploadPDF")
    public @ResponseBody ResponseResult upload(@RequestBody UploadPOJO file) throws IOException {
        String path = uploadPDF(file);
        ResponseResult responseResult = new ResponseResult();
        if (path != null) {
            responseResult.setData(path);
        }
        return responseResult;
    }

    @SuppressWarnings("all")
    public static String uploadImage(String base64) throws NotBase64Exception, IOException {
        if (!base64.contains("base64")) {
            throw new NotBase64Exception("不是base64字符串");
        }
        String name = "../uploads/images" + "/" + System.currentTimeMillis() + ".png";

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
        String[] strs = base64.split(";");
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = decoder.decodeBuffer(base64.split(",")[1]);
        fos.write(b);
        bos.flush();
        bos.close();
        return file.getPath();
    }
    public static class NotBase64Exception extends Exception {
        Logger logger = Logger.getLogger(NotBase64Exception.class.getName());
        public NotBase64Exception(){};
        NotBase64Exception(String message) {
            super(message);
            logger.info(message);
        }
    }
}
