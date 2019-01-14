package hj.action.communication.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * <p>Title : 单线程的BIO socket通讯，同步阻塞</p>
 * <p>Description : 同时只能处理一个客户端请求</p>
 * <p>Date : 2018/12/11 </p>
 *
 * @author : hejie
 */
public class BioBySingleThread {

    public static void main(String[] args) {
        //1、创建连接
        ServerSocket server = null;
        Socket socket = null;
        try {
            server = new ServerSocket(6666);
            System.out.println("socket服务已创建，等待连接...");
            //2、同步，持续接收多个客户端连接
            while (true) {
                socket = server.accept();
                String host = ((InetSocketAddress)socket.getRemoteSocketAddress()).getHostName();
                System.out.println("-----------------------------");
                System.out.println("客户端："+ host+"， 已连接！");
                Scanner scanner = new Scanner(socket.getInputStream());
                //3、阻塞，持续接手客户端数据
                while (true) {
                    String txt = "";
                    //接收数据
                    if (scanner.hasNext()) {
                        txt = scanner.next();
                        if ("quit".equals(txt)) {
                            socket.close();
                            System.out.println("客户端："+host+"，已退出！");
                            break;
                        }
                        System.out.println("收到信息：" + txt);
                    }

                    //返回数据
                    if (!socket.isClosed()){
                        OutputStream out = socket.getOutputStream();
                        out.write(("您好，已收到信息：" + txt + ". \r\n").getBytes());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            //回收、关闭连接
            //socket.close;  socket = null;
            //server.close;  server = null;
        }
    }
}
