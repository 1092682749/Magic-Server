package com.example;

import java.nio.CharBuffer;

public class bufferTest {
    public static void main(String[] args) {
        CharBuffer charBuffer = CharBuffer.allocate(1024);
        char[] chars = {'a','b','c','d','e','f'};
        charBuffer.put(chars);
        charBuffer.flip();
        for (int i = 0; i < charBuffer.length(); i++) {
            System.out.println(charBuffer.get(i));
        }
    }
}
