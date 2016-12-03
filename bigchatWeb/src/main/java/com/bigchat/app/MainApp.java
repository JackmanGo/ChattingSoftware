package com.bigchat.app;

import com.bigchat.listener.SystemInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by wang on 16-11-25.
 */
@SpringBootApplication
@ComponentScan("com.bigchat")
public class MainApp extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class);
    }

    /**
     * 添加一个拦截器.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getInterceptor());
    }

    @Bean
    public MvcInterceptor getInterceptor() {
        return new MvcInterceptor();
    }

    /**
     * 项目启动的监听
     **/
    @Bean
    public SystemInitializer startListener(){
         return new SystemInitializer();
     }

}
