<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


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
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.28.0/feather.min.css">

        <title>NestMart - Work Schedule</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/admin/css/app.css" />
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
    </head>

    <body>
        <c:if test="${param.sessionExpired}">
            <div style="color: red;">
                Your session has expired. Please log in again.
            </div>
        </c:if>
        <div class="wrapper">
            <ul class="sidebar-nav">
                <a class="sidebar-brand" href="index.html">
                    <span class="align-middle">Nestmart</span>
                </a>
                <li class="sidebar-header">
                    Pages
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link" href="index.html">
                        <i class="align-middle" data-feather="sliders"></i> <span class="align-middle">Dashboard</span>
                    </a>
                </li>

                <li class="sidebar-item active">
                    <a class="sidebar-link" href="showaccount.htm" >
                        <i class="align-middle me-2" data-feather="users"></i> <span class="align-middle">Account</span>

                    </a>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link" href="pages-sign-in.html">
                        <i class="align-middle" data-feather="box"></i> <span class="align-middle">Product</span>
                    </a>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link" href="pages-sign-up.html">
                        <i class="align-middle" data-feather="bold"></i> <span class="align-middle">Brand</span>
                    </a>
                </li>

                <li class="sidebar-item  ">
                    <a class="sidebar-link" href="pages-blank.html">
                        <i class="align-middle" data-feather="list"></i> <span class="align-middle">Category</span>
                    </a>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link" href="ui-buttons.html">
                        <i class="align-middle" data-feather="clipboard"></i> <span class="align-middle">Category Detail</span>
                    </a>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link" href="ui-forms.html">
                        <i class="align-middle" data-feather="check-circle"></i> <span class="align-middle">Discount</span>
                    </a>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link" href="ui-cards.html">
                        <i class="align-middle" data-feather="percent"></i> <span class="align-middle">Offers</span>
                    </a>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link" href="${pageContext.request.contextPath}/admin/workshedule.htm">
                        <i class="align-middle" data-feather="calendar"></i> <span class="align-middle">Schedule</span>
                    </a>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link" href="icons-feather.html">
                        <i class="align-middle" data-feather="shopping-cart"></i> <span class="align-middle">Order</span>
                    </a>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link" href="icons-feather.html">
                        <i class="align-middle" data-feather="package"></i> <span class="align-middle">Inventory</span>
                    </a>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link" href="icons-feather.html">
                        <i class="align-middle" data-feather="feather"></i> <span class="align-middle">Feedback</span>
                    </a>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link" href="icons-feather.html">
                        <i class="align-middle" data-feather="dollar-sign"></i> <span class="align-middle">Financial Transactions</span>
                    </a>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link" href="icons-feather.html">
                        <i class="align-middle" data-feather="user-check"></i> <span class="align-middle">Salary</span>
                    </a>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link" href="icons-feather.html">
                        <i class="align-middle" data-feather="navigation"></i> <span class="align-middle">Notification</span>
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
                                <a class="nav-icon dropdown-toggle d-inline-block d-sm-none" href="#" data-bs-toggle="dropdown">
                                    <i class="align-middle" data-feather="settings"></i>
                                </a>

                                <a class="nav-link dropdown-toggle d-none d-sm-inline-block" href="#" data-bs-toggle="dropdown">
                                    <img src="img/avatars/avatar.jpg" class="avatar img-fluid rounded me-1" alt="User Avatar" />
                                    <span class="text-dark">${sessionScope.email}</span>

                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/logout.htm">Logout</a>

                                    <div class="dropdown-menu dropdown-menu-end">
                                        <a class="dropdown-item" href="accounts-management.html"><i class="align-middle me-1" data-feather="users"></i> Accounts Management</a>
                                        <a class="dropdown-item" href="#"><i class="align-middle me-1" data-feather="pie-chart"></i> Analytics</a>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="index.html"><i class="align-middle me-1" data-feather="settings"></i> Settings & Privacy</a>
                                        <a class="dropdown-item" href="#"><i class="align-middle me-1" data-feather="help-circle"></i> Help Center</a>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="/logout"><i class="align-middle me-1" data-feather="log-out"></i> Log out</a>
                                    </div>
                            </li>
                        </ul>
                    </div>
                </nav>
                <main class="content">
                    <div class="container mt-4">
                        <!-- Search and Buttons -->
                        <div class="search-container">
                            <input type="text" class="form-control search-input" placeholder="Search...">
                            <button class="btn search-button">
                                <i class="align-middle" data-feather="search"></i>
                            </button>
                            <!-- Icon buttons for Export and Add -->
                            <div class="icon-container">
                                <button class="btn btn-export">
                                    <i data-feather="file-text"></i>
                                </button>
                                <button class="btn btn-add" onclick="window.location.href = '<c:url value='/admin/scheduleCreate.htm'/>'">
                                    <i data-feather="plus"></i>
                                </button>
                            </div>
                        </div>

                        <!-- Alerts -->
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger">
                                <c:out value="${error}" />
                            </div>
                        </c:if>
                        <c:if test="${not empty success}">
                            <div class="alert alert-success">
                                <c:out value="${success}" />
                            </div>
                        </c:if>
                        <a href="${pageContext.request.contextPath}/admin/assignShift.htm" class="btn btn-primary mb-3">Assign Shift</a>

                        <table class="table table-hover my-0" id="scheduleTable">
                            <thead>
                                <tr>
                                    <th>Week Schedule ID</th>
                                    <th>Week Start Date</th>
                                    <th>Week End Date</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="weekSchedule" items="${weekSchedules}">
                                    <tr>
                                        <td><c:out value="${weekSchedule.weekScheduleID}" /></td>
                                        <td><fmt:formatDate value="${weekSchedule.weekStartDate}" pattern="yyyy/MM/dd" /></td>
                                        <td><fmt:formatDate value="${weekSchedule.weekEndDate}" pattern="yyyy/MM/dd" /></td>
                                        <td class="table-actions">
                                            <a href="${pageContext.request.contextPath}/admin/scheduleDetails.htm?weekScheduleID=${weekSchedule.weekScheduleID}" class="btn btn-view" title="View Details">
                                                <i data-feather="eye"></i>
                                            </a>
                                            <a href="${pageContext.request.contextPath}/admin/weekScheduleDelete.htm?weekScheduleID=${weekSchedule.weekScheduleID}" class="btn btn-delete" title="Delete" onclick="return confirm('Are you sure you want to delete this schedule?');">
                                                <i data-feather="trash-2"></i>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty weekSchedules}">
                                    <tr>
                                        <td colspan="4">No week schedule found.</td>
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
                                    <a class="text-muted" href="https://adminkit.io/" target="_blank"><strong>Nestmart</strong></a> &copy;
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
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.29.2/feather.min.js"></script>
        <script src="${pageContext.request.contextPath}/asset/admin/js/app.js"></script>
        <script>
                                                feather.replace();
        </script>
    </body>
</html>