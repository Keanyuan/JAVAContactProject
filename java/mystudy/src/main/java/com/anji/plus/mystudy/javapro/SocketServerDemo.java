package com.anji.plus.mystudy.javapro;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * @Auther: Kean
 * @Date: 2019/2/23 4:58 PM
 * @Description: Socket 服务器端
 */
public class SocketServerDemo extends Thread {

    private ServerSocket serverSocket;


    public SocketServerDemo(int port) throws IOException {
        //初始化服务器Socket
        serverSocket = new ServerSocket(port);
        //设置超时时候
        serverSocket.setSoTimeout(10000);
    }

    //运行
    public void run() {
        while (true) {
            //获取客户端端口号
            System.out.println("Waiting for client on port " +
                    serverSocket.getLocalPort() + "...");
            try {

                //侦听并接受到此套接字的连接
                Socket server = serverSocket.accept();
                //获取服务端套接字连接的端点的地址
                System.out.println("Just connected to " + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());
                //获取客户端数据
                System.out.println(in.readUTF());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                //向客户端输出数据
                out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress() + "\n Goodbye!");
                server.close();
            } catch (SocketTimeoutException e) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

        }
    }

    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        try {
            Thread t = new SocketServerDemo(port);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
