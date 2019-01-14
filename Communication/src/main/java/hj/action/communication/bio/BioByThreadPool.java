package hj.action.communication.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>Title : 基于线程池的BIO socket通讯</p>
 * <p>Description : 可以同时处理多个客户端数据</p>
 * <p>Date : 2018/12/11 </p>
 *
 * @author : hejie
 */
public class BioByThreadPool {

    public static void main(String[] args) throws IOException {
        //1、启动服务端
        ServerSocket server = new ServerSocket(6666);
        System.out.println("服务端已创建，等待连接...");

        //2、初始化线程池，用于处理每个客户端连接
        ExecutorService pool = Executors.newFixedThreadPool(2);
        //3、同步，持续接收客户端连接
        while (true){
            final Socket client = server.accept();
            final String host = ((InetSocketAddress)client.getRemoteSocketAddress()).getHostName();
            System.out.println("-----------------------------");
            System.out.println("客户端："+ host+"， 已连接！");
            //4、从线程池中分配一个线程，单独处理一个客户端数据，
            // 每个线程内部是阻塞式接收数据的，当客户端连接数达到线程池数量时，则无法响应
            pool.execute(new Runnable() {
                public void run() {
                    final Scanner scanner;
                    try {
                        scanner = new Scanner(client.getInputStream());
                        while (true) {
                            String txt = "";
                            //接收数据
                            if (scanner.hasNext()) {
                                txt = scanner.next();
                                if ("quit".equals(txt)) {
                                    client.close();
                                    System.out.println("客户端：" + host + "，已退出！");
                                    return;
                                }
                                System.out.println("收到信息：" + txt);
                            }

                            //返回数据
                            if (!client.isClosed()) {
                                OutputStream out = client.getOutputStream();
                                out.write(("您好，已收到信息：" + txt + ". \r\n").getBytes());
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
