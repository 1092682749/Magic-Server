package com.example.demo.utils.uploadFile;

import com.example.demo.configration.diskIO.TaskExecutor;
import com.example.demo.utils.ResponseResult;
import org.hibernate.validator.constraints.EAN;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.exceptions.TemplateInputException;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.*;
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

    @RequestMapping("/ok/fileinput")
    @ResponseBody
    public Map fileInput(MultipartFile[] multipartFile) throws IOException {
//        System.out.println(fileBlob.length);
        Map<String, Object> map = new HashMap<>();
        String userDir = System.getProperty("user.dir");
        for (MultipartFile file : multipartFile) {

            File savePath = new File(userDir + "/../uploads/files");
            if (!savePath.exists()) {
                savePath.mkdirs();
            }
            String fullFileName = file.getOriginalFilename();
            int index = fullFileName.lastIndexOf("\\");
            String name = fullFileName.substring(index + 1);
            File saveFile = new File(savePath + "/" + name);
            file.transferTo(saveFile);
            String[] ss = {"http://localhost:8443/../uploads/files/" + saveFile.getName() };
            map.put("initialPreview", ss);
            FileResponseEntity responseEntity = new FileResponseEntity();
            responseEntity.width= "60px";
            responseEntity.key = "100";
            responseEntity.title = name;
            responseEntity.url = "/ok/file/delete";
            Map<String, String> e = new HashMap<>();
            e.put("id", "100");
            responseEntity.extra = e;
            List<FileResponseEntity> responseEntities = new LinkedList<>();
            responseEntities.add(responseEntity);
            map.put("initialPreviewConfig", responseEntities);
            List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
            Map<String, String> t = new HashMap<>();
            t.put("{CUSTOM_TAG_NEW}", "");t.put("{CUSTOM_TAG_INIT}", "<span class=\\'custom-css\\'>CUSTOM MARKUP</span>");
            maps.add(t);
            map.put("initialPreviewThumbTags", maps);
        }

        // id = thumb-kv-explorer-2286237_411324922003.png, index = 2286237_411324922003.png
        /**
         * initialPreview: [
         *         '<img src='/images/desert.jpg' class='file-preview-image' alt="Desert" title="Desert">',
         *     ],
         *     initialPreviewConfig: [
         *     {
         *         caption: 'desert.jpg',
         *         width: '120px',
         *         url: 'https://localhost/avatar/delete', // server delete action
         *         key: 100,
         *         extra: {id: 100}
         *     }
         *     ]
         */
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("key", "1");
        return map1;
    }

    @RequestMapping("/ok/file/delete")
    public void delete() {
        System.out.println("delete");
    }

    @RequestMapping("/statusTest")
    public ResponseEntity<byte[]> statusTest() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>("{\"key\":\"value\"}".getBytes(), httpHeaders, HttpStatus.ACCEPTED);
        return responseEntity;
    }
}

