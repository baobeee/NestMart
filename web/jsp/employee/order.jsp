<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
        <link rel="shortcut icon" href="../assets/admin/static/img/icons/icon-48x48.png" />
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

            .search-form {
                display: flex;
                align-items: center;
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
            .btn-container {
                display: flex;
                gap: 10px; /* Khoảng cách giữa các nút */
            }


            .btn-update {
                color: #007bff;
            }

            .btn-delete {
                color: #dc3545;
            }


            .btn-update:hover {
                color: #0056b3;
            }

            .btn-delete:hover {
                color: #c82333;
            }

            tr {
                position: relative;
            }
            .table td {
                vertical-align: middle; /* Center-align text vertically in table cells */
            }
            .image-container {
                position: relative;
                display: inline-block;
            }

            .img-thumbnail {
                display: block;
            }

            .image-count {
                position: absolute;
                top: 5px;
                right: 5px;
                background-color: rgba(0, 0, 0, 0.6);
                color: white;
                padding: 2px 6px;
                border-radius: 50%;
                font-size: 14px;
                font-weight: bold;
            }

            /*chuyentrang*/
            .pagination {
                display: flex;
                justify-content: center;
                align-items: center;
                margin-top: 20px;
            }

            .pagination .page-item .page-link {
                color: #282c3c;
                background-color: #fff;
                border: 1px solid #282c3c;
                padding: 8px 16px;
                margin: 0 4px;
                border-radius: 20px;
                transition: all 0.3s ease;
            }

            .pagination .page-item.active .page-link {
                color: #fff;
                background-color: #282c3c;
                border-color: #282c3c;
            }

            .pagination .page-item .page-link:hover {
                color: #fff;
                background-color: #282c3c;
                border-color: #282c3c;
            }

            .pagination .page-item.disabled .page-link {
                color: #6c757d;
                background-color: #fff;
                border-color: #dee2e6;
                cursor: not-allowed;
            }



            .btn:hover::after, .cannot-delete-indicator:hover::after {
                opacity: 1;
                visibility: visible;
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

                         <li class="sidebar-item">
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
                        <!-- Search and Buttons -->
                        <div class="search-container">
                            <form action="../employee/order.htm" method="get" class="search-form">
                                <!-- Input for search criteria -->
                                <input type="text" name="keyword" class="form-control search-input" placeholder="Search by name, order ID, or phone..." value="${keyword}">
                                <button type="submit" class="btn search-button">
                                    <i class="align-middle" data-feather="search"></i>
                                </button>
                            </form>

                          
                        </div>

                        <c:set var="suggestion" value="${closestMatch != null && !closestMatch.equalsIgnoreCase(keyword) ? closestMatch : null}" />

                        <!-- Display suggestion if it exists -->
                        <c:if test="${not empty closestMatch && !closestMatch.equalsIgnoreCase(keyword)}">
                            <p>Do you mean that <a href="../employee/order.htm?keyword=${closestMatch}">${closestMatch}</a>?</p>
                        </c:if>

                       
                        <table class="table table-hover my-0" id="productTable">
                            <thead>
                                <tr>
                                    <th>Order ID</th>
                                    <th>Name</th>
                                    <th>Phone</th>
                                    <th>Notes</th>
                                    <th>Total Amount</th>
                                    <th>Shipping Address</th>
                                    <th>Payment Method</th>
                                    <th>Order Date</th>
                                    <th>Order Status</th>
                                    <th>Actions</th>                                
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="order" items="${listOrder}">
                                    <tr>
                                        <td><c:out value="${order.orderID}" /></td>
                                        <td><c:out value="${order.name}" /></td>
                                        <td><c:out value="${order.phone}" /></td>
                                        <td><c:out value="${order.notes}" /></td>
                                        <td><fmt:formatNumber value="${order.totalAmount}" type="number" minFractionDigits="2" maxFractionDigits="2" /></td>
                                        <td><c:out value="${order.shippingAddress}" /></td>
                                        <td><c:out value="${order.paymentMethod}" /></td>
                                        <td><fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>

                                        <td>
                                            <c:choose>
                                                <c:when test="${order.orderStatus == 'Pending'}">
                                                    <span class="btn btn-warning" style="cursor: default;">${order.orderStatus}</span>
                                                </c:when>
                                                <c:when test="${order.orderStatus == 'Confirmed'}">
                                                    <span class="btn btn-info" style="cursor: default;">${order.orderStatus}</span>
                                                </c:when>
                                                <c:when test="${order.orderStatus == 'On Delivering'}">
                                                    <span class="btn btn-primary" style="cursor: default;">${order.orderStatus}</span>
                                                </c:when>
                                                <c:when test="${order.orderStatus == 'Completed'}">
                                                    <span class="btn btn-success" style="cursor: default;">${order.orderStatus}</span>
                                                </c:when>
                                                <c:when test="${order.orderStatus == 'Canceled'}">
                                                    <span class="btn btn-danger" style="cursor: default;">${order.orderStatus}</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="btn btn-secondary" style="cursor: default;">${order.orderStatus}</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:if test="${order.shipperID == 0}">
                                                <a href="../employee/assignShipper.htm?orderID=${order.orderID}" 
                                                   class="btn btn-primary btn-sm">
                                                    Assign Shipper
                                                </a>
                                            </c:if>

                                            <c:if test="${order.shipperID != 0 && order.orderStatus == 'Confirmed'}">
                                                <a href="../employee/assignShipper.htm?orderID=${order.orderID}" class="btn btn-primary btn-sm">
                                                    Change Shipper
                                                </a>
                                            </c:if>

                                            <c:if test="${order.shipperID != 0 && order.orderStatus != 'Confirmed'}">
                                                <a href="../employee/viewOrder.htm?orderID=${order.orderID}"  class="btn btn-primary btn-sm">
                                                    View Details
                                                </a>
                                            </c:if>

                                        </td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                        <div class="pagination-container">

                            <nav aria-label="Page navigation">
                                <ul class="pagination justify-content-center">
                                    <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                        <a class="page-link" href="?page=${currentPage - 1}&pageSize=${pageSize}" tabindex="-1">Previous</a>
                                    </li>

                                    <c:forEach begin="1" end="${totalPages}" var="i">
                                        <li class="page-item ${currentPage == i ? 'active' : ''}">
                                            <a class="page-link" href="?page=${i}&pageSize=${pageSize}">${i}</a>
                                        </li>
                                    </c:forEach>

                                    <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                                        <a class="page-link" href="?page=${currentPage + 1}&pageSize=${pageSize}">Next</a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.29.2/feather.min.js"></script>
        <script>
                                    feather.replace();
        </script>
        <script>
            function formatNumber(value) {
                // Tách phần nguyên và phần thập phân
                let parts = value.split('.');
                let integerPart = parts[0].replace(/\D/g, '');
                let decimalPart = parts[1] || '';

                // Định dạng phần nguyên
                integerPart = integerPart.replace(/\B(?=(\d{3})+(?!\d))/g, ",");

                // Giới hạn phần thập phân tối đa 3 chữ số
                decimalPart = decimalPart.slice(0, 3);

                // Kết hợp phần nguyên và phần thập phân
                return decimalPart ? integerPart + '.' + decimalPart : integerPart;
            }

            function onInputChange(event) {
                let input = event.target;
                let cursorPosition = input.selectionStart;
                let oldValue = input.value;
                let newValue = formatNumber(input.value);

                input.value = newValue;

                // Điều chỉnh vị trí con trỏ
                let cursorOffset = newValue.length - oldValue.length;
                input.setSelectionRange(cursorPosition + cursorOffset, cursorPosition + cursorOffset);
            }

            document.addEventListener("DOMContentLoaded", function () {
                let unitPriceInput = document.getElementById("unitPrice");
                unitPriceInput.addEventListener("input", onInputChange);
            });
        </script>

        <script src="../assets/admin/js/app.js"></script>
    </body>
</html>
