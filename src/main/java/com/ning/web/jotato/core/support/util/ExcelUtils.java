//package com.ning.web.jotato.core.support.util;
//
//import cn.afterturn.easypoi.excel.annotation.Excel;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ExcelUtils {
//
//    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    public static class PropertyExcelName {
//        private String property;
//
//        private Field field;
//
//        private String excelName;
//    }
//
//    public static List<PropertyExcelName> getPropertyExcelName(Class<?> clz) {
//        List<PropertyExcelName> ret = new ArrayList<>();
//
//        Field[] fields = clz.getDeclaredFields();
//        for (Field field : fields) {
//            Excel excel = field.getAnnotation(Excel.class);
//            if (excel != null) {
//                ret.add(new PropertyExcelName(field.getName(), field, excel.name()));
//            }
//        }
//        return ret;
//    }
//
//}
