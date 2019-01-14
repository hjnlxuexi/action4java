package hj.action.communication.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>Title : 线程池处理NIO 通讯，同步非阻塞</p>
 * <p>Description :
 * 1、主线程通过Selector注册通道事件，
 * 2、Selector持续监听通道中是否触发了事件，
 * 3、触发事件通知注册渠道处理数据
 * </p>
 * <p>Date : 2018/12/11 </p>
 *
 * @author : hejie
 */
public class NioByThreadPool {
    private Selector selector;
    private ExecutorService pool;

    NioByThreadPool() {
        //初始化线程池
        this.pool = Executors.newFixedThreadPool(2);
        //打开ServerSocket通道
        ServerSocketChannel serverChannel = null;
        try {
            serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);//设置为非阻塞
            serverChannel.socket().bind(new InetSocketAddress(6666));

            this.selector = Selector.open();
            //将socket通道接收连接的事件，注册到Selector
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务通道已打开，等待客户端连接...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 同步，监听等待事件触发
     *
     * @throws IOException
     */
    private void listen() throws IOException {
        //同步，等待事件触发
        while (true) {
            selector.select();
            //迭代，已触发的事件
            final Iterator<SelectionKey> ite = selector.selectedKeys().iterator();
            while (ite.hasNext()) {
                final SelectionKey key = ite.next();
                ite.remove();
                //有客户端连接进来，触发连接事件
                if (key.isAcceptable()) {
                    this.doConnectEvent(key);
                }
                //有客户端写数据过来，触发读数据事件
                else if (key.isReadable()) {
                    this.doReadEvent(key);
                }
            }
        }
    }

    /**
     * 处理客户端连接事件
     *
     * @param key 连接事件
     * @throws IOException
     */
    private void doConnectEvent(SelectionKey key) throws IOException {
        ServerSocketChannel server = (ServerSocketChannel) key.channel();
        // 获得和客户端连接的通道
        SocketChannel channel = server.accept();
        // 设置成非阻塞
        channel.configureBlocking(false);
        // 获取到客户端连接
        String host = ((InetSocketAddress) channel.getRemoteAddress()).getHostName();
        System.out.println(Thread.currentThread().getName() + "-客户端：" + host + ", 已连接！");
        // 给客户端打个招呼
        channel.write(ByteBuffer.wrap(("你好 " + host + "！ \r\n").getBytes()));
        // 在客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权限
        // 注册客户端通道的数据读取事件
        channel.register(selector, SelectionKey.OP_READ);
    }

    /**
     * 读取客户端数据
     *
     * @param key 读取事件
     * @throws IOException
     */
    private void doReadEvent(SelectionKey key) throws IOException {
        // 服务器可读消息，得到事件发生的socket通道
        SocketChannel channel = (SocketChannel) key.channel();
        String host = ((InetSocketAddress) channel.getRemoteAddress()).getHostName();
        // 读取的缓冲区，非阻塞，允许只读一部分数据
        ByteBuffer buffer = ByteBuffer.allocate(10);
        // 如果这里没有读完，会继续触发事件
        channel.read(buffer);
        //因为非阻塞，所以可以通过多线程的方式来处理事件的响应
        pool.execute(() -> {
            try {
                byte[] data = buffer.array();
                String msg = new String(data).trim();
                //客户端退出
                if ("quit".equals(msg)) {
                    channel.close();
                    System.out.println("客户端：" + host + ", 已退出！");
                    return;
                }
                System.out.println(Thread.currentThread().getName() + "-收到消息: " + msg);
                ByteBuffer outBuffer = ByteBuffer.wrap(("收到您的消息：" + msg + "。 \r\n").getBytes());
                channel.write(outBuffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    public static void main(String[] args) throws IOException {
        NioByThreadPool nio = new NioByThreadPool();
        nio.listen();
    }
}
