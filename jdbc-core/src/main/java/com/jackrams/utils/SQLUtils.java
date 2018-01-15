package com.jackrams.utils;

import com.jackrams.domain.Example;
import com.jackrams.domain.SelectExample;

import java.util.List;
import java.util.Map;
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

        return "";
    }

    public static String getSqlStringForSelectExample(SelectExample example){
        return "";
    }

    public static ConcurrentMap<String,List<Object>> getSQLObjectMapFromExample(Example example){
        ConcurrentHashMap<String, List<Object>> stringObjectConcurrentHashMap = new ConcurrentHashMap<>();
        if(null!=example && example.getCriteriaList().size()>=0) {

        }
        return stringObjectConcurrentHashMap;
    }

    public static ConcurrentMap<String,List<Object>> getSQLStringObjectMapFromSelectExample(SelectExample example){
        ConcurrentHashMap<String, List<Object>> stringObjectConcurrentHashMap = new ConcurrentHashMap<>();
        if(null!=example && example.getCriteriaList().size()>=0){

        }
        return stringObjectConcurrentHashMap;
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







    //工具类不允许实例化
    private SQLUtils() throws Exception{
        throw new Exception("欢迎实例化我");
    }




}