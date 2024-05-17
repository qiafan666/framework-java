package com.ning.web.jotato.base.model.req;

public class PageExtQuery extends PageQuery {
    private static final long serialVersionUID = -3818954281446460368L;
    public static final String ASC = "ASC";
    public static final String DESC = "DESC";
    private String orderBy;
    private String orderDirection = "DESC";
    private String groupBy;

    public PageExtQuery() {
    }

    public String getOrderBy() {
        return this.orderBy;
    }

    public PageExtQuery setOrderBy(String orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public String getOrderDirection() {
        return this.orderDirection;
    }

    public PageExtQuery setOrderDirection(String orderDirection) {
        if ("ASC".equalsIgnoreCase(orderDirection) || "DESC".equalsIgnoreCase(orderDirection)) {
            this.orderDirection = orderDirection;
        }

        return this;
    }

    public String getGroupBy() {
        return this.groupBy;
    }

    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }
}
