//package com.ning.web.jotato.core.support.util;
//
//import cn.afterturn.easypoi.excel.ExcelExportUtil;
//import cn.afterturn.easypoi.excel.ExcelImportUtil;
//import cn.afterturn.easypoi.excel.entity.ExportParams;
//import cn.afterturn.easypoi.excel.entity.ImportParams;
//import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
//import com.ning.web.jotato.base.enums.CharsetEnum;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URLEncoder;
//import java.util.List;
//import java.util.NoSuchElementException;
//
//public class ExportUtil {
//
//    /**
//     * excel 导出
//     *
//     * @param list         数据列表
//     * @param pojoClass    pojo类型
//     * @param fileName     导出时的excel名称
//     * @param exportParams 导出参数（标题、sheet名称、是否创建表头，表格类型）
//     * @param response resp
//     */
//    public static void exportExcel(List<?> list, Class<?> pojoClass, String fileName, ExportParams exportParams, HttpServletResponse response) throws IOException {
//        defaultExport(list, pojoClass, fileName, response, exportParams);
//    }
//
//
//    /**
//     * excel 导出
//     *
//     * @param list           数据列表
//     * @param title          表格内数据标题
//     * @param sheetName      sheet名称
//     * @param pojoClass      pojo类型
//     * @param fileName       导出时的excel名称
//     * @param response       resp
//     */
//    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName, HttpServletResponse response) throws IOException {
//        ExportParams exportParams = new ExportParams(title, sheetName, ExcelType.HSSF);
//        exportParams.setCreateHeadRows(true);
//        defaultExport(list, pojoClass, fileName, response, exportParams);
//    }
//    /**
//     * excel 导出
//     *
//     * @param list         数据列表
//     * @param pojoClass    pojo类型
//     * @param fileName     导出时的excel名称
//     * @param response     resp
//     * @param exportParams 导出参数（标题、sheet名称、是否创建表头，表格类型）
//     */
//    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response, ExportParams exportParams) throws IOException {
//        //把数据添加到excel表格中
//        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
//        downLoadExcel(fileName, response, workbook);
//    }
//
//
//    /**
//     * excel下载
//     *
//     * @param fileName 下载时的文件名称
//     * @param response resp
//     * @param workbook excel数据
//     */
//    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) throws IOException {
//        try {
//            response.setCharacterEncoding(CharsetEnum.UTF_8.getCode());
//            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
//            response.setContentType("application/vnd.ms-excel; charset=utf-8");
//            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xls", "UTF-8"));
//            workbook.write(response.getOutputStream());
//        } catch (Exception e) {
//            throw new IOException(e.getMessage());
//        }
//    }
//
//
//    /******************************************************导入**************************************************************/
//
//
//    /**
//     * excel 导入
//     *
//     * @param file      excel文件
//     * @param pojoClass pojo类型
//     * @param <T> t
//     * @return list
//     */
//    public static <T> List<T> importExcel(MultipartFile file, Class<T> pojoClass) throws IOException {
//        return importExcel(file, 1, 1, pojoClass);
//    }
//
//    /**
//     * excel 导入
//     *
//     * @param filePath   excel文件路径
//     * @param titleRows  表格内数据标题行
//     * @param headerRows 表头行
//     * @param pojoClass  pojo类型
//     * @param <T> t
//     * @return list
//     */
//    public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> pojoClass) throws IOException {
//        if (StringUtils.isBlank(filePath)) {
//            return null;
//        }
//        ImportParams params = new ImportParams();
//        params.setTitleRows(titleRows);
//        params.setHeadRows(headerRows);
//        params.setNeedSave(true);
//        params.setSaveUrl("/excel/");
//        try {
//            return ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
//        } catch (NoSuchElementException e) {
//            throw new IOException("模板不能为空");
//        } catch (Exception e) {
//            throw new IOException(e.getMessage());
//        }
//    }
//
//
//    /**
//     * excel 导入
//     *
//     * @param file       上传的文件
//     * @param titleRows  表格内数据标题行
//     * @param headerRows 表头行
//     * @param pojoClass  pojo类型
//     * @param <T> t
//     * @return list
//     */
//    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass) throws IOException {
//        if (file == null) {
//            return null;
//        }
//        try {
//            return importExcel(file.getInputStream(), titleRows, headerRows, pojoClass);
//        } catch (Exception e) {
//            throw new IOException(e.getMessage());
//        }
//    }
//
//    /**
//     * excel 导入
//     *
//     * @param inputStream 文件输入流
//     * @param titleRows   表格内数据标题行
//     * @param headerRows  表头行
//     * @param pojoClass   pojo类型
//     * @param <T> t
//     * @return list
//     */
//    public static <T> List<T> importExcel(InputStream inputStream, Integer titleRows, Integer headerRows, Class<T> pojoClass) throws IOException {
//        if (inputStream == null) {
//            return null;
//        }
//        ImportParams params = new ImportParams();
//        params.setTitleRows(titleRows);
//        params.setHeadRows(headerRows);
//        params.setSaveUrl("/excel/");
//        params.setNeedSave(true);
//        try {
//            return ExcelImportUtil.importExcel(inputStream, pojoClass, params);
//        } catch (NoSuchElementException e) {
//            throw new IOException("excel文件不能为空");
//        } catch (Exception e) {
//            throw new IOException(e.getMessage());
//        }
//    }
//
//}
