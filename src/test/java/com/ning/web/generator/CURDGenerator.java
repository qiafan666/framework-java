package com.ning.web.generator;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import static java.lang.Thread.sleep;

public class CURDGenerator {
    // controller、service、impl、req、resp路径
    // 注意：当前自动生成代码是在CodeGenerator.java中基础上生成的，一旦CodeGenerator.java修改，这里的代码也要改
/*===================================================================================================================*/
    //code generator 里面的PROJECT_PATH
    private static final String PROJECT_PATH = "D:\\java\\src\\framework-java";
    //code generator 里面的MODULE_PATH
    private static final String MODULE_PATH = "";
    //code generator 里面的输出路径
    private static final String PATH = "\\src\\main\\java";
    //code generator 里面的BASE_PACKAGE
    private static final String BASE_PACKAGE = "\\com\\ning\\web";

    private static final String PACKAGE_CONTROLLER = "\\controller";
    private static final String PACKAGE_SERVICE = "\\service";
    private static final String PACKAGE_IMPL = "\\service\\impl";
    private static final String PACKAGE_ENTITY = "\\entity";
    private static final String PACKAGE_MAPPER = "\\mapper";
    private static final String PACKAGE_CONVERT = "\\convert";
    private static final String PACKAGE_REQ = "\\pojo\\req";
    private static final String PACKAGE_RESP = "\\pojo\\resp";
    //全局搜索BaseResult的包路径
    private static final String resultImportPath = "com.ning.web.jotato.base.model.result";
    //全局搜索BaseReq的包路径
    private static final String baseReqImportPath = "com.ning.web.jotato.core.request.BaseReq";
    //全局搜索BasePage的包路径
    private static final String basePageImportPath = "com.ning.web.jotato.core.request.BasePageQuery";



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

    private static final String FILE_Service_CONTENT = "" +
            "\n"+
            "    void create(Req€NameCreate request);\n" +
            "\n" +
            "    Page<Resp€NameList> list(Req€NameList request);\n" +
            "\n" +
            "    void update(Req€NameUpdate request);\n" +
            "\n" +
            "    void delete(List<Long> request);";
    private static final String FILE_Impl_CONTENT = " " +
            "\n"+
            "    @Override\n" +
            "    public void create(Req€NameCreate request) {\n" +
            "\n"+
            "        €NameEntity entity = new €NameEntity();\n" +
            "        BeanUtils.copyProperties(request, entity);\n" +
            "        //TODO 校验参数\n" +
            "        this.save(entity);\n"+
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public Page<Resp€NameList> list(Req€NameList request) {\n" +
            "\n"+
            "        Page<€NameEntity> page = new Page<>(request.getPageNo(), request.getPageSize());\n" +
            "        LambdaQueryWrapper<€NameEntity> queryWrapper = Wrappers.lambdaQuery();\n" +
            "        queryWrapper.orderByDesc(€NameEntity::getCreatedTime);\n" +
            "        //TODO 查询条件\n" +
            "\n" +
            "        Page<€NameEntity> result = this.page(page, queryWrapper);\n" +
            "\n" +
            "        List<Resp€NameList> convertList = €NameEntityConvertor.INSTANCE.€NameEntityToResp€NameList(result.getRecords());\n" +
            "        Page<Resp€NameList> objectPage = new Page<>(request.getPageNo(), request.getPageSize(), result.getTotal());\n" +
            "        objectPage.setRecords(convertList);\n" +
            "        return  objectPage;\n"+
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void update(Req€NameUpdate request) {\n" +
            "\n" +
            "        €NameEntity entity = this.getById(request.getId());\n" +
            "        if (entity == null) {\n" +
            "            throw new RestException(\"COMMON0001\");\n" +
            "        }\n" +
            "        NullAwareBeanUtils.copyProperties(request, entity);\n" +
            "        //TODO 参数校验\n" +
            "        this.updateById(entity);\n"+
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void delete(List<Long> request) {\n" +
            "\n"+
            "        request.forEach(id -> {\n" +
            "        €NameEntity entity = this.getById(id);\n" +
            "        if (entity == null) {\n" +
            "            throw new RestException(\"COMMON0001\");\n" +
            "            }\n" +
            "        entity.setIsDeleted(1);\n" +
            "        this.updateById(entity);\n" +
            "        });\n"+
            "    }";
    private static final String FILE_Converter_CONTENT = "\n" +
            "package €Package;\n" +
            "\n" +
            "import €EntityImportPath.€NameEntity;\n"+
            "import €RespImportPath.Resp€NameList;\n"+
            "import org.mapstruct.Mapper;\n" +
            "import org.mapstruct.factory.Mappers;\n" +
            "\n" +
            "\n" +
            "import java.util.List;\n" +
            "\n" +
            "@Mapper(componentModel = \"spring\")\n" +
            "public interface €NameEntityConvertor {\n" +
            "\n" +
            "\n" +
            "    €NameEntityConvertor INSTANCE = Mappers.getMapper(€NameEntityConvertor.class);\n" +
            "\n" +
            "\n" +
            "    List<Resp€NameList> €NameEntityToResp€NameList(List<€NameEntity> records);\n" +
            "}\n";
    private static final String ReqCreate_CONTENT = "" +
            "package €Package;\n" +
            "\n"+
            "import €BaseReq;\n"+
            "import lombok.Data;\n" +
            "import lombok.EqualsAndHashCode;\n" +
            "\n" +
            "@EqualsAndHashCode(callSuper = true)\n" +
            "@Data\n" +
            "public class Req€NameCreate extends BaseReq {\n" +
            "\n" +
            "}";
    private static final String ReqUpdate_CONTENT = "" +
            "package €Package;\n" +
            "\n"+
            "import €BaseReq;\n"+
            "import lombok.Data;\n" +
            "import lombok.EqualsAndHashCode;\n" +
            "\n" +
            "@EqualsAndHashCode(callSuper = true)\n" +
            "@Data\n" +
            "public class Req€NameUpdate extends BaseReq {\n" +
            "\n" +
            "\n" +
            "}\n";
    private static final String ReqList_CONTENT = "" +
            "package €Package;\n" +
            "\n"+
            "import €BasePage;\n"+
            "import lombok.Data;\n" +
            "import lombok.EqualsAndHashCode;\n" +
            "\n" +
            "@EqualsAndHashCode(callSuper = true)\n" +
            "@Data\n" +
            "public class Req€NameList extends BasePageQuery {\n" +
            "\n" +
            "}\n";
    private static final String RespList_CONTENT = "" +
            "package €Package;\n" +
            "import lombok.Data;\n" +
            "\n" +
            "@Data\n" +
            "public class Resp€NameList {\n" +
            "\n" +
            "}\n";

    private static final List<String> operateFileList = new ArrayList<>();
    private static final List<String> operateName = new ArrayList<>();

    // 主方法
    public static void main(String[] args) throws IOException, InterruptedException {
        //读取目录下的所有文件

        ArrayList<String> list = new ArrayList<>();
        list.add(PROJECT_PATH + MODULE_PATH + PATH + BASE_PACKAGE + PACKAGE_CONTROLLER);
        list.add(PROJECT_PATH + MODULE_PATH + PATH + BASE_PACKAGE + PACKAGE_SERVICE);
        list.add(PROJECT_PATH + MODULE_PATH + PATH + BASE_PACKAGE + PACKAGE_IMPL);

        for (String path : list) {
            File dir = new File(path);

            // 检查目录是否存在
            if (dir.exists() && dir.isDirectory()) {
                listFiles(dir);
            } else {
                System.out.println("The specified path is not a directory or does not exist.");
            }
        }

        //休息10秒
        for (int i = 0; i < 2; i++) {
            System.out.println("正在生成代码，请稍后...");
            sleep(1000);
            System.out.println("代码生成完成！");
        }

        //向写入的文件里导入包
        for (String operateFile : operateFileList) {
            importPackage(operateFile);
        }

        //向req,resp,写入默认内容
        File entitydir = new File(PROJECT_PATH + MODULE_PATH + PATH + BASE_PACKAGE + PACKAGE_ENTITY);
        File[] entityfiles = entitydir.listFiles();

        File dir = new File(PROJECT_PATH + MODULE_PATH + PATH + BASE_PACKAGE + PACKAGE_REQ);
        File dir1 = new File(PROJECT_PATH + MODULE_PATH + PATH + BASE_PACKAGE + PACKAGE_RESP);

        // 检查目录是否存在
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file : files) {

                if (file.isFile() && file.getName().endsWith(".java")) {
                    if (!file.getName().endsWith("List.java")) {
                        //不是req list文件则找到entity对应实例的文件内容写入
                        String name = StringUtils.replace(file.getName(), "Req", "");
                        name = StringUtils.replace(name, "Create.java", "");
                        name = StringUtils.replace(name, "Update.java", "");

                        // 不是操作的controller文件则跳过
                        if (!operateName.contains(name)){
                            continue;
                        }

                        for (File entityfile : entityfiles) {
                            if (entityfile.getName().contains(name + "Entity")){
                                // 找到 "{ }" 的内容，把内容写到 req{} 里面去
                                String entityContent = extractEntityContent(entityfile);
                                insertContentIntoReqFile(file, entityContent);
                            }
                        }
                    }
                }
            }
        }
        if (dir1.exists() && dir1.isDirectory()) {
            File[] files = dir1.listFiles();
            for (File file : files) {
                // 不是操作的controller文件则跳过
                if (!operateName.contains(file.getName())){
                    continue;
                }

                if (file.isFile() && file.getName().endsWith(".java")) {
                    if (file.getName().endsWith("List.java")) {
                        //不是req list文件则找到entity对应实例的文件内容写入
                        String name = StringUtils.replace(file.getName(), "Resp","");
                        name = StringUtils.replace(name, "List.java", "");

                        // 不是操作的controller文件则跳过
                        if (!operateName.contains(name)){
                            continue;
                        }

                        for (File entityfile : entityfiles) {
                            if (entityfile.getName().contains(name + "Entity")){
                                // 找到 "{ }" 的内容，把内容写到 req{} 里面去
                                String entityContent = extractEntityContent(entityfile);
                                insertContentIntoReqFile(file, entityContent);
                            }
                        }
                    }
                }
            }
        }
    }

    private static String pathConvert(String path) {
        String replacedString = path.replace("\\", "."); // 用 "." 替换 "\"
        if (replacedString.startsWith(".")) {
            replacedString = replacedString.substring(1); // 去除开头的 "."
        }
        if (replacedString.endsWith(".")) {
            replacedString = replacedString.substring(0, replacedString.length() - 1); // 去除结尾的 "."
        }
        return replacedString; // 返回处理后的字符串
    }
    private static String extractEntityContent(File entityFile) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(entityFile))) {
            String line;
            boolean isContent = false;
            while ((line = reader.readLine()) != null) {

                if (line.contains("{")) {
                    isContent = true;
                    continue; // 跳过当前行，因为已经处理过了
                }
                if (isContent) {
                    if (line.contains("}") ||
                            line.contains("isDeleted") || line.contains("isDelete") ||
                            line.contains("updatedTime") || line.contains("createdTime") ||
                            line.contains("updateTime") || line.contains("createTime")) {
                        break; // 如果遇到 '}'，表示内容读取完成，退出循环
                    }
                    if (line.contains("Table")) {
                        continue; // 跳过表注释
                    }
                    content.append(line).append(System.lineSeparator());
                }
            }
        }
        content.append("\n");
        return content.toString();
    }

    private static void insertContentIntoReqFile(File reqFile, String entityContent) throws IOException {
        File tempFile = new File(reqFile.getAbsolutePath() + ".tmp");
        try (BufferedReader reader = new BufferedReader(new FileReader(reqFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            boolean isReqContent = false;
            boolean contentInserted = false; // 标志以确保只插入一次
            while ((line = reader.readLine()) != null) {
                if (line.contains("{")) {
                    isReqContent = true;
                    writer.write(line);
                    writer.newLine();
                    continue; // 继续到下一行
                }
                if (line.contains("}") && isReqContent && !contentInserted) {
                    writer.write(entityContent);
                    writer.newLine();
                    contentInserted = true;
                    isReqContent = false;
                }
                writer.write(line);
                writer.newLine();
            }
        }

        // 替换原文件
        Files.move(tempFile.toPath(), reqFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
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
                        fileType = "Impl";
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

                        operateFileList.add(file.getAbsolutePath());
                        operateName.add(controllerName);
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

                        operateFileList.add(file.getAbsolutePath());
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

                        operateFileList.add(file.getAbsolutePath());
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
        createFile1(PROJECT_PATH + MODULE_PATH + PATH + BASE_PACKAGE + PACKAGE_REQ,name);
        createFile2(PROJECT_PATH + MODULE_PATH + PATH + BASE_PACKAGE + PACKAGE_REQ,name);
        createFile3(PROJECT_PATH + MODULE_PATH + PATH + BASE_PACKAGE + PACKAGE_REQ,name);
        createFile4(PROJECT_PATH + MODULE_PATH + PATH + BASE_PACKAGE + PACKAGE_RESP,name);
        createFile5(PROJECT_PATH + MODULE_PATH + PATH + BASE_PACKAGE + PACKAGE_CONVERT,name);
    }

    //创建ReqCreate
    public static void createFile1(String path,String name) throws IOException {
        String createContent = StringUtils.replace(ReqCreate_CONTENT, "€Name", name);
        createContent = StringUtils.replace(createContent, "€BaseReq", baseReqImportPath);

        createContent = StringUtils.replace(createContent, "€Package", pathConvert(BASE_PACKAGE+PACKAGE_REQ));
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
    //创建ReqUpdate
    public static void createFile2(String path,String name) throws IOException {
        String reqUpdateContent = StringUtils.replace(ReqUpdate_CONTENT, "€Name", name);
        reqUpdateContent = StringUtils.replace(reqUpdateContent, "€BaseReq", baseReqImportPath);
        reqUpdateContent = StringUtils.replace(reqUpdateContent, "€Package", pathConvert(BASE_PACKAGE+PACKAGE_REQ));

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
    //创建ReqList
    public static void createFile3(String path,String name) throws IOException {
        String reqListContent = StringUtils.replace(ReqList_CONTENT, "€Name", name);
        reqListContent = StringUtils.replace(reqListContent, "€BasePage", basePageImportPath);
        reqListContent = StringUtils.replace(reqListContent, "€Package", pathConvert(BASE_PACKAGE+PACKAGE_REQ));

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
    //创建RespList
    public static void createFile4(String path,String name) throws IOException {
        String respListContent = StringUtils.replace(RespList_CONTENT, "€Name", name);
        respListContent = StringUtils.replace(respListContent, "€Package", pathConvert(BASE_PACKAGE+PACKAGE_RESP));

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
    //创建convertor
    public static void createFile5(String path,String name) throws IOException {
        String respListContent = StringUtils.replace(FILE_Converter_CONTENT, "€Name", name);
        respListContent = StringUtils.replace(respListContent, "€Package", pathConvert(BASE_PACKAGE+PACKAGE_CONVERT));
        respListContent = StringUtils.replace(respListContent, "€EntityImportPath", pathConvert(BASE_PACKAGE+PACKAGE_ENTITY));
        respListContent = StringUtils.replace(respListContent, "€RespImportPath", pathConvert(BASE_PACKAGE+PACKAGE_RESP));
        String respListFileName = name + "EntityConvertor.java";

        File file3 = new File(path, respListFileName);

        // 如果文件不存在，则创建新文件
        if (!file3.exists()) {
            file3.createNewFile();

            // 写入文件内容
            try (FileWriter writer = new FileWriter(file3);
                 BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
                bufferedWriter.write(respListContent);
            }
        }else {
            System.out.println(file3.getName() + "文件已存在");
        }


    }

    public static void importPackage(String operateFile) throws IOException {
        File file = new File(operateFile);

        if (file.getName().contains("Controller.java")) {
            String name = StringUtils.replace(file.getName(), "Controller.java", "");
            ArrayList<String> packageList = new ArrayList<>();
            packageList.add("javax.annotation.Resource");
            packageList.add("org.springframework.web.bind.annotation.*");
            packageList.add("java.util.List");
            packageList.add("com.baomidou.mybatisplus.extension.plugins.pagination.Page");
            packageList.add(pathConvert(BASE_PACKAGE+PACKAGE_REQ)+"." + "Req"+name+"Create");
            packageList.add(pathConvert(BASE_PACKAGE+PACKAGE_REQ)+"." + "Req"+name+"Update");
            packageList.add(pathConvert(BASE_PACKAGE+PACKAGE_REQ)+"." + "Req"+name+"List");
            packageList.add(pathConvert(BASE_PACKAGE+PACKAGE_RESP)+"." + "Resp"+name+"List");
            packageList.add(pathConvert(BASE_PACKAGE+PACKAGE_SERVICE)+"." + "I"+name+"Service");
            packageList.add(resultImportPath +"." + "BaseResult");
            packageList.add(resultImportPath +"." +"BaseResultData");
            writePackage(operateFile, packageList);
        }else if (file.getName().contains("Service.java")) {
            String name = StringUtils.replace(file.getName(), "Service.java", "");
            name = StringUtils.replace(name, "I", "");
            ArrayList<String> packageList = new ArrayList<>();
            packageList.add("java.util.List");
            packageList.add("com.baomidou.mybatisplus.extension.plugins.pagination.Page");
            packageList.add("com.baomidou.mybatisplus.extension.service.IService");
            packageList.add(pathConvert(BASE_PACKAGE+PACKAGE_REQ)+"." + "Req"+name+"Create");
            packageList.add(pathConvert(BASE_PACKAGE+PACKAGE_REQ)+"." + "Req"+name+"Update");
            packageList.add(pathConvert(BASE_PACKAGE+PACKAGE_REQ)+"." + "Req"+name+"List");
            packageList.add(pathConvert(BASE_PACKAGE+PACKAGE_RESP)+"." + "Resp"+name+"List");
            packageList.add(pathConvert(BASE_PACKAGE+PACKAGE_ENTITY) + "." + name+"Entity");
            writePackage(operateFile, packageList);
        }else if (file.getName().contains("ServiceImpl.java")) {
            String name = StringUtils.replace(file.getName(), "ServiceImpl.java", "");
            ArrayList<String> packageList = new ArrayList<>();
            packageList.add("java.util.List");
            packageList.add("com.baomidou.mybatisplus.extension.plugins.pagination.Page");
            packageList.add("com.baomidou.mybatisplus.extension.service.impl.ServiceImpl");
            packageList.add("com.baomidou.mybatisplus.core.toolkit.Wrappers");
            packageList.add("com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper");
            packageList.add("org.springframework.beans.BeanUtils");
            packageList.add("org.springframework.stereotype.Service");
            packageList.add(pathConvert(BASE_PACKAGE+PACKAGE_REQ)+"."+ "Req"+name+"Create");
            packageList.add(pathConvert(BASE_PACKAGE+PACKAGE_REQ)+"."+ "Req"+name+"Update");
            packageList.add(pathConvert(BASE_PACKAGE+PACKAGE_REQ)+"." + "Req"+name+"List");
            packageList.add(pathConvert(BASE_PACKAGE+PACKAGE_RESP)+"."+ "Resp"+name+"List");
            packageList.add(pathConvert(BASE_PACKAGE+PACKAGE_SERVICE)+"."+ "I"+name+"Service");

            packageList.add(pathConvert(BASE_PACKAGE+PACKAGE_CONVERT) + "." + name+"EntityConvertor");
            packageList.add(pathConvert(BASE_PACKAGE+PACKAGE_MAPPER) + "." + name+"Mapper");
            writePackage(operateFile, packageList);
        }else if (file.getName().contains("Convertor.java")) {
            String name = StringUtils.replace(file.getName(), "Convertor.java", "");
            ArrayList<String> packageList = new ArrayList<>();
            packageList.add("java.util.List");
            packageList.add(pathConvert(BASE_PACKAGE+PACKAGE_ENTITY) + "." + name+"Entity");
            packageList.add(PROJECT_PATH + MODULE_PATH + PATH + BASE_PACKAGE + PACKAGE_RESP +"Resp"+ name+"List");
            writePackage(operateFile, packageList);
        }

    }
    public static void writePackage(String operateFile,List<String> packageList) throws IOException {

        File file = new File(operateFile);
        File tempFile = new File(file.getAbsolutePath() + ".tmp");

        // 使用 BufferedWriter 来写入文件
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            boolean importFound = false;
            boolean packagesWritten = false;

            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();

                // 检查是否包含 "import"
                if (line.contains("import")) {
                    importFound = true;
                    continue;
                }

                // 如果上一行包含 "import" 并且当前行为空，则插入数据
                if (importFound && line.trim().isEmpty() && !packagesWritten) {
                    for (String packageName : packageList) {
                        writer.write("import " + packageName + ";");
                        writer.newLine();
                    }
                    packagesWritten = true;  // 设置标志，确保只写一次包路径
                }
            }
        }

        // 删除原文件
        if (!file.delete()) {
            System.err.println("Could not delete original file");
            return;
        }

        // 将临时文件重命名为原文件名
        if (!tempFile.renameTo(file)) {
            System.err.println("Could not rename temp file");
        }
    }
}
