package com.ning.web.jotato.base.support;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ning.web.jotato.base.model.page.MyPage;
import com.ning.web.jotato.base.model.page.MyPageResult;
import com.ning.web.jotato.base.model.page.ScrollPage;
import com.ning.web.jotato.base.model.page.ScrollPageResult;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;

import java.util.List;
import java.util.Map;

public class NingServiceImpl<M extends WebBaseMapper<T>, T> extends ServiceImpl<M, T> implements NingIService<T> {
    public NingServiceImpl() {
    }

    public MyPageResult<Map<String, Object>> selectMapsPage(MyPage page, Wrapper<T> queryWrapper) {
        return this.baseMapper.selectMapsPage(page, queryWrapper);
    }

    public MyPageResult<T> selectPage(MyPage page, Wrapper<T> queryWrapper) {
        return this.baseMapper.selectPage(page, queryWrapper);
    }

    public boolean updateAllColumnById(T entity) {
        return SqlHelper.retBool(this.baseMapper.updateAllColumnById(entity));
    }

    public Integer insertBatch(List<T> list) {
        return this.baseMapper.insertBatch(list);
    }

    public ScrollPageResult<T> selectScrollPage(ScrollPage page, Wrapper<T> queryWrapper) {
        return this.baseMapper.selectScrollPage(page, queryWrapper);
    }

    public ScrollPageResult<Map<String, Object>> selectMapsScrollPage(ScrollPage page, Wrapper<T> queryWrapper) {
        return this.baseMapper.selectMapsScrollPage(page, queryWrapper);
    }
}
