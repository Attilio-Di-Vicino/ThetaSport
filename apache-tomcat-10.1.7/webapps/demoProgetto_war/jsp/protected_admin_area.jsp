<%--
  Created by IntelliJ IDEA.
  User: attilio
  Date: 17/03/23
  Time: 18:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

                        <jsp:include page="component/charts.jsp"></jsp:include>

                        <jsp:include page="component/tables.jsp">
                            <jsp:param name="orderList" value="${orderList}"/>
                        </jsp:include>

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

    <jsp:include page="component/logout_modal.jsp"></jsp:include>
    <jsp:include page="component/script_admin.jsp"></jsp:include>
  </body>
</html>
