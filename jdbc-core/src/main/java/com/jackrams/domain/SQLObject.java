package com.jackrams.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于映射SQL和参数的传递顺序
 */

public class SQLObject  {

    private String sql="";
    private List<Object> objects=new ArrayList<>();
    private List<List<Object>> batchArgs=new ArrayList<>();

    public String getSql() {
        return sql;
    }

    public List<Object> getObjects() {
        return objects;
    }

    public void setObjects(List<Object> objects) {
        this.objects = objects;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public SQLObject addObject(Object object){
        this.objects.add(object);
        return this;
    }

    public List<List<Object>> getBatchArgs() {
        return batchArgs;
    }

    public void setBatchArgs(List<List<Object>> batchArgs) {
        this.batchArgs = batchArgs;
    }

    public void addBatchArgs(List<Object> arg){
        this.batchArgs.add(arg);
    }
}
