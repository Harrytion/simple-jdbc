package com.jackrams.jdbc;

import com.jackrams.excepts.BuilderException;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Qcriteria {

   private String colName;
   private QueryType queryType;
   private Object queryValue;

    public QueryType getQueryType() {
        return queryType;
    }



    public String getColName() {
        return colName;
    }



    public Object getQueryValue() {
        return queryValue;
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

    public static String getQcriteriaString(Qcriteria qcriteria){
        StringBuilder qb=new StringBuilder();
        qb.append(qcriteria.getColName());
        if(qcriteria.queryType==QueryType.EQUALS){
            qb.append(" = ");
        }else if(qcriteria.getQueryType()==QueryType.IN){
            qb.append( "in (");
            int size=0;
            if(qcriteria.queryValue instanceof Collection){
                Collection<? extends Object> objects=(Collection<? extends Object>) qcriteria.getQueryValue();
                size=objects.size();
            }else if(qcriteria.getQueryValue() instanceof Object[]){
                    Object[] objects=(Object[]) qcriteria.getQueryValue();
                    size=objects.length;
            }else{
                throw new BuilderException("Can not find the Iterable Object Value ");
            }

            for (int i = 0; i <size ; i++) {
                if(i==size-1){
                    qb.append("?)");
                }else{
                    qb.append("?,");
                }
            }
        }



        return qb.toString();



    }

}
