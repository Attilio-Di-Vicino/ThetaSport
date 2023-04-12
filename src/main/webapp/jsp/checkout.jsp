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
            <jsp:param name="title" value="Checkout" />
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
            <jsp:param name="homeMessageTwo" value=" Sport time"/>
            <jsp:param name="slogan" value="this is a fantastic slogan"/>
            <jsp:param name="botton" value="Shop Now"/>
            <jsp:param name="requestHref" value="HomeServlet"/>
        </jsp:include>

        <div class="untree_co-section">
            <div class="container">
                <div class="row mb-5">
                    <div class="col-md-12">
                        <div class="border p-4 rounded" role="alert">
                            Returning customer? <a href="LoginServlet">Click here</a> to login
                        </div>
                    </div>
                </div>
                <div class="border p-3 mb-3">
                    <h3 class="h6 mb-0"><a class="d-block" data-bs-toggle="collapse" href="CreditCartServlet" role="button" aria-expanded="false" aria-controls="collapsebank">Credit Cart</a></h3>

                    <div class="collapse" id="collapsebank">
                        <div class="py-2">
                            <p class="mb-0">Make your payment directly into our bank account. Please use your Order ID as the payment reference. Your order won’t be shipped until the funds have cleared in our account.</p>
                        </div>
                    </div>
                </div>

                <div class="border p-3 mb-3">
                    <h3 class="h6 mb-0"><a class="d-block" data-bs-toggle="collapse" href="BancomatServlet" role="button" aria-expanded="false" aria-controls="collapsecheque">Bancomat</a></h3>

                    <div class="collapse" id="collapsecheque">
                        <div class="py-2">
                            <p class="mb-0">Make your payment directly into our bank account. Please use your Order ID as the payment reference. Your order won’t be shipped until the funds have cleared in our account.</p>
                        </div>
                    </div>
                </div>

                <div class="border p-3 mb-5">
                    <h3 class="h6 mb-0"><a class="d-block" data-bs-toggle="collapse" href="CashServlet" role="button" aria-expanded="false" aria-controls="collapsepaypal">Cash</a></h3>

                    <div class="collapse" id="collapsepaypal">
                        <div class="py-2">
                            <p class="mb-0">Make your payment directly into our bank account. Please use your Order ID as the payment reference. Your order won’t be shipped until the funds have cleared in our account.</p>
                        </div>
                    </div>
                </div>

                <form action="ActivateCouponServlet" method="post">
                    <div class="row">
                        <!-- Strat Coupon -->
                        <div class="row mb-5">
                            <div class="col-md-12">
                                <h2 class="h3 mb-3 text-black">Coupon Code</h2>
                                <div class="p-3 p-lg-5 border bg-white">

                                    <label for="c_code" class="text-black mb-3">Enter your coupon code if you have one</label>
                                    <div class="input-group w-75 couponcode-wrap">
                                        <input type="text" class="form-control me-2" id="c_code" placeholder="Coupon Code" aria-label="Coupon Code" aria-describedby="button-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-black btn-sm" type="button" id="button-addon2">Apply</button>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <!-- End Coupon -->
                    </div>
                </form>

                <!-- in base al metodo di pagamento di sarà un form specifico -->
                <c:choose>
                    <c:when test="${creditCart == true}">
                        <form action="CreditCartServlet" method="post">
                            <div class="row">
                                <div class="col-md-6 mb-5 mb-md-0" style="float: left;">
                                    <h2 class="h3 mb-3 text-black">Credit Cart Billing Details</h2>
                                    <div class="p-3 p-lg-5 border bg-white">
                                        <!-- Strat Country -->
                                        <div class="form-group">
                                            <label for="c_country" class="text-black">Country <span class="text-danger">*</span></label>
                                            <select required name="c_country" id="c_country" class="form-control">
                                                <option value="1">Select a country</option>
                                                <option value="2">bangladesh</option>
                                                <option value="3">Algeria</option>
                                                <option value="4">Afghanistan</option>
                                                <option value="5">Ghana</option>
                                                <option value="6">Albania</option>
                                                <option value="7">Bahrain</option>
                                                <option value="8">Colombia</option>
                                                <option value="9">Dominican Republic</option>
                                            </select>
                                        </div>
                                        <!-- End Country -->
                                        <!-- Strat Frist and Last name -->
                                        <div class="form-group row">
                                            <div class="col-md-6">
                                                <label for="c_fname" class="text-black">First Name <span class="text-danger">*</span></label>
                                                <input required type="text" class="form-control" id="c_fname" name="c_fname">
                                            </div>
                                            <div class="col-md-6">
                                                <label for="c_lname" class="text-black">Last Name <span class="text-danger">*</span></label>
                                                <input required type="text" class="form-control" id="c_lname" name="c_lname">
                                            </div>
                                        </div>
                                        <!-- End Frist and Last name -->
                                        <!-- Strat Cart Number -->
                                        <div class="form-group row">
                                            <div class="col-md-12">
                                                <label for="c_cart_number" class="text-black">Cart Number <span class="text-danger">*</span></label>
                                                <input required type="text" class="form-control" id="c_cart_number" name="c_cart_number" placeholder="4667 5746 3726 2727">
                                            </div>
                                        </div>
                                        <!-- End Cart Number -->
                                        <!-- Strat CVV and date expiry -->
                                        <div class="form-group row">
                                            <div class="col-md-6">
                                                <label for="c_cvv" class="text-black">CVV <span class="text-danger">*</span></label>
                                                <input required type="text" class="form-control" id="c_cvv" name="c_cvv">
                                            </div>
                                            <div class="col-md-6">
                                                <label for="c_date_expiry" class="text-black">Date of expiry <span class="text-danger">*</span></label>
                                                <input required type="text" class="form-control" id="c_date_expiry" name="c_date_expiry">
                                            </div>
                                        </div>
                                        <!-- End CVV and date expiry -->
                                        <!-- Strat Address and Shipping Shipping -->
                                        <div class="form-group row">
                                            <div class="col-md-12">
                                                <label for="c_address" class="text-black">Address <span class="text-danger">*</span></label>
                                                <input required type="text" class="form-control" id="c_address" name="c_address" placeholder="Street address">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <div class="col-md-12">
                                                <label for="c_s_address" class="text-black">Shipping Shipping <span class="text-danger">*</span></label>
                                                <input required type="text" class="form-control" id="c_s_address" name="c_s_address" placeholder="Street shipping address">
                                            </div>
                                        </div>
                                        <!-- End Address and Shipping Address -->
                                        <!-- Strat State and Posta -->
                                        <div class="form-group row">
                                            <div class="col-md-6">
                                                <label for="c_state_country" class="text-black">State / Country <span class="text-danger">*</span></label>
                                                <input required type="text" class="form-control" id="c_state_country" name="c_state_country">
                                            </div>
                                            <div class="col-md-6">
                                                <label for="c_postal_zip" class="text-black">Posta / Zip <span class="text-danger">*</span></label>
                                                <input required type="text" class="form-control" id="c_postal_zip" name="c_postal_zip">
                                            </div>
                                        </div>
                                        <!-- End State and Posta -->
                                        <!-- Start Email and Phone -->
                                        <div class="form-group row mb-5">
                                            <div class="col-md-6">
                                                <label for="c_email_address" class="text-black">Email Address <span class="text-danger">*</span></label>
                                                <input required type="email" class="form-control" id="c_email_address" name="c_email_address">
                                            </div>
                                            <div class="col-md-6">
                                                <label for="c_phone" class="text-black">Phone <span class="text-danger">*</span></label>
                                                <input required type="text" class="form-control" id="c_phone" name="c_phone" placeholder="Phone Number">
                                            </div>
                                        </div>

                                        <!-- End Email and Phone -->
                                        <!-- Start Order Notes -->
                                        <div class="form-group">
                                            <label for="c_order_notes" class="text-black">Order Notes</label>
                                            <textarea name="c_order_notes" id="c_order_notes" cols="30" rows="5" class="form-control" placeholder="Write your notes here..."></textarea>
                                        </div>
                                        <!-- End Order Notes -->
                                    </div>
                                </div>

                                <div class="col-md-6" style="float: left;">
                                    <div class="row mb-5">
                                        <div class="col-md-12">
                                            <h2 class="h3 mb-3 text-black">Your Order</h2>
                                            <div class="p-3 p-lg-5 border bg-white">
                                                <table class="table site-block-order-table mb-5">
                                                    <thead>
                                                    <th>Product</th>
                                                    <th>Total</th>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach var="product" items="${itemsCart.getMyCart().keySet()}">
                                                        <tr>
                                                            <td>${product.getName()} <strong class="mx-2">x</strong> ${itemsCart.getMyCart().get( product )}</td>
                                                            <fmt:setLocale value = "en_US"/>
                                                            <td><fmt:formatNumber value="${itemsCart.getMyCart().get( product ) * product.getPrice()}" type="currency"/></td>
                                                        </tr>
                                                    </c:forEach>
                                                    <tr>
                                                        <td class="text-black font-weight-bold"><strong>Cart Subtotal</strong></td>
                                                        <td class="text-black">$${totalPrice}</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="text-black font-weight-bold"><strong>Order Total</strong></td>
                                                        <td class="text-black font-weight-bold"><strong>$${totalPrice}</strong></td>
                                                    </tr>
                                                    </tbody>
                                                </table>

                                                <div class="form-group">
                                                    <input type="submit" value="Place Order" class="btn btn-black btn-lg py-3 btn-block">
                                                </div>

                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </form>
                    </c:when>
                    <c:when test="${bancomat == true}">
                        <form action="BancomatServlet" method="post">
                            <div class="row">
                                <div class="col-md-6 mb-5 mb-md-0" style="float: left;">
                                    <h2 class="h3 mb-3 text-black">Bancomat Billing Details</h2>
                                    <div class="p-3 p-lg-5 border bg-white">
                                        <!-- Strat Country -->
                                        <div class="form-group">
                                            <label for="b_country" class="text-black">Country <span class="text-danger">*</span></label>
                                            <select required name="b_country" id="b_country" class="form-control">
                                                <option value="1">Select a country</option>
                                                <option value="2">bangladesh</option>
                                                <option value="3">Algeria</option>
                                                <option value="4">Afghanistan</option>
                                                <option value="5">Ghana</option>
                                                <option value="6">Albania</option>
                                                <option value="7">Bahrain</option>
                                                <option value="8">Colombia</option>
                                                <option value="9">Dominican Republic</option>
                                            </select>
                                        </div>
                                        <!-- End Country -->
                                        <!-- Strat Frist and Last name -->
                                        <div class="form-group row">
                                            <div class="col-md-6">
                                                <label for="b_fname" class="text-black">First Name <span class="text-danger">*</span></label>
                                                <input required type="text" class="form-control" id="b_fname" name="b_fname">
                                            </div>
                                            <div class="col-md-6">
                                                <label for="b_lname" class="text-black">Last Name <span class="text-danger">*</span></label>
                                                <input required type="text" class="form-control" id="b_lname" name="b_lname">
                                            </div>
                                        </div>
                                        <!-- End Frist and Last name -->
                                        <!-- Strat Cart Number -->
                                        <div class="form-group row">
                                            <div class="col-md-12">
                                                <label for="b_cart_number" class="text-black">Cart Number <span class="text-danger">*</span></label>
                                                <input required type="text" class="form-control" id="b_cart_number" name="b_cart_number" placeholder="4667 5746 3726 2727">
                                            </div>
                                        </div>
                                        <!-- End Cart Number -->
                                        <!-- Strat CVV and date expiry -->
                                        <div class="form-group row">
                                            <div class="col-md-6">
                                                <label for="b_cvv" class="text-black">CVV <span class="text-danger">*</span></label>
                                                <input required type="text" class="form-control" id="b_cvv" name="b_cvv">
                                            </div>
                                            <div class="col-md-6">
                                                <label for="b_date_expiry" class="text-black">Date of expiry <span class="text-danger">*</span></label>
                                                <input required type="text" class="form-control" id="b_date_expiry" name="b_date_expiry">
                                            </div>
                                        </div>
                                        <!-- End CVV and date expiry -->
                                        <!-- Strat Address and Shipping Shipping -->
                                        <div class="form-group row">
                                            <div class="col-md-12">
                                                <label for="b_address" class="text-black">Address <span class="text-danger">*</span></label>
                                                <input required type="text" class="form-control" id="b_address" name="b_address" placeholder="Street address">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <div class="col-md-12">
                                                <label for="b_s_address" class="text-black">Shipping Shipping <span class="text-danger">*</span></label>
                                                <input required type="text" class="form-control" id="b_s_address" name="b_s_address" placeholder="Street shipping address">
                                            </div>
                                        </div>
                                        <!-- End Address and Shipping Address -->
                                        <!-- Strat State and Posta -->
                                        <div class="form-group row">
                                            <div class="col-md-6">
                                                <label for="b_state_country" class="text-black">State / Country <span class="text-danger">*</span></label>
                                                <input required type="text" class="form-control" id="b_state_country" name="c_state_country">
                                            </div>
                                            <div class="col-md-6">
                                                <label for="b_postal_zip" class="text-black">Posta / Zip <span class="text-danger">*</span></label>
                                                <input required type="text" class="form-control" id="b_postal_zip" name="b_postal_zip">
                                            </div>
                                        </div>
                                        <!-- End State and Posta -->
                                        <!-- Start Email and Phone -->
                                        <div class="form-group row mb-5">
                                            <div class="col-md-6">
                                                <label for="b_email_address" class="text-black">Email Address <span class="text-danger">*</span></label>
                                                <input required type="email" class="form-control" id="b_email_address" name="b_email_address">
                                            </div>
                                            <div class="col-md-6">
                                                <label for="b_phone" class="text-black">Phone <span class="text-danger">*</span></label>
                                                <input required type="text" class="form-control" id="b_phone" name="b_phone" placeholder="Phone Number">
                                            </div>
                                        </div>

                                        <!-- End Email and Phone -->
                                        <!-- Start Order Notes -->
                                        <div class="form-group">
                                            <label for="b_order_notes" class="text-black">Order Notes</label>
                                            <textarea name="b_order_notes" id="b_order_notes" cols="30" rows="5" class="form-control" placeholder="Write your notes here..."></textarea>
                                        </div>
                                        <!-- End Order Notes -->
                                    </div>
                                </div>

                                <div class="col-md-6" style="float: left;">
                                    <div class="row mb-5">
                                        <div class="col-md-12">
                                            <h2 class="h3 mb-3 text-black">Your Order</h2>
                                            <div class="p-3 p-lg-5 border bg-white">
                                                <table class="table site-block-order-table mb-5">
                                                    <thead>
                                                    <th>Product</th>
                                                    <th>Total</th>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach var="product" items="${itemsCart.getMyCart().keySet()}">
                                                        <tr>
                                                            <td>${product.getName()} <strong class="mx-2">x</strong> 1</td>
                                                            <td>$${product.getPrice()}</td>
                                                        </tr>
                                                    </c:forEach>
                                                    <tr>
                                                        <td class="text-black font-weight-bold"><strong>Cart Subtotal</strong></td>
                                                        <td class="text-black">$${totalPrice}</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="text-black font-weight-bold"><strong>Order Total</strong></td>
                                                        <td class="text-black font-weight-bold"><strong>$${totalPrice}</strong></td>
                                                    </tr>
                                                    </tbody>
                                                </table>

                                                <div class="form-group">
                                                    <input type="submit" value="Place Order" class="btn btn-black btn-lg py-3 btn-block">
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form action="CashServlet" method="post">
                            <div class="row">
                                <div class="col-md-6 mb-5 mb-md-0" style="float: left;">
                                    <h2 class="h3 mb-3 text-black">Cash Billing Details</h2>
                                    <div class="p-3 p-lg-5 border bg-white">
                                        <!-- Strat Country -->
                                        <div class="form-group">
                                            <label for="cc_country" class="text-black">Country <span class="text-danger">*</span></label>
                                            <select required name="cc_country" id="cc_country" class="form-control">
                                                <option value="1">Select a country</option>
                                                <option value="2">bangladesh</option>
                                                <option value="3">Algeria</option>
                                                <option value="4">Afghanistan</option>
                                                <option value="5">Ghana</option>
                                                <option value="6">Albania</option>
                                                <option value="7">Bahrain</option>
                                                <option value="8">Colombia</option>
                                                <option value="9">Dominican Republic</option>
                                            </select>
                                        </div>
                                        <!-- End Country -->
                                        <!-- Strat Frist and Last name -->
                                        <div class="form-group row">
                                            <div class="col-md-6">
                                                <label for="cc_fname" class="text-black">First Name <span class="text-danger">*</span></label>
                                                <input required type="text" class="form-control" id="cc_fname" name="cc_fname">
                                            </div>
                                            <div class="col-md-6">
                                                <label for="cc_lname" class="text-black">Last Name <span class="text-danger">*</span></label>
                                                <input required type="text" class="form-control" id="cc_lname" name="cc_lname">
                                            </div>
                                        </div>
                                        <!-- End Frist and Last name -->
                                        <!-- Strat Address and Shipping Shipping -->
                                        <div class="form-group row">
                                            <div class="col-md-12">
                                                <label for="cc_address" class="text-black">Address <span class="text-danger">*</span></label>
                                                <input required type="text" class="form-control" id="cc_address" name="cc_address" placeholder="Street address">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <div class="col-md-12">
                                                <label for="cc_s_address" class="text-black">Shipping Shipping <span class="text-danger">*</span></label>
                                                <input required type="text" class="form-control" id="cc_s_address" name="cc_s_address" placeholder="Street shipping address">
                                            </div>
                                        </div>
                                        <!-- End Address and Shipping Address -->
                                        <!-- Strat State and Posta -->
                                        <div class="form-group row">
                                            <div class="col-md-6">
                                                <label for="cc_state_country" class="text-black">State / Country <span class="text-danger">*</span></label>
                                                <input required type="text" class="form-control" id="cc_state_country" name="cc_state_country">
                                            </div>
                                            <div class="col-md-6">
                                                <label for="cc_postal_zip" class="text-black">Posta / Zip <span class="text-danger">*</span></label>
                                                <input required type="text" class="form-control" id="cc_postal_zip" name="cc_postal_zip">
                                            </div>
                                        </div>
                                        <!-- End State and Posta -->
                                        <!-- Start Email and Phone -->
                                        <div class="form-group row mb-5">
                                            <div class="col-md-6">
                                                <label for="cc_email_address" class="text-black">Email Address <span class="text-danger">*</span></label>
                                                <input required type="email" class="form-control" id="cc_email_address" name="cc_email_address">
                                            </div>
                                            <div class="col-md-6">
                                                <label for="cc_phone" class="text-black">Phone <span class="text-danger">*</span></label>
                                                <input required type="text" class="form-control" id="cc_phone" name="cc_phone" placeholder="Phone Number">
                                            </div>
                                        </div>

                                        <!-- End Email and Phone -->
                                        <!-- Start Order Notes -->
                                        <div class="form-group">
                                            <label for="cc_order_notes" class="text-black">Order Notes</label>
                                            <textarea name="cc_order_notes" id="cc_order_notes" cols="30" rows="5" class="form-control" placeholder="Write your notes here..."></textarea>
                                        </div>
                                        <!-- End Order Notes -->
                                    </div>
                                </div>

                                <div class="col-md-6" style="float: left;">
                                    <div class="row mb-5">
                                        <div class="col-md-12">
                                            <h2 class="h3 mb-3 text-black">Your Order</h2>
                                            <div class="p-3 p-lg-5 border bg-white">
                                                <table class="table site-block-order-table mb-5">
                                                    <thead>
                                                    <th>Product</th>
                                                    <th>Total</th>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach var="product" items="${itemsCart}">
                                                        <tr>
                                                            <td>${product.getName()} <strong class="mx-2">x</strong> 1</td>
                                                            <td>$${product.getPrice()}</td>
                                                        </tr>
                                                    </c:forEach>
                                                    <tr>
                                                        <td class="text-black font-weight-bold"><strong>Cart Subtotal</strong></td>
                                                        <td class="text-black">$${totalPrice}</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="text-black font-weight-bold"><strong>Order Total</strong></td>
                                                        <td class="text-black font-weight-bold"><strong>$${totalPrice}</strong></td>
                                                    </tr>
                                                    </tbody>
                                                </table>

                                                <div class="form-group">
                                                    <input type="submit" value="Place Order" class="btn btn-black btn-lg py-3 btn-block">
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </c:otherwise>
                </c:choose>
                <!-- </form> -->
            </div>
        </div>
        <jsp:include page="component/footer.jsp"></jsp:include>
        <jsp:include page="component/script.jsp"></jsp:include>
    </body>
</html>