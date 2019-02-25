package com.anji.plus.study.javapro;
import java.io.*;
import java.net.Socket;

/**
 * @Auther: Kean
 * @Date: 2019/2/23 4:52 PM
 * @Description: Socket客户端
 */
public class SocketClientDemo {
    public static void main(String[] args) {
        String serverName = args[0];
        int port = Integer.parseInt(args[1]);

        System.out.println("Connecting to " + serverName
                + " on port " + port);
        try {
            //初始化Socket
            Socket client = new Socket(serverName, port);
            //获取ip地址
            System.out.println("Just connected to " + client.getRemoteSocketAddress());
            //初始化输出流
            OutputStream outServer = client.getOutputStream();
            //初始化输出流数据
            DataOutputStream out = new DataOutputStream(outServer);
            //写入输出流数据
            out.writeUTF("hello from " + client.getLocalSocketAddress());

            //初始化输入流
            InputStream inServer = client.getInputStream();
            //初始化输入流数据
            DataInputStream in = new DataInputStream(inServer);
            //获取输入流数据
            System.out.println("Server says " + in.readUTF());
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
