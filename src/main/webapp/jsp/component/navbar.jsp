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
        <!-- Start Header/Navigation -->
        <nav class="fixed-top custom-navbar navbar navbar navbar-expand-md navbar-dark bg-dark" arial-label="Furni navigation bar">

            <div class="container">
                <a class="navbar-brand" href="HomeServlet">Theta Sport<span>.</span></a>

                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsFurni" aria-controls="navbarsFurni" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarsFurni">
                    <ul class="custom-navbar-nav navbar-nav ms-auto mb-2 mb-md-0">
                        <li class="nav-item">
                            <a class="nav-link" href="HomeServlet">Home</a>
                        </li>
                        <li><a class="nav-link" href="">About us</a></li>
                        <li><a class="nav-link" href="">Contact us</a></li>
                        <c:choose>
                            <c:when test="${ param.isLogged == 2 }">
                                <li><a class="nav-link" href="AdminProtectedServlet">Dashboard</a></li>
                            </c:when>
                        </c:choose>
                        <c:choose>
                            <c:when test="${ param.login == null || param.login == 0 }">
                                <li><a class="nav-link" href="LoginServlet" >login</a></li>
                            </c:when>
                            <c:otherwise>
                                <!-- Nav Item - User Information -->
                                <li class="nav-item dropdown no-arrow">
                                    <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <span class="mr-2 d-none d-lg-inline text-gray-600">${param.name}</span>
                                    </a>
                                <!-- Dropdown - User Information -->
                                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                 aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="CartPageServlet">
                                    <!--<i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400 text-black"></i>-->
                                    <h6 class="text-black">My Cart</h6>
                                </a>
                                <a class="dropdown-item" href="CartPageServlet">
                                    <!--<i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>-->
                                    <h6 class="text-black">My Order</h6>
                                </a>
                                <a class="dropdown-item" href="#">
                                    <!--<i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>-->
                                    <h6 class="text-black">My Account</h6>
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <!--<i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>-->
                                    <h6 class="text-black">Logout</h6>
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </ul>

                    <ul class="custom-navbar-cta navbar-nav mb-2 mb-md-0 ms-5">
                        <a class="nav-icon position-relative text-decoration-none">
                            <li><a class="nav-link" href="#"><img src="${pageContext.request.contextPath}/images/user.svg"></a></li>
                            <li>
                                <a class="nav-link" href="CartPageServlet">
                                    <img src="${pageContext.request.contextPath}/images/cart.svg">
                                </a>
                            </li>
                            <span id="cartSection" style="height: 20px;" class="position-relative top-10 left-100 translate-middle badge rounded-pill bg-light text-dark">${param.numItemCart}</span>
                        </a>
                    </ul>
                </div>
            </div>

        </nav>
        <!-- End Header/Navigation -->
    </body>
</html>