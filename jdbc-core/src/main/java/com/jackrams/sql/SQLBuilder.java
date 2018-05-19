package com.jackrams.sql;

import com.jackrams.domain.SQLObject;

public interface SQLBuilder {
    String ARG_RE = "?";
    String INSERT = " INSERT INTO ";
    String REPLACE = " REPLACE INTO ";
    String UPDATE = " UPDATE ";
    String SET = " SET ";
    String VALUES = " VALUES ";
    String DELETE = " DELETE ";
    String SELECT = " SELECT ";
    String WHERE  = " WHERE ";
    String _AND = " AND ";
    String _EQ  = "=" ;
    String SELECT_COUNT = " COUNT(1) AS _COUNT ";
    String _COUNT = "_COUNT";
    String _IN  = " IN " ;
    String _OR  = " OR ";
    String _FROM = " FROM ";
    String _LEFT_SIGN = "( ";
    String _RIGHT_SIGN = " ) ";
    String _COMMA = ",";
    SQLObject getSQLObject() throws Exception;




}
