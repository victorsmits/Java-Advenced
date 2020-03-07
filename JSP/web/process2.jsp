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
    <title>Title</title>
</head>
<body>

<%
    Cookie user = new Cookie("user","" );
    Cookie[] cookies = request.getCookies();

    if(request.getParameterNames().hasMoreElements() ){

        for(Cookie c: cookies) {
            if (c.getName().equals("user")) {
                user.setValue(c.getValue());
                response.addCookie(user);
            }
        }

        HttpSession sessions = request.getSession();
        Enumeration<String> attributes = session.getAttributeNames();

        if(request.getParameter("add") != null){
            session.setAttribute(request.getParameter("attribute"),
                    request.getParameter("value"));
        }
        else if(request.getParameter("remove") != null){
            session.removeAttribute(request.getParameter("attribute"));
        }
        else if(request.getParameter("logout") != null){
            user.setValue("");
            response.addCookie(user);
%>
            <jsp:forward page="logout.jsp">
                <jsp:param name="user" value= "<%=user%>"/>
            </jsp:forward>
<%
        }
%>

<p> session ID:<%=sessions.getId()%> </p>
<h1> you are logged in as <%= user.getValue() %> </h1>

<form action="session.jsp" method="post">
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
    %>
            <li>
                <%=att%> : <%=session.getAttribute(att)%>
            </li>
    <%
        }
    %>
</ul>

<%
    }
%>

<form action="session.jsp" method="post">
    <input type="submit" value="continue" name="continue">
</form>
</body>
</html>
