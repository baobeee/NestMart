<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

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
        <link rel="shortcut icon" href="assets/admin/img/icon/icon-48x48.png" />
        <link href="https://unpkg.com/feather-icons@latest/dist/feather.css" rel="stylesheet">

        <link rel="canonical" href="https://demo-basic.adminkit.io/pages-blank.html" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.28.0/feather.min.css">

        <title>NestMart - Add Account</title>

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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>

    <body>
        <div class="wrapper">
            <nav id="sidebar" class="sidebar js-sidebar">
                <div class="sidebar-content js-simplebar">
                    <a class="sidebar-brand" href="index.html">
                        <span class="align-middle">Nestmart</span>
                    </a>
                    <ul class="sidebar-nav">
                        <a class="sidebar-brand" href="index.html">
                            <span class="align-middle">Nestmart</span>
                        </a>
                        <li class="sidebar-header">
                            Pages
                        </li>

                        

                <li class="sidebar-item active">
                    <a class="sidebar-link" href="account.htm" >
                        <i class="align-middle me-2" data-feather="users"></i> <span class="align-middle">Account</span>

                    </a>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link" href="products.htm">
                        <i class="align-middle" data-feather="box"></i> <span class="align-middle">Product</span>
                    </a>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link" href="brand.htm">
                        <i class="align-middle" data-feather="bold"></i> <span class="align-middle">Brand</span>
                    </a>
                </li>

                <li class="sidebar-item  ">
                    <a class="sidebar-link" href="categories.htm">
                        <i class="align-middle" data-feather="list"></i> <span class="align-middle">Category</span>
                    </a>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link" href="categoryDetail.htm">
                        <i class="align-middle" data-feather="clipboard"></i> <span class="align-middle">Category Detail</span>
                    </a>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link" href="discount.htm">
                        <i class="align-middle" data-feather="check-circle"></i> <span class="align-middle">Discount</span>
                    </a>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link" href="offers.htm">
                        <i class="align-middle" data-feather="percent"></i> <span class="align-middle">Offers</span>
                    </a>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link" href="schedule.htm">
                        <i class="align-middle" data-feather="calendar"></i> <span class="align-middle">Schedule</span>
                    </a>
                </li>

                

                <li class="sidebar-item">
                    <a class="sidebar-link" href="inventory.htm">
                        <i class="align-middle" data-feather="package"></i> <span class="align-middle">Inventory</span>
                    </a>
                </li>

        <li class="sidebar-item">
                            <a class="sidebar-link" href="viewFeedbackAd.htm">
                                <i class="align-middle" data-feather="feather"></i> <span class="align-middle">Feedback</span>
                            </a>
                        </li>

                <li class="sidebar-item">
                    <a class="sidebar-link" href="salary.htm">
                        <i class="align-middle" data-feather="user-check"></i> <span class="align-middle">Salary</span>
                    </a>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link" href="notifications.htm">
                        <i class="align-middle" data-feather="navigation"></i> <span class="align-middle">Notification</span>
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
                    <div class="container mt-4">
                        <h2>Create New Account</h2>
                        <form action="${pageContext.request.contextPath}/admin/accountCreate.htm" method="post">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="fullName">Full Name:</label>
                                        <input type="text" name="fullName" id="fullName" class="form-control" value="${fullName}" required/>
                                        <c:if test="${not empty errors['fullName']}">
                                            <span class="text-danger">${errors['fullName']}</span>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="email">Email:</label>
                                        <input type="email" name="email" id="email" class="form-control" value="${email}" required/>
                                        <c:if test="${not empty errors['email']}">
                                            <span class="text-danger">${errors['email']}</span>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="password">Password:</label>
                                        <input type="password" name="password" id="password" class="form-control" required/>
                                        <c:if test="${not empty errors['password']}">
                                            <span class="text-danger">${errors['password']}</span>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="confirmPassword">Confirm Password:</label>
                                        <input type="password" name="confirmPassword" id="confirmPassword" class="form-control" required/>
                                        <c:if test="${not empty errors['confirmPassword']}">
                                            <span class="text-danger">${errors['confirmPassword']}</span>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="phoneNumber">Phone Number:</label>
                                        <input type="text" name="phoneNumber" id="phoneNumber" class="form-control" value="${phoneNumber}" required/>
                                        <c:if test="${not empty errors['phoneNumber']}">
                                            <span class="text-danger">${errors['phoneNumber']}</span>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="address">Address:</label>
                                        <input type="text" name="address" id="address" class="form-control" value="${address}" required/>
                                        <c:if test="${not empty errors['address']}">
                                            <span class="text-danger">${errors['address']}</span>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="gender">Gender:</label>
                                        <select name="gender" id="gender" class="form-control">
                                            <option value="Male" ${gender == 'Male' ? 'selected' : ''}>Male</option>
                                            <option value="Female" ${gender == 'Female' ? 'selected' : ''}>Female</option>
                                        </select>
                                        <c:if test="${not empty errors['gender']}">
                                            <span class="text-danger">${errors['gender']}</span>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="birthday">Birthday:</label>
                                        <input type="date" name="birthday" id="birthday" class="form-control" value="${birthday}" required />
                                        <c:if test="${not empty errors['birthday']}">
                                            <span class="text-danger">${errors['birthday']}</span>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="role">Role:</label>
                                        <select name="role" id="role" class="form-control">
                                            <option value="1" ${role == '1' ? 'selected' : ''}>Admin</option>
                                            <option value="2" ${role == '2' ? 'selected' : ''}>Employee</option>
                                            <option value="3" ${role == '3' ? 'selected' : ''}>Shipper</option>
                                            <option value="4" ${role == '4' ? 'selected' : ''}>Customer</option>
                                        </select>
                                        <c:if test="${not empty errors['role']}">
                                            <span class="text-danger">${errors['role']}</span>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="hourlyRate">Hourly Rate:</label>
                                        <input type="number" name="hourlyRate" id="hourlyRate" step="0.01" class="form-control" value="${hourlyRate}" />
                                        <c:if test="${not empty errors['hourlyRate']}">
                                            <span class="text-danger">${errors['hourlyRate']}</span>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary">Create Account</button>
                                <a href="${pageContext.request.contextPath}/admin/account.htm" class="btn btn-secondary">Cancel</a>
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
