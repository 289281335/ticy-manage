package com.ticy.manage.controller;

import com.ticy.manage.entity.UserInfo;
import com.ticy.manage.service.RedisTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author tkk
 * @Time 2021/8/27 16:44
 * @Description todo
 */

@RestController
public class RedisTestController {

    @Autowired
   private RedisTestService redisTestService;

    @RequestMapping("/user")
    public UserInfo getUserInfo(String id) {
        UserInfo userinfo = redisTestService.getUserInfo(id);

        return userinfo;
    }

}
