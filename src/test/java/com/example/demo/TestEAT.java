package com.example.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestEAT {
//    class AD<T> extends Throwable {}

    public static <T extends Throwable> void doWork(T t) throws T {
        try {

            Map<String, String>[] pairs = new HashMap[10];
        } catch (Throwable realCause) {
            t.initCause(realCause);
            throw t;
        }
        try(FileOutputStream fos = new FileOutputStream(File.createTempFile("asd","txt"))) {

        } catch (NullPointerException|IOException e) {
            e.printStackTrace();
        }
    }

}
