package com.example.demo.utils.uploadFile;

import com.example.demo.utils.ResponseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.Base64;
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
}
