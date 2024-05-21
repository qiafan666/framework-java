package com.ning.web.listenner;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.ning.web.pojo.template.AppImportDTO;

import java.util.LinkedList;
import java.util.List;

public class AppImportListenner implements ReadListener<AppImportDTO> {

public AppImportListenner(List<AppImportDTO> list) {
        this.saveUserList = list;
        }

private List<AppImportDTO> saveUserList = new LinkedList<>();
@Override
public void invoke(AppImportDTO dto, AnalysisContext context) {
        saveUserList.add(dto);
        }

@Override
public void doAfterAllAnalysed(AnalysisContext context) {
        }

@Override
public void onException(Exception exception, AnalysisContext context) throws Exception {
        }

@Override
public boolean hasNext(AnalysisContext context) {
        return true;
        }

        }