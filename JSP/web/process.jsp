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
</head>
<%
    if(request.getParameterNames().hasMoreElements() ){
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");

    if (user.equals("test") && pwd.equals("test")) {
%>
<h1> you are logged in as <%= user %>
</h1>

<form action="logout.jsp">
    <input type="submit" value="logout">
</form>
<form action="process.jsp" method="post">
    <input type="text" name="user" value="<%=user%>" hidden>
    <input type="password" name="pwd" value="<%=pwd%>" hidden>
    <input type="submit" value="continue">
</form>

<%
        }
    }
%>

<body>

</body>
</html>
