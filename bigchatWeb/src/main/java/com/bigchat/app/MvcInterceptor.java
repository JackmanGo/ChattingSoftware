package com.bigchat.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by wang on 16-11-26.
 */
public class MvcInterceptor extends HandlerInterceptorAdapter{

    private static final Logger log = LoggerFactory.getLogger(MvcInterceptor.class);
    /**
     * 前置检查
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //获取用户
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        // 获取用户token
        Method method = handlerMethod.getMethod();
        log.debug("用户access token:{}, 访问目标:{}",System.currentTimeMillis(),
                method.getDeclaringClass().getName() + "." + method.getName());
        return true;
    }
}
