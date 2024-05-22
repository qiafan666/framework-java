package com.ning.web.i18n;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 该类用于将 properties 文件的编码由 ISO-8859-1 转换为 UTF-8
 */
public class PropertiesConverter {

    public static void main(String[] args) {

        List<String> files = new ArrayList<>();
        files.add("D:\\java\\src\\framework-java\\src\\main\\resources\\i18n\\messages.properties");
        files.add("D:\\java\\src\\framework-java\\src\\main\\resources\\i18n\\messages_zh_CN.properties");
        files.add("D:\\java\\src\\framework-java\\src\\main\\resources\\i18n\\messages_en_US.properties");

        for (String file : files) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "ISO-8859-1"))) {

                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append(System.lineSeparator());
                }

                // 使用 UTF-8 编码将内容重新写回文件
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"))) {
                    writer.write(content.toString());
                }

                System.out.println("Conversion completed successfully!");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
