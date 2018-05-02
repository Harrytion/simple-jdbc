package com.jackrams.sql;

import com.jackrams.domain.ColumnClass;
import com.jackrams.domain.SQLObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

public class InsertSeletiveBuilder extends AbstractBuilder {

    private transient Log log =LogFactory.getLog(InsertSeletiveBuilder.class);

    @Override
    public SQLObject getSQLObject() {
        List<ColumnClass> columnClasses = entityClass.getColumnClasses();
        List<String> columnNames= new ArrayList<>();
        this.sqlBuilder.append(INSERT).append(tableName).append("(");

        //     List<String> fliedNameList = entityClass.getFliedNameList();

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
        return null;
    }

    @Override
    protected void buildSql() {

    }

    public InsertSeletiveBuilder(Object object){

        super(object);

    }
}
