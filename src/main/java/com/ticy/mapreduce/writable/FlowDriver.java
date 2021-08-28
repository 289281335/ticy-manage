package com.ticy.mapreduce.writable;

import com.alibaba.fastjson.JSON;
import org.apache.avro.data.Json;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @Author tkk
 * @Time 2021/7/29 15:15
 * @Description todo
 */
public class FlowDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        //1 获取 job 对象
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        //2 关联本 Driver 类
        job.setJarByClass(FlowDriver.class);
        //3 关联 Mapper 和 Reducer
        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReducer.class);

        //4 设置 Map 端输出 KV 类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);

        //5 设置程序最终输出的 KV 类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        //6 设置程序的输入输出路径
        FileInputFormat.setInputPaths(job, new Path("D:\\hadoop\\wordcount\\Writable_input"));
        FileOutputFormat.setOutputPath(job, new Path("D:\\hadoop\\wordcount\\Writable_output1"));
        //7 提交 Job
        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);

    }
}
