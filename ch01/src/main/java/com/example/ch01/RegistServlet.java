package com.example.ch01;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="RegistServlet",urlPatterns = "/RegistServlet")

public class RegistServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        String name=request.getParameter("username");
        String password=request.getParameter("password");
        String []ints=request.getParameterValues("hobby");
        String x=request.getParameter("x");
        String[] y=request.getParameterValues("y");
        String[] ints1=request.getParameterValues("ints1");
        //根据条件跳转到login.html  否则跳转到regist.html 重新注册
        if((name!=null&& name.equals("admin"))&&(password!=null&&password.equals("123"))){
            System.out.println("注册成功"+"<br/><br/>");
            out.println("欢迎"+name+ "密码" +password);
            out.println("<br>兴趣爱好为:");
            //输出选中性别爱好
            if(ints!=null&& ints.length>0){
                for (String anInt : ints) {
                    if (anInt.equals("0")) {
                        out.println("<br>AI");
                    } else if (anInt.equals("1")) {
                        out.println("<br>听音乐");
                    } else if (anInt.equals("2")) {
                        out.println("<br>打篮球");
                    } else if (anInt.equals("3")) {
                        out.println("<br>玩手机");
                    } else if (anInt.equals("4")) {
                        out.println("<br>健身");
                    }

                }
            }
            else{
                System.out.println("<br>兴趣爱好为空");
            }
            out.println("<br>五秒后跳转到login界面");
            //五秒后跳转到login界面
           response.setHeader("refresh","5,url=login.html");
            //response.sendRedirect("login.html");


        }
        else{
            out.println("x="+x+",y="+y+"");
            System.out.println("注册失败  请重新注册");
            response.sendRedirect("regist.html");
        }


    }
}
