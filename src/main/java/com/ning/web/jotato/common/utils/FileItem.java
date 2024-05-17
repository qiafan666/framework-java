package com.ning.web.jotato.common.utils;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import javax.activation.MimetypesFileTypeMap;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileItem {
    private String fileName;
    private String mimeType;
    private byte[] content;

    public FileItem() {
    }

    public FileItem(String fileName, byte[] content) {
        this.fileName = fileName;
        this.content = content;
    }

    public FileItem(String fileName, byte[] content, String mimeType) {
        this.fileName = fileName;
        this.content = content;
        this.mimeType = mimeType;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMimeType() {
        if (this.mimeType == null) {
            this.mimeType = this.getContentType(this.fileName);
        }

        return this.mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public byte[] getContent() {
        return this.content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    private String getContentType(String fileName) {
        String defaultType = "application/octet-stream";
        if (fileName != null && !fileName.isEmpty()) {
            String contentType = null;

            try {
                Path path = Paths.get(fileName);
                contentType = Files.probeContentType(path);
            } catch (Exception var5) {
                var5.printStackTrace();
            }

            if (contentType == null || contentType.isEmpty()) {
                contentType = (new MimetypesFileTypeMap()).getContentType(fileName);
            }

            return contentType;
        } else {
            return defaultType;
        }
    }
}

