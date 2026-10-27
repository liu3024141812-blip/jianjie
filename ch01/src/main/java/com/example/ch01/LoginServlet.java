package com.example.ch01;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(name="LoginServlet",urlPatterns = "/LoginServlet")

public class LoginServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        String name=request.getParameter("username");
        String password=request.getParameter("password");
        String []ints=request.getParameterValues("hobby");
        //根据条件跳转到index.jsp  否则跳转到login.html 重新登录
        if((name!=null&& name.equals("admin"))&&(password!=null&&password.equals("123"))){
            System.out.println("登录成功");
            response.sendRedirect("index.jsp");
        }
        else{
            System.out.println("登录失败  请重新登录");
            response.sendRedirect("login.html");
        }


    }
}
