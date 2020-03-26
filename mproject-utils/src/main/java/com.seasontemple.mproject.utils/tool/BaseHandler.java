package com.seasontemple.mproject.utils.tool;

import com.seasontemple.mproject.utils.pojo.RequestMeta;

/**
 * @author Season Temple
 * @program: mproject
 * @description: 责任链模式测试
 * @create: 2020/03/22 16:32:35
 */
public interface BaseHandler {
    /**
     * 处理接收到前端请求的逻辑
     */
    default void received(RequestMeta requestMeta) {}

    /**
     * task过滤完成之后，处理执行task的逻辑
     */
    default void executeHandle(RequestMeta requestMeta) {}

    /**
     * 当实现的前面的方法抛出异常时，将使用当前方法进行异常处理，这样可以将每个handler的异常
     * 都只在该handler内进行处理，而无需额外进行捕获
     */
    default void errorCaught(RequestMeta requestMeta, Throwable e) {
        throw new RuntimeException(e);
    }

    /**
     * 在整个流程中，保证最后一定会执行的代码，主要是用于一些清理工作
     */
    default void afterCompleted(RequestMeta ctx) {}
}
