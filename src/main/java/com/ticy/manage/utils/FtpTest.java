package com.ticy.manage.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author tkk
 * @Time 2021/7/15 16:21
 * @Description todo
 */
public class FtpTest {

    public  boolean isFTPFileExist(String filePath, String user,
                                         String passward, String ip, int port) {
        FTPClient ftp = new FTPClient();
        try {
            // 连接ftp服务器
            ftp.connect(ip, port);
            // 登陆
            ftp.login(user, passward);
            // 检验登陆操作的返回码是否正确
            if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                ftp.disconnect();
                return false;
            }
            ftp.changeWorkingDirectory(filePath);
            FTPFile ftpFile = ftp.mlistFile(filePath);
            System.out.println("mlistFile是否存在："+(ftpFile!=null));
            FTPFile[] ftpFiles = ftp.listFiles(filePath);
            int length = ftpFiles.length;
            System.out.println("listFiles是否存在："+(length==1));
            ftp.enterLocalPassiveMode(); //开启本地被动模式--Linux，windowns开启主动enterLocalActiveMode

            // 设置文件类型为二进制，与ASCII有区别
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            // 设置编码格式
            ftp.setControlEncoding("GBK");

            // 提取绝对地址的目录以及文件名
            filePath = filePath.replace(ip + ":" + port + "/", "");
            String dir = filePath.substring(0, filePath.lastIndexOf("/"));
            String file = filePath.substring(filePath.lastIndexOf("/") + 1);

            // 进入文件所在目录，注意编码格式，以能够正确识别中文目录
            ftp.changeWorkingDirectory(new String(dir.getBytes("GBK"),
                    FTP.DEFAULT_CONTROL_ENCODING));

            // 检验文件是否存在
            InputStream is = ftp.retrieveFileStream(new String(file
                    .getBytes("GBK"), FTP.DEFAULT_CONTROL_ENCODING));
            if (is == null || ftp.getReplyCode() == FTPReply.FILE_UNAVAILABLE) {
                return false;
            }

            int size = is.available();
            System.out.println("大小:"+(size*1024*1024));

            if (is != null) {
                is.close();
                ftp.completePendingCommand();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            //logger.error(e.getMessage());
        } finally {
            if (ftp != null) {
                try {
                    ftp.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                    //logger.error(e.getMessage());
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        FtpTest ftp = new FtpTest();
        boolean f = ftp.isFTPFileExist("/Output/M20210715154041b2a4/GF1/PMS1/GF1_PMS1_E109.1_N34.7_20210417_L1A0005598933.tar.gz", "administrator", "Qwxqldm@123", "10.0.34.56",21);
        System.out.println(f);
    }
}
