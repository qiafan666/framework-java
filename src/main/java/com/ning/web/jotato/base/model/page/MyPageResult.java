package com.ning.web.jotato.base.model.page;

import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
public class MyPageResult<T> implements Serializable {
    private static final long serialVersionUID = 8328637522112232308L;
    private MyPage page;
    private List<T> list;

    public MyPageResult(MyPage page) {
        this.page = page;
    }

    public MyPageResult(List<T> list) {
        this.list = list;
    }

    public MyPageResult() {
    }

    public MyPageResult(MyPage page, List<T> list) {
        this.page = page;
        this.list = list;
    }

    public void setPage(MyPage page) {
        this.page = page;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof MyPageResult)) {
            return false;
        } else {
            MyPageResult<?> other = (MyPageResult)o;
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
        return other instanceof MyPageResult;
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
        return "PageResult(page=" + this.getPage() + ", list=" + this.getList() + ")";
    }
}
