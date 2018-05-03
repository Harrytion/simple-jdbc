package com.jackrams.sql;

import com.jackrams.domain.EntityClass;
import com.jackrams.domain.SQLObject;
import com.jackrams.domain.SelectExample;
import com.jackrams.utils.EntityUtils;
import com.jackrams.utils.SQLUtils;

import java.sql.ResultSet;

public class CountBuilder<T> implements SQLBuilder {

    private String tableName;

    private SelectExample example;
    @Override
    public SQLObject getSQLObject() {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(SELECT_COUNT).append(_FROM).append(tableName);
        SQLObject sqlObjectFromSelectExample = SQLUtils.getSQLObjectFromSelectExample(example);
        sqlBuilder.append(sqlObjectFromSelectExample.getSql());
        SQLObject sqlObject = new SQLObject();
        sqlObject.setSql(sqlBuilder.toString());
        sqlObject.setObjects(sqlObjectFromSelectExample.getObjects());
        return sqlObject;

    }


    public CountBuilder(Class<T> tClass, SelectExample example){
        this.example = example;
        EntityClass entityClass = EntityUtils.getEntityClassMap().get(tClass);
        String schema = entityClass.getSchema();
        String tableName = entityClass.getTableName();
        this.tableName =tableName;
        if(null != schema && schema.trim().length()>0){
            this.tableName=schema+"."+tableName;
        }

    }


    public long getCount(ResultSet resultSet) throws Exception{
        if(resultSet.next()){
            return resultSet.getLong(_COUNT);
        }
        return 0L;
    }


}
