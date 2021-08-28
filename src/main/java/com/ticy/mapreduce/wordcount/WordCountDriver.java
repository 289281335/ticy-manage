package com.ticy.mapreduce.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @Author tkk
 * @Time 2021/7/28 12:00
 * @Description todo
 */
public class WordCountDriver {
    private static Logger log = LoggerFactory.getLogger(WordCountDriver.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 1 获取配置信息以及获取 job 对象
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        // 2 关联本 Driver 程序的 jar
        job.setJarByClass(WordCountDriver.class);
        // 3 关联 Mapper 和 Reducer 的 jar
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);
        // 4 设置 Mapper 输出的 kv 类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        // 5 设置最终输出 kv 类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // 如果不设置 InputFormat，它默认用的是 TextInputFormat.class
        job.setInputFormatClass(CombineTextInputFormat.class);
        //虚拟存储切片最大值设置 4m
        CombineTextInputFormat.setMaxInputSplitSize(job, 20971520);

        // 6 设置输入和输出路径
        //FileInputFormat.setInputPaths(job, new Path("D:\\hadoop\\wordcount\\input"));
        //FileOutputFormat.setOutputPath(job, new Path("D:\\hadoop\\wordcount\\output"));
        FileInputFormat.setInputPaths(job, new Path("D:\\hadoop\\wordcount\\inputcombinetextinputformat"));
        FileOutputFormat.setOutputPath(job, new Path("D:\\hadoop\\wordcount\\outputcombinetextinputformat1"));

       // FileInputFormat.setInputPaths(job, new Path(args[0]));
       // FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // 7 提交 job
        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);

    }
}
