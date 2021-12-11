package com.example.demo.table;

import com.example.demo.model.*;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import org.springframework.data.annotation.Transient;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ClassToTable {
    public static String create(Class<?> clazz) throws Exception {
        StringBuilder sql = new StringBuilder("CREATE TABLE ");
        String tableName = clazz.getSimpleName();
        sql.append(tableName.toLowerCase() + "(\r\n");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Transient.class)) continue;
            String fieldName = field.getName();
            Class<?> type = field.getType();
            sql.append(fieldName.toLowerCase());
            sql.append(convertJDBCType(type.getSimpleName()));
            if (fieldName.equals("id")) {
                sql.append(" primary key not null auto_increment,\r\n");
            } else {
                sql.append(",\r\n");
            }
        }
        String sqlStr = sql.toString().substring(0, sql.toString().length() - 3);
        sqlStr += ");";
        System.out.println(sqlStr);
        return sqlStr;
    }

    public static String convertJDBCType(String javaType) throws Exception {
        if (javaType.equals("String")) {
            return " VARCHAR(225)";
        } else if (javaType.equals("Integer") || javaType.equalsIgnoreCase("int") || javaType.equalsIgnoreCase("Long") || javaType.equalsIgnoreCase("long")) {
            return " int(12)";
        } else if (javaType.equalsIgnoreCase("Short")) {
            return " int(6)";
        } else if (javaType.equals("Date")) {
            return " datetime";
        } else if (javaType.equalsIgnoreCase("Double")) {
            return " DOUBLE(16,8)";
        } else if (javaType.equalsIgnoreCase("byte")) {
            return " BINARY(1)";

        } else {
            throw new Exception("没有考虑的数据类型" + javaType);
        }
    }

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projects?useUnicode=true&characterEncoding=utf-8", "root", "root");
        Statement st = conn.createStatement();
        System.out.println(File.separator);
        System.out.println("com.example.demo.model".replace(".", File.separator));
        URL resource = Thread.currentThread().getContextClassLoader().getResource("com/example/demo/model");
        System.out.println(resource.getPath());
        File file = new File(resource.getPath());
        File[] files = file.listFiles();
        for (File item : files) {
            System.out.println(item.getName());
            if (item.getName().contains("Example")) continue;
            if (item.getName().contains("Admin")) continue;
            if (item.getName().contains("Article")) continue;
            int i = item.getName().indexOf(".");
            String className = item.getName().substring(0, i);
            Class<?> aClass = Class.forName("com.example.demo.model." + className);
            String sql = create(aClass);
            try {
                st.execute(sql);
            }catch (MySQLSyntaxErrorException e) {
                System.err.println(e.getMessage());
            }

        }


        System.out.println("success!");
    }
}
