package com.nio;

/**
 * @program: cloud2020
 * @ClassName TrueNIOTest
 * @description:
 * @author: 许
 * @create: 2020-05-04 16:03
 * @Version 1.0
 **/

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *  使用NIO完成网络通信的三个核心
 *          通  道：    负责连接
 *              java.nio.channels.Channel
 *                  SelectableChannel
 *                      SocketChannel
 *                      ServerSocketChannel
 *                      DatagramChannel
 *
 *                      Pipe.SinkChannel
 *                      Pipe.SourceChannel
 *
 *          缓冲区：    负责数据的存取
 *          选择器：    事故selectableChannel的多路复用器，用于监控SelectableChannel 的 IO 状况
 *
 *
 */
public class BlockingNIOTest {

    //客户端
    @Test
    public void client() throws IOException {
        //获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
        //读取本地图片
        FileChannel inChannel = FileChannel.open(Paths.get("C:\\Users\\acer\\Desktop\\1581997993604756.jpg"), StandardOpenOption.READ);
        //获取缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        //并发送给服务端
        while (inChannel.read(buf) != -1) {
            buf.flip();
            socketChannel.write(buf);
            buf.clear();
        }
        socketChannel.shutdownOutput();
        //接受反馈
        int len = 0;
        while( (len = socketChannel.read(buf))!=-1){
            buf.flip();
            System.out.println(new String(buf.array(),0,len));
            buf.clear();
        }

        inChannel.close();
        socketChannel.close();
    }

    //服务端
    @Test
    public void server() throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //绑定连接
        serverSocketChannel.bind(new InetSocketAddress(9898));
        //获取客户端连接的通道
        SocketChannel socketChannel = serverSocketChannel.accept();
        //读取客户端的数据并保存到本地文件夹
        FileChannel outChannel = FileChannel.open(Paths.get("C:\\Users\\acer\\Desktop\\server\\3.jpg"), StandardOpenOption.WRITE,StandardOpenOption.CREATE_NEW);
        ByteBuffer buf = ByteBuffer.allocate(1024);
        while (socketChannel.read(buf) != -1) {
            buf.flip();
            outChannel.write(buf);
            buf.clear();
        }

        //发送反馈给客户端
        buf.put("服务端接受数据成功".getBytes());
        buf.flip();
        socketChannel.write(buf);

        outChannel.close();
        socketChannel.close();
        serverSocketChannel.close();

    }

    public static void main(String[] args) {



    }


}
