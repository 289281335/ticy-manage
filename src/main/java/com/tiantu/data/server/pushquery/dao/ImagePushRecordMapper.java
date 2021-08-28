package com.tiantu.data.server.pushquery.dao;

import com.tiantu.data.server.pushquery.entity.ImagePushRecord;

public interface ImagePushRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(ImagePushRecord record);

    int insertSelective(ImagePushRecord record);

    ImagePushRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ImagePushRecord record);

    int updateByPrimaryKey(ImagePushRecord record);
}