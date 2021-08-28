package com.ticy.manage.utils;

import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.springframework.util.StringUtils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.zip.GZIPInputStream;
import java.io.*;
/**
 * @Author tkk
 * @Time 2021/3/29 17:53
 * @Description todo
 */
public class FileAnalysisUtils {

    public static void analysisTarGZXML(){

    }

    public static void readTarFile(File tarFile){
        String orginFileName = tarFile.getName();

        try {
            ArchiveInputStream archiveInputStream = null;

            if (StringUtils.endsWithIgnoreCase(tarFile.getName(), ".gz")) {
                archiveInputStream = new ArchiveStreamFactory()

                        .createArchiveInputStream("tar", new GZIPInputStream(new BufferedInputStream(new FileInputStream(tarFile))));

            } else {
                archiveInputStream = new ArchiveStreamFactory()

                        .createArchiveInputStream("tar", new BufferedInputStream(new FileInputStream(tarFile)));

            }

            TarArchiveEntry entry = null;

            while ((entry = (TarArchiveEntry) archiveInputStream.getNextEntry()) != null) {
                System.out.println(entry.getName());
                if (entry.getSize() > 0) {
                    String fileName = orginFileName.substring(0, orginFileName.indexOf(".tar.gz")) + "-MSS1.xml";
                    System.out.println(fileName);
                    System.out.println("----------------------------------------------------------------");
                    if(fileName.equalsIgnoreCase(entry.getName())){
                        System.out.println("fileSize is " + entry.getSize());

                        byte[] bytes = FileUtil.getContent(archiveInputStream);

                        System.out.println(new String(bytes));

                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public static void main(String[] args) {
        readTarFile(new File("E:\\A-天图软件\\项目资料\\秦岭项目\\数据推送系统\\卫星原始数据\\GF1_PMS1_E105.4_N32.8_20181122_L1A0003616269.tar.gz"));

// System.out.println(JSON.toJSONString(data));
//E:\A-天图软件\项目资料\秦岭项目\数据推送系统\卫星原始数据\GF1C_PMS_E108.7_N33.6_20210101_L1A1021691650.tar.gz
        //E:\satellite\GF1_PMS1_E109.2_N38.3_20170803_L1A0002525088.tar.gz
        //GF1C_PMS_E108.9_N34.1_20210101_L1A1021691590.tar.gz
        //GF1_PMS1_E105.4_N32.8_20181122_L1A0003616269.tar.gz
    }
}
