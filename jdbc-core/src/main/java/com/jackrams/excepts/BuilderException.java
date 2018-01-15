package com.jackrams.excepts;

public class BuilderException extends RuntimeException {
    static final long serialVersionUID = -4556465656925565L;
    public BuilderException(){
        super();
    }

    public BuilderException(String msg){
        super(msg);
    }
}
