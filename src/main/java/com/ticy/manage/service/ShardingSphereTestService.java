package com.ticy.manage.service;

import com.ticy.manage.entity.UserInfo;

/**
 * @Author tkk
 * @Time 2021/3/24 14:44
 * @Description todo
 */
public interface ShardingSphereTestService {

    UserInfo getUserInfo(String id);

    int addUser(UserInfo user);
}
