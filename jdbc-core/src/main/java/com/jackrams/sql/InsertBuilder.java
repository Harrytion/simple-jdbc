package com.jackrams.sql;

import com.jackrams.domain.ColumnClass;
import com.jackrams.domain.SQLObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class InsertBuilder<T> extends AbstractBuilder{

    private transient Log log = LogFactory.getLog(InsertBuilder.class);


    @Override
    protected void buildSql() throws Exception{
        build=true;
        List<ColumnClass> columnClasses = entityClass.getColumnClasses();

        this.sqlBuilder.append(INSERT).append(tableName).append("(");

   //     List<String> fliedNameList = entityClass.getFliedNameList();

        int columnSize = columnClasses.size();

        valueBuilder.append("(");

        for (int i = 0; i < columnSize; i++){
            ColumnClass columnClass = columnClasses.get(i);
            String name = columnClass.getName();
            sqlObjectList.add(columnClass.getColumnField().get(object));
            if(i==columnSize-1){
                this.sqlBuilder.append(name).append(")");
                valueBuilder.append(ARG_RE).append(")");
            }else {
                this.sqlBuilder.append(name).append(",");
                valueBuilder.append(ARG_RE).append(",");
            }
        }

        sqlBuilder.append(VALUES);

        sqlBuilder.append(valueBuilder);


    }



    public InsertBuilder(Object object,Class<T> tClass){
        super(object,tClass);

    }
}
