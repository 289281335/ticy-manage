package com.tiantu.data.server.sysman.dao;

import com.tiantu.data.server.sysman.entity.TaskLog;

public interface TaskLogMapper {
    int insert(TaskLog record);

    int insertSelective(TaskLog record);
}