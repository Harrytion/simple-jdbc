package com.jackrams.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableId implements Serializable{
    private List<Serializable> idList=new ArrayList<>();

    public List<Serializable> getIdList() {
        return idList;
    }


    public Serializable  getId(Integer i){
        return idList.get(i);
    }

    public void addId(Serializable id){
       // this.idList.add(id);
        this.idList.add(id);
    }

    public TableId(Serializable ... ids){
        this.idList.addAll(Arrays.asList(ids));
    }

}
