package com.ning.web.generator;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CURDGenerator {

    private static final String FILE_Controller_CONTENT = " " +
            "   @Resource\n" +
            "    private I€NameService i€NameService;\n" +
            "\n" +
            "\n" +
            "    /**\n" +
            "     * 列表查询\n" +
            "     * @param request 查询参数\n" +
            "     * @return BaseResultData<Page<Resp€NameList>>\n" +
            "     * @author ning\n"+
            "     */\n" +
            "    @PostMapping(value = \"/list\")\n" +
            "    public BaseResultData<Page<Resp€NameList>> list(@RequestBody Req€NameList request) {\n" +
            "        Page<Resp€NameList> page =  i€NameService.list(request);\n" +
            "        return BaseResultData.success(page);\n" +
            "    }\n" +
            "\n" +
            "    /**\n" +
            "     * 创建\n" +
            "     * @param request 创建参数\n" +
            "     * @return BaseResult\n" +
            "     * @author ning\n"+
            "     */\n"+
            "    @PostMapping(value = \"/create\")\n" +
            "    public BaseResult create(@RequestBody Req€NameCreate request) {\n" +
            "        i€NameService.create(request);\n" +
            "        return BaseResultData.success();\n" +
            "    }\n" +
            "\n" +
            "    /**\n" +
            "     * 删除\n" +
            "     * @param request 删除参数\n" +
            "     * @return BaseResult\n" +
            "     * @author ning\n"+
            "     */\n"+
            "    @PostMapping(value = \"/delete\")\n" +
            "    public BaseResult delete(@RequestParam(\"ids\") List<Long> request) {\n" +
            "        i€NameService.delete(request);\n" +
            "        return BaseResultData.success();\n" +
            "    }\n" +
            "\n" +
            "    /**\n" +
            "     * 更新\n" +
            "     * @param request 更新参数\n" +
            "     * @return BaseResult\n" +
            "     * @author ning\n"+
            "     */\n"+
            "    @PostMapping(value = \"/update\")\n" +
            "    public BaseResult update(@RequestBody Req€NameUpdate request) {\n" +
            "        i€NameService.update(request);\n" +
            "        return BaseResult.success();\n" +
            "    }\n"
            ;

    private static final String FILE_Service_CONTENT = " " +
            "   void create(Req€NameCreate request);\n" +
            "\n" +
            "    Page<Resp€NameList> list(Req€NameList request);\n" +
            "\n" +
            "    void update(Req€NameUpdate request);\n" +
            "\n" +
            "    void delete(List<Long> request);";
    private static final String FILE_Impl_CONTENT = " " +
            "   @Override\n" +
            "    public void create(Req€NameCreate request) {\n" +
            "\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public Page<Resp€NameList> list(Req€NameList request) {\n" +
            "        return null;\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void update(Req€NameUpdate request) {\n" +
            "\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void delete(List<Long> request) {\n" +
            "\n" +
            "    }";
    private static final String ReqCreate_CONTENT = "" +
            "import lombok.Data;\n" +
            "import lombok.EqualsAndHashCode;\n" +
            "\n" +
            "@EqualsAndHashCode(callSuper = true)\n" +
            "@Data\n" +
            "public class Req€NameCreate extends BaseReq {\n" +
            "}";
    private static final String ReqUpdate_CONTENT = "" +
            "import lombok.Data;\n" +
            "import lombok.EqualsAndHashCode;\n" +
            "\n" +
            "@EqualsAndHashCode(callSuper = true)\n" +
            "@Data\n" +
            "public class Req€NameUpdate extends BaseReq {\n" +
            "\n" +
            "    private Long appId;\n" +
            "}\n";
    private static final String ReqList_CONTENT = "" +
            "import lombok.Data;\n" +
            "import lombok.EqualsAndHashCode;\n" +
            "\n" +
            "@EqualsAndHashCode(callSuper = true)\n" +
            "@Data\n" +
            "public class Req€NameList extends BasePageQuery {\n" +
            "}\n";
    private static final String RespList_CONTENT = "" +
            "import lombok.Data;\n" +
            "\n" +
            "@Data\n" +
            "public class Resp€NameList {\n" +
            "}\n";

    // controller、service、impl、req、resp路径
    // 注意：当前自动生成代码是在CodeGenerator.java中基础上生成的，一旦CodeGenerator.java修改，这里的代码也要改
    private static final String controllerPath = "D:\\java\\src\\framework-java\\src\\main\\java\\com\\ning\\web\\controller";
    private static final String servicePath = "D:\\java\\src\\framework-java\\src\\main\\java\\com\\ning\\web\\service";
    private static final String reqPath = "D:\\java\\src\\framework-java\\src\\main\\java\\com\\ning\\web\\pojo\\req";
    private static final String respPath = "D:\\java\\src\\framework-java\\src\\main\\java\\com\\ning\\web\\pojo\\resp";

    // 主方法
    public static void main(String[] args) throws IOException {
        //读取目录下的所有文件

        ArrayList<String> list = new ArrayList<>();
        list.add(controllerPath);
        list.add(servicePath);

        for (String path : list) {
            File dir = new File(path);

            // 检查目录是否存在
            if (dir.exists() && dir.isDirectory()) {
                listFiles(dir);
            } else {
                System.out.println("The specified path is not a directory or does not exist.");
            }
        }
    }

    // 递归方法列出目录中的所有文件
    public static void listFiles(File dir) throws IOException {
        File[] files = dir.listFiles();

        // 检查目录是否为空
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                   /* System.out.println("Directory: " + file.getAbsolutePath());
                    // 递归读取子目录
                    listFiles(file);*/
                } else {
                    String fileType = "";
                    if (file.getName().endsWith("Controller.java")) {
                        fileType = "Controller";
                    } else if (file.getName().endsWith("Service.java")) {
                        fileType = "Service";
                    }else if (file.getName().endsWith("ServiceImpl.java")) {
//                        fileType = "Impl";
                    }else {
                        continue;
                    }
                    modifyFileContent(file,fileType);
                }
            }
        } else {
            System.out.println("The directory " + dir.getAbsolutePath() + " is empty.");
        }
    }

    // 修改文件内容
    public static void modifyFileContent(File file,String fileType) throws IOException {


        switch (fileType){
        case "Controller":
            String controllerName = StringUtils.replace(file.getName(), "Controller.java", "");

            List<String> clines = new ArrayList<>();
            boolean cline2 = false, cline3 = false, cline4 = false, cline5 = false, cline6 = false, cline7 = false, cline8 = false;

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                int lineNumber = 0;

                // 读取文件内容
                while ((line = reader.readLine()) != null) {

                    lineNumber++;

                    // 检查特定行的条件
                    if (lineNumber == 2 && line.trim().isEmpty()) {
                        cline2 = true;
                    }
                    if (lineNumber == 3 && line.trim().isEmpty()) {
                        cline3 = true;
                    }
                    if (lineNumber == 4 && line.contains("annotation.RequestMapping;")) {
                        cline4 = true;
                    }
                    if (lineNumber == 5 && line.trim().isEmpty()) {
                        cline5 = true;
                    }
                    if (lineNumber == 6 && line.contains("annotation.RestController;")) {
                        cline6 = true;
                    }
                    if (lineNumber == 7 && line.trim().isEmpty()) {
                        cline7 = true;
                    }
                    if (lineNumber == 8 && line.equals("/**")) {
                        cline8 = true;
                    }

                    // 如果特定行的条件都满足，并且当前行是第 19 行且为空行，则修改第 19 行内容
                    if (cline2 && cline3 && cline4 && cline5 && cline6 && cline7 && cline8 && lineNumber == 19 && line.trim().isEmpty()) {
                        String content = StringUtils.replace(FILE_Controller_CONTENT, "€Name", controllerName);
                        clines.add(content);

                        createFile(controllerName);
                    } else {
                        //不写入
                        clines.add(line);
                    }
                }
            }

            // 写回文件内容
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String modifiedLine : clines) {
                    writer.write(modifiedLine);
                    writer.newLine();
                }
            }
        case "Service":
            String iName = StringUtils.replace(file.getName(), "Service.java", "");
            String serviceName = StringUtils.replace(iName, "I", "");
            List<String> slines = new ArrayList<>();
            boolean sline2 = false, sline3 = false, sline4 = false, sline5 = false,sline6 = false;

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                int lineNumber = 0;

                // 读取文件内容
                while ((line = reader.readLine()) != null) {

                    lineNumber++;

                    // 检查特定行的条件
                    if (lineNumber == 2 && line.trim().isEmpty()) {
                        sline2 = true;
                    }
                    if (lineNumber == 3 && StringUtils.contains(line.trim(), serviceName)) {
                        sline3 = true;
                    }
                    if (lineNumber == 4 && line.equals("import com.baomidou.mybatisplus.extension.service.IService;")) {
                        sline4 = true;
                    }
                    if (lineNumber == 5 && line.trim().isEmpty()) {
                        sline5 = true;
                    }
                    if (lineNumber == 6 && line.equals("/**")) {
                        sline6 = true;
                    }

                    // 如果特定行的条件都满足，并且当前行是第 19 行且为空行，则修改第 19 行内容
                    if (sline2 && sline3 && sline4 && sline5 && sline6 && lineNumber == 15 && line.trim().isEmpty()) {
                        String content = StringUtils.replace(FILE_Service_CONTENT, "€Name", serviceName);
                        slines.add(content);
                    } else {
                        //不写入
                        slines.add(line);
                    }
                }
            }

            // 写回文件内容
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String modifiedLine : slines) {
                    writer.write(modifiedLine);
                    writer.newLine();
                }
            }
        case "Impl":
            String implName = StringUtils.replace(file.getName(), "ServiceImpl.java", "");
            List<String> ilines = new ArrayList<>();
            boolean iline2 = false, iline6 = false, iline7 = false, iline8 = false,iline9 = false;

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                int lineNumber = 0;

                // 读取文件内容
                while ((line = reader.readLine()) != null) {

                    lineNumber++;

                    // 检查特定行的条件
                    if (lineNumber == 2 && line.trim().isEmpty()) {
                        iline2 = true;
                    }
                    if (lineNumber == 6 && line.equals("import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;")) {
                        iline6 = true;
                    }
                    if (lineNumber == 7 && line.equals("import org.springframework.stereotype.Service;")) {
                        iline7 = true;
                    }
                    if (lineNumber == 8 && line.trim().isEmpty()) {
                        iline8 = true;
                    }
                    if (lineNumber == 9 && line.equals("/**")) {
                        iline9 = true;
                    }


                    // 如果特定行的条件都满足，并且当前行是第 19 行且为空行，则修改第 19 行内容
                    if (iline2 && iline6 && iline7 && iline8 && iline9 && lineNumber == 19 && line.trim().isEmpty()) {
                        String content = StringUtils.replace(FILE_Impl_CONTENT, "€Name", implName);
                        ilines.add(content);
                    } else {
                        //不写入
                        ilines.add(line);
                    }
                }
            }

            // 写回文件内容
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String modifiedLine : ilines) {
                    writer.write(modifiedLine);
                    writer.newLine();
                }
            }
        }

    }

    public static void createFile(String name) throws IOException {
        createFile1(reqPath,name);
        createFile2(reqPath,name);
        createFile3(reqPath,name);
        createFile4(respPath,name);
    }

    public static void createFile1(String path,String name) throws IOException {
        String createContent = StringUtils.replace(ReqCreate_CONTENT, "€Name", name);
        String createFileName = "Req" +name + "Create.java";

        File file = new File(path, createFileName);

        // 如果文件不存在，则创建新文件
        if (!file.exists()) {
            file.createNewFile();
        }

        // 写入文件内容
        try (FileWriter writer = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            bufferedWriter.write(createContent);
        }
    }
    public static void createFile2(String path,String name) throws IOException {
        String reqUpdateContent = StringUtils.replace(ReqUpdate_CONTENT, "€Name", name);
        String reqUpdateFileName = "Req" +name + "Update.java";

        File file1 = new File(path, reqUpdateFileName);

        // 如果文件不存在，则创建新文件
        if (!file1.exists()) {
            file1.createNewFile();
        }

        // 写入文件内容
        try (FileWriter writer = new FileWriter(file1);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            bufferedWriter.write(reqUpdateContent);
        }
    }
    public static void createFile3(String path,String name) throws IOException {
        String reqListContent = StringUtils.replace(ReqList_CONTENT, "€Name", name);
        String reqListFileName = "Req" +name + "List.java";

        File file2 = new File(path, reqListFileName);

        // 如果文件不存在，则创建新文件
        if (!file2.exists()) {
            file2.createNewFile();
        }

        // 写入文件内容
        try (FileWriter writer = new FileWriter(file2);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            bufferedWriter.write(reqListContent);
        }
    }
    public static void createFile4(String path,String name) throws IOException {
        String respListContent = StringUtils.replace(RespList_CONTENT, "€Name", name);
        String respListFileName = "Resp" +name + "List.java";

        File file3 = new File(path, respListFileName);

        // 如果文件不存在，则创建新文件
        if (!file3.exists()) {
            file3.createNewFile();
        }

        // 写入文件内容
        try (FileWriter writer = new FileWriter(file3);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            bufferedWriter.write(respListContent);
        }
    }
}
