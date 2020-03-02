package controller;

import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpSession;

// As of Servlet API 3.0, the @WebServlet annotation provides configuration
// settings that used to be included in the deployment descriptor web.xml.
// The annotation below conveys the same information as the following lines
// in the web.xml file in a much easier way:
//
// <servlet>
//    <servlet-name>Home</servlet-name>
//    <servlet-class>controller.Home</servlet-class>
// </servlet>
// <servlet-mapping>
//    <servlet-name>Home</servlet-name>
//    <url-pattern>/home</url-pattern>
// </servlet-mapping>

@WebServlet(name = "Session", urlPatterns = {"/session"})
public class Session extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try (PrintWriter out = response.getWriter()) {
      Cookie[] cookies = request.getCookies();
      Cookie user = findCookie("user",cookies);
      Cookie logout = findCookie("logout",cookies);
      HttpSession session = request.getSession();

      if(request.getParameter("logout") != null){
        logout.setValue("true");
        user.setValue("");

        response.addCookie(logout);
        response.addCookie(user);

        out.println("<h1>You are logged out </h1>");
      }

      if (!user.getValue().equals("")) {
        if(request.getParameter("add") != null){
          session.setAttribute(request.getParameter("attribute"),
              request.getParameter("value"));
        }
        else if(request.getParameter("remove") != null){
          session.removeAttribute(request.getParameter("attribute"));
        }
        out.println("<h1>You are connected as " +
            user.getValue()+
            "</h1>");

        out.println("<form action='session' method='POST' >\n"
            + "Attribute : <input type='text' name='attribute'/>\n"
            + "Value : <input type='text' name='value'/>\n"
            + "<input type='submit' value='Add' name='add'/>\n"
            + "<input type='submit' value='Remove' name='remove'/>\n"
            + "<input type='submit' value='Logout' name='logout'/>\n"
            + "</form>");
      }

      else if (request.getParameter("password").equals("test") &&
          request.getParameter("login").equals("test") &&
          user.getValue().equals("")) {

        user.setValue(request.getParameter("login"));
        logout.setValue("false");

        response.addCookie(user);
        response.addCookie(logout);

        session.setMaxInactiveInterval(30);

        RequestDispatcher dispatcher = request.getRequestDispatcher(
            "session");
        dispatcher.forward(request, response);
      }

      if(!session.isNew()){
        Enumeration<String> attributes = session.getAttributeNames();
        out.println("<ul>");
        while(attributes.hasMoreElements()){
          String att = attributes.nextElement();
          out.println("<li> " + att + " : " + session.getAttribute(att));
        }
        out.println("</ul>");
      }
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    response.setContentType("text/html");

    try (PrintWriter out = response.getWriter()) {
      Cookie[] cookies = request.getCookies();
      Cookie user = findCookie("user",cookies);
      Cookie logout = findCookie("logout",cookies);

      if(user.getValue().equals("")) {
        out.println("<!DOCTYPE html>" +
            "<html>" +
            "<head>" +
            "<title>Home</title>" +
            "</head>" +
            "<form action='session' method='POST' >\n"
            + "Login: <input type='text' name='login' />\n"
            + "Password: <input type='password' name='password' />\n"
            + "<input type='submit' value='Log in' />\n"
            + "</form>"
        );
      }

      out.println("</body>" +
          "</html> ");
    }

  }

  private Cookie findCookie(String name,Cookie[] cookies) {
    Cookie n = null;
    for(Cookie c: cookies){
      if(c.getName().equals(name)){
        n = c;
      }
    }
    if(n == null){
      n = new Cookie(name,"");
    }
    return n;
  }

}
