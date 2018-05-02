package com.jackrams;


import com.jackrams.domain.ColumnClass;
import com.jackrams.domain.EntityClass;
import com.jackrams.domain.Example;
import com.jackrams.domain.Page;
import com.jackrams.contants.Constants;
import com.jackrams.utils.EntityUtils;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public  abstract class AbstractBaseDaoImpl<T,ObjectId> implements BaseDao<T,ObjectId> {
   final ConcurrentMap<Class<?>, EntityClass> entityClassMap = EntityUtils.getEntityClassMap();
    @Override
    public Integer insert(T o) {
        if(null!=o){
            EntityClass entityClass = entityClassMap.get(o.getClass());
            String tableName=entityClass.getTableName();
            String catalog = entityClass.getCatalog();
            String schema = entityClass.getSchema();
            //List<String> idFlied = entityClass.getIdFlied();
            Map<String, ColumnClass> fliedColumnMap = entityClass.getFliedColumnMap();

        }


        return null;
    }

    @Override
    public Integer insertSelective(T o) {
        return null;
    }


    @Override
    public Integer insertList(Iterable<T> ts) {
        return null;
    }

    @Override
    public Integer insertListSelective(Iterable<T> ts) {
        return null;
    }

    @Override
    public Integer update(T o) {
        return null;
    }

    @Override
    public Integer updateSelective(T o) {
        return null;
    }

    @Override
    public Integer update(T newT, Example example) {
        return null;
    }

    @Override
    public Integer updateSelective(T newT, Example example) {
        return null;
    }

    @Override
    public Integer delete(ObjectId t) {
        return null;
    }

    @Override
    public Integer deletes(Collection collection) {
        return null;
    }

    @Override
    public Integer deletes(ObjectId[] serializables) {
        return null;
    }

    @Override
    public T selectById(ObjectId serializable) {
        return null;
    }

    @Override
    public List selectByIds(Collection collection) {
        return null;
    }

    @Override
    public List selectByIds(ObjectId[] serializables) {
        return null;
    }

    @Override
    public Page selectPageByExample(Example example) {
        return null;
    }

    @Override
    public List selectListByExample(Example example) {
        return null;
    }

    @Override
    public List selectListAllLikeAnd(T o) {
        return null;
    }

    @Override
    public Page selectPageAllLikeAnd(T o, int pageSize, int page) {
        return null;
    }

    @Override
    public Connection getConnection() {
        return null;
    }
}
