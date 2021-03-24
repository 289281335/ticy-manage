package com.ticy.manage.service.impl;

import com.ticy.manage.dao.UserInfoMapper;
import com.ticy.manage.entity.UserInfo;
import com.ticy.manage.service.ShardingSphereTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author tkk
 * @Time 2021/3/24 14:51
 * @Description todo
 */
@Service
public class ShardingSphereTestServiceImpl implements ShardingSphereTestService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getUserInfo(String id) {

        UserInfo userInfo = userInfoMapper.getUserInfo(id);
        return userInfo;
    }

    @Override
    public int addUser(UserInfo user) {

        int i = userInfoMapper.insert(user);
        return i;
    }
}
