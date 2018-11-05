package com.example.demo;

import java.io.*;

public class UTFTest {
    public static void main(String[] args) throws IOException {
        String path = "/Users/qingyun/Downloads/";
        String name = "lyt3.jpg";
        File file = new File("/Users/qingyun/Downloads/lyt.jpg");
        FileInputStream fis = new FileInputStream(file);
        File newFile = new File(path + name);
        if (!newFile.exists()){
            newFile.createNewFile();
        }
        BufferedInputStream bis = new BufferedInputStream(fis);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(newFile));
        byte[] unit = new byte[1024];
        while ((bis.read(unit)) != -1){
            String encoderStr = new String(unit,"utf-8");
            bos.write(encoderStr.getBytes("utf-8"),0,unit.length);
        }
    }
}
