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
        <!-- Include header -->
        <jsp:include page="component/header.jsp" >
            <jsp:param name="title" value="Login" />
        </jsp:include>

        <style>
            <%@ include file="../css/style-login.css" %>
            input[type="checkbox"] {
                display: inline-block;
                width: auto;
                margin-right: 10px;
            }
        </style>
    </head>

    <body>
        <div class="main">

            <!-- Sing in  Form -->
            <section class="sign-in">
                <div class="container">
                    <div class="signin-content">

                        <div class="signin-image">
                            <figure>
                                <img src="${pageContext.request.contextPath}/images/signin-image.png" class="img-fluid product-thumbnail">						</figure>
                            <a href="RegistrationServlet" class="signup-image-link">Create an account</a>
                        </div>

                        <div class="signin-form">
                            <h2 class="form-title">Sign up</h2>
                            <c:choose>
                                <c:when test="${ errorMessage != null}">
                                    <h4 class="term-service" style="color: red">${errorMessage}</h4>
                                </c:when>
                            </c:choose>
                            <form method="post" action="LoginServlet" class="register-form" id="login-form">
                                <div class="form-group">
                                    <label for="email"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                    <input type="text" name="email" id="email" placeholder="Your Email" required autofocus/>
                                </div>
                                <div class="form-group">
                                    <label for="password"><i class="zmdi zmdi-lock>"></i></label>
                                    <input type="password" name="password" id="password" placeholder="Password" required/>
                                </div>
                                <div class="form-group">
                                    <input type="checkbox" name="remember-me" id="remember-me" class="agree-term" />
                                    <label for="remember-me" class="label-agree-term">Remember me</label>
                                </div>


                                <div class="form-group form-button">
                                    <input type="submit" name="signin" id="signin" class="form-submit" value="Log in" />
                                </div>
                            </form>

                            <!-- Icone per accesso a pagine social -->
                            <div class="social-login">
                                <span class="social-label">Or login with</span>
                                <br>
                                <ul class="socials">
                                    <li><a href="#"><span class="fa fa-brands fa-facebook-f"></span></a></li>
                                    <li><a href="#"><span class="fa fa-brands fa-twitter"></span></a></li>
                                    <li><a href="#"><span class="fa fa-brands fa-instagram"></span></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

        </div>
    </body>
</html>