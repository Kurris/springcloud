package com.example.common.exception;


public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
        super("未授权登陆");
        setCode(401);
    }


    public UnauthorizedException(String message) {
        super(message);
        setCode(401);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private int code;

}
