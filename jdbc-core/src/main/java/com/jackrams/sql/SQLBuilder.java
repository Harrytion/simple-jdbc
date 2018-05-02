package com.jackrams.sql;

import com.jackrams.domain.SQLObject;

public interface SQLBuilder {
    String ARG_RE = "?";
    String INSERT = "INSERT INTO ";
    String UPDATE = "UPDATE ";
    String SET = " SET ";
    String VALUES = " VALUES ";
    String DELETE = "DELETE ";
    String SELECT = "SELECT ";
    SQLObject getSQLObject();




}
