package com.mrdongshan.ssh.util;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 * 使用方法：调整配置参数后，启动项目，然后访问localhost+local_port端口
 * 问题：
 * 1.有的时候虽然可以通过22端口连接到云服务器，提示SSH连接成功了，但是如果数据库不启动，那么无法通过3307访问数据库，此时也没有连接失败提示
 * 2.在测试的时候发现如果服务端开启了防火墙，开放了22端口，但是没有开放3306或者3307端口，仍然可以使用zerotier给出的IP访问到数据库(直连)，即使服务器重启后
 */
public class SSHConnectionUtil {
    public static String user = "linux服务登录名";// linux服务登录名
    public static String password = "linux密码";// 登陆密码
    public static String host = "公网IP";    // 服务器公网IP
    public static int port = 22;  // 跳板机ssh开放的接口,ssh通道端口   默认端口 22
    public static int local_port = 3307; // 这个是本地的端口，选取一个没有占用的port即可
    public static String remote_host = "数据库所在的服务器ip";// 要访问的mysql所在的ip
    public static int remote_port = 3306;// 服务器上数据库端口号,需要开放防火墙
    public static Session session = null;

    /**
     * 建立SSH连接
     */
    public static void SSHConnection() {
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            session.setPortForwardingL(local_port, remote_host, remote_port);
        } catch (Exception e) {
            // do something
        }
    }

    /**
     * 断开SSH连接
     */
    public static void closeSSH() {
        session.disconnect();
    }

}

