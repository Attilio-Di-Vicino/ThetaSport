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
                <h2 class="h3 mb-3 text-black">Category ${param.category} ${param.subCategory}</h2>
                <!-- Start sub-category -->
                <form action="RequestSumProductSoldCategoryServlet" method="post">
                    <div class="form-group">
                        <select required name="subCategoryRequest" id="subCategoryRequest" class="form-control">
                            <option value="1">Select a sub-category</option>
                            <option value="shoes">Shoes</option>
                            <option value="tshirt">T-Shirt</option>
                        </select>
                    </div>
                    <input name="categoryRequest" value="${param.category}" type="hidden"/>
                    <input type="submit" value="submit">
                </form>
                <!-- End sub-category -->
                <h2 class="h3 mb-3 text-black">Result ${param.result}</h2>
            </div>
        </div>
    </body>
</html>