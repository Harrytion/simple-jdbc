package com.jackrams.domain;

public class Example<T> {
    private final T bean;

   private Example(T t){
        this.bean=t;
    }


    public T getBean() {
        return bean;
    }

    public String toSqlString(){

       return toString();
    }
}
