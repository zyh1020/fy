package com.rsyb.Utils;

import java.io.File;
import java.util.UUID;

public class UploadUtils {

    // 根据唯一文件名生成 hashcode目录分离算法
    public static String generateRandomDir(String uuidFileName) {
        // 获得唯一文件名的hashcode
        int hashcode = uuidFileName.hashCode();
        // 获得一级目录
        int d1 = hashcode & 0xf;
        // 获得二级目录
        int d2 = (hashcode >>> 4) & 0xf;
        return "/" + d2 + "/" + d1 + "/";
    }

    public static void main(String[] args) {
        String picName = UUID.randomUUID().toString().replaceAll("-","");
        String s = generateRandomDir(picName);
//        File file = new File("F:\\testPic\\"+"\\7\\7\\");
//        if(!file.exists()){
//            boolean mkdirs = file.mkdirs();
//            if(!mkdirs)
//            {
//                System.out.println("创建失败");
//            }
//        }

        System.out.println(s);
    }
}
