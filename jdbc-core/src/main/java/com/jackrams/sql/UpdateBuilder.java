package com.jackrams.sql;

import com.jackrams.domain.SQLObject;

public class UpdateBuilder<T> extends AbstractUpdateBuilder  {




    public UpdateBuilder(Object object,Class<T> tClass) throws Exception{
        super(object,tClass);
        buildSql();
    }

    @Override
    protected void buildSql() throws Exception{
        super.buildSql();
        SQLObject sqlObject = builderSemgent();
        sqlBuilder.append(sqlObject.getSql());
        sqlObjectList.addAll(sqlObject.getObjects());
    }
}
