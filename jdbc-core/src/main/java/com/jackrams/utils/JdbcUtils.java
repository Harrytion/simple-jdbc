package com.jackrams.utils;

import com.jackrams.domain.SQLObject;
import com.jackrams.excepts.UtilsCannotInstanceException;
import com.jackrams.helpper.CountHelper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

public abstract class JdbcUtils {
    private static DataSource dataSource;


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

    public static Integer excuteBatchUpdate(SQLObject sqlObject) throws Exception{
     //   Integer count=0;
        CountHelper countHelper =new CountHelper();
        PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sqlObject.getSql());
        List<List<Object>> batchArgs = sqlObject.getBatchArgs();
        for (List<Object> args : batchArgs){
            int index=1;
            for (Object arg : args){
                preparedStatement.setObject(index++,arg);
            }
            preparedStatement.addBatch();
        }

        int[] batch = preparedStatement.executeBatch();

        countHelper.addArrayCount(batch);
        return countHelper.getCount();
    }


    public static ResultSet queryForRowSet(SQLObject sqlObject) throws Exception {
        PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sqlObject.getSql());
        List<Object> objects = sqlObject.getObjects();
        int index = 1;
        for(Object obj : objects){
            preparedStatement.setObject(index++,obj);
        }
        return preparedStatement.executeQuery();
    }


    private   JdbcUtils(){
        throw new UtilsCannotInstanceException();
    }


}
