package com.jackrams.sql;

import com.jackrams.domain.ColumnClass;
import com.jackrams.domain.EntityClass;
import com.jackrams.domain.SQLObject;
import com.jackrams.excepts.BuilderException;
import com.jackrams.utils.EntityUtils;
import static com.jackrams.utils.SQLUtils.checkDbName;

import java.util.ArrayList;
import java.util.List;

public abstract class  AbstractBuilder<T> implements SQLBuilder {

    protected String tableName;

    protected String dbName;

    protected EntityClass entityClass;

    protected String baseColumn;

    protected Object object;

    protected Class<? extends Object> objClazz;

    protected List<Object>  sqlObjectList = new ArrayList<>();

    protected StringBuilder sqlBuilder = new StringBuilder();

    protected StringBuilder valueBuilder = new StringBuilder();

    protected AbstractBuilder(Object object,Class<? extends T> objClazz){
        this.objClazz = objClazz;
        this.object = object;
        this.entityClass = EntityUtils.getEntityClassMap().get(objClazz);
        if(null==this.entityClass) throw new BuilderException("can not find Annotation @Entity in "+objClazz.getName());
        this.dbName=this.entityClass.getSchema();
        if(checkDbName(dbName))
        this.tableName=dbName+"."+this.entityClass.getTableName();
        else this.tableName=this.entityClass.getTableName();


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

    protected boolean build=false;
    @Override
    public SQLObject getSQLObject() throws Exception {
        if(!build) buildSql();
        SQLObject sqlObject =new SQLObject();
        sqlObject.setSql(sqlBuilder.toString());
        sqlObject.setObjects(sqlObjectList);
        return sqlObject;
    }


    protected SQLObject builderSemgent() throws Exception{
        SQLObject sqlObject =new SQLObject();
        List<Object> objectList =new ArrayList<>();
        StringBuilder sqlBuilder =new StringBuilder();
        List<String> idFlied = entityClass.getIdFlied();
        int idSize = idFlied.size();
        for(int i = 0; i< idSize; i++){
            ColumnClass columnClass = entityClass.getFliedColumnMap().get(idFlied.get(i));
            objectList.add(columnClass.getColumnField().get(object));
            if(i==0) {
                sqlBuilder.append(WHERE).append(columnClass.getName()).append(_EQ).append(ARG_RE);
                continue;
            }
            sqlBuilder.append(_AND).append(columnClass.getName()).append(_EQ).append(ARG_RE);
        }
        sqlObject.setSql(sqlBuilder.toString());
        sqlObject.setObjects(objectList);
        return sqlObject;
    }

}
