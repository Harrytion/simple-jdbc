package com.jackrams.sql;

import java.util.List;

public class InsertBatchBuilder<T> extends AbstractBatchBuilder<T> {
    public InsertBatchBuilder(List<T> objects, Class<T> objClazz) {
        super(objects, objClazz);
    }

    @Override
    protected void bulidSql() throws Exception{
        this.sqlBuilder.append(INSERT);
        append();
        bulidObjects();
    }


}
