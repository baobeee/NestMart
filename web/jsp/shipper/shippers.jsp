<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
        <script src="https://cdn.tailwindcss.com"></script>
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap" rel="stylesheet">
        <link rel="preconnect" href="https://fonts.gstatic.com">
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
                vertical-align: middle; /* Center-align text vertically in table cells */
            }
        </style>
        <style>


            /* Base styles */
            body {
                font-family: 'Inter', sans-serif;
                line-height: 1.5;
                color: #333;
            }

            .container {
                width: 100%;
                padding-right: 15px;
                padding-left: 15px;
                margin-right: auto;
                margin-left: auto;
            }

            /* Navigation styles */
            .navbar {
                padding: 0.5rem 1rem;
            }

            .navbar-brand {
                font-size: 1.25rem;
                padding-top: 0.3125rem;
                padding-bottom: 0.3125rem;
                margin-right: 1rem;
            }

            /* Form styles */
            .form-control {
                display: block;
                width: 100%;
                padding: 0.375rem 0.75rem;
                font-size: 1rem;
                line-height: 1.5;
                color: #495057;
                background-color: #fff;
                background-clip: padding-box;
                border: 1px solid #ced4da;
                border-radius: 0.25rem;
                transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
            }

            .btn {
                display: inline-block;
                font-weight: 400;
                text-align: center;
                vertical-align: middle;
                user-select: none;
                border: 1px solid transparent;
                padding: 0.375rem 0.75rem;
                font-size: 1rem;
                line-height: 1.5;
                border-radius: 0.25rem;
                transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
            }

            .btn-primary {
                color: #fff;
                background-color: #007bff;
                border-color: #007bff;
            }

            /* Order card styles */
            .order-card {
                background-color: #fff;
                border: 1px solid #dee2e6;
                border-radius: 0.25rem;
                margin-bottom: 1rem;
                padding: 1rem;
            }

            .order-card h3 {
                margin-top: 0;
                margin-bottom: 0.5rem;
            }

            .order-card p {
                margin-bottom: 0.5rem;
            }

            .badge {
                display: inline-block;
                padding: 0.25em 0.4em;
                font-size: 75%;
                font-weight: 700;
                line-height: 1;
                text-align: center;
                white-space: nowrap;
                vertical-align: baseline;
                border-radius: 0.25rem;
            }

            .badge-primary {
                color: #fff;
                background-color: #007bff;
            }

            .badge-info {
                color: #fff;
                background-color: #17a2b8;
            }

            /* Responsive styles */
            @media (max-width: 768px) {
                .container {
                    padding-right: 10px;
                    padding-left: 10px;
                }

                .navbar {
                    padding: 0.5rem;
                }

                .form-inline {
                    flex-direction: column;
                }

                .form-inline .form-control,
                .form-inline .btn {
                    width: 100%;
                    margin-bottom: 0.5rem;
                }

                .order-card {
                    padding: 0.75rem;
                }
            }

            /* Additional mobile-friendly styles */
            @media (max-width: 576px) {
                body {
                    font-size: 14px;
                }

                h1 {
                    font-size: 1.5rem;
                }

                .order-card h3 {
                    font-size: 1.1rem;
                }

                .btn {
                    padding: 0.5rem 1rem;
                }
            }
            .timer {
                font-weight: bold;
                color: green;
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
                            <a class="sidebar-link" href="shippers.htm">
                                <i class="align-middle" data-feather="sliders"></i> <span class="align-middle">Dashboard</span>
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
                    <ul class="navbar-nav navbar-align">
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
                </nav>
                <main class="content">
                    <div class="container mt-4">

                        <h1 class="mb-4">Manage Order</h1>

                        <div class="row mb-4">
                            <div class="col-12">
                                <form method="get" action="${pageContext.request.contextPath}/shipper/shippers.htm" class="form-inline">
                                    <input type="hidden" name="shipperId" value="${shipperId}" />
                                    <input type="text" name="orderID" class="form-control mr-2" placeholder="Search by Order ID..." value="${param.orderID}" />
                                    <input type="text" name="search" class="form-control mr-2" placeholder="Search by Customer Name " value="${param.customerName}" />            <select name="status" class="form-control mr-2" id="statusFilter">
                                        <option value="">Choose Status</option>
                                        <option value="Confirmed" ${param.status == 'Confirmed' ? 'selected' : ''}>Confirm</option>
                                        <option value="On Delivering" ${param.status == 'On Delivering' ? 'selected' : ''}>Delivering</option>
                                        <option value="Completed" ${param.status == 'Completed' ? 'selected' : ''}>Completed</option>
                                    </select>
                                    <button type="submit" class="btn btn-primary">Filter</button>
                                </form>
                            </div>
                        </div>

                        <div class="row">
                            <c:forEach var="order" items="${orders}">
                                <div class="col-md-6 col-lg-4">
                                    <div class="order-card" id="order-${order.orderID}">
                                        <h3>Order #${order.orderID}</h3>
                                        <p><strong>Customer:</strong> ${order.customerName}</p>
                                        <p><strong>Phone Number:</strong> ${order.customerPhone}</p>
                                        <p><strong>Shipping Address: </strong>${order.shippingAddress}</p>
                                        <p><strong>Order Date:</strong> ${order.formattedOrderDate.split(' ')[0]}</p>
                                        <p><strong>Order Time:</strong> ${order.formattedOrderDate.split(' ')[1]}</p>                                    <p><strong>Total:</strong> ${order.totalAmount} $</p>
                                        <p><strong>Order Status:</strong> <span class="order-status badge badge-primary">${order.orderStatus}</span></p>
                                        <p><strong>Payment Method:</strong> ${order.paymentMethod}</p>
                                        <p><strong>Transaction Status:</strong> <span class="transaction-status badge badge-info">${order.transactionStatus}</span></p>

                                        <c:choose>
                                            <c:when test="${order.orderStatus == 'Pending Confirmation'}">
                                                <button onclick="updateOrderStatus(${order.orderID}, 'Confirmed', '${order.paymentMethod}')" class="btn btn-primary btn-update">Order Confirmed</button>
                                            </c:when>
                                            <c:when test="${order.orderStatus == 'Confirmed'}">
                                                <button onclick="updateOrderStatus(${order.orderID}, 'On Delivering', '${order.paymentMethod}')" class="btn btn-info btn-update">Start delivery</button>
                                            </c:when>
                                            <c:when test="${order.orderStatus == 'On Delivering'}">
                                                <button onclick="updateOrderStatus(${order.orderID}, 'Completed', '${order.paymentMethod}')" class="btn btn-success btn-update">Completed Order</button>
                                            </c:when>
                                            <c:when test="${order.orderStatus == 'Approved'}">
                                                <button onclick="confirmReturnPickup(${order.orderID})" class="btn btn-warning btn-update">Confirm Return Pickup</button>
                                            </c:when>
                                            <c:when test="${order.orderStatus == 'Completed' || order.orderStatus == 'Return Completed'}">
                                                <p class="text-success">Order ${order.orderStatus}</p>
                                            </c:when>
                                            <c:otherwise>
                                                <p>No action available</p>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </c:forEach>
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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.28.0/feather.min.js"></script> <!-- Feather Icons JS -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <script>
        </script>
       <script>document.getElementById('accountInfoModal').addEventListener('show.bs.modal', function (event) {
                // Gửi request để lấy thông tin tài khoản từ server
                fetch('/admin/information')
                        .then(response => response.json())
                        .then(data => {
                            // Gán dữ liệu tài khoản vào các field trong modal
                            document.getElementById('email').value = data.email;
                            document.getElementById('fullName').value = data.fullName;
                            document.getElementById('phoneNumber').value = data.phoneNumber;
                            document.getElementById('address').value = data.address;
                        });
            });
        </script>
        <script>
            
            function approveReturn(orderID) {
                $.ajax({
                    url: '../shipper/approveReturn.htm',
                    type: 'POST',
                    data: {
                        orderID: orderID
                    },
                    success: function (response) {
                        if (response === 'success') {
                            alert('Return request approved successfully.');
                            location.reload();
                        } else {
                            alert('Error approving return request.');
                        }
                    },
                    error: function (xhr, status, error) {
                        console.error('Error approving return request:', error);
                        alert('Error approving return request: ' + error);
                    }
                });
            }

            function confirmReturnPickup(orderID) {
                $.ajax({
                    url: '../shipper/confirmReturnPickup.htm',
                    type: 'POST',
                    data: {
                        orderID: orderID
                    },
                    success: function (response) {
                        if (response === 'success') {
                            alert('Return pickup confirmed successfully.');
                            location.reload();
                        } else {
                            alert('Error confirming return pickup.');
                        }
                    },
                    error: function (xhr, status, error) {
                        console.error('Error confirming return pickup:', error);
                        alert('Error confirming return pickup: ' + error);
                    }
                });
            }

            $(document).ready(function () {
                function filterOrders(status) {
                    if (status === "") {
                        $(".order-item").show();
                    } else {
                        $(".order-item").hide();
                        $(".order-item[data-status='" + status + "']").show();
                    }
                }

                filterOrders($("#statusFilter").val());

                $("#statusFilter").change(function () {
                    filterOrders($(this).val());
                });

                function updateOrderStatus(orderID, status, paymentMethod) {
                    $.ajax({
                        url: '${pageContext.request.contextPath}/shipper/updateOrderStatus.htm',
                        type: 'POST',
                        data: {
                            orderID: orderID,
                            status: status,
                            paymentMethod: paymentMethod
                        },
                        success: function (response) {
                            console.log('Server response:', response);
                            if (response === 'success') {
                                var $orderCard = $('#order-' + orderID);
                                $orderCard.find('.order-status').text(status);
                                $orderCard.closest('.order-item').attr('data-status', status);
                                updateButtonStatus(orderID, status, paymentMethod);
                                updateTransactionStatus(orderID, status, paymentMethod);

                                if (status === 'On Delivering') {
                                    var startTime = new Date().getTime();
                                    localStorage.setItem('startTime-' + orderID, startTime);
                                    startTimer(orderID);
                                } else if (status === 'Completed') {
                                    finalizeTimer(orderID);
                                    $orderCard.find('button').remove();
                                    $orderCard.append('<p>Order Completed</p>');
                                }

                                // Update the status filter dropdown and trigger filtering
                                $("#statusFilter").val(status).trigger('change');
                            } else {
                                console.error('Server returned non-success response:', response);
                                alert('Có lỗi xảy ra khi cập nhật trạng thái đơn hàng.');
                            }
                        },
                        error: function (xhr, status, error) {
                            console.error('Error updating order status:', xhr.responseText);
                            alert('Có lỗi xảy ra khi gửi yêu cầu: ' + error);
                        }
                    });
                }

                function updateButtonStatus(orderID, status, paymentMethod) {
                    var orderCard = $('#order-' + orderID);
                    var button = orderCard.find('button');

                    switch (status) {
                        case 'Confirmed':
                            button.text('Start delivery');
                            button.off('click').on('click', function () {
                                updateOrderStatus(orderID, 'On Delivering', paymentMethod);
                            });
                            break;
                        case 'On Delivering':
                            button.text('Confirm delivery');
                            button.off('click').on('click', function () {
                                updateOrderStatus(orderID, 'Completed', paymentMethod);
                            });
                            break;
                        case 'Completed':
                            button.remove();
                            orderCard.append('<p>Order Completed</p>');
                            break;
                        default:
                            console.warn('Unexpected order status:', status);
                    }
                }

                function updateTransactionStatus(orderID, status, paymentMethod) {
                    var transactionStatusElement = $('#order-' + orderID + ' .transaction-status');

                    if (paymentMethod === 'Bank Transfer') {
                        if (['Confirmed', 'On Delivering', 'Completed'].includes(status)) {
                            transactionStatusElement.text('Completed');
                        }
                    } else if (paymentMethod === 'Cash') {
                        if (status === 'Completed') {
                            transactionStatusElement.text('Completed');
                        } else if (['Confirmed', 'On Delivering'].includes(status)) {
                            transactionStatusElement.text('Pending');
                        }
                    } else {
                        console.warn('Unexpected payment method or status:', paymentMethod, status);
                    }
                }

                function startTimer(orderID) {
                    var startTime = parseInt(localStorage.getItem('startTime-' + orderID));
                    var timerElement = $('#timer-' + orderID);

                    function updateTimer() {
                        var currentTime = new Date().getTime();
                        var secondsElapsed = Math.floor((currentTime - startTime) / 1000);
                        timerElement.text(secondsElapsed);

                        if (localStorage.getItem('completedTime-' + orderID)) {
                            clearTimeout(timeoutId);
                            displayCompletedTime(orderID);
                        } else {
                            timeoutId = setTimeout(updateTimer, 1000);
                        }
                    }

                    var timeoutId = setTimeout(updateTimer, 0);
                    localStorage.setItem('timeoutId-' + orderID, timeoutId);
                }

                function finalizeTimer(orderID) {
                    var startTime = localStorage.getItem('startTime-' + orderID);
                    if (startTime) {
                        var endTime = new Date().getTime();
                        var totalSeconds = Math.floor((endTime - parseInt(startTime)) / 1000);
                        localStorage.setItem('completedTime-' + orderID, totalSeconds);
                        localStorage.removeItem('startTime-' + orderID);
                        localStorage.removeItem('timeoutId-' + orderID);
                        displayCompletedTime(orderID);
                    }
                }

                function displayCompletedTime(orderID) {
                    var completedTime = localStorage.getItem('completedTime-' + orderID);
                    if (completedTime) {
                        $('#timer-' + orderID).text(completedTime + ' (Hoàn thành)');
                    }
                }

                // Initialize timers for existing orders
                $('.order-card').each(function () {
                    var orderID = $(this).attr('id').split('-')[1];
                    var startTime = localStorage.getItem('startTime-' + orderID);
                    var status = $(this).find('.order-status').text();
                    var completedTime = localStorage.getItem('completedTime-' + orderID);

                    if (completedTime) {
                        displayCompletedTime(orderID);
                    } else if (startTime && status === 'On Delivering') {
                        startTimer(orderID);
                    }
                });

                // Make updateOrderStatus globally accessible
                window.updateOrderStatus = updateOrderStatus;
            });
        </script>
                        
        <script>
            feather.replace();
        </script>
    </body>
</html>
