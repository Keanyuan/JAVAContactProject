package com.anji.plus.study.javapro;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * @Auther: Kean
 * @Date: 2019/2/24 12:48 PM
 * @Description:
 */
public class SendEmailDemo {
    //sogmyiiztptqbbbi

    public static void main(String[] args) {

    }

    private static void sendQQMail() {
        // 收件人电子邮箱
        String to = "qizhiyuan@anji-plus.com";
        // 发件人电子邮箱
        String from = "617293977@qq.com";
        // 指定发送邮件的主机为 localhost
        String host = "smtp.qq.com";//QQ 邮件服务器

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);
        //用户认证部分
        //如果需要提供用户名和密码给e-mail服务器来达到用户认证的目的，
        // 你可以通过如下设置来完成：
        properties.put("mail.smtp.auth", "true");
//        properties.setProperty("mail.user", "myuser");
//        properties.setProperty("mail.password", "mypwd");

        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //发件人邮件用户名、密码
                return new PasswordAuthentication("617293977@qq.com", "sogmyiiztptqbbbi");
            }
        });
        // 创建默认的 MimeMessage 对象
        MimeMessage message = new MimeMessage(session);
        try {
            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));
            // 1.Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: 头部头字段
            message.setSubject("This is the Subject Line!");
            // 1.设置消息体
            message.setText("This is actual message");
            // 2.发送 HTML 消息, 可以插入html标签
//            message.setContent("<h1>This is actual message</h1>",
//                    "text/html" );
            // 发送消息
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static void sendGMail() {
        // 收件人电子邮箱
        String to = "abcd@gmail.com";
        // 发件人电子邮箱
        String from = "web@gmail.com";
        // 指定发送邮件的主机为 localhost
        String host = "localhost";

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);
        //用户认证部分
        //如果需要提供用户名和密码给e-mail服务器来达到用户认证的目的，
        // 你可以通过如下设置来完成：
        properties.put("mail.smtp.auth", "true");
        properties.setProperty("mail.user", "myuser");
        properties.setProperty("mail.password", "mypwd");

        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties);
        // 创建默认的 MimeMessage 对象
        MimeMessage message = new MimeMessage(session);
        try {
            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));
            // 1.Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            Address address1 = new InternetAddress(to);
            Address address2 = new InternetAddress(to);
            Address address3 = new InternetAddress(to);
            Address[] addresses = new Address[]{address1, address2, address3};
            //2.发送一封e-mail给多个收件人
            message.addRecipients(Message.RecipientType.TO, addresses);
            // Set Subject: 头部头字段
            message.setSubject("This is the Subject Line!");
            // 1.设置消息体
            message.setText("This is actual message");
            // 2.发送 HTML 消息, 可以插入html标签
            message.setText("<h1>This is a actual message</h1>", "text/html");

            // 创建消息部分
            BodyPart messageBodyPart = new MimeBodyPart();
            // 消息
            messageBodyPart.setText("This is message body");
            // 创建多重消息
            Multipart multipart = new MimeMultipart();
            // 设置文本消息部分
            multipart.addBodyPart(messageBodyPart);

            //// 附件部分
            messageBodyPart = new MimeBodyPart();
            String filename = "tempfile.txt";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);


            // 发送消息
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
