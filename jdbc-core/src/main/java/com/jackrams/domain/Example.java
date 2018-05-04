package com.jackrams.domain;

import java.util.ArrayList;
import java.util.List;

public class Example{

    private Criteria criteria;

    private List<Criteria> criteriaList;



   protected Example(){
        this.criteria=new Criteria();
        this.criteriaList=new ArrayList<>(2);
    }



    public Criteria getCriteria() {
        return criteria;
    }

    public List<Criteria> getCriteriaList() {
        return criteriaList;
    }



    public static Example custom(){
      return new Example();
    }



    public Example andNewCriteria(){
       this.criteriaList.add(this.criteria);
       this.criteria=new Criteria("and");
       return this;
    }

    public Example orNewCriteria(){
       this.criteriaList.add(this.criteria);
       this.criteria=new Criteria("or");
       return this;
    }

    public Example andIsNull(String property) {
        this.criteria.criterions.add(new Criterion(property, "is null", "and"));
        return this;
    }



    public Example andIsNotNull(String property) {
        this.criteria.criterions.add(new Criterion(property, "is not null", "and"));
        return this;
    }

    public Example andEqualTo(String property, Object value) {
        this.criteria.criterions.add(new Criterion(property, value, "=", "and"));
        return this;
    }

    public Example andNotEqualTo(String property, Object value) {
        this.criteria.criterions.add(new Criterion(property, value, "<>", "and"));
        return this;
    }

    public Example andGreaterThan(String property, Object value) {
        this.criteria.criterions.add(new Criterion(property, value, ">", "and"));
        return this;
    }

    public Example andGreaterThanOrEqualTo(String property, Object value) {
        this.criteria.criterions.add(new Criterion(property, value, ">=", "and"));
        return this;
    }


    public Example andLessThan(String property, Object value) {
        this.criteria.criterions.add(new Criterion(property, value, "<", "and"));
        return this;
    }

    public Example andLessThanOrEqualTo(String property, Object value) {
        this.criteria.criterions.add(new Criterion(property, value, "<=", "and"));
        return this;
    }

    public Example andIn(String property, Iterable values) {
        this.criteria.criterions.add(new Criterion(property, values, "in", "and"));
        return this;
    }

    public Example andNotIn(String property, Iterable values) {
        this.criteria.criterions.add(new Criterion(property, values, "not in", "and"));
        return this;
    }

    public Example andBetween(String property, Object value1, Object value2) {
        this.criteria.criterions.add(new Criterion(property, value1, value2, "between", "and"));
        return this;
    }

    public Example andNotBetween(String property, Object value1, Object value2) {
        this.criteria.criterions.add(new Criterion(property, value1, value2, "not between", "and"));
        return this;
    }

    public Example andLike(String property, Object value) {
        this.criteria.criterions.add(new Criterion(property, value, "like", "and"));
        return this;
    }

    public Example andNotLike(String property, Object value) {
        this.criteria.criterions.add(new Criterion(property, value, "not like", "and"));
        return this;
    }


    public Example orIsNull(String property) {
        this.criteria.criterions.add(new Criterion(property, "is null", "or"));
        return this;
    }

    public Example orIsNotNull(String property) {
        this.criteria.criterions.add(new Criterion(property, "is not null", "or"));
        return this;
    }


    public Example orEqualTo(String property, Object value) {
        this.criteria.criterions.add(new Criterion(property, value, "=", "or"));
        return this;
    }

    public Example orNotEqualTo(String property, Object value) {
        this.criteria.criterions.add(new Criterion(property, value, "<>", "or"));
        return this;
    }

    public Example orGreaterThan(String property, Object value) {
        this.criteria.criterions.add(new Criterion(property, value, ">", "or"));
        return this;
    }

    public Example orGreaterThanOrEqualTo(String property, Object value) {
        this.criteria.criterions.add(new Criterion(property, value, ">=", "or"));
        return this;
    }

    public Example orLessThan(String property, Object value) {
        this.criteria.criterions.add(new Criterion(property, value, "<", "or"));
        return this;
    }

    public Example orLessThanOrEqualTo(String property, Object value) {
        this.criteria.criterions.add(new Criterion(property, value, "<=", "or"));
        return this;
    }

    public Example orIn(String property, Iterable values) {
        this.criteria.criterions.add(new Criterion(property, values, "in", "or"));
        return this;
    }

    public Example orNotIn(String property, Iterable values) {
        this.criteria.criterions.add(new Criterion(property, values, "not in", "or"));
        return this;
    }

    public Example orBetween(String property, Object value1, Object value2) {
        this.criteria.criterions.add(new Criterion(property, value1, value2, "between", "or"));
        return this;
    }

    public Example orNotBetween(String property, Object value1, Object value2) {
        this.criteria.criterions.add(new Criterion(property, value1, value2, "not between", "or"));
        return this;
    }

    public Example orLike(String property, String value) {
        this.criteria.criterions.add(new Criterion(property, value, "like", "or"));
        return this;
    }

    public Example orNotLike(String property, String value) {
        this.criteria.criterions.add(new Criterion(property, value, "not like", "or"));
        return this;
    }


    public Example end(){
       this.criteriaList.add(this.criteria);
       this.criteria=null;
       return this;
    }


    public static class Criteria {
        private String andOr;
        private List<Criterion> criterions;
        public Criteria() {
            this.criterions = new ArrayList<Criterion>(2);
        }

        public Criteria(String andOr){
            this.andOr=andOr;
        }

        public List<Criterion> getCriterions() {
            return criterions;
        }

        public String getAndOr() {
            return andOr;
        }

        public void setAndOr(String andOr) {
            this.andOr = andOr;
        }
    }

    public static class Criterion {
        private String property;
        private Object value;
        private Object secondValue;
        private String condition;
        private String andOr;

        public Criterion(String property, String condition, String andOr) {
            this.property = property;
            this.condition = condition;
            this.andOr = andOr;
        }


        public Criterion(String property, Object value, String condition, String andOr) {
            this.property = property;
            this.value = value;
            this.condition = condition;
            this.andOr = andOr;
        }

        public Criterion(String property, Object value1, Object value2, String condition, String andOr) {
            this.property = property;
            this.value = value1;
            this.secondValue = value2;
            this.condition = condition;
            this.andOr = andOr;
        }

        public String getProperty() {
            return property;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public Object[] getValues() {
            if (value !=null) {
                if (secondValue != null) {
                    return new Object[]{value, secondValue};
                } else {
                    return new Object[] {value};
                }
            } else {
                return new Object[]{};
            }
        }
        public String getCondition() {
            return condition;
        }

        public String getAndOr() {
            return andOr;
        }
    }



}
