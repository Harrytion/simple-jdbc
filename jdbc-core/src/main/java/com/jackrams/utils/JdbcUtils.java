package com.jackrams.utils;

import com.jackrams.domain.SQLObject;
import com.jackrams.excepts.UtilsCannotInstanceException;
import com.jackrams.helpper.CountHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.jackrams.contants.Constants.showSql;

public abstract class JdbcUtils {
    private static DataSource dataSource;

    private static transient Log log = LogFactory.getLog(JdbcUtils.class);

    public static void setDataSource(DataSource dataSource) {
        if(dataSource==null){
            throw new UtilsException("dataSource isNull");
        }
        JdbcUtils.dataSource = dataSource;
    }

    /**
     * 加载DataSource
     */


    public static Connection getConnection() throws SQLException{

        return dataSource.getConnection();
    }


    public static PreparedStatement getPreparedStatement(String str) throws SQLException{

        return dataSource.getConnection().prepareStatement(str);
    }


    public static Integer excuteUpdate(String sql, List<Object> objects) throws SQLException{
        if(showSql) {
            log.info(sql);
        }
      Connection connection = dataSource.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int index=1;
        for (Object obj : objects){
            preparedStatement.setObject(index++,obj);
        }
        int updateSize = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
      return updateSize;
    }

    public static Integer excuteUpdate(SQLObject sqlObject) throws Exception{
        return excuteUpdate(sqlObject.getSql(),sqlObject.getObjects());
    }

    public static Integer excuteBatchUpdate(SQLObject sqlObject) throws Exception{
     //   Integer count=0;
      CountHelper countHelper =new CountHelper();
      if(showSql){
          log.info(sqlObject.getSql());
        }
      Connection connection = dataSource.getConnection();
      try {
      connection.setAutoCommit(false);
      PreparedStatement preparedStatement = connection.prepareStatement(sqlObject.getSql());
        List<List<Object>> batchArgs = sqlObject.getBatchArgs();
        for (List<Object> args : batchArgs){
            int index=1;
            for (Object arg : args){
                preparedStatement.setObject(index++,arg);
            }
            preparedStatement.addBatch();
        }

        int[] batch = preparedStatement.executeBatch();
        connection.commit();
        preparedStatement.close();

        countHelper.addArrayCount(batch);

        }catch (Exception e){
          throw e;
        }finally {
          connection.setAutoCommit(true);
          connection.close();
        }

        return countHelper.getCount();
    }


    public static ResultSet queryForRowSet(SQLObject sqlObject) throws Exception {
        if(showSql){
            log.info(sqlObject.getSql());
        }
      Connection connection = dataSource.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(sqlObject.getSql());
        List<Object> objects = sqlObject.getObjects();
        int index = 1;
        for(Object obj : objects){
            preparedStatement.setObject(index++,obj);
        }
      ResultSet resultSet = preparedStatement.executeQuery();
      preparedStatement.close();
      connection.close();


      return resultSet;
    }


    private   JdbcUtils(){
        throw new UtilsCannotInstanceException();
    }


}
