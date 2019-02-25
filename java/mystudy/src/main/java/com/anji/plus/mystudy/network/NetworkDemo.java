package com.anji.plus.mystudy.network;

import java.io.IOException;
import java.net.*;

/**
 * @Auther: kean_qi
 * @Date: 2019/2/25 17:08
 * @Description:
 */
public class NetworkDemo {
    public static void main(String[] args) {
        ipandaddress();
    }

    private static void  getContentfile(){
        int size;
        try {
            URL url = new URL("//www.w3cschool.cn/wp-content/themes/w3cschool/assets/img/newlogo.png");
        } catch (MalformedURLException e) {
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
