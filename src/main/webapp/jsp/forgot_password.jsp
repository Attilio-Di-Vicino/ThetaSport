<%--
  Created by IntelliJ IDEA.
  Project: ThetaSport
  Version: 1.0
  Current year: 2023
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <head>
        <!-- Include header -->
        <jsp:include page="component/header.jsp" >
            <jsp:param name="title" value="Forgot Password" />
        </jsp:include>

        <style>
            <%@ include file="../css/style-login.css" %>
        </style>
</head>
    <body>
        <br><br>
        <!-- Sign up form -->
        <section class="signup">
            <div class="container">
                <div class="signup-content">
                    <div class="signup-form">
                        <h4 class="form-title">An email will be sent to the following address with the respective password</h4>
                        <c:choose>
                            <c:when test="${ errorMessage != null}">
                                <h4 class="term-service" style="color: red">${errorMessage}</h4>
                            </c:when>
                        </c:choose>
                        <form method="post" action="ForgotPasswordServlet">
                            <div class="form-group">
                                <label for="email">
                                    <i class="zmdi zmdi-email"></i>
                                </label>
                                <input required maxlength="20" type="email" name="email" id="email" placeholder="Your Email" />
                            </div>

                            <div class="form-group">
                                <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" />
                                <label for="agree-term" class="label-agree-term">
                                    I agree all statements in
                                    <a href="#" class="term-service">Terms of service</a>
                                </label>
                            </div>
                            <div class="form-group form-button">
                                <input type="submit" name="signup" id="signup" class="form-submit" value="Send request" />
                            </div>
                        </form>
                    </div>
                    <div class="signup-image">
                        <figure>
                            <img src="${pageContext.request.contextPath}/images/signup-image.png" class="img-fluid product-thumbnail">						</figure>
                        </figure>
                        <a href="LoginServlet" class="signup-image-link">I am already member</a>
                    </div>
                </div>
            </div>
        </section>
        <br><br>
    </body>
</html>