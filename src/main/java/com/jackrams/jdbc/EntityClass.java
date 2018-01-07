package com.jackrams.jdbc;

import java.util.List;
import java.util.Map;

public class EntityClass {

    private String className;
    private Class<?> clazz;
    private String tableName;
    private List<ColumnClass> columnClasses;
    private List<String> fliedNameList;
    private Map<String,ColumnClass> fliedColumnMap;
    private List<String> idFlied;

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
