package com.example.demo.reverse;

import java.io.*;
import java.sql.*;

public class CodeGenerate {
    public static String jdbcUrl = "jdbc:mysql://dyzhello.club:3306/testredis?useUnicode=true&characterEncoding=utf-8";
    public static String username = "root";
    public static String password = "dyz";
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        String filename = "aaa";
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(jdbcUrl,username,password);
        String sql = "select table_name from information_schema.TABLES where table_schema = 'testredis'";
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet set = pst.executeQuery();
        ResultSetMetaData m = set.getMetaData();
        int c = m.getColumnCount();
        for(int i=1;i<=c;i++)
        {
            System.out.print(m.getColumnName(i));
            System.out.print("\r\n");
        }

        while (set.next()){
            filename = set.getString(1);
            System.out.println(set.getString(1));
        }
//        FileOutputStream out = new FileOutputStream(new File("/"+filename+".java"));
        File javafile = new File("/Users/qingyun/Desktop/ll/"+filename+".java");
        if (!javafile.exists()){
            javafile.createNewFile();
        }
        FileWriter writer = new FileWriter(javafile,true);
        writer.write("public class " + filename + "{}");
        writer.flush();
    }
}
