package com.example.demo.fuli;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.*;
import java.nio.CharBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetMaoMi {
    public String getContent(String url) throws IOException {
        URL urlO = new URL(url);
        URLConnection connection = urlO.openConnection();
        HttpURLConnection http = (HttpURLConnection) connection;
        http.setRequestMethod("GET");
        http.setRequestProperty("User-Agent:", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");
        http.connect();
        if (http.getResponseCode() >= 300) {
            System.out.println("请求出错！");
            return "error";
        }
        InputStream in = http.getInputStream();
        InputStreamReader inr = new InputStreamReader(in);
        BufferedReader reader = new BufferedReader(inr);
        String line = null;
        line = reader.readLine();
        StringBuilder content = new StringBuilder();
        while(line != null) {
            content.append(line);
            reader.readLine();
        }
        in.close();
        System.out.println(content.toString());
        return content.toString();
    }
    String httpClientGetUrl(String url) throws IOException {
        CloseableHttpClient httpClient=HttpClients.createDefault(); // 创建httpClient实例
        HttpGet httpGet=new HttpGet(url); // 创建httpget实例
        httpGet.setHeader("User-Agent:", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");
        CloseableHttpResponse response=httpClient.execute(httpGet); // 执行http get请求
        HttpEntity entity=response.getEntity(); // 获取返回实体
//        System.out.println("网页内容："+EntityUtils.toString(entity, "utf-8")); // 获取网页内容
        String retStr = EntityUtils.toString(entity, "utf-8");;
        response.close(); // response关闭
        httpClient.close(); // httpClient关闭
        return retStr;
    }
    void getRequestDatagrame() throws IOException {
        ServerSocket ss = new ServerSocket(9090);
        Socket socket = ss.accept();
        InputStream in = socket.getInputStream();
        InputStreamReader ir = new InputStreamReader(in);
        BufferedReader reader = new BufferedReader(ir);
        String line = null;
        line = reader.readLine();
        StringBuilder content = new StringBuilder();
        while(line != null) {
            content.append(line);
            System.out.println(line);
            line = reader.readLine();
        }
        in.close();
        System.out.println(content.toString());
    }
    public static void main(String[] args) throws IOException {
        GetMaoMi maoMi = new GetMaoMi();
        String content = maoMi.httpClientGetUrl("https://www.219tt.com/index/home.html");
        Pattern pattern = Pattern.compile("<a[^>]+?href=[\"']?([^\"']+)[\"']?[^>]*>([^<]+)</a>");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
            String ret = matcher.group(1);
            if (ret.contains(".html")) {
                String cont = maoMi.httpClientGetUrl("https://www.219tt.com/" + ret);
                System.out.println(cont);
            }
        }
    }
}
