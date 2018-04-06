package com.jackrams.helpper;

import com.jackrams.contants.Constants;
import com.jackrams.domain.ColumnClass;
import com.jackrams.domain.EntityClass;
import com.jackrams.scanner.ScannerException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public abstract class PropertyHelpper {


    private static final String SET_METHOD = "set[a-zA-Z0-9_]+";
    private static final String GET_METHOD = "get[a-zA-Z0-9_]+";
    private static final String BOOL_GET_METHOD= "is[a-zA-Z0-9_]+";


    public static String getFieldNameByMethod(String methodName, List<String> fields) {
        String fieldName="";
        if(methodName.matches(BOOL_GET_METHOD)){
            fieldName=methodName.replaceFirst("is","");
        }
        if(methodName.matches(GET_METHOD)){
            fieldName=methodName.replaceFirst("get","");
        }
        if(methodName.matches(SET_METHOD)){
            fieldName=methodName.replaceFirst("set","");
        }
        for(String listName : fields){
            if(listName.equalsIgnoreCase(fieldName)) return fieldName;
        }
        return  null;
    }


    public static  String getPropertyByField(Class clzz,String field){
        ConcurrentMap<Class<?>, EntityClass> entityClassMap = Constants.entityClassMap;
        EntityClass entityClass = entityClassMap.get(clzz);
        Map<String, ColumnClass> fliedColumnMap = entityClass.getFliedColumnMap();
        ColumnClass columnClass = fliedColumnMap.get(field);
        if(columnClass!=null) return columnClass.getName();
        return null;
    }


    private PropertyHelpper(){
      //  throw new
    }

}
