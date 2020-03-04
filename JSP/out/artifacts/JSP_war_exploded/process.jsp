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
<jsp:scriptlet>
    String user = request.getParameter("user");
    String pwd = request.getParameter("pwd");
</jsp:scriptlet>

<jsp:scriptlet>
    if(user.equal("test") && pwd.equal("test")){
</jsp:scriptlet>
        <h1>you are logged in as
    <jsp:scriptlet>
        user;
    }
    else{
        response.sendRedirect("login.jsp");
    }
    </jsp:scriptlet>
</h1>
<body>

</body>
</html>
