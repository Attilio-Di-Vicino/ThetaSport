<%--
  Created by IntelliJ IDEA.
  User: attilio
  Date: 25/03/23
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <body>
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
                                    <c:forEach var="product" items="${param.itemsCart}">
                                        <tr>
                                            <td>${product.getName()} <strong class="mx-2">x</strong> 1</td>
                                            <td>$${product.getPrice()}</td>
                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <td class="text-black font-weight-bold"><strong>Cart Subtotal</strong></td>
                                        <td class="text-black">$${param.totalPrice}</td>
                                    </tr>
                                    <tr>
                                        <td class="text-black font-weight-bold"><strong>Order Total</strong></td>
                                        <td class="text-black font-weight-bold"><strong>$${param.totalPrice}</strong></td>
                                    </tr>
                                    </tbody>
                                </table>

                                <div class="form-group">
                                    <button class="btn btn-black btn-lg py-3 btn-block" onclick="window.location='thankyou.html'">Place Order</button>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>
