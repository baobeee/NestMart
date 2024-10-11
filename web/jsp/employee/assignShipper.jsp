<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Responsive Admin &amp; Dashboard Template based on Bootstrap 5">
        <meta name="author" content="AdminKit">
        <meta name="keywords" content="adminkit, bootstrap, bootstrap 5, admin, dashboard, template, responsive, css, sass, html, theme, front-end, ui kit, web">

        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link rel="shortcut icon" href="../../admin/static/img/icons/icon-48x48.png" />
        <link href="https://unpkg.com/feather-icons@latest/dist/feather.css" rel="stylesheet">

        <link rel="canonical" href="https://demo-basic.adminkit.io/pages-blank.html" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.28.0/feather.min.css">

        <title>NestMart - Categories</title>

        <link rel="stylesheet" href="../assets/admin/css/app.css"/>
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap" rel="stylesheet">
        <style>
            .search-container {
                display: flex;
                align-items: center;
                margin-bottom: 20px;
                width: 100%;
            }
            .search-input {
                flex: 1;
                height: 45px;
                padding: 0 10px;
                border-radius: 20px 0 0 20px;
                border: 1px solid #ced4da;
                border-right: none;
                font-size: 16px;
            }
            .search-button {
                height: 45px;
                width: 65px;
                border-radius: 0 20px 20px 0;
                border: 1px solid #ced4da;
                border-left: none;
                background-color: #f1f1f1;
                color: #333;
                display: flex;
                align-items: center;
                justify-content: center;
                cursor: pointer;
                margin-right: 15px;
            }
            .search-button i {
                font-size: 20px;
            }
            .search-button:hover {
                background-color: #e0e0e0;
            }
            .icon-container {
                display: flex;
                align-items: center;
                gap: 10px;
                margin-left: auto; /* Đẩy các nút về bên phải */
            }
            .icon-container .btn {
                background-color: #f1f1f1;
                border: none;
                color: #333;
                width: 50px;
                height: 50px;
                border-radius: 50%;
                display: flex;
                align-items: center;
                justify-content: center;
                cursor: pointer;
                font-size: 24px;
                transition: background-color 0.3s, box-shadow 0.3s;
            }
            .icon-container .btn:hover {
                background-color: #e0e0e0;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            }
            .table-actions {
                position: relative;
                display: flex;
                justify-content: center;
                align-items: center;
                gap: 5px;
            }
            .table-actions {
                display: flex;
                gap: 5px;
                justify-content: center;
                align-items: center;
            }
            .table-actions .btn {
                background-color: transparent;
                border: none;
                cursor: pointer;
                font-size: 20.5px;
                padding: 5px;
                transition: background-color 0.3s, box-shadow 0.3s;
            }
            .table-actions .btn-update {
                color: #007bff;
            }
            .table-actions .btn-delete {
                color: #dc3545;
            }
            .table-actions .btn:hover {
                background-color: #f0f0f0;
                box-shadow: 0 0 4px rgba(0, 0, 0, 0.2);
            }
            .table-actions .btn-update:hover {
                color: #0056b3;
            }
            .table-actions .btn-delete:hover {
                color: #c82333;
            }
            tr {
                position: relative;
            }
            .table td {
                vertical-align: middle;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin: 20px 0;
            }

            th, td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: left;
            }

            th {
                background-color: #f4f4f4;
                color: #333;
                font-weight: bold;
            }

            tr:nth-child(even) {
                background-color: #f9f9f9;
            }

            tr:hover {
                background-color: #e2e2e2;
            }

            img {
                max-width: 100%;
                height: auto;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
    </head>

    <body>
        <div class="wrapper">
            <nav id="sidebar" class="sidebar js-sidebar">
                <div class="sidebar-content js-simplebar">
                    <a class="sidebar-brand" href="index.html">
                        <span class="align-middle">Nestmart</span>
                    </a>

                    <ul class="sidebar-nav">
                        <li class="sidebar-header">
                            Pages
                        </li>
                        <li class="sidebar-item ">
                            <a class="sidebar-link" href="index.htm">
                                <i class="align-middle me-2" data-feather="user"></i> <span class="align-middle">Account</span>
                            </a>
                        </li>
                        <li class="sidebar-item active">
                            <a class="sidebar-link" href="order.htm">
                                <i class="align-middle me-2" data-feather="shopping-cart"></i> <span class="align-middle">Orders</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a class="sidebar-link" href="returnRequests.htm">
                                <i class="align-middle me-2" data-feather="rotate-ccw"></i> <span class="align-middle">Return Request</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a class="sidebar-link" href="viewFeedbackEmp.htm">
                                <i class="align-middle me-2" data-feather="star"></i> <span class="align-middle">Feedback</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a class="sidebar-link" href="viewNotifications.htm">
                                <i class="align-middle me-2" data-feather="bell"></i> <span class="align-middle">Notification</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a class="sidebar-link" href="supportmessage.htm">
                                <i class="align-middle me-2" data-feather="message-square"></i> <span class="align-middle">Support Message</span>
                            </a>
                        </li>
                    </ul>   


                </div>
            </nav>

            <div class="main">
                <nav class="navbar navbar-expand navbar-light navbar-bg">
                    <a class="sidebar-toggle js-sidebar-toggle">
                        <i class="hamburger align-self-center"></i>
                    </a>

                     <div class="navbar-collapse collapse">
                        <ul class="navbar-nav navbar-align">
                            <!-- Các mục khác của navbar... -->

                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle d-none d-sm-inline-block" href="#" data-bs-toggle="dropdown">
                                    <span class="text-dark">${sessionScope.email}</span>
                                </a>

                                <div class="dropdown-menu dropdown-menu-end">
                                    <a class="dropdown-item" href="../account/accountInformation.htm">
                                        <i class="fa fa-user"></i> Account Information
                                    </a>
                                    <a class="dropdown-item" href="../account/changePassword.htm">
                                        <i class="fa fa-user"></i> Change Password
                                    </a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="../logout.htm">
                                        <i class="align-middle me-1" data-feather="log-out"></i> Log out
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </nav>

                <main class="content">
                    <div class="container mt-4">
                        <!-- Search and Buttons -->


                        <form action="${pageContext.request.contextPath}/employee/order.htm" method="post">
                            <input type="hidden" name="orderID" value="${order.orderID}" />

                            <!-- Các trường thông tin đơn hàng -->
                            <div class="form-group">
                                <label for="name">Name:</label>
                                <input type="text" class="form-control" id="name" name="name" value="${order.name}" readonly>
                            </div>

                            <div class="form-group">
                                <label for="phone">Phone:</label>
                                <input type="text" class="form-control" id="phone" name="phone" value="${order.phone}" readonly>
                            </div>

                            <div class="form-group">
                                <label for="shippingAddress">Shipping Address:</label>
                                <textarea class="form-control" id="shippingAddress" name="shippingAddress" rows="4" readonly>${order.shippingAddress}</textarea>
                            </div>

                            <div class="form-group">
                                <label for="paymentMethod">Payment Method:</label>
                                <input type="text" class="form-control" id="paymentMethod" name="paymentMethod" value="${order.paymentMethod}" readonly>
                            </div>

                            <div class="form-group">
                                <label for="notes">Notes:</label>
                                <textarea class="form-control" id="notes" name="notes" rows="4" readonly>${order.notes}</textarea>
                            </div>


                            <div class="form-group">
                                <label for="orderDate">Order Date:</label>
                                <input type="text" class="form-control" id="orderDate" name="orderDate"
                                       value="<fmt:formatDate value='${order.orderDate}' pattern='yyyy-MM-dd HH:mm:ss' />" readonly>
                            </div>


                            <div class="form-group">
                                <label for="orderStatus">Order Status:</label>
                                <input type="text" class="form-control" id="orderStatus" name="orderStatus" value="${order.orderStatus}" readonly>
                            </div>

                            <div class="form-group">
                                <label for="shipperID">Shipper:</label>
                                <select class="form-control" id="shipperID" name="shipperID">
                                    <c:forEach var="shipper" items="${shippers}">
                                        <option value="${shipper.accountID}"
                                                <c:if test="${order.shipperID == shipper.accountID}">selected</c:if>>
                                            ${shipper.fullName}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>


                            <!-- Bảng chi tiết đơn hàng -->
                            <h2>Order Details</h2>
                            <table border="1" class="table table-hover my-3">
                                <thead>
                                    <tr>
                                        <th>Product ID</th>
                                        <th>Quantity</th>
                                        <th>Unit Price</th>
                                        <th>Total Price</th>
                                        <th>Product Name</th>
                                        <th>Image</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="detail" items="${orderDetails}"> 
                                        <tr>
                                            <td><c:out value="${detail.productID}" /></td>
                                            <td><c:out value="${detail.quantity}" /></td>
                                            <td><c:out value="${detail.unitPrice}" /></td>
                                            <td><c:out value="${detail.totalPrice}" /></td>
                                            <td><c:out value="${detail.productName}" /></td>
                                            <td><img src="../assets/admin/images/uploads/products/${detail.image}" alt="Product Image" width="100" /></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <div class="form-group d-flex justify-content-end align-items-center">
                                <label for="totalAmount" class="mr-2">Total Amount:</label>
                                <span id="totalAmount" class="font-weight-bold" style="margin-top: -10px">${order.totalAmount}</span>
                            </div>
                            <div class="form-group mt-3">
                                <button type="submit" id="updateOrderButton" class="btn btn-primary">Update Order</button>
                                <a href="${pageContext.request.contextPath}/employee/order.htm" class="btn btn-secondary">Cancel</a>
                            </div>

                        </form>
                    </div>
                </main>
                <footer class="footer">
                    <div class="container-fluid">
                        <div class="row text-muted">
                            <div class="col-6 text-start">
                                <p class="mb-0">
                                    <a class="text-muted" href="https://adminkit.io/" target="_blank"><strong>AdminKit</strong></a> &copy;
                                </p>
                            </div>
                            <div class="col-6 text-end">
                                <ul class="list-inline">
                                    <li class="list-inline-item">
                                        <a class="text-muted" href="https://adminkit.io/" target="_blank">Support</a>
                                    </li>
                                    <li class="list-inline-item">
                                        <a class="text-muted" href="https://adminkit.io/" target="_blank">Help Center</a>
                                    </li>
                                    <li class="list-inline-item">
                                        <a class="text-muted" href="https://adminkit.io/" target="_blank">Privacy</a>
                                    </li>
                                    <li class="list-inline-item">
                                        <a class="text-muted" href="https://adminkit.io/" target="_blank">Terms</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.28.0/feather.min.js"></script> <!-- Feather Icons JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

        <script src="admin/static/js/app.js"></script>
    </body>
</html>



