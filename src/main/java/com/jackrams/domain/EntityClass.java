package com.jackrams.domain;

import java.util.List;
import java.util.Map;

public class EntityClass {
    //驼峰规则是否开启映射Table _
    private Boolean camelCase=true;
    //实体类名称
    private String className;
    //实体类类
    private Class<?> clazz;
    //表名
    private String tableName;
    //表-列
    private List<ColumnClass> columnClasses;
    //类属性名列表
    private List<String> fliedNameList;
    //类属性名和表列名映射
    private Map<String,ColumnClass> fliedColumnMap;
    //Id 属性
    private List<String> idFlied;

    public Boolean getCamelCase() {
        return camelCase;
    }

    public void setCamelCase(Boolean camelCase) {
        this.camelCase = camelCase;

    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<ColumnClass> getColumnClasses() {
        return columnClasses;
    }

    public void setColumnClasses(List<ColumnClass> columnClasses) {
        this.columnClasses = columnClasses;
    }

    public List<String> getFliedNameList() {
        return fliedNameList;
    }

    public void setFliedNameList(List<String> fliedNameList) {
        this.fliedNameList = fliedNameList;
    }

    public Map<String, ColumnClass> getFliedColumnMap() {
        return fliedColumnMap;
    }

    public void setFliedColumnMap(Map<String, ColumnClass> fliedColumnMap) {
        this.fliedColumnMap = fliedColumnMap;
    }

    public List<String> getIdFlied() {
        return idFlied;
    }

    public void setIdFlied(List<String> idFlied) {
        this.idFlied = idFlied;
    }
}
