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
        <form action="AddProductServlet" method="post" enctype="multipart/form-data">
            <div class="col-md-6 mb-5 mb-md-0" style="float: left;">
                <div class="p-3 p-lg-5 border bg-white">
                    <h2 class="h3 mb-3 text-black">Add ${param.category.toLowerCase()} product</h2>
                    <!-- Start sub-category -->
                    <div class="form-group">
                        <label for="subcategory" class="text-black">Country <span class="text-danger">*</span></label>
                        <select required name="subcategory" id="subcategory" class="form-control">
                            <option value="1">Select a sub-category</option>
                            <option value="shoes">Shoes</option>
                            <option value="tshirt">T-Shirt</option>
                        </select>
                    </div>
                    <!-- End sub-category -->
                    <!-- Start name -->
                    <div class="form-group row">
                        <div class="col-md-6">
                            <label for="name" class="text-black">Name <span class="text-danger">*</span></label>
                            <input required maxlength="45" type="text" class="form-control" id="name" name="name" placeholder="Air Jordan">
                        </div>
                    </div>
                    <!-- End name -->
                    <!-- Start Description -->
                    <div class="form-group row">
                        <div class="col-md-12">
                            <label for="description" class="text-black">Description <span class="text-danger">*</span></label>
                            <input required maxlength="300" type="text" class="form-control" id="description" name="description" placeholder="This is a fantastic shoes">
                        </div>
                    </div>
                    <!-- End Description -->
                    <!-- Start stock -->
                    <div class="form-group row">
                        <div class="col-md-6">
                            <label for="stock" class="text-black">Stock <span class="text-danger">*</span></label>
                            <input required min="1" step="10" type="number" placeholder="100" class="form-control" id="stock" name="stock">
                        </div>
                    </div>
                    <!-- End stock -->
                    <!-- Start price -->
                    <div class="form-group row">
                        <div class="col-md-12">
                            <label for="price" class="text-black">Price <span class="text-danger">*</span></label>
                            <input required min="1" type="number" step="4.99" class="form-control" id="price" name="price" placeholder="49.99">
                        </div>
                    </div>
                    <!-- End price -->
                    <!-- Start image -->
                    <div class="form-group row">
                        <div class="col-md-6">
                            <label for="image" class="text-black">Image <span class="text-danger">*</span></label>
                            <input required type="file" class="form-control" id="image" name="image" accept="image/*">
                        </div>
                    </div>
                    <!-- End image -->
                    <div class="form-group">
                        <input name="category" value="${param.category}" type="hidden"/>
                        <input class="add-product" type="submit" value="Add product" class="btn btn-black btn-lg py-3 btn-block">
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>