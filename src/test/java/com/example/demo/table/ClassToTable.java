package com.example.demo.table;

import com.example.demo.model.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ClassToTable {
    public static String create(Class<?> clazz) throws Exception {
        StringBuilder sql = new StringBuilder("CREATE TABLE ");
        String tableName = clazz.getSimpleName();
        sql.append(tableName + "(\r\n");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            Class<?> type = field.getType();
            sql.append(fieldName);
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
        if (javaType.equals("String")){
            return " VARCHAR(225)";
        } else if (javaType.equals("Integer") || javaType.equals("int") || javaType.equals("Long") || javaType.equals("long")){
            return " int(12)";
        } else if (javaType.equals("Short")) {
            return " int(6)";
        } else if (javaType.equals("Date")) {
            return " datetime";
        } else if (javaType.equals("Double")) {
            return " DOUBLE(16,8)";
        }else {
            throw new Exception("没有考虑的数据类型"+javaType);
        }
    }
    public static void main(String[] args) throws Exception {
//        String sql = create(.class);
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://dyzhello.club:3306/testredis?useUnicode=true&characterEncoding=utf-8","root","dyz");
        Statement st = conn.createStatement();
//        st.execute(sql);
        System.out.println("success!");
    }
}