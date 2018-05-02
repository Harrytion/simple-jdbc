package com.jackrams.helpper;

import com.jackrams.domain.SQLObject;

public class UpdateStrategy extends AbstractStrategy  {




    public UpdateStrategy(Object object){
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
