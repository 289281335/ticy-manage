package com.ticy.manage.controller;

import com.jcraft.jsch.ChannelSftp;
import com.ticy.manage.config.RabbitConfig;
import com.ticy.manage.model.Result;
import com.ticy.manage.myannotation.SystemLog;
import com.ticy.manage.service.MytestService;
import com.ticy.manage.service.RabbitMQService;
import com.ticy.manage.service.impl.MytestServiceImpl;
import com.ticy.manage.service.impl.RabbitMQServiceImpl;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
    @Autowired
    private RabbitMQServiceImpl rabbitMQServiceImpl;

    @GetMapping("/test")
    @SystemLog(operationType = "查询",operationName = "测试AOP日志")
    public Result test() {
        return new Result("1001", "测试成功", "西安真美丽");
    }


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

    @Autowired
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    @GetMapping("/sendDirectMessage")
    public String sendDirectMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE, RabbitConfig.IMAGE_PUSH, map);
        return "ok";
    }


}
