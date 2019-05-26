package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Get {
    private volatile boolean isUse = false;
    public synchronized void openDevice(int number) throws InterruptedException {
        isUse = true;
        System.out.println(number + "打开了设备");
        System.out.println("开始工作");
        Thread.sleep(1000);
        closeDevice();
    }
    public synchronized void closeDevice() {
        isUse = false;
        System.out.println("设备已空闲");
    }
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://wenku.baidu.com/view/77a948f35ff7ba0d4a7302768e9951e79b896907.html");
        InputStream is = url.openStream();
        byte[] bytes = new byte[1024];
        StringBuilder sb = new StringBuilder();
        while (is.read(bytes) > 0) {
            sb.append(new String(bytes));
        }
        System.out.println(sb.toString());
    }
}
