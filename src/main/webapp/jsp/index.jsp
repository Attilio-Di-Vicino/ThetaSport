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
    <jsp:include page="component/header.jsp" >
        <jsp:param name="title" value="Theta Sport" />
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
            <jsp:param name="slogan" value="Challenge the limits of your sport with ThetaSport"/>
            <jsp:param name="botton" value="Shop Now"/>
            <jsp:param name="requestHref" value="HomeServlet"/>
        </jsp:include>

        <!-- List Product -->
        <div class="untree_co-section product-section before-footer-section">
            <div class="container">
                <div class="row">
                    <!-- Start Column 1 -->
                    <c:forEach var="product" items="${productBeanList}">
                        <div class="col-12 col-md-4 col-lg-3 mb-5">
                            <a class="product-item" href="#">
                                <img src="${pageContext.request.contextPath}/images/product/${product.getImage()}" class="img-fluid product-thumbnail">
                                <h3 class="product-title">${product.getName()}</h3>
                                <strong class="product-price">$ ${product.getPrice()}</strong>

                                <span class="icon-cross">
                                <img src="${pageContext.request.contextPath}/images/cross.svg" class="img-fluid">
							</span>
                            </a>
                            <form action="SingleProductServlet" method="get">
                                <button name="codeProduct" value=${product.getCode()}>More</button>
                            </form>

                            <button id="addToCartButton-${product.getCode()}" onclick="addToCart( '${product.getCode()}', 'index', '${pageContext.request.contextPath}' )">Aggiungi al carrello</button>

                            <form action="AddCartServlet" method="get">
                                <input type="hidden" name="landingPage" value="index">
                                <button name="codeProduct" value=${product.getCode()}>Add Cart</button>
                            </form>
                            <form action="RemoveCartServlet" method="get">
                                <button name="codeProduct" value=${product.getCode()}>Remove Cart</button>
                                <input type="hidden" name="landingPage" value="index">
                            </form>
                        </div>
                    </c:forEach>
                    <%-- End Column 1 --%>
                </div>
            </div>
        </div>

        <jsp:include page="component/footer.jsp"></jsp:include>
    </body>
</html>
