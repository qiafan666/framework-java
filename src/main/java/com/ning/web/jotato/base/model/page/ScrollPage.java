package com.ning.web.jotato.base.model.page;

import java.io.Serializable;

public class ScrollPage implements Serializable {
    private static final long serialVersionUID = 8982229060300208342L;
    private Long pageSize;
    private Long pageNo;
    private Boolean hasNextPage;

    public static ScrollPageBuilder builder() {
        return new ScrollPageBuilder();
    }

    public Long getPageSize() {
        return this.pageSize;
    }

    public Long getPageNo() {
        return this.pageNo;
    }

    public Boolean getHasNextPage() {
        return this.hasNextPage;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNo(Long pageNo) {
        this.pageNo = pageNo;
    }

    public void setHasNextPage(Boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ScrollPage)) {
            return false;
        } else {
            ScrollPage other = (ScrollPage)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47: {
                    Object this$pageSize = this.getPageSize();
                    Object other$pageSize = other.getPageSize();
                    if (this$pageSize == null) {
                        if (other$pageSize == null) {
                            break label47;
                        }
                    } else if (this$pageSize.equals(other$pageSize)) {
                        break label47;
                    }

                    return false;
                }

                Object this$pageNo = this.getPageNo();
                Object other$pageNo = other.getPageNo();
                if (this$pageNo == null) {
                    if (other$pageNo != null) {
                        return false;
                    }
                } else if (!this$pageNo.equals(other$pageNo)) {
                    return false;
                }

                Object this$hasNextPage = this.getHasNextPage();
                Object other$hasNextPage = other.getHasNextPage();
                if (this$hasNextPage == null) {
                    if (other$hasNextPage != null) {
                        return false;
                    }
                } else if (!this$hasNextPage.equals(other$hasNextPage)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof ScrollPage;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $pageSize = this.getPageSize();
        result = result * 59 + ($pageSize == null ? 43 : $pageSize.hashCode());
        Object $pageNo = this.getPageNo();
        result = result * 59 + ($pageNo == null ? 43 : $pageNo.hashCode());
        Object $hasNextPage = this.getHasNextPage();
        result = result * 59 + ($hasNextPage == null ? 43 : $hasNextPage.hashCode());
        return result;
    }

    public ScrollPage() {
    }

    public ScrollPage(Long pageSize, Long pageNo, Boolean hasNextPage) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.hasNextPage = hasNextPage;
    }

    public String toString() {
        return "ScrollPage(pageSize=" + this.getPageSize() + ", pageNo=" + this.getPageNo() + ", hasNextPage=" + this.getHasNextPage() + ")";
    }

    public static class ScrollPageBuilder {
        private Long pageSize;
        private Long pageNo;
        private Boolean hasNextPage;

        ScrollPageBuilder() {
        }

        public ScrollPageBuilder pageSize(Long pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public ScrollPageBuilder pageNo(Long pageNo) {
            this.pageNo = pageNo;
            return this;
        }

        public ScrollPageBuilder hasNextPage(Boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
            return this;
        }

        public ScrollPage build() {
            return new ScrollPage(this.pageSize, this.pageNo, this.hasNextPage);
        }

        public String toString() {
            return "ScrollPage.ScrollPageBuilder(pageSize=" + this.pageSize + ", pageNo=" + this.pageNo + ", hasNextPage=" + this.hasNextPage + ")";
        }
    }
}
