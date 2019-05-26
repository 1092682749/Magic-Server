package com.example.demo.mysqlTest;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Date {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
        Class.forName("com.mysql.jdbc.Driver");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Connection connection = DriverManager.getConnection("jdbc:mysql://dyzhello.club:3306/test?useUnicode=true&characterEncoding=utf-8", "root", "dyz");
        String insert = "insert into ttime (tt) values (?)";
        String query = "select * from ttime";
        PreparedStatement st = connection.prepareStatement(query);
        //st.setDate(1, new java.sql.Date(format.parse("2019-01-02 12:12:00").getTime()));
        //new java.sql.Date(format.parse("2019-01-02 12:12:00").getTime());
        //st.setString(1, "2019-01-02 12:12:00");
        ResultSet set = st.executeQuery();
        set.next();
        set.next();
        Timestamp d = set.getTimestamp("tt");
        System.out.println(format.format(d));
    }
}
