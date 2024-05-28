package com.ning.web.jotato.base.model.page;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class MyPage implements Serializable {
    private static final long serialVersionUID = -816030718296977748L;
    private Long pageSize;
    private Long pageNo;
    private Long totalRecord;

    public void setPageSize(Integer pageSize) {
        this.pageSize = (long)pageSize;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = (long)pageNo;
    }

    public Long getTotalPage() {
        if (this.getPageSize() <= 0L) {
            return 0L;
        } else {
            return this.getTotalRecord() <= 0L ? 0L : this.getTotalRecord() / this.getPageSize() + (this.getTotalRecord() % this.getPageSize() > 0L ? 1L : 0L);
        }
    }

    public static PageBuilder builder() {
        return new PageBuilder();
    }

    public void setTotalRecord(Long totalRecord) {
        this.totalRecord = totalRecord;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof MyPage)) {
            return false;
        } else {
            MyPage other = (MyPage)o;
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

                Object this$totalRecord = this.getTotalRecord();
                Object other$totalRecord = other.getTotalRecord();
                if (this$totalRecord == null) {
                    if (other$totalRecord != null) {
                        return false;
                    }
                } else if (!this$totalRecord.equals(other$totalRecord)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof MyPage;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $pageSize = this.getPageSize();
        result = result * 59 + ($pageSize == null ? 43 : $pageSize.hashCode());
        Object $pageNo = this.getPageNo();
        result = result * 59 + ($pageNo == null ? 43 : $pageNo.hashCode());
        Object $totalRecord = this.getTotalRecord();
        result = result * 59 + ($totalRecord == null ? 43 : $totalRecord.hashCode());
        return result;
    }

    public MyPage() {
    }

    public MyPage(Long pageSize, Long pageNo, Long totalRecord) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.totalRecord = totalRecord;
    }

    public String toString() {
        return "Page(pageSize=" + this.getPageSize() + ", pageNo=" + this.getPageNo() + ", totalRecord=" + this.getTotalRecord() + ")";
    }

    public static class PageBuilder {
        private Long pageSize;
        private Long pageNo;
        private Long totalRecord;

        PageBuilder() {
        }

        public PageBuilder pageSize(Long pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public PageBuilder pageNo(Long pageNo) {
            this.pageNo = pageNo;
            return this;
        }

        public PageBuilder totalRecord(Long totalRecord) {
            this.totalRecord = totalRecord;
            return this;
        }

        public MyPage build() {
            return new MyPage(this.pageSize, this.pageNo, this.totalRecord);
        }

        public String toString() {
            return "Page.PageBuilder(pageSize=" + this.pageSize + ", pageNo=" + this.pageNo + ", totalRecord=" + this.totalRecord + ")";
        }
    }
}
