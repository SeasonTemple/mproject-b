package com.seasontemple.mproject.utils.exception;

/**
 * @author Season Temple
 * @program: mproject
 * @description: dao自定义异常处理类
 * @create: 2020/04/01 01:22:15
 */
public class CustomException extends RuntimeException {

    public CustomException(String msg){
        super(msg);
    }

    public CustomException() {
        super();
    }
}