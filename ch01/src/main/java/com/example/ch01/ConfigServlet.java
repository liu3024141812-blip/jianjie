package com.example.ch01;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="ConfigServlet",value = "/ConfigServlet",initParams = {@WebInitParam(name="encoding",value = "utf-8")})
public class ConfigServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        //获取servletConfig
        ServletConfig config =this.getServletConfig();
        String serveltName=config.getServletName();
        out.println("当前servelt的name是:"+serveltName);
        String encodingValue = config.getInitParameter("encoding");
        out.println("当前servelt的初始化参数enco的值是"+encodingValue);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
