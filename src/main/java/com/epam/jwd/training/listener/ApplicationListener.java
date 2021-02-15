//package com.epam.jwd.training.listener;
//
//import com.epam.jwd.training.pool.ConnectionPool;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//
//@WebListener
//public class ApplicationListener implements ServletContextListener {
//
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        ConnectionPool.getInstance();
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//        ConnectionPool.getInstance().destroyConnections();
//    }
//
//}
