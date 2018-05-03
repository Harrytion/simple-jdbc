package com.jackrams.sql;

import com.jackrams.domain.Example;
import com.jackrams.domain.SQLObject;
import com.jackrams.utils.SQLUtils;

public class SelectByExampleBuilder<T> extends AbstractSelectBuilder<T> {
    private Example example;

    public SelectByExampleBuilder(Class<T> tClass, Example example) {
        super(tClass);
        this.example = example;
    }

    @Override
    protected void selectSql() throws Exception {
        buildedSelectSql=true;
        SQLObject sqlObjectFromExample = SQLUtils.getSQLObjectFromExample(example);
        sqlBuilder.append(sqlObjectFromExample.getSql());
        sqlObjectArgs.addAll(sqlObjectFromExample.getObjects());
    }
}
