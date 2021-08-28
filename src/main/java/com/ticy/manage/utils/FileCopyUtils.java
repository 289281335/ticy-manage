package com.ticy.manage.utils;

import java.io.*;

/**
 * @Author tkk
 * @Time 2021/4/27 14:40
 * @Description todo
 */
public class FileCopyUtils {
    
    
    /*
     * @Author tkk
     * @Description //复制一个文件到目标路径，并且修改文件名称
     * @Date  2021/4/27 14:41
     * @Param 
     * @return 
     **/
    public static void copyByStream(File fromFile, File toFile)
    {
        FileInputStream ins = null;
        FileOutputStream out = null;
        try
        {
            ins = new FileInputStream(fromFile);
            /*
             * 使用FileOutputStream写文件
             * 如果目标文件不存在，则FileOutputStream会创建目标文件(前提是父路径文件对象必须存在)。
             */
            out = new FileOutputStream(toFile);
            byte[] buf = new byte[1024];
            int size = 0;
            // 每次读取1024个字节,然后写入1024自己
            while ((size = ins.read(buf)) != -1)
            {
                out.write(buf, 0, size);
            }

        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally
        {
            if (ins != null)
            {
                try
                {
                    ins.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (out != null)
            {
                try
                {
                    out.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

        }
    }// 方法结束

}
