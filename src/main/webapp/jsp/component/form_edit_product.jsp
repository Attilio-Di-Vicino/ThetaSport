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
        <div class="col-md-6 mb-5 mb-md-0" style="float: left;">
            <div class="p-3 p-lg-5 border bg-white">
                <h2 class="h3 mb-3 text-black">Code product ${param.singleProduct.getCode()}</h2>
                <h2 class="h3 mb-3 text-black">Category ${param.singleProduct.getCategory().toString()} ${param.singleProduct.getSubCategory().toString()}</h2>
                <!-- Start Name -->
                <form action="EditNameProductServlet" method="post">
                    <div class="form-group">
                        <label for="nameSingleProduct" class="text-black">Name </label>
                        <input required maxlength="45" value="${param.singleProduct.getName()}" type="text" class="form-control" id="nameSingleProduct" name="nameSingleProduct">
                        <input type="hidden" name="codeSingleProduct" value="${param.singleProduct.getCode()}" />
                        <input type="submit" value="change" class="btn btn-primary btn-icon-split" />
                    </div>
                </form>
                <!-- End Name -->
                <!-- Start description -->
                <form action="EditDescriptionProductServlet" method="post">
                    <div class="form-group row">
                        <div class="col-md-6">
                            <label for="descriptionSingleProduct" class="text-black">Description </label>
                            <input required maxlength="300" value="${param.singleProduct.getDescription()}" type="text" class="form-control" id="descriptionSingleProduct" name="descriptionSingleProduct">
                            <input type="hidden" name="codeSingleProduct" value="${param.singleProduct.getCode()}" />
                            <input type="submit" value="change" class="btn btn-primary btn-icon-split"/>
                        </div>
                    </div>
                </form>
                <!-- End description -->
                <!-- Start stock -->
                <form action="EditStockProductServlet" method="post">
                    <div class="form-group row">
                        <div class="col-md-6">
                            <label for="stockSingleProduct" class="text-black">Stock </label>
                            <input required step="10" type="number" value="${param.singleProduct.getStock()}" class="form-control" id="stockSingleProduct" name="stockSingleProduct">
                            <input type="hidden" name="codeSingleProduct" value="${param.singleProduct.getCode()}" />
                            <input type="submit" value="change" class="btn btn-primary btn-icon-split" />
                        </div>
                    </div>
                </form>
                <!-- End stock -->
                <!-- Start price -->
                <form action="EditPriceProductServlet" method="post">
                    <div class="form-group row">
                        <div class="col-md-12">
                            <label for="priceSingleProduct" class="text-black">Price </label>
                            <input required type="number" value="${param.singleProduct.getPrice()}" step="4.99" class="form-control" id="priceSingleProduct" name="priceSingleProduct">
                            <input type="hidden" name="codeSingleProduct" value="${param.singleProduct.getCode()}" />
                            <input type="submit" value="change" class="btn btn-primary btn-icon-split" />
                        </div>
                    </div>
                </form>
                <!-- End price -->
            </div>
        </div>
    </body>
</html>