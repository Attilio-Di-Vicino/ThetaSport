<%--
  Created by IntelliJ IDEA.
  User: attilio
  Date: 24/03/23
  Time: 21:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <jsp:include page="component/header.jsp" >
    <jsp:param name="title" value="Cart" />
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
      <jsp:param name="homeMessageTwo" value=" Your Cart"/>
      <jsp:param name="slogan" value="this is a fantastic slogan"/>
      <jsp:param name="botton" value="Shop Now"/>
      <jsp:param name="requestHref" value="#"/>
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
                <c:forEach var="product" items="${itemsCart}">
                <tbody>
                  <tr>
                    <td class="product-thumbnail">
                      <img src="images/product-1.png" alt="Image" class="img-fluid">
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
                            <button class="btn btn-outline-black" name="codeProduct" value=${product.getCode()}>Remove</button>
                          </form>
                        </div>
                        <input type="text" class="form-control text-center quantity-amount" value="1" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1">
                        <div class="input-group-append">
                          <form action="AddCartServlet" method="get">
                            <input type="hidden" name="landingPage" value="cart">
                            <button class="btn btn-outline-black" name="codeProduct" value=${product.getCode()}>Add</button>
                          </form>
                        </div>
                      </div>

                    </td>
                    <td>$49.00</td>
                    <td><a href="#" class="btn btn-black btn-sm">X</a></td>
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

















    <%--<h1>Your Cart</h1>
    <c:choose>
      <c:when test="${ errorCart == true }">
        <a href="LoginServlet" >login</a>
        <h4>${errorMessage}</h4>
      </c:when>
      <c:otherwise>
        <a href="LogoutServlet" >logout</a>
        <h4>Cart: ${numItemCart}</h4>
      </c:otherwise>
    </c:choose>

    <c:forEach var="product" items="${itemsCart}">
      <h4>Name: ${product.getName()} Description: ${product.getDescription()} Price: ${product.getPrice()}</h4>
      <form action="SingleProductServlet" method="get">
        <button name="codeProduct" value=${product.getCode()}>More</button>
      </form>
      <form action="AddCartServlet" method="get">
        <button name="codeProduct" value=${product.getCode()}>Add Cart</button>
        <input type="hidden" name="landingPage" value="cart">
      </form>
      <form action="RemoveCartServlet" method="get">
        <button name="codeProduct" value=${product.getCode()}>Remove Cart</button>
        <input type="hidden" name="landingPage" value="cart">
      </form>
    </c:forEach>
      <h4>Total Price: ${totalPrice}</h4>--%>


    <jsp:include page="component/footer.jsp"></jsp:include>
    <jsp:include page="component/script.jsp"></jsp:include>
  </body>
</html>
