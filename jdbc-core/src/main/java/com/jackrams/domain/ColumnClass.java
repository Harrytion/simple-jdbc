package com.jackrams.domain;

import javax.persistence.TemporalType;
import java.lang.reflect.Field;

public final class ColumnClass {
    private Field columnField;
    private String fieldName;
    private String name;
    private Boolean unique;
    private Boolean nullable;
    private Boolean insertable;
    private Boolean updatable;
    private String columnDefinition;
    private String table;
    private Integer length;
    private Integer precision;
    private Integer scale;
    private Boolean idable;

    private TemporalType temporalType;


    public TemporalType getTemporalType() {
        return temporalType;
    }

    public void setTemporalType(TemporalType temporalType) {
        this.temporalType = temporalType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isUnique() {
        return unique;
    }

    public void setUnique(Boolean unique) {
        this.unique = unique;
    }

    public Boolean isNullable() {
        return nullable;
    }

    public void setNullable(Boolean nullable) {
        this.nullable = nullable;
    }

    public Boolean isInsertable() {
        return insertable;
    }

    public Boolean getInsertable() {
        return insertable;
    }

    public void setInsertable(Boolean insertable) {
        this.insertable = insertable;
    }

    public Boolean isUpdatable() {
        return updatable;
    }

    public void setUpdatable(Boolean updatable) {
        this.updatable = updatable;
    }

    public String getColumnDefinition() {
        return columnDefinition;
    }

    public void setColumnDefinition(String columnDefinition) {
        this.columnDefinition = columnDefinition;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public Boolean isIdable() {
        return idable;
    }

    public void setIdable(Boolean idable) {


        this.idable = idable;
    }


    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Field getColumnField() {
        return columnField;
    }

    public void setColumnField(Field columnField) {
        this.columnField = columnField;
    }
}
