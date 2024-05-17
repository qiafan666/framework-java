package com.ning.web.jotato.base.model.req;


import com.ning.web.jotato.base.model.result.BaseBean;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;

@Getter
public class PageQuery extends BaseBean {
    private static final long serialVersionUID = -8110421572007354470L;
    private static final int DEFAULT_PAGE_SIZE = 10;
    private @NotNull(
            message = "每页大小不能为空"
    ) @Min(
            value = 1L,
            message = "每页大小不能小于1"
    ) int pageSize = 10;
    private @NotNull(
            message = "页数不能为空"
    ) @Min(
            value = 1L,
            message = "页数不能小于1"
    ) int pageNo = 1;
    private boolean needTotalCount = true;

    public PageQuery() {
    }

    public PageQuery setPageNo(int pageNo) {
        this.pageNo = Math.max(pageNo, 1);
        return this;
    }

    public PageQuery setPageSize(int pageSize) {
        this.pageSize = pageSize < 1 ? 10 : pageSize;
        return this;
    }

    public int getOffset() {
        return (this.getPageNo() - 1) * this.getPageSize();
    }

    public void setNeedTotalCount(boolean needTotalCount) {
        this.needTotalCount = needTotalCount;
    }
}
