package com.ticy.manage.service.impl;

import com.ticy.manage.config.RabbitConfig;
import com.ticy.manage.service.RabbitMQService;
import com.ticy.manage.utils.SpringContextJobUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author tkk
 * @Time 2021/4/19 20:58
 * @Description todo
 */
@Service
public class RabbitMQServiceImpl implements RabbitMQService {
   // @Autowired
   // private  RabbitTemplate rabbitTemplate;




    public static String sendDirectMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        RabbitTemplate rabbitTemplate = (RabbitTemplate) SpringContextJobUtil.getBean("rabbitTemplate");
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
        return "ok";
    }

    public static void main(String[] args) {
        sendDirectMessage();
    }

}
