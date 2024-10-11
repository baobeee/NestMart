<%-- 
    Document   : ChangePassword
    Created on : Oct 5, 2024, 12:25:53 AM
    Author     : Win10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Responsive Admin & Dashboard Template based on Bootstrap 5">
        <meta name="author" content="AdminKit">
        <meta name="keywords" content="adminkit, bootstrap, bootstrap 5, admin, dashboard, template, responsive, css, sass, html, theme, front-end, ui kit, web">

        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link rel="shortcut icon" href="../../admin/static/img/icons/icon-48x48.png" />

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap" rel="stylesheet">

        <!-- Bootstrap 5 CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Feather Icons -->
        <link href="https://unpkg.com/feather-icons@latest/dist/feather.css" rel="stylesheet">

        <!-- Custom Styles -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/admin/css/app.css" />

        <title>NestMart - Accounts</title>

        <style>
            .content {
                padding: 20px;
                background-color: #f8f9fa;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            .container {
                max-width: 500px;
                margin: 0 auto;
                background: white;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
            }

            h2 {
                font-size: 24px;
                margin-bottom: 20px;
                color: #333;
            }

            .form-control {
                border-radius: 5px;
                border: 1px solid #ced4da;
            }

            .form-control:focus {
                border-color: #007bff;
                box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
            }

            .btn-primary {
                background-color: #007bff;
                border-color: #007bff;
                padding: 10px 20px;
                border-radius: 5px;
                transition: background-color 0.3s, border-color 0.3s;
            }

            .btn-primary:hover {
                background-color: #0056b3;
                border-color: #0056b3;
            }

            .btn-secondary {
                background-color: #6c757d;
                border-color: #6c757d;
                padding: 10px 20px;
                border-radius: 5px;
                transition: background-color 0.3s, border-color 0.3s;
            }

            .btn-secondary:hover {
                background-color: #5a6268;
                border-color: #5a6268;
            }

            .invalid-feedback {
                font-size: 0.875em;
                color: #dc3545;
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
                    <a class="sidebar-link" href="${pageContext.request.contextPath}/admin/schedule.htm">
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
                    <a class="sidebar-link" href="${pageContext.request.contextPath}/admin/salary.htm">
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
                                <a class="nav-link dropdown-toggle d-none d-sm-inline-block" href="#" data-bs-toggle="dropdown">
                                    <span class="text-dark">${sessionScope.email}</span>
                                </a>

                                <div class="dropdown-menu dropdown-menu-end">
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/account/accountInformation.htm">
                                        <i class="fa fa-user"></i> Account Information
                                    </a>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/account/changePassword.htm">
                                        <i class="fa fa-user"></i> Change Password
                                    </a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/logout.htm">
                                        <i class="align-middle me-1" data-feather="log-out"></i> Log out
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </nav>
                <main class="content">
                    <div class="container mt-5">
                        <div class="card">
                            <div class="card-header">
                                <h2 class="text-center">Change Password</h2>
                            </div>
                            <div class="card-body">
                                <form action="${pageContext.request.contextPath}/account/changePassword.htm" method="post" class="needs-validation" novalidate>

                                    <!-- Display Error Message -->
                                    <c:if test="${not empty error}">
                                        <div class="alert alert-danger">${error}</div>
                                    </c:if>

                                    <div class="form-group mb-3">
                                        <label for="oldPassword"><strong>Old Password:</strong></label>
                                        <input type="password" class="form-control" id="oldPassword" name="oldPassword" required />
                                        <div class="invalid-feedback">Please enter your old password.</div>
                                    </div>

                                    <div class="form-group mb-3">
                                        <label for="newPassword"><strong>New Password:</strong></label>
                                        <input type="password" class="form-control" id="newPassword" name="newPassword" required />
                                        <div class="invalid-feedback">Please enter a valid new password.</div>
                                    </div>

                                    <div class="form-group mb-3">
                                        <label for="confirmNewPassword"><strong>Confirm New Password:</strong></label>
                                        <input type="password" class="form-control" id="confirmNewPassword" name="confirmNewPassword" required />
                                        <div class="invalid-feedback">Passwords do not match.</div>
                                    </div>

                                    <div class="text-center">
                                        <button type="submit" class="btn btn-primary">Change Password</button>
                                        <a href="${pageContext.request.contextPath}/shipper/account.htm" class="btn btn-secondary">Cancel</a>
                                    </div>
                                </form>
                            </div>
                        </div>
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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.29.2/feather.min.js"></script>
        <script src="${pageContext.request.contextPath}/asset/admin/js/app.js"></script>
        <script>
            feather.replace();
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
    </body>
</html>
