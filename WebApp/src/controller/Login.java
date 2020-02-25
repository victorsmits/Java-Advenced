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

@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try (PrintWriter out = response.getWriter()) {
      Cookie[] cookies = request.getCookies();
      Cookie user = findCookie("user",cookies);

      if (!user.getValue().equals("")) {
        out.println("<h1>You are connected as " +
            user.getValue() +
            "</h1>");

        out.println("<a href='login'>continue</a> "
            + "<a href='login?logout=true'>logout</a>");
      }
      else if (request.getParameter("password").equals("test") &&
          request.getParameter("login").equals("test") &&
          user.getValue().equals("")) {

        user.setValue(request.getParameter("login"));

        response.addCookie(user);

        RequestDispatcher dispatcher = request.getRequestDispatcher(
            "login");
        dispatcher.forward(request, response);
      }
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    response.setContentType("text/html");

    try (PrintWriter out = response.getWriter()) {
      Enumeration<String> Parameters = request.getParameterNames();
      Cookie[] cookies = request.getCookies();
      Cookie user = findCookie("user",cookies);
      Cookie logout = findCookie("logout",cookies);

      if(logout == null){
        Cookie l = new Cookie("logout","false" );

        response.addCookie(l);

        RequestDispatcher dispatcher = request.getRequestDispatcher(
            "login");
        dispatcher.forward(request, response);
      }

      if(user == null){
        Cookie u = new Cookie("user","" );

        response.addCookie(u);

        RequestDispatcher dispatcher = request.getRequestDispatcher(
            "login");
        dispatcher.forward(request, response);
      }

      System.out.println(user);
      System.out.println(logout);

      if(request.getParameter("logout") != null) {
        assert logout != null;
        if (logout.getValue().equals("false")) {
          assert user != null;
          logout.setValue("true");
        }
      }

      if(user.getValue().equals("")) {
        out.println("<!DOCTYPE html>" +
            "<html>" +
            "<head>" +
            "<title>Home</title>" +
            "</head>" +
            "<form action='login' method='POST' >\n"
            + "Login: <input type='text' name='login' />\n"
            + "Password: <input type='password' name='password' />\n"
            + "<input type='submit' value='Log in' />\n"
            + "</form>"
        );
      }

      if(!user.getValue().equals("") &&
          logout.getValue().equals("false")){
        out.println("<h1>You are connected as " +
            user.getValue()+
            "</h1>");

        out.println("<a href='login'>continue</a> "
            + "<a href='login?logout=true'>logout</a>");
      }

      assert logout != null;
      if(logout.getValue().equals("true") &&
          request.getParameter("logout") != null){
        logout.setValue("false");
        user.setValue("");
        out.println("<h1>You are logged out </h1>");
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
    return n;
  }

}
