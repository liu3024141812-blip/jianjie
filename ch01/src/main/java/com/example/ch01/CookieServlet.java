package com.example.ch01;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.System.out;

@WebServlet(name="cookieservlet",value="/cookieservlet")
public class CookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        Cookie[] cookies=request.getCookies();
        boolean flag=false;
        if(cookies!=null&&cookies.length>0){

            for (Cookie cookie : cookies) {
                String name= cookie.getName();
                if(name.equals("lastTime")){
                    flag=true;
                    String value=cookie.getValue();
                    out.println("欢迎回来，您上次访问时间为："+value);
//22-用系统时间设置cookie的value
                    Date date=new Date();
                    SimpleDateFormat sdf=new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss");
                    String str_time=sdf.format(date);

                    cookie.setValue(str_time);
                    cookie.setMaxAge(60*60*24*30);//设置一个月期限

                    response.addCookie(cookie);
                    break;
                }
            }
        }
        //第一次访问
        if(cookies==null || cookies.length==0 || flag==false){
//1-
            Date date=new Date();
            SimpleDateFormat sdf=new SimpleDateFormat(  "yyyy-MM-dd hh:mm:ss");
            String str_time=sdf.format(date);
//2-
            Cookie cookie=new Cookie(  "lastTime",str_time);
            cookie.setMaxAge(60*60*24*30);//设置一个月期限
//3-
            response.addCookie(cookie);
//4-
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}









