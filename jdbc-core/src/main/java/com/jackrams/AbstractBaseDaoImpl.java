package com.jackrams;


import com.jackrams.domain.Example;
import com.jackrams.domain.Page;
import com.jackrams.domain.SQLObject;
import com.jackrams.excepts.OneIdGetManyObjectException;
import com.jackrams.sql.*;
import com.jackrams.utils.JdbcUtils;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public  abstract class AbstractBaseDaoImpl<T> implements BaseDao<T> {
    @Override
    public Integer insert(T o) throws Exception{
        if(null!=o){
            SQLBuilder sqlBuilder =new InsertBuilder(o,getDomainClass());
            SQLObject sqlObject = sqlBuilder.getSQLObject();
         return JdbcUtils.excuteUpdate(sqlObject);

        }


        return null;
    }

    @Override
    public Integer insertSelective(T o) throws Exception{

        if(null !=o) return JdbcUtils.excuteUpdate(new InsertSeletiveBuilder(o,domainClass).getSQLObject());

        return null;
    }


    @Override
    public Integer insertList(Collection<T> ts) throws Exception{
        if(null != ts){
            InsertBatchBuilder<T> tInsertBatchBuilder = new InsertBatchBuilder<>(new ArrayList<>(ts), domainClass);
            SQLObject sqlObject = tInsertBatchBuilder.getSQLObject();
            int count = JdbcUtils.excuteBatchUpdate(sqlObject);
            if(count<0) count=Math.abs(count)/2;
            return count;
        }
        return null;
    }


    @Override
    public Integer update(T o)throws Exception {
        return JdbcUtils.excuteUpdate(new UpdateBuilder(o,domainClass).getSQLObject());
    }

    @Override
    public Integer updateSelective(T o) throws Exception{
        UpdateSelectiveBuilder<T> tUpdateSelectiveBuilder = new UpdateSelectiveBuilder<>(o, domainClass);
        SQLObject sqlObject = tUpdateSelectiveBuilder.getSQLObject();

        return JdbcUtils.excuteUpdate(sqlObject);
    }

    @Override
    public Integer update(T newT, Example example) throws Exception{
        UpdateExampleBuilder<T> tUpdateExampleBuilder = new UpdateExampleBuilder<>(newT, domainClass, example);
        SQLObject sqlObject = tUpdateExampleBuilder.getSQLObject();
        return JdbcUtils.excuteUpdate(sqlObject);
    }

    @Override
    public Integer updateSelective(T newT, Example example) throws Exception{
        UpdateSelectiveExampleBuilder<T> tUpdateSelectiveExampleBuilder = new UpdateSelectiveExampleBuilder<>(newT, domainClass, example);
        SQLObject sqlObject = tUpdateSelectiveExampleBuilder.getSQLObject();
        return JdbcUtils.excuteUpdate(sqlObject);
    }

    @Override
    public Integer delete(Object t) throws Exception{
       return deletes(t);
    }

    @Override
    public Integer deletes(Collection<Object> collection) throws Exception{
        Object[] objects = collection.toArray();
        return deletes(objects);
    }

    @Override
    public Integer deletes(Object ... serializables) throws Exception{
        DeleteBuilder<T> tDeleteBuilder = new DeleteBuilder<>(domainClass, serializables);
        SQLObject sqlObject = tDeleteBuilder.getSQLObject();
        return JdbcUtils.excuteUpdate(sqlObject);
    }

    @Override
    public T selectById(Object serializable) throws Exception{
        List<T> selectByIds = selectByIds(serializable);
        if(selectByIds.size()>1) throw new OneIdGetManyObjectException();
        T t = selectByIds.get(0);
        return t;
    }

    @Override
    public List<T> selectByIds(Collection<Object> collection) throws Exception{
        Object[] objects = collection.toArray();
        return selectByIds(objects);
    }

    @Override
    public List<T> selectByIds(Object ... serializables) throws Exception{
        SelectByIdsBuilder<T> tSelectByIdsBuilder = new SelectByIdsBuilder<T>(domainClass,serializables);
        SQLObject sqlObject = tSelectByIdsBuilder.getSQLObject();
        ResultSet resultSet = JdbcUtils.queryForRowSet(sqlObject);
        return tSelectByIdsBuilder.parseObject(resultSet);
    }

    @Override
    public Page selectPageByExample(Example example,int pageSize, int page) throws Exception{


        return null;
    }

    @Override
    public List selectListByExample(Example example) throws  Exception{
        return null;
    }

    @Override
    public List selectListAllLikeAnd(T o) throws Exception {
        return null;
    }

    @Override
    public Page selectPageAllLikeAnd(T o, int pageSize, int page) throws Exception {
        return null;
    }

    private int getCount(Example example){
     return 0;
    }

    private int getCount(T t){
        return 0;
    }


    private Class<T> domainClass;

    public AbstractBaseDaoImpl() {

        domainClass = getDomainClass();
    }

}
