package com.ticy.manage.utils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Properties;

/**
 * @Author tkk
 * @Time 2021/1/8 9:21
 * @Description todo
 */
public class FTPUtils {

    /**
     * 上传文件至FTP服务器
     *
     * @param url       服务器IP地址
     * @param port      服务器端口
     * @param userName  用户登录名
     * @param password  用户登录密码
     * @param storePath 服务器文件存储路径
     * @param fileName  服务器文件存储名称
     * @param is        文件输入流
     * @return <b>true</b>：上传成功
     * <br/>
     * <b>false</b>：上传失败
     */
    public static boolean storeFile(String url, int port, String userName, String password, String storePath, String fileName, InputStream is) {
        boolean result = false;
        FTPClient ftp = new FTPClient();
        try {
            // 连接至服务器，端口默认为21时，可直接通过URL连接
            ftp.connect(url, port);
            // 登录服务器
            ftp.login(userName, password);
            // 判断返回码是否合法
            if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                // 不合法时断开连接
                ftp.disconnect();
                // 结束程序
                return result;
            }
            // 判断ftp目录是否存在，如果不存在则创建目录，包括创建多级目录
            String s = "/" + storePath;
            String[] dirs = s.split("/");
            ftp.changeWorkingDirectory("/");
            //按顺序检查目录是否存在，不存在则创建目录
            for (int i = 1; dirs != null && i < dirs.length; i++) {
                if (!ftp.changeWorkingDirectory(dirs[i])) {
                    if (ftp.makeDirectory(dirs[i])) {
                        if (!ftp.changeWorkingDirectory(dirs[i])) {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            }
            // 设置文件操作目录
            ftp.changeWorkingDirectory(storePath);
            // 设置文件类型，二进制
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            // 设置缓冲区大小
            ftp.setBufferSize(3072);
            // 上传文件
            result = ftp.storeFile(fileName, is);
            // 关闭输入流
            is.close();
            // 登出服务器
            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 判断输入流是否存在
                if (null != is) {
                    // 关闭输入流
                    is.close();
                }
                // 判断连接是否存在
                if (ftp.isConnected()) {
                    // 断开连接
                    ftp.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }



    /**
     * 连接sftp服务器
     *
     * @param host     主机
     * @param port     端口
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public static ChannelSftp connect(String host, int port, String username,
                               String password) {
        ChannelSftp sftp = null;
        try {
            JSch jsch = new JSch();
            jsch.getSession(username, host, port);
            Session sshSession = jsch.getSession(username, host, port);
            System.out.println("Session created.");
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            System.out.println("Session connected.");
            System.out.println("Opening Channel.");
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
            // sshSession.disconnect();
            System.out.println("Connected to " + host + ".");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sftp;
    }

    /**
     * 上传文件
     *
     * @param directory  上传的目录
     * @param file 要上传的文件
     * @param sftp
     */
    public static boolean upload(String directory, File file, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            sftp.put(new FileInputStream(file), file.getName());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * @Author tkk
     * @Description //MultipartFile 转File
     * @Date  2021/1/8 11:27
     * @Param
     * @return
     **/
    public static File transferToFile(MultipartFile file) throws Exception {

        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }
    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

