package com.jackrams.sql;

import com.jackrams.domain.ColumnClass;

import java.lang.reflect.Field;
import java.util.List;

public class AbstractUpdateSelectiveBuilder<T> extends AbstractBuilder<T> {

    public AbstractUpdateSelectiveBuilder(Object object, Class<? extends T> objClazz) {
        super(object, objClazz);
    }

    @Override
    protected void buildSql() throws Exception{
        build = true;
        sqlBuilder.append(UPDATE).append(tableName)
                .append(SET);
        List<ColumnClass> columnClasses = entityClass.getColumnClasses();
        int size = columnClasses.size();
        for (int i = 0; i < size; i++) {
            ColumnClass columnClass = columnClasses.get(i);
            if (columnClass.isIdable()) continue;
            Field columnField = columnClass.getColumnField();
            try {
                Object value = columnField.get(object);
                if (null == value) continue;
                sqlObjectList.add(value);
                if (i == size - 1) {
                    sqlBuilder.append(" ").append(columnClass.getName()).append(_EQ).append(ARG_RE).append(" ");
                }
                sqlBuilder.append(" ").append(columnClass.getName()).append(_EQ).append(ARG_RE).append(_COMMA);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}