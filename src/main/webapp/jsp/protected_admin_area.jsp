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
                                <h4>Add product:</h4>
                                <c:forEach items="${categoryList}" var="category">
                                    <h4>Add: ${category.toString()}</h4>
                                    <form action="AddProductServlet" method="post" enctype="multipart/form-data">
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
                                            <label for="image">image</label>
                                            <input required type="file" name="image" id="image" accept="image/*"/>
                                        </div>
                                        <div>
                                            <input name="category" value="${category}" type="hidden"/>
                                            <input type="submit" value="Add ${category.toString()}" />
                                        </div>
                                    </form>
                                </c:forEach>
                            </c:when>
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
                                                <input required maxlength="45" type="text" name="nameSingleProduct" id="nameSingleProduct" placeholder="Shoes nike" value="${singleProduct.getName()}" />
                                                <input type="hidden" name="codeSingleProduct" value="${singleProduct.getCode()}" />
                                                <input type="submit" value="change" />
                                            </div>
                                        </form>

                                        <form action="EditDescriptionProductServlet" method="post">
                                            <div>
                                                <label for="descriptionSingleProduct">current description: ${singleProduct.getDescription()} change to: </label>
                                                <input maxlength="100" type="text" name="descriptionSingleProduct" id="descriptionSingleProduct" placeholder="This is a fantastic shoes" value="${singleProduct.getDescription()}" />
                                                <input type="hidden" name="codeSingleProduct" value="${singleProduct.getCode()}" />
                                                <input type="submit" value="change" />
                                            </div>
                                        </form>

                                        <form action="EditStockProductServlet" method="post">
                                            <div>
                                                <label for="stockSingleProduct">current stock: ${singleProduct.getStock()} change to: </label>
                                                <input type="number" name="stockSingleProduct" id="stockSingleProduct" placeholder="10" value="${singleProduct.getStock()}"/>
                                                <input type="hidden" name="codeSingleProduct" value="${singleProduct.getCode()}" />
                                                <input type="submit" value="change" />
                                            </div>
                                        </form>

                                        <form action="EditPriceProductServlet" method="post">
                                            <div>
                                                <label for="priceSingleProduct">current price: ${singleProduct.getPrice()} change to: </label>
                                                <input type="number" step="4.99" name="priceSingleProduct" id="priceSingleProduct" placeholder="24,99" value="${singleProduct.getPrice()}" />
                                                <input type="hidden" name="codeSingleProduct" value="${singleProduct.getCode()}" />
                                                <input type="submit" value="change" />
                                            </div>
                                        </form>

                                    </c:when>
                                </c:choose>
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

                        <!-- Page Heading -->
                        <h1 class="h3 mb-2 text-gray-800">Orders</h1>

                        <!-- DataTales Example -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">DataTables Orders</h6>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                        <tr>
                                            <th>Order ID</th>
                                            <th>Name</th>
                                            <th>Email</th>
                                            <th>Total</th>
                                            <th>Quantity of product</th>
                                            <th>Date</th>
                                        </tr>
                                        </thead>
                                        <tfoot>
                                        <tr>
                                            <th>Order ID</th>
                                            <th>Name</th>
                                            <th>Email</th>
                                            <th>Total</th>
                                            <th>Quantity of product</th>
                                            <th>Date</th>
                                        </tr>
                                        </tfoot>
                                        <tbody>
                                        <c:forEach var="order" items="${orderList}">
                                            <tr>
                                                <td>${order.getOrderId()}</td>
                                                <td>${order.getName()}</td>
                                                <td>${order.getEmail()}</td>
                                                <td>$${order.getTotal()}</td>
                                                <td>${order.getQuantity()}</td>
                                                <td>${order.getDate()}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

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