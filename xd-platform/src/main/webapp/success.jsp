<%--
  Created by IntelliJ IDEA.
  User: liushuangbo
  Date: 2018/8/8
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>主页</title>
</head>
<body>
<%=request.getAttribute("nickName")%>你好，
<br>
${nickName}登陆成功
</body>
</html>
