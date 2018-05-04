package com.jackrams.sql;

import com.jackrams.domain.ColumnClass;
import com.jackrams.domain.EntityClass;
import com.jackrams.domain.SQLObject;
import com.jackrams.utils.EntityUtils;
import static com.jackrams.utils.SQLUtils.checkDbName;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBatchBuilder<T> implements SQLBuilder {

    protected boolean build=false;

    protected String tableName;

    protected String dbName;

    protected EntityClass entityClass;

    protected List<ColumnClass> columnClasses;

    protected Class<? extends Object> objClazz;

    protected List<T> objects;

    protected List<List<Object>> batchArgList=new ArrayList<>();

    protected StringBuilder sqlBuilder = new StringBuilder();

    protected String baseColumns;

    protected AbstractBatchBuilder(List<T> objects,Class<? extends T> objClazz){
        if(objects.size()==0) throw new IllegalArgumentException("the list of object size is 0");
        this.objects=objects;
        this.objClazz=objClazz;
        this.entityClass=EntityUtils.getEntityClassMap().get(objClazz);
        this.dbName=this.entityClass.getSchema();
        String tableName=this.entityClass.getTableName();
        if(checkDbName(dbName)){
            this.tableName=dbName+"."+tableName;
        }
        this.columnClasses=this.entityClass.getColumnClasses();
        buildBaseColumns();
    }

    protected abstract void bulidSql() throws Exception;

    @Override
    public SQLObject getSQLObject() throws Exception{
        if (!build) bulidSql();
        SQLObject sqlObject = new SQLObject();
        sqlObject.setSql(sqlBuilder.toString());
        sqlObject.setBatchArgs(batchArgList);
        return sqlObject;
    }

    private void buildBaseColumns(){
        StringBuilder baseColumnsBuilder =  new StringBuilder();
        for(ColumnClass columnClass : columnClasses){
            baseColumnsBuilder.append(columnClass.getName()).append(_COMMA);
        }
         baseColumnsBuilder = baseColumnsBuilder.deleteCharAt(baseColumnsBuilder.length() - 1);
        this.baseColumns=baseColumnsBuilder.toString();
    }
}
