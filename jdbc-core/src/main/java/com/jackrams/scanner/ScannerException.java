package com.jackrams.scanner;

public class ScannerException extends RuntimeException {
    static final long serialVersionUID = -45546565692565L;

    public ScannerException(){
        super();
    }

    public ScannerException(String msg){
        super(msg);
    }
    public ScannerException(Throwable throwable){
        super(throwable);
    }

}
