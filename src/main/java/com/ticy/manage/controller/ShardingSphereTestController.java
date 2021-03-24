package com.ticy.manage.controller;

import com.ticy.manage.entity.UserInfo;
import com.ticy.manage.service.ShardingSphereTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author tkk
 * @Time 2021/3/24 14:42
 * @Description todo
 */
@RestController
public class ShardingSphereTestController {

    @Autowired
    private ShardingSphereTestService shardingSphereTestService;

    @RequestMapping("/userinfo")
    public UserInfo getUserInfo(String id) {
        UserInfo userinfo = shardingSphereTestService.getUserInfo(id);

        return userinfo;
    }

    @RequestMapping("/addUser")
    public String addUser(String username,String password,String idcard,int age,String id) {
        UserInfo user = new UserInfo();
        user.setAge(age);
        user.setId(id);
        user.setIdcard(idcard);
        user.setUsername(username);
        user.setPassword(password);
        int i = shardingSphereTestService.addUser(user);
        System.out.println(i);
        if(i != -1){
            return "success";
        }else{
            return "fail";
        }

    }
}
