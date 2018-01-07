package com.jackrams.jdbc;

import java.util.List;

public class Qcriteria {

   private String colName;
   private QueryType queryType;
   private Object queryValue;

    public QueryType getQueryType() {
        return queryType;
    }

    public void setQueryType(QueryType queryType) {
        this.queryType = queryType;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public Object getQueryValue() {
        return queryValue;
    }

    public void setQueryValue(Object queryValue) {
        this.queryValue = queryValue;
    }

    private Qcriteria(){
    }

    private Qcriteria(String colName,QueryType queryType,Object queryValue){
        super();
        this.colName=colName;
        this.queryType=queryType;
        this.queryValue=queryValue;
    }

    public static Qcriteria getNewInstance(String colName,QueryType queryType,Object queryValue){
        return new Qcriteria(colName,queryType,queryValue);
    }

    @Override
    public int hashCode() {
        return ((colName.hashCode()+queryValue.hashCode())/31)+30;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Qcriteria)) return false;
        Qcriteria qcriteria=(Qcriteria) obj;
        return this.colName.equals(qcriteria.getColName())&&this.getQueryType()==qcriteria.getQueryType()
                &&this.getQueryValue().equals(qcriteria.queryValue)
                ;
    }
}
