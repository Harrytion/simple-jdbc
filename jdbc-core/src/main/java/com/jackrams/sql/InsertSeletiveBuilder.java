package com.jackrams.sql;

import com.jackrams.domain.ColumnClass;
import com.jackrams.domain.SQLObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

public class InsertSeletiveBuilder<T> extends AbstractBuilder {

    private transient Log log =LogFactory.getLog(InsertSeletiveBuilder.class);


    @Override
    protected void buildSql() {
        build=true;
        List<ColumnClass> columnClasses = entityClass.getColumnClasses();
        List<String> columnNames= new ArrayList<>();
        this.sqlBuilder.append(INSERT).append(tableName).append(_LEFT_SIGN);

        int columnSize = 0;

        for (ColumnClass columnClass : columnClasses){
            Object o = null;
            try {
                o = columnClass.getColumnField().get(object);
            }catch (Exception e){
                log.error("error in builder sql because of " + e.getMessage());
            }
            if(null != o){
                columnNames.add(columnClass.getName());
                columnSize++;
                sqlObjectList.add(o);
            }
        }

        valueBuilder.append("(");



        sqlBuilder.append(VALUES);

        sqlBuilder.append(valueBuilder);
    }

    public InsertSeletiveBuilder(Object object,Class<? extends T> clazz){

        super(object,clazz);

    }
}
