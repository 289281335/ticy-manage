package com.ticy.manage.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author tkk
 * @Time 2021/1/8 9:34
 * @Description todo
 */
@Service
public interface MytestService {
    boolean FTPupload(MultipartFile file) throws IOException;

    boolean upload(MultipartFile file);
}
