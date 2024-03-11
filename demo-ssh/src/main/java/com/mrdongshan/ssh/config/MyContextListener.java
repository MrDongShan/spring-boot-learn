package com.mrdongshan.ssh.config;

import com.mrdongshan.ssh.util.SSHConnectionUtil;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
@Component
public class MyContextListener implements ServletContextListener {

    public MyContextListener() {
        super();
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        // 建立连接
        try {
            SSHConnectionUtil.SSHConnection();
            System.out.println("成功建立SSH连接");
        } catch (Throwable e) {
            System.out.println("SSH连接失败！");
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // 断开连接
        System.out.println("Context destroyed ... !");
        try {
            SSHConnectionUtil.closeSSH(); // disconnect
            System.out.println("成功断开SSH连接!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("断开SSH连接出错！");
        }
    }
}
