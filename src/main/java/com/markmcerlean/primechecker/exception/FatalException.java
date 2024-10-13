package com.markmcerlean.primechecker.exception;

public class FatalException extends Exception{
    private static final long serialVersionUID = 0;
    public FatalException() {super(); }
    public FatalException(String message) {super(message); }
    public FatalException(String message, Throwable e) {super(message, e); }
    public FatalException(Throwable e) {super(e); }
}
