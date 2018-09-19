package com.example.demo.table;

import com.example.demo.Constraints;
import com.example.demo.DBTable;
import com.example.demo.SQLInteger;
import com.example.demo.SQLString;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TableCreator {
    public static void main(String[] args) throws ClassNotFoundException {
        args = new String[1];
        args[0] = "com.example.demo.Member";
        if (args.length < 1){
            System.out.println("arguments :  annotated classes");
            System.exit(0);
        }
        for (String className : args){
            Class<?> cl = Class.forName(className);
            DBTable dbTable = cl.getAnnotation(DBTable.class);
            if (dbTable == null){
                System.out.println("No DBTable annotations in class" + className);
                continue;
            }
            String tableName = dbTable.name();
            if (tableName.length() < 1){
                tableName = cl.getName().toUpperCase();
            }
            List<String> clomnDefs = new ArrayList<>();
            for (Field field : cl.getDeclaredFields()){
                String clumnName = null;
                Annotation[] anns = field.getDeclaredAnnotations();
                if (anns.length < 1){
                    continue;
                }
                if (anns[0] instanceof SQLInteger){
                    SQLInteger sInt = (SQLInteger) anns[0];
                    if (sInt.name().length() < 1){
                        clumnName = field.getName().toUpperCase();
                    }else {
                        clumnName = sInt.name();
                    }
                    clomnDefs.add(clumnName + " INT "+getConstraints(sInt.constraints()));
                }
                if (anns[0] instanceof SQLString){
                    SQLString sqlString = (SQLString) anns[0];
                    if (sqlString.name().length() < 1){
                        clumnName = field.getName().toUpperCase();
                    }else {
                        clumnName = sqlString.name();
                    }
                    clomnDefs.add(clumnName+" VARCHAR("+sqlString.value()+") "+getConstraints(sqlString.constraints()));
                }
            }
            StringBuilder sql = new StringBuilder("CREATE TABLE "+ tableName
            +"(");
            for (String c : clomnDefs){
                sql.append("\n "+c+",");
            }
            String sqls = sql.substring(0,sql.length() - 1) + ");";
            System.out.println("sql is\n" + sqls);
        }
    }

    private static String getConstraints(Constraints con) {
        String constraints = "";
        if (!con.allowNull()){
            constraints += "NOT NULL";
        }
        if (con.primaryKey()){
            constraints += "PRIMARY KEY";
        }
        if (con.unique()){
            constraints += "UNIQUE";
        }
        return constraints;
    }
}
