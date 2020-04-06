package com.seasontemple.mproject.utils.exception;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 自定义401无权限异常
 * @create: 2020/04/05 00:24:32
 */
public class CustomUnauthorizedException extends RuntimeException {

    public CustomUnauthorizedException(String msg){
        super(msg);
    }

    public CustomUnauthorizedException() {
        super();
    }
}