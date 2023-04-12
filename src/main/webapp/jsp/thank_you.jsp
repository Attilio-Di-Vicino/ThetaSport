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
    <jsp:include page="component/header.jsp" >
        <jsp:param name="title" value="Thank You!" />
    </jsp:include>
    <body class="bg-light">
        <jsp:include page="component/navbar.jsp">
            <jsp:param name="name" value="${userBean.getName()}"/>
            <jsp:param name="isLogged" value="${isLogged}"/>
            <jsp:param name="login" value="${login}"/>
            <jsp:param name="numItemCart" value="${numItemCart}"/>
        </jsp:include>

        <jsp:include page="component/hero_section.jsp">
            <jsp:param name="homeMessageOne" value="Challenge the limits"/>
            <jsp:param name="homeMessageTwo" value=" of your sport with ThetaSport"/>
            <jsp:param name="slogan" value="Challenge the limits of your sport with ThetaSport"/>
            <jsp:param name="botton" value="Shop Now"/>
            <jsp:param name="image" value="bicycle.svg"/>
            <jsp:param name="requestHref" value="HomeServlet"/>
        </jsp:include>

        <!-- Open Content -->
        <section class="bg-light">
            <div class="container pb-5">
                <div class="row">
                    <div class="col-lg-5 mt-5">
                        <div class="card mb-3">
                            <img style="height: 600px;" class="card-img img-fluid" src="${pageContext.request.contextPath}/images/delivery.png" alt="Card image cap" id="product-detail">
                        </div>
                    </div>
                    <!-- col end -->
                    <div class="col-lg-7 mt-5">
                        <div class="card">
                            <div class="card-body">
                                <h1 class="h2">Thank you for your order!</h1>
                                <p class="h5 py-2">Your order will be delivered in 2-3 working days!
                                    Don't hesitate to contact us at support@thetasport.com</p>
                                <ul class="list-inline">
                                    <li class="list-inline-item">
                                        <h6>your tracking code:</h6>
                                    </li>
                                    <li class="list-inline-item">
                                        <p class="text-muted"><strong>#242630</strong></p>
                                    </li>
                                </ul>

                                <h4>Details:</h4>
                                <ul class="list-inline">
                                    <li class="list-inline-item">
                                        <h6>Name :</h6>
                                    </li>
                                    <li class="list-inline-item">
                                        <p class="text-muted"><strong>${name}</strong></p>
                                    </li><br>
                                    <li class="list-inline-item">
                                        <h6>Email :</h6>
                                    </li>
                                    <li class="list-inline-item">
                                        <p class="text-muted"><strong>${email}</strong></p>
                                    </li><br>
                                    <li class="list-inline-item">
                                        <h6>Phone :</h6>
                                    </li>
                                    <li class="list-inline-item">
                                        <p class="text-muted"><strong>${phone}</strong></p>
                                    </li><br>
                                    <li class="list-inline-item">
                                        <h6>State Country :</h6>
                                    </li>
                                    <li class="list-inline-item">
                                        <p class="text-muted"><strong>${stateCountry}</strong></p>
                                    </li><br>
                                    <li class="list-inline-item">
                                        <h6>Country :</h6>
                                    </li>
                                    <li class="list-inline-item">
                                        <p class="text-muted"><strong>${country}</strong></p>
                                    </li><br>
                                    <li class="list-inline-item">
                                        <h6>Address :</h6>
                                    </li>
                                    <li class="list-inline-item">
                                        <p class="text-muted"><strong>${address}</strong></p>
                                    </li><br>
                                    <li class="list-inline-item">
                                        <h6>Shipping Address :</h6>
                                    </li>
                                    <li class="list-inline-item">
                                        <p class="text-muted"><strong>${shippingAddress}</strong></p>
                                    </li><br>
                                    <li class="list-inline-item">
                                        <h6>Postal Code :</h6>
                                    </li>
                                    <li class="list-inline-item">
                                        <p class="text-muted"><strong>${postalCode}</strong></p>
                                    </li><br>
                                    <li class="list-inline-item">
                                        <h6>Order Notes :</h6>
                                    </li>
                                    <li class="list-inline-item">
                                        <p class="text-muted"><strong>${orderNotes}</strong></p>
                                    </li>
                                </ul>
                                <div class="row pb-3">
                                    <div class="col d-grid">
                                        <a class="btn btn-secondary me-2" href="HomeServlet">Back To Shop</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Close Content -->
        <br><br>

        <jsp:include page="component/footer.jsp"></jsp:include>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"></script>
    </body>
</html>