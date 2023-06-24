<%--
  Created by IntelliJ IDEA.
  User: attilio
  Date: 27/03/23
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h4>protected_admin_area</h4>

<form action="RequestAddProductServlet" method="get">
  <button>Add Product</button>
</form>

<form action="RequestEditProductServlet" method="get">
  <button>Edit Product</button>
</form>

<form action="RequestSumProductSoldCategoryServlet" method="get">
  <button>Sales updates</button>
</form>

<form action="RequestOfferTFIDFProductServlet" method="get">
  <button>Send offers</button>
</form>

<c:choose>
  <c:when test="${addproduct == true}">
    <h4>Add product:</h4>
    <c:forEach items="${categoryList}" var="category">
      <h4>Add: ${category.toString()}</h4>
      <form action="AddProductServlet" method="post">
        <div>
          <label for="name">name</label>
          <input required maxlength="45" type="text" name="name" id="name" placeholder="Air Jordan" />
        </div>
        <div>
          <label for="description">Description</label>
          <input required maxlength="100" type="text" name="description" id="description" placeholder="This is a fantastic shoes" />
        </div>
        <div>
          <label for="stock">stock</label>
          <input required type="number" name="stock" id="stock" placeholder="100" />
        </div>
        <div>
          <label for="price">price</label>
          <input required type="number" step="0.01" name="price" id="price" placeholder="49.99" />
        </div>
        <div>
          <input name="category" value="${category}" type="hidden"/>
          <input type="submit" value="Add ${category.toString()}" />
        </div>
      </form>
    </c:forEach>
  </c:when>
</c:choose>

<c:choose>
  <c:when test="${editproduct == true}">
    <c:forEach var="product" items="${productBeanList}">
      <h4>Name: ${product.getName()} Description: ${product.getDescription()} Price: ${product.getPrice()}</h4>
      <form action="EditProductServlet" method="get">
        <button name="codeProduct" value=${product.getCode()}>Edit</button>
      </form>
    </c:forEach>
    <c:choose>
      <c:when test="${editsingleproduct == true}">
        <h4>Code: ${singleProduct.getCode()}</h4>
        <h4>Category: ${singleProduct.getCategory().toString()}</h4>


        <form action="EditNameProductServlet" method="post">
          <div>
            <label for="nameSingleProduct">current name: ${singleProduct.getName()} change to: </label>
            <input required maxlength="45" type="text" name="nameSingleProduct" id="nameSingleProduct" placeholder="Shoes nike" />
            <input type="hidden" name="codeSingleProduct" value="${singleProduct.getCode()}" />
            <input type="submit" value="change" />
          </div>
        </form>

        <form action="EditDescriptionProductServlet" method="post">
          <div>
            <label for="descriptionSingleProduct">current description: ${singleProduct.getDescription()} change to: </label>
            <input maxlength="100" type="text" name="descriptionSingleProduct" id="descriptionSingleProduct" placeholder="This is a fantastic shoes" />
            <input type="hidden" name="codeSingleProduct" value="${singleProduct.getCode()}" />
            <input type="submit" value="change" />
          </div>
        </form>

        <form action="EditStockProductServlet" method="post">
          <div>
            <label for="stockSingleProduct">current stock: ${singleProduct.getStock()} change to: </label>
            <input type="number" name="stockSingleProduct" id="stockSingleProduct" placeholder="10" />
            <input type="hidden" name="codeSingleProduct" value="${singleProduct.getCode()}" />
            <input type="submit" value="change" />
          </div>
        </form>

        <form action="EditPriceProductServlet" method="post">
          <div>
            <label for="priceSingleProduct">current price: ${singleProduct.getPrice()} change to: </label>
            <input type="number" step="4.99" name="priceSingleProduct" id="priceSingleProduct" placeholder="24,99" />
            <input type="hidden" name="codeSingleProduct" value="${singleProduct.getCode()}" />
            <input type="submit" value="change" />
          </div>
        </form>

      </c:when>
    </c:choose>
  </c:when>
</c:choose>

<c:choose>
  <c:when test="${salesupdates == true}">
    <h4>Choose category:</h4>
    <c:forEach items="${categoryList}" var="category">
      <form action="RequestSumProductSoldCategoryServlet" method="post">
        <div>
          <input name="categoryRequest" value="${category}" type="hidden"/>
          <input type="submit" value="${category.toString()}" />
        </div>
      </form>
    </c:forEach>
  </c:when>
</c:choose>

<c:choose>
  <c:when test="${queydone == true}">
    <h4>For category: ${categotySum} the total of products sold is: ${result}</h4>
  </c:when>
</c:choose>

<c:choose>
  <c:when test="${sendoffers == true}">
    <h4>Send offers: da implementare</h4>

  </c:when>
</c:choose>
</body>
</html>
