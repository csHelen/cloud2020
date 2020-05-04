package com.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;

/**
 * @program: cloud2020
 * @ClassName NotBlockingNIOTest
 * @description:
 * @author: 许
 * @create: 2020-05-04 17:26
 * @Version 1.0
 **/
public class NotBlockingNIOTest {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                try {
                    client();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            },"Thread"+i).start();
        }
    }

    @Test
    public static void client() throws IOException {
        SocketChannel open = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
        //非阻塞
        open.configureBlocking(false);
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put((Thread.currentThread().getName()+"\t"+new Date().toString()).getBytes());
        buf.flip();
        open.write(buf);
        open.close();
    }
    @Test
    public void server() throws IOException {
        ServerSocketChannel open = ServerSocketChannel.open();
        //非阻塞
        open.configureBlocking(false);
        open.bind(new InetSocketAddress(9898));

        //获取选择器
        Selector selector = Selector.open();
        //注册通道到选择器上，并且指定“监听接受时间”
        open.register(selector, SelectionKey.OP_ACCEPT);
        //轮询式获取选择器上已经准备就绪的事件
        while (selector.select() > 0) {
            //获取当前选择器中偶有注册的选择键（已就绪的监听事件）
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            //获取准备休息的时间
            while(it.hasNext()){
                SelectionKey next = it.next();
                //判断具体是什么事件
                if (next.isAcceptable()) {
                    //若接受就绪，获取客户端连接
                    SocketChannel accept = open.accept();
                    //切换非阻塞模式
                    accept.configureBlocking(false);
                    //将通道注册到选择器中
                    accept.register(selector,SelectionKey.OP_READ);
                }else if(next.isReadable()){
                    //获取当前选择器上 读就绪 状态的通道
                    SocketChannel channel = (SocketChannel)next.channel();
                    ByteBuffer allocate = ByteBuffer.allocate(1024);
                    int len = 0;
                    while((len = channel.read(allocate))!=-1){
                        allocate.flip();
                        System.out.println(new String(allocate.array(),0,len));
                        allocate.clear();
                    }
                }
                //取消选择键
                it.remove();
            }
        }

    }
}
