<%@ page import="java.util.Enumeration" %><%--
  Created by IntelliJ IDEA.
  User: victorsmits
  Date: 07/03/2020
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>

<form action="session.jsp" method="post">
    <input type="text" name="user">
    <input type="password" name="pwd">
    <input type="submit" value="Login">
</form>
<%
    if(request.getParameterNames().hasMoreElements() ){
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");
        Cookie[] cookies = request.getCookies();

        for(Cookie c: cookies) {
            if (c.getName().equals("user")) {
                user = c.getValue();
%>
                <jsp:forward page="process2.jsp">
                    <jsp:param name="user" value= "<%=user%>"/>
                </jsp:forward>
<%
            }
        }

        if (user.equals("test") && pwd.equals("test")) {
            response.addCookie(new Cookie("user",user));
%>
            <jsp:forward page="process2.jsp">
                <jsp:param name="user" value= "<%=user%>"/>
            </jsp:forward>
<%
        }
    }
%>

</body>
</html>
