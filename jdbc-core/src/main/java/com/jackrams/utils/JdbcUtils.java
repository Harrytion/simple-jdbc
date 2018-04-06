package com.jackrams.utils;

import com.jackrams.domain.SQLObject;
import com.jackrams.excepts.UtilsCannotInstanceException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class JdbcUtils {
    private static DataSource dataSource;

    public static DataSource getDataSource(){
        if(dataSource==null){
            throw new UtilsException("dataSource is Null");
        }
        return dataSource;

    }

    public static void setDataSource(DataSource dataSource) {
        if(dataSource==null){
            throw new UtilsException("dataSource isNull");
        }
        JdbcUtils.dataSource = dataSource;
    }

    /**
     * 加载DataSource
     */
    static{
        ServiceLoader<DataSourceLoader> dataSourceLoaders=ServiceLoader.load(DataSourceLoader.class);
        Iterator<DataSourceLoader> iterator = dataSourceLoaders.iterator();

        if(iterator.hasNext()){
            iterator.next().setDataSource();
        }

    }

    public static Connection getConnection() throws SQLException{

        return dataSource.getConnection();
    }


    public static PreparedStatement getPreparedStatement(String str) throws SQLException{

        return dataSource.getConnection().prepareStatement(str);
    }


    public static Integer excuteUpdate(String sql, List<Object> objects) throws SQLException{
        PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sql);
        int index=1;
        for (Object obj : objects){
            preparedStatement.setObject(index++,obj);
        }
        return preparedStatement.executeUpdate();
    }

    public static Integer excuteUpdate(SQLObject sqlObject) throws Exception{
        return excuteUpdate(sqlObject.getSql(),sqlObject.getObjects());
    }

    public static Integer excuteBatchUpdate(List<SQLObject> sqlObjects) throws Exception{
        Integer count=0;
       final Map<String,PreparedStatement> preparedStatementMap =new HashMap<>(sqlObjects.size());
        PreparedStatement firstPreparedStatement=null;
        for (SQLObject sqlObject : sqlObjects){
            if(null !=sqlObject.getSql()){
                PreparedStatement preparedStatement = preparedStatementMap.get(sqlObject.getSql());
                if(null==preparedStatement){
                    preparedStatement=dataSource.getConnection().prepareStatement(sqlObject.getSql());
                    firstPreparedStatement=preparedStatement;
                    preparedStatementMap.put(sqlObject.getSql(),firstPreparedStatement);
                }
                List<Object> objects = sqlObject.getObjects();



            }else {

            }
        }

        for (String key : preparedStatementMap.keySet()){
            int[] batch = preparedStatementMap.get(key).executeBatch();
            count+=batchCount(batch);
        }

        return count;
    }


    private   JdbcUtils(){
        throw new UtilsCannotInstanceException();
    }

    private static Integer batchCount(int [] batch){
        AtomicInteger countAtomicInteger =new AtomicInteger();
    //    Integer count=0;
        for (Integer i :batch){
            countAtomicInteger.addAndGet(i);
        }
        return countAtomicInteger.get();
    }
}
