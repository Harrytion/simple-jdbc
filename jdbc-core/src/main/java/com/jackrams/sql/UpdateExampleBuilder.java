package com.jackrams.sql;

import com.jackrams.domain.Example;
import com.jackrams.domain.SQLObject;
import com.jackrams.utils.SQLUtils;

public class UpdateExampleBuilder<T> extends AbstractUpdateBuilder {
    private Example example;
    public UpdateExampleBuilder(Object object, Class objClazz,Example example) {
        super(object, objClazz);
        this.example=example;
    }

    @Override
    protected void buildSql() throws Exception {
        super.buildSql();
        SQLObject sqlObjectFromExample = SQLUtils.getSQLObjectFromExample(example);
        sqlBuilder.append(sqlObjectFromExample.getSql());
        sqlObjectList.addAll(sqlObjectFromExample.getObjects());

    }
}
