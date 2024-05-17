package com.ning.web.jotato.base.support;

import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import java.util.List;

import com.baomidou.mybatisplus.core.injector.methods.Delete;
import com.baomidou.mybatisplus.core.injector.methods.DeleteBatchByIds;
import com.baomidou.mybatisplus.core.injector.methods.DeleteById;
import com.baomidou.mybatisplus.core.injector.methods.DeleteByMap;
import com.baomidou.mybatisplus.core.injector.methods.Insert;
import com.baomidou.mybatisplus.core.injector.methods.SelectBatchByIds;
import com.baomidou.mybatisplus.core.injector.methods.SelectById;
import com.baomidou.mybatisplus.core.injector.methods.SelectByMap;
import com.baomidou.mybatisplus.core.injector.methods.SelectCount;
import com.baomidou.mybatisplus.core.injector.methods.SelectList;
import com.baomidou.mybatisplus.core.injector.methods.SelectMaps;
import com.baomidou.mybatisplus.core.injector.methods.SelectMapsPage;
import com.baomidou.mybatisplus.core.injector.methods.SelectObjs;
import com.baomidou.mybatisplus.core.injector.methods.SelectPage;
import com.baomidou.mybatisplus.core.injector.methods.Update;
import com.baomidou.mybatisplus.core.injector.methods.UpdateById;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NingSqlInjector extends DefaultSqlInjector {
    public NingSqlInjector() {
    }

    public List getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        Stream.Builder<Object> builder = Stream.builder().add(new Insert()).add(new InsertBatch()).add(new Delete()).add(new DeleteByMap()).add(new Update()).add(new SelectByMap()).add(new SelectCount()).add(new SelectMaps()).add(new SelectMapsPage()).add(new SelectObjs()).add(new SelectList()).add(new SelectPage());
        if (tableInfo.havePK()) {
            builder.add(new DeleteById()).add(new DeleteBatchByIds()).add(new UpdateById()).add(new UpdateAllColumnById()).add(new SelectById()).add(new SelectBatchByIds());
        } else {
            this.logger.warn(String.format("%s ,Not found @TableId annotation, Cannot use Mybatis-Plus 'xxById' Method.", tableInfo.getEntityType()));
        }

        return (List)builder.build().collect(Collectors.toList());
    }
}
