<%--
  Created by IntelliJ IDEA.
  User: attilio
  Date: 19/03/23
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <jsp:include page="component/header.jsp" >
        <jsp:param name="title" value="Product" />
    </jsp:include>
</head>
    <body>

        <jsp:include page="component/navbar.jsp">
            <jsp:param name="isLogged" value="${isLogged}"/>
            <jsp:param name="login" value="${login}"/>
            <jsp:param name="numItemCart" value="${numItemCart}"/>
        </jsp:include>

        <jsp:include page="component/hero_section.jsp">
            <jsp:param name="homeMessageOne" value="Best Product"/>
            <jsp:param name="homeMessageTwo" value=" Sport time"/>
            <jsp:param name="slogan" value="this is a fantastic slogan"/>
            <jsp:param name="botton" value="Shop Now"/>
            <jsp:param name="requestHref" value="#"/>
        </jsp:include>


        <!-- Open Content -->
        <section class="bg-light">
            <div class="container pb-5">
                <div class="row">
                    <div class="col-lg-5 mt-5">
                        <div class="card mb-3">
                            <img class="card-img img-fluid" src="${pageContext.request.contextPath}/images/sofa.png" alt="Card image cap" id="product-detail">
                        </div>
                        <div class="row">
                            <!--Start Controls-->
                            <div class="col-1 align-self-center">
                                <a href="#multi-item-example" role="button" data-bs-slide="prev">
                                    <i class="text-dark fas fa-chevron-left"></i>
                                    <span class="sr-only">Previous</span>
                                </a>
                            </div>
                            <!--End Controls-->
                            <!--Start Carousel Wrapper-->
                            <div id="multi-item-example" class="col-10 carousel slide carousel-multi-item" data-bs-ride="carousel">
                                <!--Start Slides-->
                                <div class="carousel-inner product-links-wap" role="listbox">

                                    <!--First slide-->
                                    <div class="carousel-item active">
                                        <div class="row">
                                            <div class="col-4">
                                                <a href="#">
                                                    <img class="card-img img-fluid" src="${pageContext.request.contextPath}/images/sofa.png" alt="Product Image 1">
                                                </a>
                                            </div>
                                            <div class="col-4">
                                                <a href="#">
                                                    <img class="card-img img-fluid" src="${pageContext.request.contextPath}/images/sofa.png" alt="Product Image 2">
                                                </a>
                                            </div>
                                            <div class="col-4">
                                                <a href="#">
                                                    <img class="card-img img-fluid" src="${pageContext.request.contextPath}/images/sofa.png" alt="Product Image 3">
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <!--/.First slide-->

                                    <!--Second slide-->
                                    <div class="carousel-item">
                                        <div class="row">
                                            <div class="col-4">
                                                <a href="#">
                                                    <img class="card-img img-fluid" src="${pageContext.request.contextPath}/images/sofa.png" alt="Product Image 4">
                                                </a>
                                            </div>
                                            <div class="col-4">
                                                <a href="#">
                                                    <img class="card-img img-fluid" src="${pageContext.request.contextPath}/images/sofa.png" alt="Product Image 5">
                                                </a>
                                            </div>
                                            <div class="col-4">
                                                <a href="#">
                                                    <img class="card-img img-fluid" src="${pageContext.request.contextPath}/images/sofa.png" alt="Product Image 6">
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <!--/.Second slide-->

                                    <!--Third slide-->
                                    <div class="carousel-item">
                                        <div class="row">
                                            <div class="col-4">
                                                <a href="#">
                                                    <img class="card-img img-fluid" src="${pageContext.request.contextPath}/images/sofa.png" alt="Product Image 7">
                                                </a>
                                            </div>
                                            <div class="col-4">
                                                <a href="#">
                                                    <img class="card-img img-fluid" src="${pageContext.request.contextPath}/images/sofa.png" alt="Product Image 8">
                                                </a>
                                            </div>
                                            <div class="col-4">
                                                <a href="#">
                                                    <img class="card-img img-fluid" src="${pageContext.request.contextPath}/images/sofa.png" alt="Product Image 9">
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <!--/.Third slide-->
                                </div>
                                <!--End Slides-->
                            </div>
                            <!--End Carousel Wrapper-->
                            <!--Start Controls-->
                            <div class="col-1 align-self-center">
                                <a href="#multi-item-example" role="button" data-bs-slide="next">
                                    <i class="text-dark fas fa-chevron-right"></i>
                                    <span class="sr-only">Next</span>
                                </a>
                            </div>
                            <!--End Controls-->
                        </div>
                    </div>
                    <!-- col end -->
                    <div class="col-lg-7 mt-5">
                        <div class="card">
                            <div class="card-body">
                                <h1 class="h2">${singleProduct.getName()}</h1>
                                <p class="h3 py-2">$${singleProduct.getPrice()}</p>
                                <p class="py-2">
                                    <i class="fa fa-star text-warning"></i>
                                    <i class="fa fa-star text-warning"></i>
                                    <i class="fa fa-star text-warning"></i>
                                    <i class="fa fa-star text-warning"></i>
                                    <i class="fa fa-star text-secondary"></i>
                                    <span class="list-inline-item text-dark">Rating 4.8 | 36 Comments</span>
                                </p>
                                <ul class="list-inline">
                                    <li class="list-inline-item">
                                        <h6>Category:</h6>
                                    </li>
                                    <li class="list-inline-item">
                                        <p class="text-muted"><strong>${singleProduct.getCategory()}</strong></p>
                                    </li>
                                </ul>

                                <h6>Description:</h6>
                                <p>${singleProduct.getDescription()}.</p>
                                <ul class="list-inline">
                                    <li class="list-inline-item">
                                        <h6>Avaliable Color :</h6>
                                    </li>
                                    <li class="list-inline-item">
                                        <p class="text-muted"><strong>White / Black</strong></p>
                                    </li>
                                </ul>

                                <h6>Specification:</h6>
                                <ul class="list-unstyled pb-3">
                                    <li>Lorem ipsum dolor sit</li>
                                    <li>Amet, consectetur</li>
                                    <li>Adipiscing elit,set</li>
                                    <li>Duis aute irure</li>
                                    <li>Ut enim ad minim</li>
                                    <li>Dolore magna aliqua</li>
                                    <li>Excepteur sint</li>
                                </ul>


                                    <div class="row">
                                        <div class="col-auto">
                                            <ul class="list-inline pb-3">
                                                <li class="list-inline-item">Size :

                                                </li>
                                                <li class="list-inline-item"><span class="btn btn-success btn-size">S</span></li>
                                                <li class="list-inline-item"><span class="btn btn-success btn-size">M</span></li>
                                                <li class="list-inline-item"><span class="btn btn-success btn-size">L</span></li>
                                                <li class="list-inline-item"><span class="btn btn-success btn-size">XL</span></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="row pb-3">
                                        <div class="col d-grid">
                                            <form action="AddCartServlet" method="get">
                                                <button class="btn btn-success btn-lg" name="codeProduct" value=${singleProduct.getCode()}>Add Cart</button>
                                                <input type="hidden" name="landingPage" value="single_product">
                                            </form>
                                        </div>
                                        <div class="col d-grid">
                                            <form action="RemoveCartServlet" method="get">
                                                <button class="btn btn-success btn-lg" name="codeProduct" value=${singleProduct.getCode()}>Remove Cart</button>
                                                <input type="hidden" name="landingPage" value="single_product">
                                            </form>
                                        </div>
                                    </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Close Content -->








        <%--<c:choose>
            <c:when test="${ login == null || login == 0 }">
                <a href="LoginServlet" >login</a>
            </c:when>
            <c:otherwise>
                <a href="LogoutServlet" >logout</a>
                <h4>Cart: ${numItemCart}</h4>
                <button><a href="CartPageServlet">view cart</a></button>
            </c:otherwise>
        </c:choose>

        <h4>Name: ${singleProduct.getName()}</h4>
        <h4>Description: ${singleProduct.getDescription()}</h4>
        <h4>Price: ${singleProduct.getPrice()}</h4>
        <h4>Stock: ${singleProduct.getStock()}</h4>
        <h4>Category: ${singleProduct.getCategory().toString()}</h4>

        <form action="AddCartServlet" method="get">
            <button name="codeProduct" value=${singleProduct.getCode()}>Add Cart</button>
            <input type="hidden" name="landingPage" value="single_product">
        </form>
        <form action="RemoveCartServlet" method="get">
            <button name="codeProduct" value=${singleProduct.getCode()}>Remove Cart</button>
            <input type="hidden" name="landingPage" value="single_product">
        </form>--%>

        <jsp:include page="component/footer.jsp"></jsp:include>
        <jsp:include page="component/script.jsp"></jsp:include>

    </body>
</html>
