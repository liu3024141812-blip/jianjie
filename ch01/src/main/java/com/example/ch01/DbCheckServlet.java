package com.example.ch01;

import com.example.ch01.util.DBUtil;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 连接自检页：访问 http://localhost:8080/ch01/DbCheckServlet
 * 能打出数据库版本和 user 表数据，就说明 web.xml 里的四个参数配对了。
 */
@WebServlet(name = "DbCheckServlet", urlPatterns = "/DbCheckServlet")
public class DbCheckServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html><html><head><meta charset='UTF-8'><title>MySQL 连接自检</title></head><body>");
        out.println("<h2>MySQL 连接自检</h2>");
        out.println("<p>driver = " + DBUtil.getDriver() + "</p>");
        out.println("<p>url = " + DBUtil.getUrl() + "</p>");
        out.println("<p>username = " + DBUtil.getUsername() + " / password = " + DBUtil.getMaskedPassword() + "</p>");

        try (Connection conn = DBUtil.getConnection()) {
            out.println("<p style='color:green'>✅ 连接成功：getConnection() 返回 " + conn.getClass().getName() + "</p>");

            try (PreparedStatement ps = conn.prepareStatement("SELECT VERSION(), DATABASE()");
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    out.println("<p>MySQL 版本：" + rs.getString(1) + "<br>当前数据库：" + rs.getString(2) + "</p>");
                }
            }

            try (PreparedStatement ps = conn.prepareStatement("SELECT id, username, password FROM user ORDER BY id");
                 ResultSet rs = ps.executeQuery()) {
                out.println("<table border='1' cellpadding='4'><tr><th>id</th><th>username</th><th>password</th></tr>");
                while (rs.next()) {
                    out.println("<tr><td>" + rs.getInt("id") + "</td><td>" + rs.getString("username")
                            + "</td><td>" + rs.getString("password") + "</td></tr>");
                }
                out.println("</table>");
            }
        } catch (SQLException e) {
            out.println("<p style='color:red'>❌ 连接失败：" + e.getMessage() + "</p>");
            out.println("<pre>");
            for (StackTraceElement el : e.getStackTrace()) {
                out.println(el.toString());
            }
            out.println("</pre>");
            out.println("<p>排查顺序：1) MySQL 服务是否启动；2) url 中的端口/数据库名；"
                    + "3) 用户名密码；4) pom.xml 是否引入 mysql-connector-j（并重新构建部署）。</p>");
        }
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
