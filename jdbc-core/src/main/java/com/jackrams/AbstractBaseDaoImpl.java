package com.jackrams;


import com.jackrams.domain.Example;
import com.jackrams.domain.Id;
import com.jackrams.domain.Page;
import com.jackrams.domain.SQLObject;
import com.jackrams.sql.InsertBuilder;
import com.jackrams.sql.InsertSeletiveBuilder;
import com.jackrams.sql.SQLBuilder;
import com.jackrams.utils.JdbcUtils;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public  abstract class AbstractBaseDaoImpl<T> implements BaseDao<T,Id> {
    @Override
    public Integer insert(T o) throws Exception{
        if(null!=o){
            SQLBuilder sqlBuilder =new InsertBuilder(o);
            SQLObject sqlObject = sqlBuilder.getSQLObject();
         return JdbcUtils.excuteUpdate(sqlObject);

        }


        return null;
    }

    @Override
    public Integer insertSelective(T o) throws Exception{

        if(null !=o) return JdbcUtils.excuteUpdate(new InsertSeletiveBuilder(o).getSQLObject());

        return null;
    }


    @Override
    public Integer insertList(Iterable<T> ts) {
        if(null != ts){
            List<T> objectList =new ArrayList<>();
            Iterator<T> iterator = ts.iterator();
            while (iterator.hasNext()){
                objectList.add(iterator.next());
            }

        }
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
    public Integer delete(Id t) {
        return null;
    }

    @Override
    public Integer deletes(Collection<Id> collection) {
        return null;
    }

    @Override
    public Integer deletes(Id[] serializables) {
        return null;
    }

    @Override
    public T selectById(Id serializable) {
        return null;
    }

    @Override
    public List selectByIds(Collection<Id> collection) {
        return null;
    }

    @Override
    public List selectByIds(Id[] serializables) {
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



    private Class<? extends T> domainClass;

    public AbstractBaseDaoImpl() {

        domainClass = getDomainClass();
    }

}
