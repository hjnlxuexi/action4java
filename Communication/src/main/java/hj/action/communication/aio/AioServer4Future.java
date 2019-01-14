package hj.action.communication.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * <p>Title : AIO通讯，将来式，异步非阻塞</p>
 * <p>Description : </p>
 * <p>Date : 2018/12/11 </p>
 *
 * @author : hejie
 */
public class AioServer4Future {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(6666));

        Future<AsynchronousSocketChannel> futureSocket = server.accept();
        //异步，无需等待客户端连接，可以去玩别的，当有客户端连接时通知我
        while (!futureSocket.isDone()) {
            Thread.sleep(1000);
            System.out.println("还没有客户端连接...");
        }
        AsynchronousSocketChannel channel = futureSocket.get();
        String host = ((InetSocketAddress) channel.getRemoteAddress()).getHostName();
        System.out.println("客户端：" + host + "，已连接！");
        ByteBuffer bf = ByteBuffer.allocate(10);
        Future<Integer> futureRead = channel.read(bf);
        //费阻塞，无需等待和完整的读取客户端数据，可以去玩别的，当有客户端有数据时通知我
        while (!futureRead.isDone()) {
            Thread.sleep(500);
            System.out.println("客户端：" + host + "，还没有发送数据...");
        }
        //读取的行数
        Integer readNum = futureRead.get();
        //读取内容
        bf.flip();
        CharBuffer charBuffer = CharBuffer.allocate(10);
        CharsetDecoder decoder = Charset.defaultCharset().newDecoder();
        decoder.decode(bf, charBuffer, false);
        charBuffer.flip();
        String data = new String(charBuffer.array(), 0, charBuffer.limit());
        System.out.println("读取第【" + readNum + "】行，内容：" + data);

        Future<Integer> futureWrite = channel.write(ByteBuffer.wrap(("你发送的消息" + data).getBytes()));
        if (!futureWrite.isDone()) {
            System.out.println("正在往外写数据...");
        }
        Integer num = futureWrite.get();
        System.out.println("响应：" + num + "行");

    }
}
