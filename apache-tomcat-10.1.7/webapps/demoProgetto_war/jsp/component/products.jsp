<%--
  Created by IntelliJ IDEA.
  User: attilio
  Date: 25/03/23
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
    <!-- List Product -->
    <div class="untree_co-section product-section before-footer-section">
        <div class="container">
            <div class="row">
                <!-- Start Column 1 -->
                <c:forEach var="product" items="${param.productBeanList}">
                    <div class="col-12 col-md-4 col-lg-3 mb-5">
                        <a class="product-item" href="#">
                            <img src="${pageContext.request.contextPath}/images/product-3.png" class="img-fluid product-thumbnail">
                            <h3 class="product-title">${product.getName()}</h3>
                            <strong class="product-price">$ ${product.getPrice()}</strong>

                            <span class="icon-cross">
                                    <form action="AddCartServlet" method="get">
                                        <button name="codeProduct" value=${product.getCode()}>Add Cart</button>
                                        <input type="hidden" name="landingPage" value="${param.provenance}">
                                    </form>
                                </span>
                        </a>
                    </div>
                </c:forEach>
                <!-- End Column 1 -->
            </div>
        </div>
    </div>
</body>
</html>
