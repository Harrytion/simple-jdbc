package com.jackrams.excepts;

public class OneIdGetManyObjectException extends Exception{
    static final long serialVersionUID = Short.MAX_VALUE;
    public OneIdGetManyObjectException() {

    }

    public OneIdGetManyObjectException(String message) {
        super(message);
    }

    public OneIdGetManyObjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public OneIdGetManyObjectException(Throwable cause) {
        super(cause);
    }

    public OneIdGetManyObjectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
