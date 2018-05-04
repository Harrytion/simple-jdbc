package com.jackrams.utils;

import com.jackrams.domain.Example;
import com.jackrams.domain.SQLObject;
import com.jackrams.domain.SelectExample;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class SQLUtils {

    private static Pattern UNDERLINE_TO_CAMELHUMP_PATTERN = Pattern.compile("_[a-z]");

    /**
     * 将驼峰风格替换为下划线风格
     */
    public static String camelhumpToUnderline(String str) {
        final int size;
        final char[] chars;
        final StringBuilder sb = new StringBuilder(
                (size = (chars = str.toCharArray()).length) * 3 / 2 + 1);
        char c;
        for (int i = 0; i < size; i++) {
            c = chars[i];
            if (isUppercaseAlpha(c)) {
                sb.append('_').append(toLowerAscii(c));
            } else {
                sb.append(c);
            }
        }
        return sb.charAt(0) == '_' ? sb.substring(1) : sb.toString();
    }

    /**
     * 将下划线风格替换为驼峰风格
     */
    public static String underlineToCamelhump(String str) {
        Matcher matcher = UNDERLINE_TO_CAMELHUMP_PATTERN.matcher(str);
        StringBuilder builder = new StringBuilder(str);
        for (int i = 0; matcher.find(); i++) {
            builder.replace(matcher.start() - i, matcher.end() - i, matcher.group().substring(1).toUpperCase());
        }
        if (Character.isUpperCase(builder.charAt(0))) {
            builder.replace(0, 1, String.valueOf(Character.toLowerCase(builder.charAt(0))));
        }
        return builder.toString();
    }


    public static boolean isUppercaseAlpha(char c) {
        return (c >= 'A') && (c <= 'Z');
    }


    public static char toLowerAscii(char c) {
        if (isUppercaseAlpha(c)) {
            c += (char) 0x20;
        }
        return c;
    }


    public static String getSqlStringForExample(Example example){
        ConcurrentMap<String, List<Object>> sqlObjectMapFromExample = getSQLObjectMapFromExample(example);
        if(!sqlObjectMapFromExample.isEmpty()){
            Set<String> stringSet = sqlObjectMapFromExample.keySet();
            Iterator<String> iterator = stringSet.iterator();
            if(iterator.hasNext()){
                return iterator.next();
            }
        }

        return "";
    }

    public static String getSqlStringForSelectExample(SelectExample example){
        ConcurrentMap<String, List<Object>> sqlStringObjectMapFromSelectExample = getSQLStringObjectMapFromSelectExample(example);
        if(!sqlStringObjectMapFromSelectExample.isEmpty()){
            Iterator<String> iterator = sqlStringObjectMapFromSelectExample.keySet().iterator();
            return iterator.next();
        }
        return "";
    }

    public static ConcurrentMap<String,List<Object>> getSQLObjectMapFromExample(Example example){
        ConcurrentHashMap<String, List<Object>> stringObjectConcurrentHashMap = new ConcurrentHashMap<>();
        if(null!=example && example.getCriteriaList().size()>0) {
            StringBuilder sqlBuilder=new StringBuilder();
            List<Object> objectList=new ArrayList<>();
            final List<Example.Criteria> criteriaList = example.getCriteriaList();
            for (Example.Criteria criteria : criteriaList){
                if(null==criteria.getAndOr()||StringUtils.isEmpty(criteria.getAndOr())){
                    sqlBuilder.append(" WHERE (");
                }else{
                    sqlBuilder.append(" ").append(criteria.getAndOr())
                            .append(" (");
                }

                List<Example.Criterion> criterions = criteria.getCriterions();
                for (Example.Criterion criterion : criterions){
                    int index = criterions.indexOf(criterion);
                    if(index>0){
                        sqlBuilder.append(" ").append(criterion.getAndOr());
                    }
                    Object[] values = criterion.getValues();
                    if(values.length==0){
                        sqlBuilder.append(" ").append(criterion.getProperty()).append(" ").append(criterion.getCondition());
                    }
                    if (values.length==1){
                        Object value=values[0];
                        if(value instanceof Iterable){
                           final Iterable iterable=(Iterable)value;
                           final Iterator iterator = iterable.iterator();
                           sqlBuilder.append(" ").append(criterion.getProperty()).append(" ").append(criterion.getCondition())
                                   .append("(");
                           while (iterator.hasNext()){
                               sqlBuilder.append("?,");
                               objectList.add(iterator.next());
                           }
                            sqlBuilder= deleteChatAtLastIndex(sqlBuilder).append(")");
                        }else {
                            sqlBuilder.append(" ").append(criterion.getProperty()).append(" ").append(criterion.getCondition())
                                    .append(" ?");
                            objectList.add(criterion.getValue());
                        }
                    }

                    if (values.length==2){
                        sqlBuilder.append(" ").append(criterion.getProperty()).append(" ").append(criterion.getCondition())
                                .append(" ? and ?");
                        objectList.add(values[0]);
                        objectList.add(values[1]);
                    }

                }
                sqlBuilder.append(")");
                if(example instanceof SelectExample) {
                    SelectExample selectExample = (SelectExample) example;
                    Integer page = selectExample.getPage();
                    Integer pageSize = selectExample.getPageSize();
                    List<String> descProperties = selectExample.getDescProperties();
                    List<String> orderByProperties = selectExample.getOrderByProperties();
                    if (orderByProperties.size() > 0) {
                        sqlBuilder.append(" order by");
                        for (String orderBy : orderByProperties){
                            if(descProperties.contains(orderBy)){
                                sqlBuilder.append(" ").append(orderBy).append(" desc,");
                            }else {
                                sqlBuilder.append(" ").append(orderBy).append(" asc ,");
                            }
                        }
                        sqlBuilder=deleteChatAtLastIndex(sqlBuilder);
                    }

                    if(null!=pageSize&&0!=pageSize){
                     if(null==page){
                         page=1;
                     }
                     int start=(page-1)*pageSize;
                     sqlBuilder.append(" limit ").append(start).append(",").append(pageSize);
                    }
                }

                stringObjectConcurrentHashMap.putIfAbsent(sqlBuilder.toString(),objectList);
            }
        }
        return stringObjectConcurrentHashMap;
    }

    /**
     * List<Object> 获取的参数
     * String 获取到的SQL条件
     * 通过Example获取查询条件SQL以及参数
     * @param example
     * @return
     */
    public static ConcurrentMap<String,List<Object>> getSQLStringObjectMapFromSelectExample(SelectExample example){

        return getSQLObjectMapFromExample(example);
    }


    public static SQLObject getSQLObjectFromExample(Example example){
        List<SQLObject> sqlObjectListFromExample = getSQLObjectListFromExample(example);
        return sqlObjectListFromExample.size()>0?sqlObjectListFromExample.get(0):new SQLObject();

    }


    public static SQLObject getSQLObjectFromSelectExample(SelectExample example){
        List<SQLObject> sqlObjectListFromSelectExample = getSQLObjectListFromSelectExample(example);

        return sqlObjectListFromSelectExample.size()>0?sqlObjectListFromSelectExample.get(0):new SQLObject();

    }



    public static List<SQLObject> getSQLObjectListFromExample(Example example){
        List<SQLObject> sqlObjects=new ArrayList<>();
        ConcurrentMap<String, List<Object>> sqlObjectMapFromExample = getSQLObjectMapFromExample(example);
        if(!sqlObjectMapFromExample.isEmpty()){
            Set<String> strings = sqlObjectMapFromExample.keySet();
            for (String str : strings){
                SQLObject sqlObject=new SQLObject();
                sqlObject.setSql(str);
                sqlObject.setObjects(sqlObjectMapFromExample.get(str));
                sqlObjects.add(sqlObject);
            }
        }

        return sqlObjects;

    }


    public static List<SQLObject> getSQLObjectListFromSelectExample(SelectExample example){

        return getSQLObjectListFromExample(example);

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



    public static String toUnderLine(String str){
        if(StringUtils.isEmpty(str))
            return "";

        StringBuilder sb=new StringBuilder();
        Character first=str.charAt(0);
        String lowerCase = first.toString().toLowerCase();
        sb.append(lowerCase);
        sb.append(camelhumpToUnderline(str.substring(1,str.length())));
        return sb.toString();

    }

    public static boolean checkDbName(String dbName){
        return null!=dbName&&dbName.trim().length()>0;

    }


    private static StringBuilder deleteChatAtLastIndex(StringBuilder sb){
        int index = sb.length() - 1;

        return sb.deleteCharAt(index);


    }
    //工具类不允许实例化
    private SQLUtils() throws Exception{
        throw new Exception("欢迎实例化我");
    }




}