<%--
  Created by IntelliJ IDEA.
  Project: ThetaSport
  Version: 1.0
  Current year: 2023
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="component/header.jsp" >
            <jsp:param name="title" value="Cart" />
        </jsp:include>
    </head>
    <body>
        <jsp:include page="component/navbar.jsp">
            <jsp:param name="name" value="${userBean.getName()}"/>
            <jsp:param name="isLogged" value="${isLogged}"/>
            <jsp:param name="login" value="${login}"/>
            <jsp:param name="numItemCart" value="${numItemCart}"/>
        </jsp:include>

        <jsp:include page="component/hero_section.jsp">
            <jsp:param name="homeMessageOne" value="Best Product"/>
            <jsp:param name="homeMessageTwo" value=" Your Cart"/>
            <jsp:param name="slogan" value="this is a fantastic slogan"/>
            <jsp:param name="botton" value="Shop Now"/>
            <jsp:param name="requestHref" value="HomeServlet"/>
        </jsp:include>

        <div class="untree_co-section before-footer-section">
            <div class="container">
                <div class="row mb-5">
                    <div class="site-blocks-table">
                        <table class="table">
                            <thead>
                            <tr>
                                <th class="product-thumbnail">Image</th>
                                <th class="product-name">Product</th>
                                <th class="product-price">Price</th>
                                <th class="product-quantity">Quantity</th>
                                <th class="product-total">Total</th>
                                <th class="product-remove">Remove</th>
                            </tr>
                            </thead>
                            <!-- Start Product -->
                            <c:forEach var="product" items="${itemsCart.getMyCart().keySet()}">
                                <tbody>
                                <tr>
                                    <td class="product-thumbnail">
                                        <form action="SingleProductServlet" method="get">
                                            <button style="background-color: transparent; border: none" name="codeProduct" value=${product.getCode()}>
                                                <img src="${pageContext.request.contextPath}/images/product/${product.getImage()}" style="height: 120px; width: 120px;" alt="Image" class="img-fluid">
                                            </button>
                                        </form>
                                    </td>
                                    <td class="product-name">
                                        <h2 class="h5 text-black">${product.getName()}</h2>
                                    </td>
                                    <td>$${product.getPrice()}</td>
                                    <td>
                                        <div class="input-group mb-3 d-flex align-items-center quantity-container" style="max-width: 120px;">
                                            <div class="input-group-prepend">
                                                <form action="RemoveCartServlet" method="get">
                                                    <input type="hidden" name="landingPage" value="cart">
                                                    <button class="btn btn-outline-black" name="codeProduct" value="${product.getCode()}"> - </button>
                                                </form>
                                            </div>
                                            <input type="text" name="numP" class="form-control text-center quantity-amount" value="${itemsCart.getMyCart().get( product )}"
                                                   placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1">
                                            <div class="input-group-append">
                                                <form action="AddCartServlet" method="get">
                                                    <input type="hidden" name="landingPage" value="cart">
                                                    <button class="btn btn-outline-black" name="codeProduct" value="${product.getCode()}"> + </button>
                                                </form>
                                            </div>
                                        </div>

                                    </td>
                                    <fmt:setLocale value = "en_US"/>
                                    <td><fmt:formatNumber value="${itemsCart.getMyCart().get( product ) * product.getPrice()}" type="currency"/></td>
                                    <td><form action="RemoveObjectCartServlet" method="get" >
                                        <input type="hidden" name="codeProduct" value="${product.getCode()}">
                                        <input type="submit" class="btn btn-black btn-sm" value="X">
                                    </form></td>
                                </tr>
                                </tbody>
                            </c:forEach>
                            <!-- End Product -->
                        </table>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="row mb-5">
                            <div class="col-md-6 mb-3 mb-md-0">
                                <button class="btn btn-black btn-sm btn-block">Update Cart</button>
                            </div>
                            <div class="col-md-6">
                                <button class="btn btn-outline-black btn-sm btn-block" href="HomeServlet">Continue Shopping</button>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <label class="text-black h4" for="coupon">Coupon</label>
                                <p>Enter your coupon code if you have one.</p>
                            </div>
                            <div class="col-md-8 mb-3 mb-md-0">
                                <input type="text" class="form-control py-3" id="coupon" placeholder="Coupon Code">
                            </div>
                            <div class="col-md-4">
                                <button class="btn btn-black">Apply Coupon</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 pl-5">
                        <div class="row justify-content-end">
                            <div class="col-md-7">
                                <div class="row">
                                    <div class="col-md-12 text-right border-bottom mb-5">
                                        <h3 class="text-black h4 text-uppercase">Cart Totals</h3>
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <div class="col-md-6">
                                        <span class="text-black">Subtotal</span>
                                    </div>
                                    <div class="col-md-6 text-right">
                                        <strong class="text-black">${totalPrice}</strong>
                                    </div>
                                </div>
                                <div class="row mb-5">
                                    <div class="col-md-6">
                                        <span class="text-black">Total</span>
                                    </div>
                                    <div class="col-md-6 text-right">
                                        <strong class="text-black">${totalPrice}</strong>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-12">
                                        <button class="btn btn-black btn-lg py-3 btn-block"><a href="CheckoutServlet" style="color: white; text-decoration: none;">Proceed To Checkout</a></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="component/footer.jsp"></jsp:include>
        <jsp:include page="component/script.jsp"></jsp:include>
    </body>
</html>