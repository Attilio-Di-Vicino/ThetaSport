<%--
  Created by IntelliJ IDEA.
  User: attilio
  Date: 18/03/23
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Registration</title>
    <style>
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
        <c:choose>
            <c:when test="${ errorMessage != null}">
                <h4>${errorMessage}</h4>
            </c:when>
        </c:choose>
        <form action="RegistrationServlet" method="post" name="modulo" onsubmit="return testpass(this)">
            <div>
                <label for="name">name</label>
                <input required autofocus maxlength="20" type="text" name="name" id="name" placeholder="Mario" />
            </div>
            <div>
                <label for="email">email</label>
                <input required maxlength="20" type="email" name="email" id="email" placeholder="mariorossi@gmail.com"/>
            </div>
            <div>
                <label for="email">password</label>
                <input id="password" type="password" name="password" id="password"
                       title="Minimum 8 characters, one number, one uppercase and one lowercase letter"
                       required minlength="6"/>
                <!-- pattern="(?=^.{8,}$)((?=.*d)|(?=.*W+))(?![.n])(?=.*[A-Z])(?=.*[a-z]).*"  pattern per la password -->
                <p class="validation01">
                <span class="invalid">Minimum 8 characters, one number, one uppercase letter and one lowercase letter</span>
                    <span class="valid">Your password meets our requirements, thank you.</span>
                </p>
            </div>
            <div>
                <label for="email">conferma password</label>
                <input id="conferma_password" type="password" name="conferma_password" onsubmit="return testpass(this)"
                       title="Minimum 8 characters, one number, one uppercase and one lowercase letter"
                       required minlength="6"/><br />
                    <p class="validation01">
                        <span class="invalid">Minimum 8 characters, one number, one uppercase letter and one lowercase letter</span>
                        <span class="valid">Your password meets our requirements, thank you.</span>
                    </p>

            </div>
            <div>
                <input type="submit" value="Sign in" />
            </div>
        </form>
    </body>
</html>
<script type="text/javascript">
    function testpass(conferma_password){
        // Verifico che le due password siano uguali, in caso contrario avverto
        // dell'errore con un Alert
        if (conferma_password.password.value != conferma_password.conferma_password.value) {
            alert("La password inserita non coincide con la prima!")
            conferma_password.password.focus()
            conferma_password.password.select()
            return false
        }
        return true
    }
</script>
