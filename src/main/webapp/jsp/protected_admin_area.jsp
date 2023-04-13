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
    <head>
        <jsp:include page="component/header.jsp" >
            <jsp:param name="title" value="Dashboard - Admin" />
        </jsp:include>

        <!-- Custom fonts for this template-->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

        <!-- Custom styles for this template-->
        <!-- Custom styles for this page -->
        <style>
            <%@ include file="../css/sb-admin-2.min.css" %>
            <%@ include file="../vendor/datatables/dataTables.bootstrap4.min.css" %>
        </style>
    </head>
    <body id="page-top">
    <!-- chiamata per controllare i privilegi di accesso dell'admin -->
    <%--<jsp:include page="AdminProtectedServlet"></jsp:include>--%>

    <!-- Page Wrapper -->
    <div id="wrapper">

        <jsp:include page="component/sidebar.jsp"></jsp:include>

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <jsp:include page="component/topbar_admin.jsp"></jsp:include>

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
                        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                    </div>

                    <div>
                        <jsp:include page="component/first_content_row.jsp">
                            <jsp:param name="earningMonthly" value="${earningMonthly}"/>
                            <jsp:param name="earningYears" value="${earningYears}"/>
                            <jsp:param name="earningTotal" value="${earningTotal}"/>
                        </jsp:include>

                        <c:choose>
                            <c:when test="${addproduct == true}">
                                <c:forEach items="${categoryList}" var="category">
                                    <jsp:include page="component/form_add_product.jsp">
                                        <jsp:param name="category" value="${category}"/>
                                    </jsp:include>
                                </c:forEach>
                            </c:when>
                            <c:when test="${productCreated == true}">
                                <h4 class="h3 mb-2 text-gray-800">Product created success: ${product.getName()}</h4>
                                <a class="btn btn-success btn-icon-split">
                                        <span class="icon text-white-50">
                                            <i class="fas fa-check"></i>
                                        </span>
                                    <span class="text">Success</span>
                                </a>
                            </c:when>
                            <c:when test="${editproduct == true}">
                                <!-- Page Heading -->
                                <h1 class="h3 mb-2 text-gray-800">Product</h1>
                                <!-- DataTales -->
                                <div class="card shadow mb-4">
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Product </h6>
                                    </div>
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                                <thead>
                                                <tr>
                                                    <th>Category</th>
                                                    <th>Sub-category</th>
                                                    <th>Name</th>
                                                    <th>Stock</th>
                                                    <th>Price</th>
                                                    <th></th>
                                                </tr>
                                                </thead>
                                                <tfoot>
                                                <tr>
                                                    <th>Category</th>
                                                    <th>Sub-category</th>
                                                    <th>Name</th>
                                                    <th>Stock</th>
                                                    <th>Price</th>
                                                    <th></th>
                                                </tr>
                                                </tfoot>
                                                <tbody>
                                                <c:forEach var="product" items="${productBeanList}">
                                                    <tr>
                                                        <td>${product.getCategory().toString()}</td>
                                                        <td>${product.getSubCategory().toString()}</td>
                                                        <td>${product.getName()}</td>
                                                        <td>${product.getStock()}</td>
                                                        <td>$${product.getPrice()}</td>
                                                        <form action="EditProductServlet" method="get">
                                                            <td>
                                                                <button class="btn btn-primary btn-icon-split" name="codeProduct" value=${product.getCode()}>
                                                                    <span class="text">Edit</span>
                                                                </button>
                                                            </td>
                                                        </form>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </c:when>
                            <c:when test="${editproduct == true}">
                                <c:forEach var="product" items="${productBeanList}">
                                    <h4>Name: ${product.getName()} Description: ${product.getDescription()} Price: ${product.getPrice()}</h4>
                                    <form action="EditProductServlet" method="get">
                                        <button name="codeProduct" value=${product.getCode()}>Edit</button>
                                    </form>
                                </c:forEach>
                            </c:when>
                            <c:when test="${editsingleproduct == true}">
                                <div class="col-md-10 mb-5 mb-md-0">
                                    <div class="p-3 p-lg-5 border bg-white">
                                        <h2 class="h3 mb-3 text-black">Code product ${singleProduct.getCode()}</h2>
                                        <h2 class="h3 mb-3 text-black">Category ${singleProduct.getCategory().toString()} ${singleProduct.getSubCategory().toString()}</h2>
                                        <!-- Start Name -->
                                        <form action="EditNameProductServlet" method="post">
                                            <div class="form-group">
                                                <label for="nameSingleProduct" class="text-black">Name </label>
                                                <input required maxlength="45" value="${singleProduct.getName()}" type="text" class="form-control" id="nameSingleProduct" name="nameSingleProduct">
                                                <input type="hidden" name="codeSingleProduct" value="${singleProduct.getCode()}" />
                                                <input type="submit" value="change" class="btn btn-primary btn-icon-split" />
                                            </div>
                                        </form>
                                        <!-- End Name -->
                                        <!-- Start description -->
                                        <form action="EditDescriptionProductServlet" method="post">
                                            <div class="form-group row">
                                                <div class="col-md-6">
                                                    <label for="descriptionSingleProduct" class="text-black">Description </label>
                                                    <input required maxlength="300" value="${singleProduct.getDescription()}" type="text" class="form-control" id="descriptionSingleProduct" name="descriptionSingleProduct">
                                                    <input type="hidden" name="codeSingleProduct" value="${singleProduct.getCode()}" />
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
                                                    <input required step="10" type="number" value="${singleProduct.getStock()}" class="form-control" id="stockSingleProduct" name="stockSingleProduct">
                                                    <input type="hidden" name="codeSingleProduct" value="${singleProduct.getCode()}" />
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
                                                    <input required type="number" value="${singleProduct.getPrice()}" step="4.99" class="form-control" id="priceSingleProduct" name="priceSingleProduct">
                                                    <input type="hidden" name="codeSingleProduct" value="${singleProduct.getCode()}" />
                                                    <input type="submit" value="change" class="btn btn-primary btn-icon-split" />
                                                </div>
                                            </div>
                                        </form>
                                        <!-- End price -->
                                    </div>
                                </div>
                            </c:when>
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

                            <c:when test="${sendoffers == true}">
                                <h4>Send offers: da implementare</h4>
                            </c:when>

                            <c:otherwise>
                                <jsp:include page="component/charts.jsp"></jsp:include>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${queydone == true}">
                                <h4>For category: ${categotySum} the total of products sold is: ${result}</h4>
                            </c:when>
                        </c:choose>

                    </div>
                    <!-- /.container-fluid -->

                </div>
                <!-- End of Main Content -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Your Website 2021</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Bootstrap core JavaScript-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"></script>
    <jsp:include page="component/script_admin.jsp"></jsp:include>
    </body>
</html>