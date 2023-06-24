<%--
  Created by IntelliJ IDEA.
  User: attilio
  Date: 16/03/23
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${ errorMessage != null}">
                <h4>${errorMessage}</h4>
            </c:when>
        </c:choose>
        <form action="LoginServlet" method="post">
            <div>
                <label for="email">email</label>
                <input type="text" name="email" id="email" required autofocus/>
            </div>
            <div>
                <label for="password">password</label>
                <input type="password" name="password" id="password" required/>
            </div>
            <div>
                <input type="submit" value="Submit" />
            </div>
        </form>
        <button>
            <a href="RegistrationServlet">register now</a>
        </button>
        <button>
            <a href="ForgotPasswordServlet">forgot password</a>
        </button>
    </body>
</html>
