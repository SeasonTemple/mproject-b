package com.seasontemple.mproject.web.aop;

import com.seasontemple.mproject.utils.custom.ResponseBean;
import com.seasontemple.mproject.utils.custom.ResultCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * @author Season Temple
 * @program: mproject
 * @description: AOP切面处理参数校验
 * @create: 2020/04/18 19:56:27
 */
@Component
@Aspect
public class BindingResultAop {

    /**
     * 切入点
     * 设置切入点为web层
     * AspectJ支持命名切入点，方法必须是返回void类型
     */
    @Pointcut("execution(* com.*.*.*.controller..*.*(..))")
    public void aopMethod(){}

    /**
     * 检查 Controller 方法的参数是否有效
     * 环绕通知：目标方法执行前后分别执行一些代码，发生异常的时候执行其相应逻辑
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("aopMethod()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        BindingResult bindingResult = null;
        for(Object arg:joinPoint.getArgs()){//遍历被通知方法(controller方法)的参数列表
            if(arg instanceof BindingResult){//参数校验结果会存放在BindingResult中
                bindingResult = (BindingResult) arg;
            }
        }
        if(bindingResult != null){
            if(bindingResult.hasErrors()){//检查是否存在校验错误
                String errorInfo = "";
                List<FieldError> errors = bindingResult.getFieldErrors();//获取字段参数不合法的错误集合
                for(FieldError error : errors){
                    errorInfo = errorInfo + "[" + error.getField() + " " + error.getDefaultMessage() + "]";
                }
                return ResponseBean.builder().code(ResultCode.ERROR).msg(errorInfo).build().success(); //返回异常错误
            }
        }
        return joinPoint.proceed();//执行目标方法
    }
}