package com.nio;

import java.nio.ByteBuffer;

/**
 * @program: cloud2020
 * @ClassName NIOTest
 * @description:
 * @author: 许
 * @create: 2020-05-04 01:12
 * @Version 1.0
 **/

/**
 * NIO主要属性
 *      capacity  容量
 *      limit   可读取的位置，界限
 *      position  当前读取的位置
 *          就是一个数组
 *      Mark 标记，记录当前position位置，可通过reset恢复到mark位置
 *
 *     -1 <= mark <= position <= limit <= capacity
 *
 *     5、直接缓冲区  和  非直接缓冲区
 *     非直接缓冲区 ：  通过 allocate() 方法分配缓冲区，将缓冲区建立在JVM的内存中
 *     直接缓冲区 ：  通过allocateDirect() 方法分配直接缓冲区，将缓冲区建立在物理内存中，提高效率？
 *
 *
 *
 *
 */
public class NIOTest {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        byteBuffer.put("abcde".getBytes());
        System.out.println("=================================");

        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        System.out.println("=================================");
        //切换成读模式
        byteBuffer.flip();

        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        System.out.println("=================================");

        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        System.out.println(new String(bytes,0,bytes.length));
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        System.out.println("=================================");
        //重读
        byteBuffer.rewind();
        System.out.println(new String(bytes,0,bytes.length));
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());


        System.out.println("=================================");
        //清空，数据依然存在，只是处于被遗忘状态，可以自己get得嘛
        byteBuffer.clear();
        System.out.println(new String(bytes,0,bytes.length));
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
    }

}
