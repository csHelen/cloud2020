package com.nio;

/**
 * @program: cloud2020
 * @ClassName ChannelTest
 * @description:
 * @author: 许
 * @create: 2020-05-04 02:09
 * @Version 1.0
 **/

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 通道本身不存储数据，配合缓冲区传输数据
 *  java.nio.channels.Channel
 *          FileChannel
 *          以下是网络
 *          SocketChannel
 *          ServerSocketChannel
 *          DatagramChannel
 *
 *      获取通道        getChannel
 *
 */
public class ChannelTest {

    //字符集
    public static void test5(){
        Charset gbk = Charset.forName("GBK");
        //获取编码器
        CharsetEncoder charsetEncoder = gbk.newEncoder();
        //获取解码器
        CharsetDecoder charsetDecoder = gbk.newDecoder();

        CharBuffer cBuf = CharBuffer.allocate(1024);
        cBuf.put("尚硅谷!");
        cBuf.flip();
        //编码
        ByteBuffer bBuf = gbk.encode(cBuf);
        for (int i = 0; i < bBuf.limit(); i++) {
            System.out.println(bBuf.get());
        }
        bBuf.flip();
        //解码
        CharBuffer decode = gbk.decode(bBuf);
        System.out.println(decode.toString());

    }


    public static void test4() throws IOException{
        RandomAccessFile raf1 = new RandomAccessFile("C:\\Users\\acer\\Desktop\\1.txt","rw");


        FileChannel channel = raf1.getChannel();

        //分撒读取
        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);
        ByteBuffer[] bufs = {buf1,buf2};
        channel.read(bufs);

        for (ByteBuffer buf : bufs) {
            buf.flip();
        }
        System.out.println(new String(bufs[0].array(),0,bufs[0].limit()));
        System.out.println("===============");
        System.out.println(new String(bufs[1].array(),0,bufs[1].limit()));

        //聚集写入
        RandomAccessFile ref2 = new RandomAccessFile("C:\\Users\\acer\\Desktop\\4.txt","rw");
        FileChannel channel1 = ref2.getChannel();
        channel1.write(bufs);
    }


    public static void test3()throws IOException{
        FileChannel inChannel = FileChannel.open(
                Paths.get("C:\\Users\\acer\\Desktop\\1.txt"),
                StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(
                Paths.get("C:\\Users\\acer\\Desktop\\3.txt"),
                StandardOpenOption.READ,
                StandardOpenOption.WRITE,
                StandardOpenOption.CREATE_NEW);

        inChannel.transferTo(0,inChannel.size(),outChannel);
        outChannel.transferFrom(inChannel,0,inChannel.size());
        inChannel.close();
        outChannel.close();
    }

    //使用直接缓冲区完成文件的复制（内存映射文件
    public static void test2() throws IOException {
        FileChannel inChannel = FileChannel.open(
                Paths.get("C:\\Users\\acer\\Desktop\\1.txt"),
                StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(
                Paths.get("C:\\Users\\acer\\Desktop\\2.txt"),
                StandardOpenOption.READ,
                StandardOpenOption.WRITE,
                StandardOpenOption.CREATE_NEW);

        //内存映射文件
        MappedByteBuffer in = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer out = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        //直接对缓冲区进行数据的读写操作
        byte[] dst = new byte[in.limit()];
        in.get(dst);
        out.put(dst);

    }

    public static void test1() throws IOException {
        FileInputStream fis = new FileInputStream("1.png");
        FileOutputStream fos = new FileOutputStream("2.png");

        FileChannel fischannel = fis.getChannel();
        FileChannel foschannel = fos.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (fischannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            foschannel.write(byteBuffer);
            byteBuffer.clear();
        }

        foschannel.close();
        fischannel.close();
        fos.close();
        fis.close();


    }

    public static void main(String[] args) throws IOException {
        test5();

    }

}
