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
        <link href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.19/tailwind.min.css" rel="stylesheet">

        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link rel="shortcut icon" href="../../admin/static/img/icons/icon-48x48.png" />
        <link href="https://unpkg.com/feather-icons@latest/dist/feather.css" rel="stylesheet">

        <link rel="canonical" href="https://demo-basic.adminkit.io/pages-blank.html" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.28.0/feather.min.css">

        <title>NestMart - Categories</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="../assets/admin/css/app.css"/>
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap" rel="stylesheet">
        <style>
            /* Colorful Dashboard Styles */

            body {
                background-color: #f0f2f5;
                font-family: 'Arial', sans-serif;
                color: #333; /* Default text color */
                ;

            }

            .content {
                padding: 2rem;
            }

            h1 {
                color: #34495e;
                font-size: 2.5rem;
                margin-bottom: 1.5rem;
                text-align: center;
                text-shadow: 1px 1px 2px rgba(0,0,0,0.1);
            }

            /* Main table styles */
            table {
                width: 100%;
                border-collapse: separate;
                border-spacing: 0 10px;
                margin-top: 20px;
            }

            th {
                background-color: #3498db;
                color: white;
                padding: 15px;
                text-align: left;
                font-weight: bold;
                text-transform: uppercase;
                letter-spacing: 1px;
            }

            td {
                background-color: white;
                padding: 15px;
                box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            }

            tr:hover td {
                background-color: #f5f7fa;
                transition: background-color 0.3s ease;
            }

            /* View Details button */
            .btn-view-details {
                background-color: #2ecc71;
                color: white;
                border: none;
                padding: 10px 15px;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            .btn-view-details:hover {
                background-color: #27ae60;
            }



            /* Form elements */
            textarea, select {
                width: 100%;
                padding: 10px;
                margin-bottom: 15px;
                border: 1px solid #bdc3c7;
                border-radius: 5px;
            }

            select {
                appearance: none;
                background-image: url('data:image/svg+xml;utf8,<svg fill="black" height="24" viewBox="0 0 24 24" width="24" xmlns="http://www.w3.org/2000/svg"><path d="M7 10l5 5 5-5z"/><path d="M0 0h24v24H0z" fill="none"/></svg>');
                background-repeat: no-repeat;
                background-position-x: 98%;
                background-position-y: 50%;
            }

            button[type="submit"] {
                background-color: #3498db;
                color: white;
                border: none;
                padding: 10px 20px;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            button[type="submit"]:hover {
                background-color: #2980b9;
            }


            .order-details {
                display: none;
                z-index: 1;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgba(0,0,0,0.4);
            }
            .order-details-content {
                background-color: #fefefe;
                margin: 15% auto;
                padding: 20px;
                border: 1px solid #888;
                width: 80%;
            }
            .blue-header {
                background-color: #3498db;
                color: white;
                font-size: 0.75rem;
                font-weight: bold;
                padding: 0.5rem;
                text-transform: uppercase;
            }
            .close {
                color: #aaa;
                float: right;
                font-size: 28px;
                font-weight: bold;
            }
            .close:hover,
            .close:focus {
                color: black;
                text-decoration: none;
                cursor: pointer;
            }
            .table-header {
                font-size: 0.875rem;
                font-weight: 600;
                text-transform: uppercase;
                color: #4a5568;
                background-color: #edf2f7;
                padding: 0.75rem 1rem;
            }
            .table-data {
                font-size: 0.875rem;
                color: #2d3748;
                padding: 0.75rem 1rem;
            }
            .blue-header {
                background-color: #3498db;
                color: white;
                font-size: 0.75rem;
                font-weight: bold;
                padding: 0.5rem;
                text-transform: uppercase;
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
                <li class="sidebar-item">
                    <a class="sidebar-link" href="order.htm">
                        <i class="align-middle me-2" data-feather="shopping-cart"></i> <span class="align-middle">Orders</span>
                    </a>
                </li>
                <li class="sidebar-item active">
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
                        <h1>Return Requests</h1>
                        <c:choose>
                            <c:when test="${empty returnRequests and empty completedReturns}">
                                <div class="alert alert-info" role="alert">
                                    There are currently no return requests to process.
                                </div>
                            </c:when>
                            <c:otherwise>
                                <table>
                                    <tr>
                                        <th class="blue-header">Order ID</th>
                                        <th class="blue-header">Customer Name</th>
                                        <th class="blue-header">Return Request Date</th>
                                        <th class="blue-header">Action</th>
                                    </tr>
                                    <c:forEach var="order" items="${returnRequests}">
                                        <tr>
                                            <td>${order.orderID}</td>
                                            <td>${order.customerName}</td>
                                            <td id="returnRequestDate-${order.orderID}">${order.formattedReturnRequestDate}</td>

                                            <td><button onclick="showDetails(${order.orderID})">View Details</button></td>
                                        </tr>
                                    </c:forEach>
                                </table>
                                 <h2>Completed Returns</h2>
                    <table>
                        <tr>
                            <th class="blue-header">Order ID</th>
                            <th class="blue-header">Customer Name</th>
                            <th class="blue-header">Return Completed Date</th>
                            <th class="blue-header">Status</th>
                            <th class="blue-header">Action</th>
                        </tr>
                        <c:forEach var="order" items="${completedReturns}">
                            <tr>
                                <td>${order.orderID}</td>
                                <td>${order.customerName}</td>
                                <td id="returnCompletedDate-${order.orderID}">${order.formattedReturnRequestDate}</td>
                                <td>
                                    <span class="status-badge ${order.orderStatus == 'Approved' ? 'status-approved' : 'status-denied'}">
                                        ${order.orderStatus}
                                    </span>
                                </td>
                                <td>
                                    <form action="../employee/deleteCompletedReturn.htm" method="POST" onsubmit="return confirm('Are you sure you want to delete this completed return?');">
                                        <input type="hidden" name="orderID" value="${order.orderID}">
                                        <button type="submit" class="btn-delete">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                                <c:forEach var="order" items="${returnRequests}">
                                    <div id="orderDetails-${order.orderID}" class="order-details">
                                        <div class="order-details-content">
                                            <span class="close" onclick="closeDetails(${order.orderID})">&times;</span>
                                            <h2>Order #${order.orderID}</h2>
                                            <table>
                                                <tr>
                                                    <th>Customer Name</th>
                                                    <td>${order.customerName}</td>
                                                    <th>Customer Email</th>
                                                    <td>${order.customerEmail}</td>
                                                </tr>
                                                <tr>
                                                    <th>Order Date</th>
                                                    <td id="orderDate-${order.orderID}">${order.formattedOrderDate}</td>
                                                    <th>Return Request Date</th>
                                                    <td id="returnRequestDate-detail-${order.orderID}">${order.formattedReturnRequestDate}</td>
                                                </tr>
                                                <tr>
                                                    <th>Total Amount</th>
                                                    <td><fmt:formatNumber value="${order.totalAmount}" type="currency"/></td>
                                                    <th>Payment Method</th>
                                                    <td>${order.paymentMethod}</td>
                                                </tr>
                                                <tr>
                                                    <th>Shipping Address</th>
                                                    <td colspan="3">${order.shippingAddress}</td>
                                                </tr>
                                                <tr>
                                                    <th>Return Request Reason</th>
                                                    <td colspan="3">${order.returnRequestReason}</td>
                                                </tr>
                                            </table>

                                            <div class="order-products">
                                                <h3>Order Details</h3>
                                                <table>
                                                    <tr>
                                                        <th>Product ID</th>
                                                        <th>Product Name</th>
                                                        <th>Quantity</th>
                                                        <th>Unit Price</th>
                                                        <th>Total Price</th>
                                                    </tr>
                                                    <c:forEach var="detail" items="${order.orderDetails}">
                                                        <tr>
                                                            <td>${detail.product.productID}</td>
                                                            <td>${detail.product.productName}</td>
                                                            <td>${detail.quantity}</td>
                                                            <td><fmt:formatNumber value="${detail.unitPrice}" type="currency"/></td>
                                                            <td><fmt:formatNumber value="${detail.totalPrice}" type="currency"/></td>
                                                        </tr>
                                                    </c:forEach>
                                                </table>
                                            </div>
                                            <form action="../employee/process.htm" method="POST">
                                                <input type="hidden" name="orderID" value="${order.orderID}">

                                                <textarea name="message" placeholder="Enter message..."></textarea>

                                                <select name="newOrderStatus" id="newOrderStatus">
                                                    <option value="Approved">Approve</option>
                                                    <option value="Denied">Deny</option>
                                                </select>

                                                <input type="hidden" name="newReturnRequestStatus" id="newReturnRequestStatus">

                                                <button type="submit">Process Request</button>
                                            </form>

                                        </div>
                                    </div>
                                </c:forEach>    
                            </c:otherwise>
                        </c:choose>
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
        <script>
                                        feather.replace();
        </script>
        <script src="admin/static/js/app.js"></script>
        <script>
                                        document.getElementById('newOrderStatus').addEventListener('change', function () {
                                            var returnRequestStatus = this.value === 'Approved' ? 'Approved' : 'Denied';
                                            document.getElementById('newReturnRequestStatus').value = returnRequestStatus;
                                            console.log('New Return Request Status:', returnRequestStatus); // For debugging
                                        });

                                        document.getElementById('returnRequestForm').addEventListener('submit', function (e) {
                                            var returnRequestStatus = document.getElementById('newReturnRequestStatus').value;
                                            if (!returnRequestStatus) {
                                                e.preventDefault();
                                                alert('Please select an order status');
                                            }
                                            console.log('Submitting form with Return Request Status:', returnRequestStatus); // For debugging
                                        });
                                        function formatDate(dateString) {
                                            const date = new Date(dateString);
                                            return date.getFullYear() + '-' +
                                                    String(date.getMonth() + 1).padStart(2, '0') + '-' +
                                                    String(date.getDate()).padStart(2, '0') + ' ' +
                                                    String(date.getHours()).padStart(2, '0') + ':' +
                                                    String(date.getMinutes()).padStart(2, '0');
                                        }

                                        function showDetails(orderId) {
                                            document.getElementById('orderDetails-' + orderId).style.display = 'block';
                                        }

                                        function closeDetails(orderId) {
                                            document.getElementById('orderDetails-' + orderId).style.display = 'none';
                                        }

                                        document.addEventListener('DOMContentLoaded', function () {
                                            document.querySelectorAll('td[id^="orderDate-"]').forEach(td => {
                                                td.textContent = formatDate(td.textContent);
                                            });

                                            document.querySelectorAll('td[id^="returnRequestDate-"]').forEach(td => {
                                                td.textContent = formatDate(td.textContent);
                                            });
                                        });
        </script>
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
