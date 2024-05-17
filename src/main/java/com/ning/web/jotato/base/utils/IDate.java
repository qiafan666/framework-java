package com.ning.web.jotato.base.utils;

import java.io.Serializable;
import java.util.Date;

public interface IDate {
    Date getStartDate();

    Date getLastDate();

    public abstract static class AbstractDate implements IDate, Serializable {
        private static final long serialVersionUID = -816664701246913714L;
        private final Date startDate;
        private final Date lastDate;

        AbstractDate(Date startDate, Date lastDate) {
            this.startDate = startDate;
            this.lastDate = lastDate;
        }

        public Date getStartDate() {
            return this.startDate;
        }

        public Date getLastDate() {
            return this.lastDate;
        }
    }
}
