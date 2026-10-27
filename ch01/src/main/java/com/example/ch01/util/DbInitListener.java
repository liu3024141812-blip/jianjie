package com.example.ch01.util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

/**
 * 应用启动时把 web.xml 里的 JDBC 配置交给 DBUtil。
 * 注册方式：web.xml 里的 &lt;listener&gt;（不要再用 @WebListener，避免重复注册）。
 */
public class DbInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            DBUtil.init(sce.getServletContext());
            System.out.println("[DbInitListener] 数据库配置初始化成功");
        } catch (RuntimeException e) {
            // 启动阶段就报出来，比第一次访问页面才 500 好排查
            System.out.println("[DbInitListener] 数据库配置初始化失败: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("[DbInitListener] 应用停止");
    }
}
