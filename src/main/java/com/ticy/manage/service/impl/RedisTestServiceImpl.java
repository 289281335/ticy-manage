package com.ticy.manage.service.impl;

import com.ticy.manage.dao.UserInfoMapper;
import com.ticy.manage.entity.UserInfo;
import com.ticy.manage.service.RedisTestService;
import com.ticy.manage.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author tkk
 * @Time 2021/8/27 16:47
 * @Description 测试缓存；
 */
@Service
public class RedisTestServiceImpl implements RedisTestService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private RedisUtil redisUtil;



    @Override
    public UserInfo getUserInfo(String id) {

        UserInfo userInfo;
        userInfo = (UserInfo)redisUtil.get("userinfo");

        if(userInfo == null){
            System.out.println("查询redis结果为NULL,将查询数据库");

            userInfo = userInfoMapper.getUserInfo(id);

            System.out.println("将查询数据库的结果放入Redis,userInfo:"+userInfo);
            boolean flag = redisUtil.set("userinfo", userInfo);

            System.out.println("插入redis结果:"+flag);
        }else {
            System.out.println("----查询Redis命中----:"+userInfo);
        }
        return userInfo;
    }
}
