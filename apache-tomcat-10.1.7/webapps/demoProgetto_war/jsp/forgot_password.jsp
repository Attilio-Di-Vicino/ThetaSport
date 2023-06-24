<%--
  Created by IntelliJ IDEA.
  User: attilio
  Date: 19/03/23
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Forgot password</title>
</head>
  <body>
    <c:choose>
      <c:when test="${ errorMessage != null}">
        <h4>${errorMessage}</h4>
      </c:when>
    </c:choose>
    <h4>Verrà invita una mail al seguente indirzzo con la rispettiva password</h4>
    <form action="ForgotPasswordServlet" method="post">
      <div>
        <label for="email">email</label>
        <input required maxlength="20" type="email" name="email" id="email" placeholder="mariorossi@gmail.com"/>
      </div>
      <div>
        <input type="submit" value="Send request" />
      </div>
    </form>

  </body>
</html>
