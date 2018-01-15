package com.jackrams.domain;

import java.util.ArrayList;
import java.util.List;

public final class SelectExample extends Example{
    private Integer page;

    private Integer pageSize;

    private List<String> ascProperties;

    private List<String> descProperties;
    public SelectExample addPage(Integer page){
        this.page=page;
        return this;
    }

    public SelectExample addPageSize(Integer pageSize){
        this.pageSize=pageSize;
        return this;
    }

    public SelectExample(){
        super();
        ascProperties=new ArrayList<>(2);
        descProperties=new ArrayList<>(2);
    }

    public static SelectExample custom(){
        return new SelectExample();
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getPage() {
        return page;
    }

    public List<String> getAscProperties() {
        return ascProperties;
    }

    public List<String> getDescProperties() {
        return descProperties;
    }

    public SelectExample addAsc(String proterty){
        this.ascProperties.add(proterty);
        return this;
    }

    public SelectExample addDesc(String proterty){
        this.descProperties.add(proterty);
        return this;
    }
}
