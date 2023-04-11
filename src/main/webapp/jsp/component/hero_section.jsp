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
    <body>
        <!-- Start Hero Section -->
        <div class="hero">
            <div class="container">
                <div class="row justify-content-between">
                    <div class="col-lg-5">
                        <div class="intro-excerpt">
                            <h1 style="font-size: xx-large">${param.homeMessageOne}<span clsas="d-block">${param.homeMessageTwo}</span></h1>
                            <%--<p class="mb-4">${param.slogan}</p>--%>
                            <p><a href="${param.requestHref}" class="btn btn-secondary me-2">${param.botton}</a></p>
                        </div>
                    </div>
                    <div class="col-lg-7">
                        <div class="hero-img-wrap">
                            <img src="${pageContext.request.contextPath}/images/${param.image}" class="img-fluid">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Hero Section -->
    </body>
</html>