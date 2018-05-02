package com.jackrams.helpper;

import com.jackrams.domain.EntityClass;
import com.jackrams.utils.EntityUtils;

import java.util.List;

public abstract class  AbstractStrategy implements SQLStrategy {
    protected EntityClass entityClass;

    protected Object object;

    protected Class<? extends Object> objClazz;

    protected List<Object> objects;

    protected StringBuilder sqlBuilder = new StringBuilder();

    protected AbstractStrategy(Object object){
        this.objClazz = object.getClass();
        this.object = object;
        this.entityClass = EntityUtils.getEntityClassMap().get(object.getClass());
    }

    protected abstract void buildSql();
}
