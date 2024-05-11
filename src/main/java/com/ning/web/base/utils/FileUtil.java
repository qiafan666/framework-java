/*
 * Copyright (c) 2018. Aberic - aberic@qq.com - All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language goverdoaction permissions and
 * limitations under the License.
 */

package com.ning.web.base.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class FileUtil {

    private static Logger log = LogManager.getLogger(FileUtil.class);

    private static final int BUFFER_SIZE = 10 * 1024;

    /**
     * @Title: compress
     * @Description: 压缩文件
     * @param filePaths 需要压缩的文件地址列表（绝对路径）
     * @param zipFilePath 需要压缩到哪个zip文件（无需创建这样一个zip，只需要指定一个全路径）
     * @param keepDirStructure 压缩后目录是否保持原目录结构
     * @throws IOException
     * @return int 压缩成功的文件个数
     */
    public static int compress(List<Map<String,String>> filePaths, String zipFilePath, Boolean keepDirStructure) {
        byte[] buf = new byte[BUFFER_SIZE];
        File zipFile = new File(zipFilePath);
        int fileCount = 0;//记录压缩了几个文件？
        ZipOutputStream zos = null;
        try {
            // zip文件不存在，则创建文件，用于压缩
            if(!zipFile.exists()) {
                zipFile.createNewFile();
            }
            zos = new ZipOutputStream(new FileOutputStream(zipFile));
            for(int i = 0; i < filePaths.size(); i++){
                Map<String,String> fileMap = filePaths.get(i);
                String relativePath = fileMap.get("url");
                if(StringUtils.isEmpty(relativePath)){
                    continue;
                }
                File sourceFile = new File(relativePath); //绝对路径找到file
                if(sourceFile == null || !sourceFile.exists()){
                    continue;
                }
                FileInputStream fis = new FileInputStream(sourceFile);
                if(keepDirStructure != null && keepDirStructure){
                    // 保持目录结构
                    zos.putNextEntry(new ZipEntry(relativePath));
                }else{
                    // 直接放到压缩包的根目录
                    zos.putNextEntry(new ZipEntry(fileMap.get("fileName")));
                }
                //System.out.println("压缩当前文件："+sourceFile.getName());
                int len;
                while((len = fis.read(buf)) > 0){
                    zos.write(buf, 0, len);
                }
                fis.close();
                zos.closeEntry();
                fileCount++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(Objects.nonNull(zos)){
                try{
                    zos.close();
                }catch (Exception e1){
                    e1.printStackTrace();
                }
            }
        }
        return fileCount;
    }

    /**
     * 解压指定zip文件
     *
     * @param unZipFile 压缩文件的路径
     * @param destFile  解压到的目录
     */
    public static void unZip(String unZipFile, String destFile) {
        FileOutputStream fileOut;
        File file;
        InputStream inputStream;
        try {
            // 生成一个zip的文件
            ZipFile zipFile = new ZipFile(unZipFile);
            Enumeration enumeration = zipFile.entries();
            while (enumeration.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) enumeration.nextElement();
                file = new File(destFile + File.separator + entry.getName());
                if (entry.isDirectory()) {
                    file.mkdirs();
                } else {
                    // 如果指定文件的目录不存在,则创建之.
                    File parent = file.getParentFile();
                    if (!parent.exists()) {
                        parent.mkdirs();
                    }
                    // 获取出该压缩实体的输入流
                    inputStream = zipFile.getInputStream(entry);
                    fileOut = new FileOutputStream(file);
                    int length = 0;
                    // 将实体写到本地文件中去
                    byte[] buffer = new byte[BUFFER_SIZE];
                    while ((length = inputStream.read(buffer)) > 0) {
                        fileOut.write(buffer, 0, length);
                    }
                    fileOut.close();
                    inputStream.close();
                }
            }
            zipFile.close();
        } catch (IOException e) {
            log.error("Error", e);
        }
    }

    public static void unZipAndSave(MultipartFile file, String parentPath, String childrenPath) throws IOException {
        String fileName = file.getOriginalFilename();
        File dest = new File(parentPath + "/" + fileName);
        File childrenFile = new File(childrenPath);
        if (childrenFile.exists()) { // 判断文件父目录是否存在
            deleteFiles(childrenPath);
        }
        childrenFile.mkdirs();
        File absoluteFile = dest.getAbsoluteFile();
        log.info("unZipAndSave-absoluteFile={}",absoluteFile);
        file.transferTo(absoluteFile); //保存文件
        unZip(String.format("%s/%s", parentPath, fileName), parentPath);
        dest.delete();
    }

    public static void save(MultipartFile skFile, MultipartFile certificateFile, String skPath, String certificatePath) throws IOException {
        save(skFile, skPath);
        save(certificateFile, certificatePath);
    }

    public static void save(MultipartFile serverCrtFile, MultipartFile clientCertFile, MultipartFile clientKeyFile,
                            String serverCrtFilePath, String clientCertFilePath, String clientKeyFilePath) throws IOException {
        if(serverCrtFile!=null && serverCrtFile.getSize()>0){
            save(serverCrtFile, serverCrtFilePath);
        }
        if(clientCertFile!=null && clientCertFile.getSize()>0){
            save(clientCertFile, clientCertFilePath);
        }
        if(clientKeyFile!=null && clientKeyFile.getSize()>0){
            save(clientKeyFile, clientKeyFilePath);
        }

    }

    public static void save(MultipartFile file, String path) throws IOException {
        File dest = new File(path);
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        if (dest.exists()) {
            dest.delete();
        }
        File absoluteFile = dest.getAbsoluteFile();
        file.transferTo(absoluteFile); //保存文件
    }

    /**
     * 通过递归得到某一路径下所有的目录及其文件并删除所有文件
     * @param filePath 文件夹路径
     */
    public static void deleteFiles(String filePath) {
        File root = new File(filePath);
        if (!root.exists()) {
            return;
        }
        File[] files = root.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                deleteFiles(file.getAbsolutePath());// 递归调用
                file.delete();
                log.debug(String.format("显示%s下所有子目录及其文件", file.getAbsolutePath()));
            } else {
                file.delete();
                log.debug(String.format("显示%s下所有子目录%s====文件名：%s", filePath, file.getAbsolutePath(), file.getName()));
            }
        }
    }

    /**
     * create by:  zhouxq
     * description: 删除单个文件
     * create time: 2022/5/12/012 23:30
     * @params [filePath]
     * @return void
     */
    public static void deleteFile(String filePath) {
        File root = new File(filePath);
        if (!root.exists()) {
            return;
        }
        root.delete();
    }

    /**
     * 文件夹copy
     * @param srcPath
     * @param destPath
     */
    public static void dirCopy(String srcPath, String destPath) {
        File src = new File(srcPath);
        if (!new File(destPath).exists()) {
            new File(destPath).mkdirs();
        }
        for (File s : src.listFiles()) {
            if (s.isFile()) {
                fileCopy(s.getPath(), destPath + File.separator + s.getName());
            } else {
                dirCopy(s.getPath(), destPath + File.separator + s.getName());
            }
        }
    }

    public static void fileCopy(String srcPath, String destPath) {
        File src = new File(srcPath);
        File dest = new File(destPath);
        //使用jdk1.7 try-with-resource 释放资源，并添加了缓存流
        try(InputStream is = new BufferedInputStream(new FileInputStream(src));
            OutputStream out =new BufferedOutputStream(new FileOutputStream(dest))) {
            byte[] flush = new byte[1024];
            int len = -1;
            while ((len = is.read(flush)) != -1) {
                out.write(flush, 0, len);
            }
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] fileToBytes(String filePath) {
        byte[] buffer = null;
        File file = new File(filePath);
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        try {
            fis = new FileInputStream(file);
            bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            buffer = bos.toByteArray();
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(FileTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            //Logger.getLogger(FileTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (null != fis) {
                    fis.close();
                }
            } catch (IOException ex) {
                //Logger.getLogger(FileTest.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                try {
                    if(null != bos){
                        bos.close();
                    }
                } catch (IOException ex) {
                    // Logger.getLogger(FileTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return buffer;
    }
}
