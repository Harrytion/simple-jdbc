package com.jackrams.gen;

import com.jackrams.InfoBean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class PersonBase extends AbstractBase<InfoBean> {

    @Override
    public InfoBean getText() {
        return new InfoBean();
    }

    public PersonBase(){
        Type genericInterfaces = PersonBase.class.getGenericSuperclass();
        Type type1 = ((ParameterizedType) genericInterfaces).getActualTypeArguments()[0];
        System.out.print(type1.getTypeName());
    }
}
