package hj.action.communication.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * <p>Title : AIO 通讯，回调式，异步非阻塞</p>
 * <p>Description : </p>
 * <p>Date : 2018/12/11 </p>
 *
 * @author : hejie
 */
public class AioServer4Callback {
    public static void main(String[] args) throws IOException, InterruptedException {
        final AsynchronousServerSocketChannel channel = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(6666));
        System.out.println("服务端已启动，等待连接....");
        //注册等待连接事件，异步回调
        channel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            public void completed(AsynchronousSocketChannel client, Object attachment) {
                try {

                    String host = ((InetSocketAddress) client.getRemoteAddress()).getHostName();
                    System.out.println("客户端：" + host + "，已连接！");

                    ByteBuffer buffer = ByteBuffer.allocate(10);
                    //等待读取事件，异步回调
                    client.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                        public void completed(Integer result, ByteBuffer attachment) {
                            attachment.flip();
                            CharBuffer charBuffer = CharBuffer.allocate(10);
                            CharsetDecoder decoder = Charset.defaultCharset().newDecoder();
                            decoder.decode(attachment, charBuffer, false);
                            charBuffer.flip();
                            String data = new String(charBuffer.array(), 0, charBuffer.limit()).trim();
                            System.out.println("读取数据:" + data + "，result：" + result);

                            //清空已读数据，并再次注册，持续读取数据
                            buffer.clear();
                            client.read(buffer, attachment, this);
                        }

                        public void failed(Throwable exc, ByteBuffer attachment) {
                            System.out.println("读取客户端数据失败，" + exc.getLocalizedMessage());
                        }
                    });

                    //再次注册，等待其他客户端连接
                    channel.accept(null, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public void failed(Throwable exc, Object attachment) {
                System.out.println("客户端连接失败，" + exc.getLocalizedMessage());
            }
        });
        while (true) {
            Thread.sleep(1000);
        }
    }
}
