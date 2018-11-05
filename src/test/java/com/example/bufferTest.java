package com.example;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
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
        byte b = '2';
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 大端序（小下标是高位）小端序（小下标低位）
//        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.put(0,b);
        buffer.putInt(1,2);
        buffer.putShort(6,(short)1);
        System.out.println(buffer.getInt(4));
        System.out.println(buffer.getInt(1));
    }
}
