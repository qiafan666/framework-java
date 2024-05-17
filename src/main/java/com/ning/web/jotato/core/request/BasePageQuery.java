package com.ning.web.jotato.core.request;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BasePageQuery extends BaseReq{
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

    public BasePageQuery() {
    }

    public int getPageNo() {
        return this.pageNo < 1 ? 1 : this.pageNo;
    }

    public BasePageQuery setPageNo(int pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public BasePageQuery setPageSize(int pageSize) {
        if (pageSize < 1) {
            pageSize = 10;
        }

        this.pageSize = pageSize;
        return this;
    }

    public int getOffset() {
        return (this.getPageNo() - 1) * this.getPageSize();
    }

    public boolean isNeedTotalCount() {
        return this.needTotalCount;
    }

    public void setNeedTotalCount(boolean needTotalCount) {
        this.needTotalCount = needTotalCount;
    }
}
