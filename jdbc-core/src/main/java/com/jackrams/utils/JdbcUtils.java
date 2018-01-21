package com.jackrams.utils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

public class JdbcUtils {
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


}
