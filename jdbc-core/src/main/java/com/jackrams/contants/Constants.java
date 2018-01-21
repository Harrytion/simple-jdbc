package com.jackrams.contants;

import com.jackrams.domain.EntityClass;
import com.jackrams.domain.SQLObject;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author 一介布衣
 *
 */


public interface Constants {
    //Default Jdbc Type is Mysql, And Only Support Mysql Now
    String defaultJdbcType="mysql";
    //
   ConcurrentMap<Class<?>,EntityClass> entityClassMap=new ConcurrentHashMap<>();
   //开启驼峰转_默认开启
   Boolean camelCase=true;
}
