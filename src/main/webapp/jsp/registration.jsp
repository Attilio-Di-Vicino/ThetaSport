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
            <jsp:param name="title" value="Registration" />
        </jsp:include>

        <style>
            <%@ include file="../css/style-login.css" %>
            .validation01 {
                background: red;
                color: #fff;
                display: none;
                font-size: 12px;
                padding: 3px;
                position: absolute;
                right: -110px;
                text-align: center;
                top: 0;
                width: 100px;
            }
            input:focus + .validation01 {
                display: block;
            }
            input:focus:required:valid + .validation01 {
                background: green;
            }
            input:focus:required:valid + .validation01 .invalid {
                display: none;
            }
            input:focus:required:invalid + .validation01 .valid {
                display: none;
            }
        </style>
    </head>

    <body>
    <br><br>
        <div class="main">
            <!-- Sign up form -->
            <section class="signup">
                <div class="container">
                    <div class="signup-content">
                        <div class="signup-form">
                            <h2 class="form-title">Sign up</h2>
                            <c:choose>
                                <c:when test="${ errorMessage != null}">
                                    <h4 class="term-service" style="color: red">${errorMessage}</h4>
                                </c:when>
                            </c:choose>
                            <form method="post" action="RegistrationServlet" class="register-form" name="modulo" onsubmit="return testpass(this)">
                                <div class="form-group">
                                    <label for="name">
                                        <i class="zmdi zmdi-account material-icons-name"></i>
                                    </label>
                                    <input required autofocus maxlength="45" type="text" name="name" id="name" placeholder="Your Name" />
                                </div>
                                <div class="form-group">
                                    <label for="email">
                                        <i class="zmdi zmdi-email"></i>
                                    </label>
                                    <input required maxlength="20" type="email" name="email" id="email" placeholder="Your Email" />
                                </div>
                                <div class="form-group">
                                    <label for="password">
                                        <i class="zmdi zmdi-lock"></i>
                                    </label>
                                    <input id="password" type="password" name="password" id="password"
                                           title="Minimum 8 characters, one number, one uppercase and one lowercase letter"
                                           required minlength="6"/>
                                    <!-- pattern="(?=^.{8,}$)((?=.*d)|(?=.*W+))(?![.n])(?=.*[A-Z])(?=.*[a-z]).*"  pattern per la password -->
                                    <p class="validation01">
                                        <span class="invalid">Minimum 8 characters, one number, one uppercase letter and one lowercase letter</span>
                                        <span class="valid">Your password meets our requirements, thank you.</span>
                                    </p>
                                </div>
                                <div class="form-group">
                                    <label for="conferma_password"><i class="zmdi zmdi-lock-outline"></i></label>
                                    <input id="conferma_password" type="password" name="conferma_password" onsubmit="return testpass(this)"
                                           title="Minimum 8 characters, one number, one uppercase and one lowercase letter"
                                           required minlength="6"/><br />
                                    <p class="validation01">
                                        <span class="invalid">Minimum 8 characters, one number, one uppercase letter and one lowercase letter</span>
                                        <span class="valid">Your password meets our requirements, thank you.</span>
                                    </p>
                                </div>

                                <div class="form-group">
                                    <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" />
                                    <label for="agree-term" class="label-agree-term">
                                        I agree all statements in
                                        <a href="#" class="term-service">Terms of service</a>
                                    </label>
                                </div>
                                <div class="form-group form-button">
                                    <input type="submit" name="signup" id="signup" class="form-submit" value="Register" />
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
        </div>
    <br><br>

        <script type="text/javascript">
            function testpass(conferma_password){
                // Verifico che le due password siano uguali, in caso contrario avverto
                // dell'errore con un Alert
                if (conferma_password.password.value != conferma_password.conferma_password.value) {
                    alert("The entered password does not match the first one!")
                    conferma_password.password.focus()
                    conferma_password.password.select()
                    return false
                }
                return true
            }
        </script>
    </body>
</html>