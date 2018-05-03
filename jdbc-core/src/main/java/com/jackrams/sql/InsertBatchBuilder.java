package com.jackrams.sql;

import com.jackrams.domain.ColumnClass;

import java.util.ArrayList;
import java.util.List;

public class InsertBatchBuilder<T> extends AbstractBatchBuilder<T> {
    public InsertBatchBuilder(List<T> objects, Class<T> objClazz) {
        super(objects, objClazz);
    }

    @Override
    protected void bulidSql() throws Exception{
        this.sqlBuilder.append(INSERT).append(tableName).append(_LEFT_SIGN).append(baseColumns).append(_RIGHT_SIGN).append(VALUES);
        int colSize = this.columnClasses.size();
        this.sqlBuilder.append(_LEFT_SIGN);
        for(int i=0;i<colSize;i++){
            this.sqlBuilder.append(ARG_RE).append(_COMMA);
        }
        this.sqlBuilder = this.sqlBuilder.deleteCharAt(this.sqlBuilder.length()-1);
        this.sqlBuilder.append(_RIGHT_SIGN);
        for (Object o : objects){
            List<Object> objectList = new ArrayList<>();
            for(ColumnClass columnClass : columnClasses){
                Object value = columnClass.getColumnField().get(o);
                objectList.add(value);
            }
            batchArgList.add(objectList);
        }
    }


}
