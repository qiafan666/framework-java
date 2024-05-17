package com.ning.web.jotato.base.model.page;

import java.io.Serializable;
import java.util.List;

public class ScrollPageResult<T> implements Serializable {
    private static final long serialVersionUID = -7205096233284858088L;
    private ScrollPage page;
    private List<T> list;

    public ScrollPageResult(ScrollPage page) {
        this.page = page;
    }

    public ScrollPageResult(List<T> list) {
        this.list = list;
    }

    public ScrollPageResult() {
    }

    public ScrollPageResult(ScrollPage page, List<T> list) {
        this.page = page;
        this.list = list;
    }

    public ScrollPage getPage() {
        return this.page;
    }

    public List<T> getList() {
        return this.list;
    }

    public void setPage(ScrollPage page) {
        this.page = page;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ScrollPageResult)) {
            return false;
        } else {
            ScrollPageResult<?> other = (ScrollPageResult)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$page = this.getPage();
                Object other$page = other.getPage();
                if (this$page == null) {
                    if (other$page != null) {
                        return false;
                    }
                } else if (!this$page.equals(other$page)) {
                    return false;
                }

                Object this$list = this.getList();
                Object other$list = other.getList();
                if (this$list == null) {
                    if (other$list != null) {
                        return false;
                    }
                } else if (!this$list.equals(other$list)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof ScrollPageResult;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $page = this.getPage();
        result = result * 59 + ($page == null ? 43 : $page.hashCode());
        Object $list = this.getList();
        result = result * 59 + ($list == null ? 43 : $list.hashCode());
        return result;
    }

    public String toString() {
        return "ScrollPageResult(page=" + this.getPage() + ", list=" + this.getList() + ")";
    }
}
