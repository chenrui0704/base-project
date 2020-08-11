package com.base.frame.util;

import org.springframework.core.io.ClassPathResource;

import java.io.*;

/**
 *  文件工具类
 *
 * */
public class FileUtil {

    public static String readerResourcesDirectory(String path){
        try {
            return reader(new ClassPathResource(path).getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String reader(String path){
        try {
            return reader(new FileInputStream(new File(path)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String reader(File file){
        try {
            return reader(new FileInputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String reader(InputStream inputStream){
        try {
            return reader(new BufferedReader(new InputStreamReader(inputStream, "UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String reader(BufferedReader reader){
        StringBuffer content = new StringBuffer();
        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return content.toString();
    }

}
