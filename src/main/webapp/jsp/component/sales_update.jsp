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
                <!-- Start sub-category -->
                <form action="RequestSumProductSoldCategoryServlet" method="post">
                    <div class="form-group">
                        <select required name="subCategoryRequest" id="subCategoryRequest" class="form-control">
                            <option value="shoes">Select a sub-category</option>
                            <option value="shoes">Shoes</option>
                            <option value="tshirt">T-Shirt</option>
                        </select>
                    </div>
                    <a class="btn btn-success btn-icon-split float-left">
                        <input name="categoryRequest" value="${param.category}" type="hidden"/>
                        <span class="icon text-white-50">
                                            <i class="fas fa-check"></i>
                                        </span>
                        <span class="text"><input style="background-color: transparent !important;border: 0 !important; color: white;" type="submit" value="choose" /></span>
                    </a>
                </form>
                <br><br>
                <!-- End sub-category -->
                <h2 class="h3 mb-3 text-black">Category ${param.category} ${param.subCategory}</h2>
                <h2 class="h3 mb-3 text-black">Result ${param.result}</h2>
            </div>
        </div>
    </body>
</html>