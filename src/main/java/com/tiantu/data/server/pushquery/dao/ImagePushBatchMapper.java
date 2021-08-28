package com.tiantu.data.server.pushquery.dao;

import com.tiantu.data.server.pushquery.entity.ImagePushBatch;

public interface ImagePushBatchMapper {
    int deleteByPrimaryKey(String id);

    int insert(ImagePushBatch record);

    int insertSelective(ImagePushBatch record);

    ImagePushBatch selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ImagePushBatch record);

    int updateByPrimaryKey(ImagePushBatch record);
}