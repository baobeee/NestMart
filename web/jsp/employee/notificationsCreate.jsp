<%@page import="java.time.LocalDateTime, java.time.format.DateTimeFormatter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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

        <title>NestMart - Notifications</title>

        <link href="../assets/admin/css/app.css" rel="stylesheet">
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
    </head>

    <body>
        <%
            LocalDateTime now = LocalDateTime.now();
            String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        %>
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
                <li class="sidebar-item">
                    <a class="sidebar-link" href="viewFeedbackEmp.htm">
                        <i class="align-middle me-2" data-feather="star"></i> <span class="align-middle">Feedback</span>
                    </a>
                </li>
                <li class="sidebar-item active">
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
                        
                        <table class="table table-hover my-0" id="accountTable">
                            <div class="container">
                                <h2>Send Notification</h2>



                                <form:form action="addNotification.htm" modelAttribute="addNotificationForm" method="POST">
                                    <div class="form-group">
                                        <label for="customerID">CustomerID:</label>
                                        <form:select class="form-control" path="customerID" id="customer" required="true" >
                                            <form:options items="${accountsList}" itemValue="accountID" itemLabel="fullName" />
                                        </form:select>
                                    </div>
                                    <div class="form-group">
                                        <label for="title">Title</label>
                                        <form:input class="form-control" path="title" type="text" required="true" />
                                    </div>
                                    <div class="form-group">
                                        <label for="message">Message</label>
                                        <form:input class="form-control" path="message" type="text" required="true" />
                                    </div>
                                    <div class="form-group">
                                        <form:input class="form-control" path="status" type="text" required="true" value="Unread" hidden="true" />
                                    </div>
                                    <div class="form-group">
                                        <label for="notificationType">Notification Type</label>
                                        <form:input class="form-control" path="notificationType" type="text" required="true" />
                                    </div>
                                    <form:button class="btn btn-primary" type="submit">Submit</form:button>
                                    <form:button class="btn btn-secondary" type="reset">Reset</form:button>
                                </form:form>
                            </div>
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
