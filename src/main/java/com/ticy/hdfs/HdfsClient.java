package com.ticy.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


/**
 * @Author tkk
 * @Time 2021/7/23 16:08
 * @Description HdfsClient
 */
public class HdfsClient {

    @Test
    public void hdfsMkdir() throws URISyntaxException, IOException, InterruptedException {
     // 1 获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:8020"), configuration,"root");
        // 2 创建目录
        //boolean m = fs.mkdirs(new Path("/hdfs/mytest/"));
        //System.out.println(m);

        // 2 上传文件
        //fs.copyFromLocalFile(new Path("C:\\Users\\Ticy\\Desktop\\1.txt"), new Path("/hdfs/mytest/"));
        //2 文件下载
        fs.copyToLocalFile(false,new Path("/hdfs/mytest/1.txt"),new Path("D:\\hadoop"),true);

        // 3 关闭资源
        fs.close();


    }

}




