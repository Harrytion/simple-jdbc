package com.jackrams.sql;

import com.jackrams.domain.EntityClass;
import com.jackrams.utils.EntityUtils;

import java.util.List;

public abstract class AbstractBatchBuilder<T> implements SQLBuilder {

    protected String tableName;

    protected String dbName;

    protected EntityClass entityClass;


    protected Class<? extends Object> objClazz;

    protected List<T> objects;

    protected StringBuilder sqlBuilder = new StringBuilder();



    protected AbstractBatchBuilder(List<T> objects){
        if(objects.size()==0) throw new IllegalArgumentException("the list of object size is 0");
        this.objects=objects;
        this.objClazz=objects.get(0).getClass();
        this.entityClass=EntityUtils.getEntityClassMap().get(this.objClazz);
        this.dbName=this.entityClass.getSchema();
        this.tableName=this.entityClass.getTableName();

    }

    protected abstract void bulidSql();





}
