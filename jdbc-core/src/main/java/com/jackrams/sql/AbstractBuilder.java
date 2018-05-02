package com.jackrams.sql;

import com.jackrams.domain.ColumnClass;
import com.jackrams.domain.EntityClass;
import com.jackrams.utils.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class  AbstractBuilder implements SQLBuilder {

    protected String tableName;

    protected String dbName;

    protected EntityClass entityClass;

    protected String baseColumn;

    protected Object object;

    protected Class<? extends Object> objClazz;

    protected List<Object>  sqlObjectList = new ArrayList<>();

    protected StringBuilder sqlBuilder = new StringBuilder();

    protected StringBuilder valueBuilder = new StringBuilder();

    protected AbstractBuilder(Object object){
        this.objClazz = object.getClass();
        this.object = object;
        this.entityClass = EntityUtils.getEntityClassMap().get(object.getClass());
        this.dbName=this.entityClass.getSchema();
        this.tableName=this.entityClass.getTableName();


    }

    protected String getBaseColumn(){
        StringBuilder sb = new StringBuilder();
        List<ColumnClass> columnClasses = entityClass.getColumnClasses();
        int size = columnClasses.size();
        for(int i=0 ; i < size; i++){
            ColumnClass columnClass = columnClasses.get(i);
            if(i==size-1){
                sb.append(columnClass.getName());
                break;
            }
            sb.append(columnClass.getName()).append(",");
        }
        this.baseColumn=sb.toString();

        return this.baseColumn;
    }
    protected abstract void buildSql() throws Exception;
}
