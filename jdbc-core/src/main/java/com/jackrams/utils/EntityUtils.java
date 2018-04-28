package com.jackrams.utils;

import com.jackrams.domain.EntityClass;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author 一介布衣
 *
 */


public class EntityUtils {
    //Default Jdbc Type is Mysql, And Only Support Mysql Now
   public static final String defaultJdbcType="mysql";
    //
   private static ConcurrentMap<Class<?>,EntityClass> entityClassMap=new ConcurrentHashMap<>();
   private static ConcurrentMap<Class<?>,EntityClass> cloneClassMap=null;


   private static boolean change=true;

    public static ConcurrentMap<Class<?>, EntityClass> getEntityClassMap() {
        if(change) cloneEntityClassMapToCloneClassMap();
        return cloneClassMap;
    }

    private static void cloneEntityClassMapToCloneClassMap(){
        cloneClassMap=new ConcurrentHashMap<>(entityClassMap.size());
        Set<Class<?>> classSet = entityClassMap.keySet();
        for (Class<?> clazz : classSet){
            cloneClassMap.putIfAbsent(clazz,entityClassMap.get(clazz));
        }
        change=false;
    }


    public static boolean entityClassMapEmpty(){
       return entityClassMap.isEmpty();
    }

    public static void putIfAbsent(Class<?> clazz,EntityClass entityClass){
        entityClassMap.putIfAbsent(clazz,entityClass);
        change=true;
    }

    public static void putAll(ConcurrentMap<Class<?>, EntityClass> concurrentMap){
        entityClassMap.putAll(concurrentMap);
        change=true;
    }

}

