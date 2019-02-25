package com.anji.plus.mystudy.network;

import java.io.IOException;
import java.io.PrintStream;
import java.net.*;

/**
 * @Auther: kean_qi
 * @Date: 2019/2/25 17:08
 * @Description:
 */
public class NetworkDemo {
    public static void main(String[] args) {
        connectSpecifyHost();
    }

    //网络抓取
    private static void urlInput(){

    }

    //使用 Socket 连接到指定主机
    private static void connectSpecifyHost(){
        InetAddress address;
        try {
            Socket socket = new Socket("www.w3cschool.cn", 80);
            address = socket.getInetAddress();
            System.out.println("连接到 " + address);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //查看主机指定文件的最后修改时间
    private static void lastChangeTimestamp(){
        try {
            URL u = new URL("http://127.0.0.1/pom.xml");
            URLConnection uc = u.openConnection();
            uc.setUseCaches(false);
            long timestamp = uc.getLastModified();
            System.out.println("java.bmp 文件最后修改时间 : " + timestamp);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //使用 Socket 类的 accept() 方法
    // 和 ServerSocket 类的
    // MultiThreadServer(socketname) 方法来实现多线程服务器程序
    private static void multiThreadServer(){
        try {
            ServerSocket ssock = new ServerSocket(1234);
            System.out.println("Listening");
            while (true){
                Socket socket = ssock.accept();
                System.out.println("Connected");
                new Thread(new MultiThreadServer(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取远程文件的大小
    private static void  getContentfile(){
        int size;
        try {
            URL url = new URL("http://www.pptbz.com/pptpic/UploadFiles_6909/201203/2012031220134655.jpg");
            URLConnection conn = url.openConnection();
            size = conn.getContentLength();
            if (size < 0) {
                System.out.println("无法获取文件大小。");
            } else {
                System.out.println("文件大小为： " + size + "bytes");
            }
            conn.getInputStream().close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 获取本机ip地址及主机名
    private static void ipandaddress(){
        try {
            InetAddress address = InetAddress.getLocalHost();
            System.out.println("本地IP  " + address.getHostAddress()) ;
            System.out.println("主机名 " + address.getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    //查看端口是否已使用
    private static void catHost(String[] args){
        Socket Skt;
        String host = "localhost";
        if (args.length > 0) {
            host = args[0];
        }
        for (int i = 0; i < 1024; i++) {
            try {
//                System.out.println("查看 "+ i);
                Skt = new Socket(host, i);
                System.out.println("端口 " + i + " 已被使用");
            }
            catch (UnknownHostException e) {
                System.out.println("Exception occured"+ e);
                break;
            }
            catch (IOException e) {
            }
        }
    }
    //获取指定主机的IP地址
    private static void getApi(){
        InetAddress address = null;
        try {
            address = InetAddress.getByName("mirror.anji-plus.com");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        System.out.println(address.getHostName() + " = " + address.getHostAddress());
        System.exit(0);
    }
}


class MultiThreadServer implements Runnable{
    Socket csocket;

    public MultiThreadServer(Socket csocket) {
        this.csocket = csocket;
    }



    @Override
    public void run() {
        try {
            PrintStream pstream = new PrintStream
                    (csocket.getOutputStream());
            for (int i = 100; i >= 0; i--) {
                pstream.println(i +
                        " bottles of beer on the wall");
            }
            pstream.close();
            csocket.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}