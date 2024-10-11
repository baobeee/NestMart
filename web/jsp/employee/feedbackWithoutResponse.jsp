<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
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

        <title>NestMart - Feedbacks</title>

        <link href="../assets/admin/css/app.css" rel="stylesheet">
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
                margin-left: auto;
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
                gap: 10px;
            }

            .btn {
                background-color: transparent;
                border: none;
                cursor: pointer;
                font-size: 20.5px;
                padding: 5px;
                transition: background-color 0.3s, box-shadow 0.3s;
            }

            .btn-update {
                color: #007bff;
            }

            .btn-delete {
                color: #dc3545;
            }

            .btn:hover {
                background-color: #f0f0f0;
                box-shadow: 0 0 4px rgba(0, 0, 0, 0.2);
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
                vertical-align: middle;
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

            .cannot-delete-indicator {
                background-color: #ffcccb;
                color: #d32f2f;
                padding: 8px 2px;
                border-radius: 4px;
                font-size: 10px;
                cursor: not-allowed;
                display: inline-flex;
                align-items: center;
                border: 1px solid #d32f2f;
            }

            .btn i, .cannot-delete-indicator i {
                margin-right: 5px;
            }

            .btn, .cannot-delete-indicator {
                position: relative;
            }

            .btn::after, .cannot-delete-indicator::after {
                content: attr(title);
                position: absolute;
                bottom: 100%;
                left: 50%;
                transform: translateX(-50%);
                background-color: #333;
                color: white;
                padding: 5px 100px;
                border-radius: 4px;
                font-size: 12px;
                white-space: nowrap;
                opacity: 0;
                visibility: hidden;
                transition: opacity 0.3s, visibility 0.3s;
            }

            .btn:hover::after, .cannot-delete-indicator:hover::after {
                opacity: 1;
                visibility: visible;
            }

            .link-spacing {
                margin-right: 15px;
            }
        </style>
    </head>

    <body>
        <div class="wrapper">
            <ul class="sidebar-nav">
                <a class="sidebar-brand" href="index.html">
                    <span class="align-middle">NestMart</span>
                </a>
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
                <li class="sidebar-item">
                    <a class="sidebar-link" href="returnRequests.htm">
                        <i class="align-middle me-2" data-feather="rotate-ccw"></i> <span class="align-middle">Return Request</span>
                    </a>
                </li>
                <li class="sidebar-item active">
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
                        <div class="search-container">
                            <form action="${pageContext.request.contextPath}/admin/findFeedback.htm" method="get" class="search-form">

                                <input type="text" name="keyword" class="form-control search-input" placeholder="Search by name, ID, or category..." value="${keyword}">
                                <button type="submit" class="btn search-button">
                                    <i class="align-middle" data-feather="search"></i>
                                </button>
                            </form>
                            <!--<input name="keyword" type="text" class="form-control search-input" placeholder="Enter product name...">
                            <button class="btn search-button" type="submit">
                                <i class="align-middle" data-feather="search"></i>
                            </button>
                         Icon buttons for Export and Add -->
                            <div class="icon-container">
                                <button class="btn btn-export">
                                    <i data-feather="file-text"></i>
                                </button>
                                <!-- Update the Add button -->
                                <button class="btn btn-add" onclick="window.location.href = '${pageContext.request.contextPath}/addInventoryForm.htm'">
                                    <i data-feather="plus"></i>
                                </button>

                            </div>
                        </div>
                        <a href="viewFeedbackEmp.htm" class="link-spacing">All Feedback</a>
                        <a href="feedbackWithResponse.htm">Feedback With Response</a>
                        <h3>List of Unresponsive Feedback</h3>
                        <table class="table table-hover my-0" id="accountTable">
                            <thead>
                                <tr>
                                    <th>FeedbackID</th>
                                    <th>ProductID</th>
                                    <th>Product Name</th>
                                    <th>Customer Name</th>
                                    <th>Rating</th>
                                    <th>Feedback Content</th>
                                    <th>Feedback Image</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="feedback" items="${feedbacks}">
                                    <tr>
                                        <td>${feedback.feedbackID}</td>
                                        <td>${feedback.productID}</td>
                                        <td>${feedback.product.productName}</td>
                                        <td>${feedback.customerName}</td>
                                        <td width="8%">
                                            <div style="color: #f5c518; font-size: 13px;">
                                                <c:forEach var="i" begin="1" end="${feedback.rating}">
                                                    &#9733; 
                                                </c:forEach>
                                                <c:forEach var="i" begin="${feedback.rating + 1}" end="5">
                                                    &#9734; 
                                                </c:forEach>
                                            </div>
                                        </td>
                                        <td>${feedback.feedbackContent} <br/> <small><c:out value="${feedback.formattedFeedbackDate}" /></small></td>
                                        <td>
                                            <img src="${pageContext.request.contextPath}/assets/admin/images/uploads/products/${feedback.feedbackImage}" alt="Feedback Image" alt="${feedback.product.productName}" width="100" />
                                        </td>
                                        <td class="table-actions">
                                            <a href="${pageContext.request.contextPath}/employee/responseFeedback.htm?feedbackID=${feedback.feedbackID}" class="btn btn-update" title="Reponse">
                                                <i data-feather="message-circle"></i>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>

                                <c:if test="${empty feedbacks}">
                                    <tr>
                                        <td colspan="3">No feedback found.</td>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table>
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
        <script src="../assets/admin/js/app.js"></script>
    </body>
</html>