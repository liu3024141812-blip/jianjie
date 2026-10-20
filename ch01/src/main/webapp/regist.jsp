<%--
  Created by IntelliJ IDEA.
  User: LMW
  Date: 2026/10/13
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册处理</title>
</head>
<body>
     regist.jsp
<%
String name=request.getParameter("username");
String pwd=request.getParameter("password");
%>
     用户名：<%= name == null ? "" : name %><br/>
     密码：<%= pwd == null ? "" : pwd %>
</body>
</html>
