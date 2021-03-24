package com.ticy.manage.dao;

import com.ticy.manage.entity.UserInfo;

public interface UserInfoMapper {
    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo getUserInfo(String id);
}