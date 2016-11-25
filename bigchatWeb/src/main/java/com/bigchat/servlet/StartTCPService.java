package com.bigchat.servlet;


import javaSocketService.SocketService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by wang on 16-3-27.
 */
public class StartTCPService extends HttpServlet {
    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        new Thread(){
            @Override
            public void run(){
                SocketService.startTCPService();
              }
        }.start();
    }
}
