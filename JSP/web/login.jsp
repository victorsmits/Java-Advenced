<%--
  Created by IntelliJ IDEA.
  User: victorsmits
  Date: 02/03/2020
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="process.jsp" method="post">
    <input type="text" name="user">
    <input type="password" name="pwd">
    <input type="submit" value="Login">
</form>

<form action="login.jsp" method="post">
    <input type="text" name="user">
    <input type="password" name="pwd">
    <input type="submit" value="Login">
</form>
<%
    if(request.getParameterNames().hasMoreElements() ){
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");

        if (user.equals("test") && pwd.equals("test")) {
%>
<jsp:forward page="process.jsp">
    <jsp:param name="user" value= "<%=user%>"/>
    <jsp:param name="pwd" value= "<%=pwd%>"/>
</jsp:forward>
<%
        }
    }
%>

</body>
</html>
