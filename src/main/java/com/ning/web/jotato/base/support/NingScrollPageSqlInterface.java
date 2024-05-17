package com.ning.web.jotato.base.support;

import com.baomidou.mybatisplus.core.metadata.IPage;

public interface NingScrollPageSqlInterface<T> {
    IPage<T> getPageList(IPage<T> var1);
}
