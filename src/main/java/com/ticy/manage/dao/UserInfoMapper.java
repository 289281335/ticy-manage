package com.ticy.manage.dao;

import com.ticy.manage.entity.UserInfo;
import com.ticy.manage.recursion.Menu;

import java.util.List;

public interface UserInfoMapper {
    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo getUserInfo(String id);

    List<Menu> getMenuByPid(String parentId);

}