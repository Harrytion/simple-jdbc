package com.jackrams.sql;

import com.jackrams.domain.SQLObject;

public class UpdateBuilder extends AbstractBuilder  {




    public UpdateBuilder(Object object){
        super(object);
        buildSql();
    }

    @Override
    public SQLObject getSQLObject() {
        SQLObject sqlObject = new SQLObject();
        sqlObject.setSql(sqlBuilder.toString());

        return null;
    }

    @Override
    protected void buildSql() {

    }
}
