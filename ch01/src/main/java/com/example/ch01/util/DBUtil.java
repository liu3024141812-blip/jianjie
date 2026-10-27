package com.example.ch01.util;

import jakarta.servlet.ServletContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库工具类：从 web.xml 的 &lt;context-param&gt; 读取 JDBC 四个要素，
 * 统一对外提供 Connection。
 *
 * <p>web.xml 中的参数名必须与这里保持一致：
 * jdbcDriver / jdbcUrl / jdbcUsername / jdbcPassword</p>
 */
public final class DBUtil {

    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "";
    private static String username = "";
    private static String password = "";
    private static boolean initialized = false;

    private DBUtil() {
    }

    /** 由 DbInitListener 在应用启动时调用一次。 */
    public static void init(ServletContext context) {
        if (initialized) {
            return;
        }
        driver = value(context.getInitParameter("jdbcDriver"), driver);
        url = value(context.getInitParameter("jdbcUrl"), url);
        username = value(context.getInitParameter("jdbcUsername"), username);
        password = value(context.getInitParameter("jdbcPassword"), password);

        if (url.isEmpty()) {
            throw new IllegalStateException("web.xml 中缺少 context-param: jdbcUrl");
        }
        try {
            // JDBC 4 之后驱动会自动注册，这里显式加载以便尽早暴露“忘了加依赖”的问题
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(
                    "找不到数据库驱动 " + driver + "，请检查 pom.xml 是否引入了 mysql-connector-j", e);
        }
        initialized = true;
        System.out.println("[DBUtil] MySQL 配置加载完成 -> " + url + " (user=" + username + ")");
    }

    private static String value(String fromWebXml, String fallback) {
        return (fromWebXml == null || fromWebXml.trim().isEmpty()) ? fallback : fromWebXml.trim();
    }

    /** 取一条新连接，用完请 try-with-resources 关闭。 */
    public static Connection getConnection() throws SQLException {
        if (!initialized) {
            throw new IllegalStateException("DBUtil 尚未初始化，请确认 web.xml 中注册了 DbInitListener");
        }
        return DriverManager.getConnection(url, username, password);
    }

    public static String getDriver() {
        return driver;
    }

    public static String getUrl() {
        return url;
    }

    public static String getUsername() {
        return username;
    }

    /** 密码不回显，只用于日志展示。 */
    public static String getMaskedPassword() {
        return password.isEmpty() ? "" : "******";
    }
}
