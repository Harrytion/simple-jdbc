package com.jackrams.jdbc;

import com.jackrams.excepts.BuilderException;

import java.util.*;

public class QcriteriaBulider {

    private List<Qcriteria> qcriteriaList;

    private List<Integer> priorityList;

    private List<LogicQcri> logicQcris;

    private List<String> descColumns;

    private List<String> ascColumns;

   public QcriteriaBulider append(Qcriteria qcriteria){
        this.qcriteriaList.add(qcriteria);

        return this;
    }


    public QcriteriaBulider append(LogicQcri qcriteria){

       return append(qcriteria,0);

    }

    /**
     * 逻辑运算优先级在于两个条件之间
     * 例如
     * (a and b) or c 第一个逻辑的优先级值要大于第二个
     * a and (b or c) 第二个逻辑的优先级值要大于第一个
     *
     * @param logicQcri
     * @param priority
     * @return
     */

    public QcriteriaBulider append(LogicQcri logicQcri,int priority){
        this.logicQcris.add(logicQcri);
        this.priorityList.add(priority);
        return this;
    }


    public QcriteriaBulider append(String column,boolean isDesc){
       if(isDesc) {
           this.descColumns.add(column);
       }else {
           this.ascColumns.add(column);
       }
       return this;
    }



    public QcriteriaBulider(){
        super();
        this.logicQcris=new ArrayList<>();
        this.priorityList=new ArrayList<>();
        this.descColumns=new ArrayList<>();
        this.ascColumns=new ArrayList<>();
        this.qcriteriaList=new ArrayList<>();
    }

    @Override
    public String toString() {
        Map<String, List<Object>> qcriteraMap = getQcriteraMap(this);
        if(!qcriteraMap.isEmpty()){
            Iterator<Map.Entry<String, List<Object>>> iterator = qcriteraMap.entrySet().iterator();
            if(iterator.hasNext()){
                return iterator.next().getKey();
            }
        }
        return null;
    }

    public List<Qcriteria> getQcriteriaList() {
        return qcriteriaList;
    }

    public List<LogicQcri> getLogicQcris() {
        return logicQcris;
    }

    public List<Integer> getPriorityList() {
        return priorityList;
    }

    public List<String> getAscColumns() {
        return ascColumns;
    }

    public List<String> getDescColumns() {
        return descColumns;
    }

    public static Map<String,List<Object>> getQcriteraMap(QcriteriaBulider qcriteriaBulider){
        List<Qcriteria> qcriteriaList = qcriteriaBulider.getQcriteriaList();
        List<LogicQcri> logicQcris = qcriteriaBulider.getLogicQcris();
        List<Integer> priorityList = qcriteriaBulider.getPriorityList();
        List<String> ascColumns = qcriteriaBulider.getAscColumns();
        List<String> descColumns = qcriteriaBulider.getDescColumns();
        if(qcriteriaList.isEmpty()){
            return new HashMap<>();
        }
        if(qcriteriaList.size()==1){
            List<Object> objects=new ArrayList<>();

        }


        if(qcriteriaList.size()!=logicQcris.size()+1){
            throw new BuilderException("Qcriteria Size is On Support LogicQcris");
        }
        Map<String,List<Object>> qMap=new HashMap<>();

        StringBuilder qb=new StringBuilder( );

        Collections.sort(priorityList);



        return  null;
    }




}
