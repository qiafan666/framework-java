package com.ning.web.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ning.web.jotato.base.model.result.BaseResult;
import com.ning.web.jotato.base.model.result.BaseResultData;
import com.ning.web.jotato.common.exception.RestException;
import com.ning.web.jotato.core.annotion.MessageValid;
import com.ning.web.jotato.core.annotion.WIntercepts;
import com.ning.web.jotato.core.web.interceptor.login.WebLoginWInterceptor;
import com.ning.web.pojo.req.ReqAppCreate;
import com.ning.web.pojo.req.ReqAppList;
import com.ning.web.pojo.req.ReqAppUpdate;
import com.ning.web.pojo.resp.RespAppList;
import com.ning.web.service.IAppService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * 应用管理
 *
 * @author ning
 * @since 2024-05-16 06:26:42
 */
@RestController
@RequestMapping("/api/v1/app")
@WIntercepts(before = WebLoginWInterceptor.class) // 登录拦截器,校验token
public class AppController {

    @Resource
    private IAppService iAppService;


    /**
     * 列表查询
     * @param request 查询列表
     * @return BaseResultData<Page<RespAppList>>
     */
    @PostMapping(value = "/list")
    public BaseResultData<Page<RespAppList>> list(@RequestBody ReqAppList request) {
        Page<RespAppList> page =  iAppService.list(request);
        return BaseResultData.success(page);
    }

    /**
     * 创建
     * @param request 创建
     * @return BaseResult
     */
    @PostMapping(value = "/create")
    @MessageValid({
            "appName:number:must:unempty",
            "appStatus:string:must:unempty::32:::^(online|offline)$"
    })
    public BaseResult create(@RequestBody ReqAppCreate request) {
        iAppService.create(request);
        return BaseResultData.success();
    }

    @PostMapping(value = "/update")
    public BaseResult update(@RequestBody ReqAppUpdate request) {
        iAppService.update(request);
        return BaseResult.success();
    }

    @DeleteMapping(value = "/delete")
    public BaseResult delete(@RequestParam("ids") List<Long> request) {
        iAppService.delete(request);
        return BaseResultData.success();
    }

    @GetMapping(value = "/download")
    public ResponseEntity<InputStreamResource> download() throws IOException {
        // 从资源目录中读取模板文件
        ClassPathResource resource = new ClassPathResource("template/app.xlsx");

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=template.xlsx");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .body(new InputStreamResource(resource.getInputStream()));
    }

    @PostMapping(value = "/import")
    public BaseResult importData(@RequestParam("file") MultipartFile file) {
        if (ObjectUtils.isEmpty(file)) {
            throw new RestException("FE00002");
        }
        //解析文件
        iAppService.processFile(file);
        return BaseResult.success();
    }

    @PostMapping(value = "/export")
    public BaseResultData<String> export(@RequestParam List<Long> appIds) {
        return BaseResultData.success("success");
    }

}
