package com.ticy.manage.controller;

import com.jcraft.jsch.ChannelSftp;
import com.ticy.manage.model.Result;
import com.ticy.manage.service.MytestService;
import com.ticy.manage.service.impl.MytestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author tkk
 * @Time 2021/1/7 15:29
 * @Description todo
 */
@RestController
public class Mytest {

    @Autowired
    private MytestService mytestService;
    @Autowired
    private MytestServiceImpl mytestServiceImpl;


    @PostMapping("/uploadtest")
    public Result mytestUpload(@RequestParam("uploadFile") MultipartFile file) {
        boolean flag = false;
        try {
            flag =  mytestService.FTPupload(file);
        }catch (IOException e){
            e.printStackTrace();
        }
        if(!flag){
            System.out.println("上传失败");
            return new Result(null, "上传失败", null);
        }
        return new Result(null, "上传成功", null);
    }


    @GetMapping("/ftpConnect")
    public Result ftpConnect() {
        ChannelSftp sftp = mytestServiceImpl.FtpConnect();
        if(sftp != null){
            System.out.println();
         return new Result(null, "链接成功", null);
        }else{
            return new Result(null, "链接失败", null);
        }

    }



    @PostMapping("/upload")
    public Result upload(@RequestParam("uploadFile") MultipartFile file) {
        boolean flag = false;
        flag =  mytestService.upload(file);
        if(!flag){
            System.out.println("上传失败");
            return new Result(null, "上传失败", null);
        }
        return new Result(null, "上传成功", null);
    }
}
