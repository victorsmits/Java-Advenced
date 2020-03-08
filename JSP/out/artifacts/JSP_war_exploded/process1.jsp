<%@ page import="java.util.Enumeration" %><%--
  Created by IntelliJ IDEA.
  User: Harold
  Date: 04/03/2020
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
    <%
        String user = "";
        Cookie[] cookies = request.getCookies();

        Enumeration<String> attributes = session.getAttributeNames();

        for(Cookie c: cookies) {
            if (c.getName().equals("user")) {
                user = c.getValue();
            }
        }

    %>

    <p> session ID: <%= session.getId() %> </p>
    <h1> you are logged in as <%= user %> </h1>

    <form action="home" method="post">
        Attribute : <input type='text' name='attribute'/>
        Value : <input type='text' name='value'/>
        <input type='submit' value='Add' name='add'/>
        <input type='submit' value='Remove' name='remove'/>
        <input type="submit" value="logout" name="logout">
    </form>

    <ul>
        <%
            while(attributes.hasMoreElements()){
                String att = attributes.nextElement();
                out.println("<li>" + att + " : " + session.getAttribute(att)
                        + "</li>");
            }
        %>
    </ul>
</body>
</html>
