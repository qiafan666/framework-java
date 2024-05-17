package com.ning.web.jotato.base.support;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.function.Predicate;

public class UpdateAllColumnById extends AbstractMethod {
    private static final long serialVersionUID = 1L;
    private Predicate<TableFieldInfo> predicate;

    public UpdateAllColumnById(String name, Predicate<TableFieldInfo> predicate) {
        super(name);
        this.predicate = predicate;
    }

    public UpdateAllColumnById() {
        super("updateAllColumnById");
    }

    public UpdateAllColumnById(Predicate<TableFieldInfo> predicate) {
        super("updateAllColumnById");
        this.predicate = predicate;
    }

    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod sqlMethod = SqlMethod.UPDATE_BY_ID;
        String additional = this.optlockVersion(tableInfo) + tableInfo.getLogicDeleteSql(true, true);
        String sqlSet = this.filterTableFieldInfo(tableInfo.getFieldList(), this.getPredicate(), (i) -> {
            return i.getSqlSet(true, "et.");
        }, "\n");
        sqlSet = SqlScriptUtils.convertSet(sqlSet);
        String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), sqlSet, tableInfo.getKeyColumn(), "et." + tableInfo.getKeyProperty(), additional);
        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sql, modelClass);
        return this.addUpdateMappedStatement(mapperClass, modelClass, this.getMethod(sqlMethod), sqlSource);
    }

    private Predicate<TableFieldInfo> getPredicate() {
        Predicate<TableFieldInfo> noLogic = (t) -> {
            return !t.isLogicDelete();
        };
        return this.predicate != null ? noLogic.and(this.predicate) : noLogic;
    }

    public UpdateAllColumnById setPredicate(Predicate<TableFieldInfo> predicate) {
        this.predicate = predicate;
        return this;
    }
}
