package com.jackrams.utils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
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

    /**
     *
     * @param left
     * @param str  匹配字符串
     * @param right
     * @return
     *
     * like 字符串匹配规则
     *
     *
     */

    public static String likeString(boolean left,String str,boolean right){
        if(str==null || str.isEmpty()) return "";

        StringBuilder sb=new StringBuilder();
        if(left){
            sb.append("%");
        }
        sb.append(str);
        if(right){
            sb.append("%");
        }

        return sb.toString();

    }

    /**
     *
     * @param str
     * @return
     *
     * like %str
     */
    public static String leftLikeString(String str){

        return likeString(true,str,false);

    }

    /**
     *
     * @param str
     * @return
     *
     * like str%
     */
    public static String rightLikeString(String str){

        return likeString(false,str,true);
    }


    /**
     *
     * @param str
     * @return
     *
     * like %str%
     */
    public static String allLikeString(String str){
        return likeString(true,str,true);
    }


    public static PreparedStatement getPreparedStatement(String str) throws SQLException{

        return dataSource.getConnection().prepareStatement(str);
    }


}
