package com.ticy.manage.service.impl;

import com.jcraft.jsch.ChannelSftp;
import com.ticy.manage.entity.UserInfo;
import com.ticy.manage.service.MytestService;
import com.ticy.manage.service.ShardingSphereTestService;
import com.ticy.manage.utils.FTPUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author tkk
 * @Time 2021/1/8 9:36
 * @Description todo
 */
@Service
public class MytestServiceImpl implements MytestService {
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
     */
    private String url = "172.16.1.12";
    private int port = 22;
    private String userName = "root";
    private String password = "tiantu.com";
    private String storePath = "/root/temp/";
    private String fileName = "";
    private String directory = "/root/temp/";

    @Override
    public boolean FTPupload(MultipartFile file) {
        fileName = file.getOriginalFilename();
        try {
             byte [] byteArr=file.getBytes();
             InputStream is = new ByteArrayInputStream(byteArr);
             boolean b = FTPUtils.storeFile(url, port, userName, password, storePath, fileName, is);
             return b;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * @Author tkk
     * @Description //上传文件
     * @Date  2021/1/8 11:31
     * @Param
     * @return
     **/
    @Override
    public boolean upload(MultipartFile file) {
        //连接FTP
        ChannelSftp channelSftp = FtpConnect();
        File file1 = null;
        try {
            file1 = FTPUtils.transferToFile(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FTPUtils.upload(directory, file1, channelSftp);
    }







    /*
     * @Author tkk
     * @Description //测试FTP连接
     * @Date  2021/1/8 10:58
     * @Param 
     * @return 
     **/

    public ChannelSftp FtpConnect() {
        return FTPUtils.connect(url, port, userName, password);
    }



}
