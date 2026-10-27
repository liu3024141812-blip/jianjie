<%--
  Created by IntelliJ IDEA.
  User: LMW
  Date: 2026/10/20
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录处理</title>
</head>
<body>

<%
  String name=request.getParameter("username");
  String password=request.getParameter("password");
%>
用户名：<%= name == null ? "" : name %><br/>
密码：<%= password == null ? "" : password %>
</body>
</html>
