<%--
  Created by IntelliJ IDEA.
  User: attilio
  Date: 28/03/23
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
              <c:forEach var="order" items="${param.orderList}">
                <tr>
                  <td>${order.getOrderId()}</td>
                  <td>${order.getName()}</td>
                  <td>${order.getEmail()}</td>
                  <td>${order.getDate()}</td>
                  <td>$${order.getTotal()}</td>
                  <td>${order.getQuantity()}</td>
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
</body>
</html>
