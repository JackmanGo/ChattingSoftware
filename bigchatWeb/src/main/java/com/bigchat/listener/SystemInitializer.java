package com.bigchat.listener;

import javaSocketService.SocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Created by wang on 16-12-3.
 */
public class SystemInitializer implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(SystemInitializer.class);
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        new Thread(){
            @Override
            public void run(){
                logger.info("socket 成功运行...");
                SocketService.startTCPService();
            }
        }.start();
    }
}
