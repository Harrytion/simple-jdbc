package com.jackrams.sql;

import com.jackrams.domain.SQLObject;

public class UpdateSelectiveBuilder<T> extends AbstractUpdateSelectiveBuilder {



    @Override
    protected void buildSql() throws Exception{
        super.buildSql();
        SQLObject sqlObject = builderSemgent();
        sqlBuilder.append(sqlObject.getSql());
        sqlObjectList.addAll(sqlObject.getObjects());
    }

    public UpdateSelectiveBuilder(Object object, Class objClazz) {
        super(object, objClazz);
    }


}
