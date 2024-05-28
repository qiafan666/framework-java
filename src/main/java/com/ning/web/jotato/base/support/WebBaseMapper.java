package com.ning.web.jotato.base.support;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ning.web.jotato.base.model.page.MyPage;
import com.ning.web.jotato.base.model.page.MyPageResult;
import com.ning.web.jotato.base.model.page.ScrollPage;
import com.ning.web.jotato.base.model.page.ScrollPageResult;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface WebBaseMapper<T> extends BaseMapper<T> {
    default <E> MyPageResult<E> selectPageSql(MyPage page, NingPageSqlInterface<E> target) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<E> req = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(page.getPageNo(), page.getPageSize());
        IPage<E> res = target.getPageList(req);
        MyPageResult<E> result = new MyPageResult<>();
        page.setTotalRecord(res.getTotal());
        result.setPage(page);
        result.setList(res.getRecords());
        return result;
    }

    default MyPageResult<Map<String, Object>> selectMapsPageSql(MyPage page, NingPageSqlInterface<Map<String, Object>> target) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Map<String, Object>> req = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(page.getPageNo(), page.getPageSize());
        IPage<Map<String, Object>> res = target.getPageList(req);
        MyPageResult<Map<String, Object>> result = new MyPageResult<>();
        page.setTotalRecord(res.getTotal());
        result.setPage(page);
        result.setList(res.getRecords());
        return result;
    }

    default <E> ScrollPageResult<E> selectScrollPageSql(ScrollPage page, NingPageSqlInterface<E> target) {
        PageInner<E> req = new PageInner<>(page.getPageNo(), page.getPageSize(), 0L, false);
        IPage<E> res = target.getPageList(req);
        ScrollPageResult<E> result = new ScrollPageResult<>();
        page.setHasNextPage((long)res.getRecords().size() > page.getPageSize());
        result.setPage(page);
        result.setList(res.getRecords().subList(0, Math.min(res.getRecords().size(), page.getPageSize().intValue())));
        return result;
    }

    default ScrollPageResult<Map<String, Object>> selectMapsScrollPageSql(ScrollPage page, NingPageSqlInterface<Map<String, Object>> target) {
        PageInner<Map<String, Object>> req = new PageInner<>(page.getPageNo(), page.getPageSize(), 0L, false);
        IPage<Map<String, Object>> res = target.getPageList(req);
        ScrollPageResult<Map<String, Object>> result = new ScrollPageResult<>();
        page.setHasNextPage((long)res.getRecords().size() > page.getPageSize());
        result.setPage(page);
        result.setList(res.getRecords().subList(0, Math.min(res.getRecords().size(), page.getPageSize().intValue())));
        return result;
    }

    default MyPageResult<T> selectPage(MyPage page, @Param("ew") Wrapper<T> queryWrapper) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> req = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(page.getPageNo(), page.getPageSize());
        req = this.selectPage(req, queryWrapper);
        MyPageResult<T> result = new MyPageResult<>();
        page.setTotalRecord(req.getTotal());
        result.setPage(page);
        result.setList(req.getRecords());
        return result;
    }

    default MyPageResult<Map<String, Object>> selectMapsPage(MyPage page, @Param("ew") Wrapper<T> queryWrapper) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Map<String, Object>> req = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(page.getPageNo(), page.getPageSize());
        req = this.selectMapsPage(req, queryWrapper);
        MyPageResult<Map<String, Object>> result = new MyPageResult<>();
        page.setTotalRecord(req.getTotal());
        result.setPage(page);
        result.setList(req.getRecords());
        return result;
    }

    default ScrollPageResult<T> selectScrollPage(ScrollPage page, @Param("ew") Wrapper<T> queryWrapper) {
        PageInner<T> req = new PageInner<>(page.getPageNo(), page.getPageSize(), 0L, false);
        req = this.selectPage(req, queryWrapper);
        ScrollPageResult<T> result = new ScrollPageResult<>();
        page.setHasNextPage((long)req.getRecords().size() > page.getPageSize());
        result.setPage(page);
        result.setList(req.getRecords().subList(0, Math.min(req.getRecords().size(), page.getPageSize().intValue())));
        return result;
    }

    default ScrollPageResult<Map<String, Object>> selectMapsScrollPage(ScrollPage page, @Param("ew") Wrapper<T> queryWrapper) {
        PageInner<Map<String, Object>> req = new PageInner<>(page.getPageNo(), page.getPageSize(), 0L, false);
        req = this.selectMapsPage(req, queryWrapper);
        ScrollPageResult<Map<String, Object>> result = new ScrollPageResult<>();
        page.setHasNextPage((long)req.getRecords().size() > page.getPageSize());
        result.setPage(page);
        result.setList(req.getRecords().subList(0, Math.min(req.getRecords().size(), page.getPageSize().intValue())));
        return result;
    }

    Integer updateAllColumnById(@Param("et") T var1);

    Integer insertBatch(List<T> var1);

    public static class PageInner<T> extends com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> {
        private static final long serialVersionUID = 1L;

        public PageInner(long current, long size, long total, boolean searchCount) {
            if (current > 1L) {
                this.current = current;
            }

            this.size = size;
            this.total = total;
            this.searchCount = searchCount;
        }

        public long offset() {
            long current = this.getCurrent();
            return current <= 1L ? 0L : Math.max((current - 1L) * (this.getSize() - 1L), 0L);
        }

        public long getSize() {
            return this.size + 1L;
        }
    }
}
