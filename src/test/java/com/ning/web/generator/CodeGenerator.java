package com.ning.web.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.ning.web.jotato.base.support.WebBaseMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;

/**
 * @author cyr
 * @version 1.0
 * @date 2022/3/8 16:27
 */
@Slf4j
public class CodeGenerator {

    // ======================================================
    // ======================================================
    //作者名
    private static final String AUTHOR = "ning";
    //表table的前缀，不加到生成的类名中
    public static final String[] PREFIX = {"sys_","yw_"};
    //要生成的表名
    public static final String[] TABLES = {"yw_alarm_detail"};
    private static final String DB_ColumnName_create_time = "created_time";
    private static final String DB_ColumnName_modify_time = "updated_time";
    private static final String DB_ColumnName_is_deleted = "is_deleted";

    //功能模块名称，生成的文件会存放到模块下
    //当前项目路径 + 项目路径
    private static final String PROJECT_PATH = "/java/src/framework-java";

    private static final String MODULE_PATH = "";
    private static final String PATH = "/src/main/java";
    private static final String BASE_PACKAGE = "com.ning.web";

    private static final String PACKAGE_CONTROLLER = "controller";
    private static final String PACKAGE_SERVICE = "service";
    private static final String PACKAGE_IMPL = "service.impl";
    private static final String PACKAGE_ENTITY = "entity";
    private static final String PACKAGE_MAPPER = "mapper";
    // mapp.xml 生成路径
    private static final String XML_PACKAGE = "/src/main/resources/mapper";

    private static final String JDBC_URL = "jdbc:mysql://192.168.1.181:3306/trusted_dev?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String JDBC_USERNAME = "trusted_dev";
    private static final String JDBC_PASSWORD = "Trusted123!@#";
    // ======================================================
    // ======================================================

    public static void main(String[] args) {

        FastAutoGenerator generator = FastAutoGenerator.create(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        //2、全局配置
        configGlobal(generator, PROJECT_PATH);
        //3、包配置
        configPackage(generator, PROJECT_PATH);
        //4、包相关配置
        configPackage(generator, PROJECT_PATH);
        //5、策略配置
        configStrategy(generator);
        //6、模板引擎配置
        configTemplate(generator);
        //7、执行
        generator.execute();
    }

    /**
     * 进行全局配置
     *
     * @param generator   :
     * @param projectPath :
     * @date 2021/2/8 13:28
     */
    private static void configGlobal(FastAutoGenerator generator, String projectPath) {
        generator.globalConfig(builder -> {
            builder
                    // 设置作者名
                    .author(AUTHOR)
                    //设置输出路径
                    .outputDir(projectPath.concat(PATH))
                    //注释日期
                    .commentDate("yyyy-MM-dd hh:mm:ss")
                    //定义生成的实体类中日期的类型 TIME_PACK=LocalDateTime;ONLY_DATE=Date
                    .dateType(DateType.ONLY_DATE)
                    //覆盖之前的文件
                    .fileOverride()
                    //开启 swagger 模式
                    //禁止打开输出目录，默认打开
                    .disableOpenDir();
        });
    }

    /**
     * 各个包配置
     *
     * @param generator :
     * @date 2021/2/8 13:34
     */
    private static void configPackage(FastAutoGenerator generator, String projectPath) {

        String xmlOutputPatch = projectPath.concat(XML_PACKAGE);

        generator.packageConfig(builder -> {
            builder
                    // 设置父包名
                    .parent(BASE_PACKAGE)
                    //设置模块包名
                    .moduleName(MODULE_PATH)
                    //Controller 包名
                    .controller(PACKAGE_CONTROLLER)
                    //Service 包名
                    .service(PACKAGE_SERVICE)
                    // ***ServiceImpl 包名
                    .serviceImpl(PACKAGE_IMPL)
                    //pojo 实体类包名
                    .entity(PACKAGE_ENTITY)
                    //Mapper 包名
                    .mapper(PACKAGE_MAPPER)
                    //Mapper XML 包名
                    .xml(xmlOutputPatch)
                    //自定义文件包名
                    //.other("utils")
                    //配置 **Mapper.xml 路径信息：项目的 resources 目录的 Mapper 目录下
                    .pathInfo(Collections.singletonMap(OutputFile.mapperXml, xmlOutputPatch));
        });
    }

    /**
     * 策略配置
     *
     * @param generator :
     * @date 2021/2/8 13:34
     */
    private static void configStrategy(FastAutoGenerator generator) {
        generator.strategyConfig(builder -> {
            builder
                    // 设置需要生成的数据表名
                    .addInclude(TABLES)
                    // 设置过滤表前缀
                    .addTablePrefix(PREFIX)
                    //===========================================
                    //1、Controller策略配置
                    .controllerBuilder()
                    //格式化 Controller 类文件名称，%s进行匹配表名，如 UserController
                    .formatFileName("%sController")

                    //开启生成 @RestController 控制器
                    .enableRestStyle()
                    //===========================================
                    //===========================================
                    //2、service 策略配置
                    .serviceBuilder()
                    //格式化 service 接口文件名称，%s进行匹配表名，如 UserService
                    .formatServiceFileName("I%sService")
                    //格式化 service 实现类文件名称，%s进行匹配表名，如 UserServiceImpl
                    .formatServiceImplFileName("%sServiceImpl")
                    //===========================================
                    //===========================================
                    //3、实体类策略配置
                    .entityBuilder()
                    //开启 Lombok
                    .enableLombok()
                    //不实现 Serializable 接口，不生产 SerialVersionUID
//                    .disableSerialVersionUID()
                    //逻辑删除字段名
                    // TODO 修改点
                    .logicDeleteColumnName(DB_ColumnName_is_deleted)
                    //数据库表映射到实体的命名策略：下划线转驼峰命
                    .naming(NamingStrategy.underline_to_camel)
                    //数据库表字段映射到实体的命名策略：下划线转驼峰命
                    .columnNaming(NamingStrategy.underline_to_camel)
                    //添加表字段填充，"create_time"字段自动填充为插入时间，"modify_time"字段自动填充为插入修改时间
                    // TODO 修改点
                    .formatFileName("%sEntity")
                    .addTableFills(
                            new Column(DB_ColumnName_create_time, FieldFill.INSERT),
                            new Column(DB_ColumnName_modify_time, FieldFill.INSERT_UPDATE)
                    )
                    // 开启生成实体时生成字段注解
                    .enableTableFieldAnnotation()
                    //===========================================
                    //===========================================
                    //4、Mapper策略配置
                    .mapperBuilder()
                    //设置父类
                    .superClass(WebBaseMapper.class)
                    //格式化 mapper 文件名称
                    .formatMapperFileName("%sMapper")
                    //开启 @Mapper 注解
                    .enableMapperAnnotation()
                    //格式化 Xml 文件名称
                    .formatXmlFileName("%sMapper")
            //===========================================
            //===========================================
            ;

        });
    }

    /**
     * 模版引擎配置
     *
     * @param generator :
     * @date 2021/2/8 13:59
     */
    private static void configTemplate(FastAutoGenerator generator) {
        generator
                //模板引擎配置 默认是VelocityTemplateEngine
                .templateEngine(new FreemarkerTemplateEngine())
                .templateConfig(builder -> {
                    builder
                            .controller("templates/controller.java")
                            .service("templates/service.java")
                            .serviceImpl("templates/serviceImpl.java")
                            .entity("templates/entity.java")
                            .mapperXml("templates/mapper.xml");
                });
    }


}
