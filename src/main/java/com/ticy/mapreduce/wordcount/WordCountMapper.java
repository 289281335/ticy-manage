package com.ticy.mapreduce.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author tkk
 * @Time 2021/7/28 11:59
 * @Description todo
 */
public class WordCountMapper extends Mapper {
    Text k = new Text();
    IntWritable v = new IntWritable(1);


    @Override
    protected void map(Object key, Object value, Context context) throws IOException, InterruptedException {


        // 1 获取一行
        String line = value.toString();
        // 2 切割
        String[] words = line.split(" ");
        // 3 输出
        for (String word : words) {
            k.set(word);
            context.write(k, v);
        }

    }
}
