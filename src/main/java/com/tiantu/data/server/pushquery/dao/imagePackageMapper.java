package com.tiantu.data.server.pushquery.dao;

import com.tiantu.data.server.pushquery.entity.imagePackage;

public interface imagePackageMapper {
    int deleteByPrimaryKey(String id);

    int insert(imagePackage record);

    int insertSelective(imagePackage record);

    imagePackage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(imagePackage record);

    int updateByPrimaryKey(imagePackage record);
}