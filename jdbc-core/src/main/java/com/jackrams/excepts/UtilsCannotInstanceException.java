package com.jackrams.excepts;

public class UtilsCannotInstanceException extends RuntimeException{


    public UtilsCannotInstanceException(){
        super("Utils Class Has Instance ");
    }

    public UtilsCannotInstanceException(String msg){
        super(msg);
    }

    public UtilsCannotInstanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UtilsCannotInstanceException(Throwable cause) {
        super(cause);
    }

    public UtilsCannotInstanceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
