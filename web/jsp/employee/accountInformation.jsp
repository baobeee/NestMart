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

        <title>NestMart - Accounts Information</title>

        <link rel="stylesheet" href="../assets/admin/css/app.css" />
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

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

                 <li class="sidebar-item active">
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
                    <div class="container mt-5">
                        <h2 class="text-center mb-4">Update Account Information</h2>

                        <!-- Hiển thị thông báo lỗi -->
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger">${error}</div>
                        </c:if>

                        <!-- Hiển thị thông báo thành công -->
                        <c:if test="${not empty message}">
                            <div class="alert alert-success">${message}</div>
                        </c:if>

                        <form action="../account/updateInformation.htm" method="post" class="needs-validation" novalidate>
                            <input type="hidden" name="accountID" value="${account.accountID}" />

                            <div class="form-group mb-3">
                                <label for="email"><strong>Email:</strong></label>
                                <input type="text" class="form-control" id="email" name="email" value="${account.email}" readonly />
                            </div>

                            <div class="form-group mb-3">
                                <label for="fullName"><strong>Full Name:</strong></label>
                                <input type="text" class="form-control" id="fullName" name="fullName" value="${account.fullName}" required />
                            </div>

                            <div class="form-group mb-3">
                                <label for="phoneNumber"><strong>Phone Number:</strong></label>
                                <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" value="${account.phoneNumber}" required />
                            </div>

                            <div class="form-group mb-3">
                                <label for="address"><strong>Address:</strong></label>
                                <input type="text" class="form-control" id="address" name="address" value="${account.address}" required />
                            </div>

                            <div class="form-group mb-3">
                                <label for="gender"><strong>Gender:</strong></label>
                                <select class="form-control" id="gender" name="gender" required>
                                    <option value="" disabled>Select Gender</option>
                                    <option value="Male" ${account.gender == 'Male' ? 'selected' : ''}>Male</option>
                                    <option value="Female" ${account.gender == 'Female' ? 'selected' : ''}>Female</option>
                                    <option value="Other" ${account.gender == 'Other' ? 'selected' : ''}>Other</option>
                                </select>
                            </div>
                            <div class="form-group mb-3">
                                <label for="birthday"><strong>Birthday:</strong></label>
                                <input type="date" class="form-control" id="birthday" name="birthday" value="${account.birthday}" required />
                            </div>
                            <div class="text-center">
                                <button type="submit" class="btn btn-primary">Update Information</button>
                                <a href="../employee/index.htm" class="btn btn-secondary">Cancel</a>
                            </div>
                        </form>

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
        <script src="../asset/admin/js/app.js"></script>
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
