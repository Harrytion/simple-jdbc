package com.jackrams.helpper;

import com.jackrams.domain.ColumnClass;
import com.jackrams.domain.Example;
import com.jackrams.domain.SelectExample;
import com.jackrams.utils.EntityUtils;
import com.jackrams.utils.SQLUtils;

import java.util.List;

public class ExampleHelper<T> {

    private T t;

    private Class<T> tClass;

    private List<ColumnClass> columnClasses;

    public Example buildAllLikeExample() throws Exception{
        Example custom = Example.custom();
        for (ColumnClass columnClass : columnClasses){
            Object value = columnClass.getColumnField().get(t);
            if(null !=value)custom.andLike(columnClass.getName(),SQLUtils.allLikeString(value.toString()));

        }
        return custom;
    }

    public SelectExample buildAllLikeSelectExample(int page,int pageSize) throws Exception{
        SelectExample custom = SelectExample.custom();
        for (ColumnClass columnClass : columnClasses){
            Object value = columnClass.getColumnField().get(t);
            if(null !=value)custom.andLike(columnClass.getName(),value);

        }
        custom.addPage(page);
        custom.addPageSize(pageSize);
        return custom;
    }
    public ExampleHelper(Class<T> tClass,T t){
        this.tClass=tClass;
        this.t=t ;
        this.columnClasses=EntityUtils.getEntityClassMap().get(tClass).getColumnClasses();
    }
}
