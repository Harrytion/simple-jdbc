package com.jackrams.jdbc;

public enum QueryType {




    //like
    LIKE,

    //=
    EQUALS

    //like %?%
    ,ALL_LIKE

    //in ( );
    ,IN

    // not in ()
    ,NOT_IN

    //not like
    ,NOT_LIKE

    //<>
    ,NOT_EQUALS,

    //like %?
    LEFT_LIKE,

    //like ?%
    RIGHT_LIKE,

    // not like %?
    NOT_LEFT_LIKE,

    //not like ?%
    NOT_RIGHT_LIKE,

    // not like %?%
    NOT_ALL_LIKE


}
