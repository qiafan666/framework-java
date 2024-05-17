package com.ning.web.jotato.base.support;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ning.web.jotato.base.model.page.Page;
import com.ning.web.jotato.base.model.page.PageResult;
import com.ning.web.jotato.base.model.page.ScrollPage;
import com.ning.web.jotato.base.model.page.ScrollPageResult;

import java.util.List;
import java.util.Map;

public interface NingIService<T> extends IService<T> {
    PageResult<T> selectPage(Page var1, Wrapper<T> var2);

    default PageResult<T> selectPage(Page page) {
        return this.selectPage(page, Wrappers.emptyWrapper());
    }

    PageResult<Map<String, Object>> selectMapsPage(Page var1, Wrapper<T> var2);

    default PageResult<Map<String, Object>> selectMapsPage(Page page) {
        return this.selectMapsPage(page, Wrappers.emptyWrapper());
    }

    ScrollPageResult<T> selectScrollPage(ScrollPage var1, Wrapper<T> var2);

    default ScrollPageResult<T> selectScrollPage(ScrollPage page) {
        return this.selectScrollPage(page, Wrappers.emptyWrapper());
    }

    ScrollPageResult<Map<String, Object>> selectMapsScrollPage(ScrollPage var1, Wrapper<T> var2);

    default ScrollPageResult<Map<String, Object>> selectMapsScrollPage(ScrollPage page) {
        return this.selectMapsScrollPage(page, Wrappers.emptyWrapper());
    }

    boolean updateAllColumnById(T var1);

    Integer insertBatch(List<T> var1);
}
