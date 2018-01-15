package com.jackrams.jdbc;

public enum  LogicQcri {

    And{
        @Override
        public String toString() {
            return "and";
        }
    }
    ,
    Or{
        @Override
        public String toString() {
            return "or";
        }
    }


}
