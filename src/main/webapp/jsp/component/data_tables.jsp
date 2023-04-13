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
    </body>
</html>