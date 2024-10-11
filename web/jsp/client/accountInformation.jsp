<%-- 
    Document   : index
    Created on : Oct 5, 2024, 11:04:48 AM
    Author     : Win10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <title>Nestmart - Update Account Information</title>
        <link href="https://fonts.googleapis.com/css?family=Cairo:400,600,700&amp;display=swap" rel="stylesheet"/>
        <link href="https://fonts.googleapis.com/css?family=Poppins:600&amp;display=swap" rel="stylesheet"/>
        <link href="https://fonts.googleapis.com/css?family=Playfair+Display:400i,700i" rel="stylesheet"/>
        <link href="https://fonts.googleapis.com/css?family=Ubuntu&amp;display=swap" rel="stylesheet"/>
        <link rel="shortcut icon" type="image/x-icon" href="../assets/client/images/NestMart_icon.png" />
    <link rel="stylesheet" href="../assets/client/css/bootstrap.min.css" />
    <link rel="stylesheet" href="../assets/client/css/animate.min.css" />
    <link rel="stylesheet" href="../assets/client/css/font-awesome.min.css" />
    <link rel="stylesheet" href="../assets/client/css/nice-select.css" />
    <link rel="stylesheet" href="../assets/client/css/slick.min.css" />
    <link rel="stylesheet" href="../assets/client/css/style.css" />
    <link rel="stylesheet" href="../assets/client/css/main-color04.css" />
        <style>
            /* Style cho phần main-content */
            #main-content {
                width: 100%;
                display: flex;
                justify-content: center; /* Canh giữa theo chiều ngang */
                padding-top: 50px; /* Thêm khoảng cách phía trên nếu cần */
            }

            /* Bọc form để đảm bảo căn giữa */
            .form-wrapper {
                display: flex;
                justify-content: center;
                width: 100%;
            }

            /* Container cho form */
            .form-container {
                background-color: #f8f9fa;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                max-width: 500px;
                width: 100%; /* Đảm bảo form có thể điều chỉnh kích thước */
                box-sizing: border-box;
            }

            /* Style cho các ô text */
            .txt-input {
                width: 100%;
                padding: 12px;
                border: 2px solid #ced4da;
                border-radius: 8px;
                font-size: 16px;
                box-sizing: border-box;
                transition: border-color 0.3s, box-shadow 0.3s;
            }

            /* Style cho các nhóm form row */
            .form-row {
                display: flex;
                justify-content: space-between; /* Sắp xếp theo chiều ngang */
                margin-bottom: 15px;
            }

            /* Điều chỉnh kích thước cho các input trong form-row */
            .form-row .form-group {
                flex: 1; /* Mỗi form-group sẽ chiếm cùng một khoảng không gian */
                margin-right: 10px; /* Thêm khoảng cách giữa các form-group */
            }

            /* Đảm bảo không có margin ở form-group cuối cùng */
            .form-row .form-group:last-child {
                margin-right: 0; /* Không thêm khoảng cách bên phải cho form-group cuối cùng */
            }

            /* Style cho nhãn (labels) */
            .form-group label {
                display: block;
                margin-bottom: 8px;
                font-weight: bold;
                font-size: 16px;
                color: #333;
            }

            /* Style cho thông báo lỗi */
            .error-message {
                color: #dc3545;
                font-size: 0.875em;
                margin-top: 5px;
                font-style: italic;
            }

            /* Style cho thông báo thành công và lỗi */
            .alert-success, .alert-danger {
                border-radius: 8px;
                padding: 15px;
                margin-bottom: 15px;
                font-size: 16px;
                font-weight: bold;
            }

            .alert-success {
                color: #155724;
                background-color: #d4edda;
                border: 1px solid #c3e6cb;
            }

            .alert-danger {
                color: #721c24;
                background-color: #f8d7da;
                border: 1px solid #f5c6cb;
            }

            /* Style cho các nút và liên kết */
            .form-row.wrap-btn {
                display: flex;
                flex-direction: column;
                align-items: center;
                margin-top: 20px;
            }

            .form-row.wrap-btn > * {
                margin: 10px 0;
            }

            .btn-submit {
                background-color: #ff9702;
                color: #fff;
                border: none;
                padding: 12px 20px;
                font-size: 18px;
                border-radius: 8px;
                cursor: pointer;
                transition: background-color 0.3s, transform 0.3s;
                width: 100%;
                text-align: center;
            }

            .btn-submit:hover {
                background-color: #0056b3;
                transform: scale(1.05);
            }

            .btn-submit:active {
                background-color: #004085;
                transform: scale(0.98);
            }

            .link-to-help {
                color: #ff9702;
                text-decoration: none;
                font-size: 16px;
                text-align: center;
            }

            .link-to-help:hover {
                text-decoration: underline;
            }
            .button-group {
                display: flex;               /* Sử dụng flexbox */
                justify-content: center;     /* Căn giữa các nút */
                gap: 10px;                  /* Khoảng cách giữa các nút (có thể tùy chỉnh) */
            }

            .button-group button,
            .button-group a {
                flex: 1;                    /* Tạo các nút có chiều rộng bằng nhau */
                text-align: center;         /* Căn giữa văn bản trong nút */
            }
        </style>

        <style>
            /* Style the popup content */
            .popup-content {
                display: none;
                position: absolute;
                top: 50px; /* Adjust according to your header height */
                right: 0; /* Align it to the right side of the screen */
                background-color: #FF9702; /* White background */
                min-width: 200px; /* Set a minimum width */
                box-shadow: 0px 8px 16px rgba(0,0,0,0.3); /* Adds a shadow for a 3D effect */
                z-index: 9999; /* Ensure it stays above other content */
                border-radius: 8px; /* Rounded corners for a smoother look */
                padding: 10px 0; /* Padding around the content */
            }

            /* Style for popup links */
            .popup-content a {
                color: #FF9702; /* Yellow text */
                font-weight: 600; /* Semi-bold for better readability */
                padding: 12px 16px; /* Padding for clickable links */
                text-decoration: none; /* Remove underline from links */
                display: block; /* Make the links block elements */
                transition: background-color 0.3s ease, color 0.3s ease; /* Smooth transition on hover */
            }

            /* Hover effect on links */
            .popup-content a:hover {
                background-color: #FF9702;
                color: #ffffff;
                border-radius: 4px;
            }

            .dropdown {
                position: relative;
            }

        </style>
    </head>
    <body>
        <c:if test="${not empty message}">
            <div class="alert alert-success">
                ${message}
            </div>
        </c:if>
        <header id="header" class="header-area style-01 layout-03">
            <div class="header-top bg-main hidden-xs">
                <div class="container">
                    <!-- Left top bar -->
                    <div class="top-bar left">
                        <ul class="horizontal-menu">
                            <li>
                                <a href="#">
                                    <i class="fa fa-envelope" aria-hidden="true"></i> Organic@company.com
                                </a>
                            </li>
                            <li>
                                <a href="#">Free Shipping for all Orders of $99</a>
                            </li>
                        </ul>
                    </div>
                    <div class="top-bar right">
                        <ul class="horizontal-menu">
                            <c:choose>
                                <c:when test="${not empty sessionScope.email}">
                                    <li class="dropdown">
                                        <a href="javascript:void(0);" class="dropbtn" onclick="togglePopup()">
                                            <i class="nestmart-icon icon-user"></i>Hello, ${sessionScope.email}
                                        </a>
                                        <div id="popupMenu" class="popup-content">
                                            <a href="../account/accountInformation.htm">Account Information</a>
                                            <a href="../account/changePassword.htm">Change Password</a>
                                            <a href="../logout.htm">Logout</a>
                                        </div>
                                    </li>
                                </c:when>

                                <c:otherwise>
                                    <li>
                                        <a href="login.htm" class="login-link">
                                            <i class="nestmart-icon icon-login"></i>Login
                                        </a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </div>
                </div>
            </div>

            <script>
                // Toggle dropdown visibility
                function togglePopup() {
                    var popupMenu = document.getElementById("popupMenu");
                    if (popupMenu.style.display === "block") {
                        popupMenu.style.display = "none";
                    } else {
                        popupMenu.style.display = "block";
                    }
                }

                // Close the dropdown if the user clicks outside of it
                window.onclick = function (event) {
                    if (!event.target.matches('.dropbtn')) {
                        var popupMenu = document.getElementById("popupMenu");
                        if (popupMenu.style.display === "block") {
                            popupMenu.style.display = "none";
                        }
                    }
                }
            </script>
            <div class="header-middle nestmart-sticky-object">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-3 col-md-2 col-sm-6 col-xs-6">
                            <a href="index.htm" class="nestmart-logo">
                                <img src="../assets/client/images/NestMart_logo.png" alt="nestmart logo" width="220" height="36"/>
                            </a>
                        </div>
                        <div class="col-lg-6 col-md-7 hidden-sm hidden-xs">
                            <div class="primary-menu">
                                <ul class="menu nestmart-menu clone-main-menu clone-primary-menu" id="primary-menu" data-menuname="main menu">
                                    <li class="menu-item"><a href="../client/clientboard.htm">Home</a>

                                    </li>
                                    <li class="menu-item">

                                        <a href="../client/product.htm" class="menu-name" data-title="Product">Product</a>

                                    </li>
                                    <li class="menu-item">

                                        <a href="../clietnt/discount.htm" class="menu-name" data-title="Discount">Discount</a>

                                    </li>
                                    <li class="menu-item">
                                        <a href="../client/about.htm" class="menu-name" data-title="About">About</a>
                                    </li>
                                    <!--                                viewNotifications-->
                                    <li class="menu-item">
                                        <c:choose>
                                            <c:when test="${not empty sessionScope.email}">
                                                <a href="viewNotifications.htm" class="menu-name" data-title="Order History">Notifications</a>
                                            </c:when>
                                            <c:otherwise>
                                                <!--                                            chua login-->
                                                <a href="../login.htm" class="menu-name" data-title="Order History">Notifications</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </li>

                                    <li class="menu-item">
                                        <c:choose>
                                            <c:when test="${not empty sessionScope.email}">
                                                <a href="../client/orderHistory.htm" class="menu-name" data-title="Order History">My Purchase</a>
                                            </c:when>
                                            <c:otherwise>
                                                <!--                                            chua login-->
                                                <a href="../login.htm" class="menu-name" data-title="Order History">My Purchase</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-md-6 col-xs-6">
                            <div class="nestmart-cart-info">
                                <div class="mobile-search">
                                    <a href="javascript:void(0)" class="open-searchbox"><i class="nestmart-icon icon-search"></i></a>
                                    <div class="mobile-search-content">
                                        <form action="#" class="form-search" name="mobile-search" method="get">
                                            <a href="#" class="btn-close"><span class="nestmart-icon icon-close-menu"></span></a>
                                            <input type="text" name="s" class="input-text" value="" placeholder="Search here..."/>
                                            <button type="submit" class="btn-submit">Go</button>
                                        </form>
                                    </div>
                                </div>
                                <div class="minicart-block">
                                    <div class="minicart-contain">
                                        <a href="cart.htm" class="link-to">
                                            <span class="icon-qty-combine">
                                                <img src="../assets/client/images/icons8-cart-30.png" style="margin-top: -10px">
                                            </span>
                                            <span class="title">My Cart</span>
                                        </a>
                                    </div>  
                                </div>
                                <div class="mobile-menu-toggle">
                                    <a class="btn-toggle" data-object="open-mobile-menu" href="javascript:void(0)">
                                        <span></span>
                                        <span></span>
                                        <span></span>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="header-bottom hidden-sm hidden-xs">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-3 col-md-4">
                            <div class="vertical-menu vertical-category-block">

                            </div>
                        </div>
                        <div class="col-lg-9 col-md-8 padding-top-2px">
                            <div class="header-search-bar layout-01">
                                <form action="../../client/product.htm" class="form-search" name="desktop-search" method="get">
                                    <input type="text" name="keyword" class="input-text" placeholder="Search product..." value="${keyword}">

                                    <button type="submit" class="btn-submit">
                                        <i class="nestmart-icon icon-search"></i>
                                    </button>
                                </form>
                            </div>

                            <div class="live-info">
                                <p class="telephone">
                                    <i class="fa fa-phone" aria-hidden="true"></i>
                                    <b class="phone-number">(+84) 123 456 789</b>
                                </p>
                                <p class="working-time">Mon-Fri: 8:30am-7:30pm; Sat-Sun: 9:30am-4:30pm</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <!--Hero Section-->
        <div class="hero-section hero-background">
            <h1 class="page-title">Organic Fruits</h1>
        </div>

        <!-- Navigation section -->
        <div class="container">
            <nav class="nestmart-nav">
                <ul>
                    <li class="nav-item"><a href="../client/index.htm" class="permal-link">Home</a></li>
                    <li class="nav-item"><span class="current-page">Update Information</span></li>
                </ul>
            </nav>
        </div>

        <!-- Main content -->
        <div id="main-content" class="main-content">
            <div class="form-wrapper">
                <div class="form-container">
                    <h2 class="text-center mb-4">Update Account Information</h2>

                    <!-- Hiển thị thông báo lỗi -->
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">${error}</div>
                    </c:if>


                    <form action="../account/updateInformation.htm" method="post">
                        <input type="hidden" name="accountID" value="${account.accountID}" />

                        <div class="form-row">
                            <div class="form-group">
                                <label for="email"><strong>Email:</strong></label>
                                <input type="text" id="email" name="email" class="txt-input" value="${account.email}" readonly />
                            </div>

                            <div class="form-group">
                                <label for="fullName"><strong>Full Name:</strong></label>
                                <input type="text" id="fullName" name="fullName" class="txt-input" value="${account.fullName}" required />
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group">
                                <label for="phoneNumber"><strong>Phone Number:</strong></label>
                                <input type="text" id="phoneNumber" name="phoneNumber" class="txt-input" value="${account.phoneNumber}" required />
                            </div>

                            <div class="form-group">
                                <label for="address"><strong>Address:</strong></label>
                                <input type="text" id="address" name="address" class="txt-input" value="${account.address}" required />
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group">
                                <label for="gender"><strong>Gender:</strong></label>
                                <select id="gender" name="gender" class="txt-input" required>
                                    <option value="" disabled>Select Gender</option>
                                    <option value="Male" ${account.gender == 'Male' ? 'selected' : ''}>Male</option>
                                    <option value="Female" ${account.gender == 'Female' ? 'selected' : ''}>Female</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="birthday"><strong>Birthday:</strong></label>
                                <input type="date" id="birthday" name="birthday" class="txt-input" value="${account.birthday}" required />
                            </div>
                        </div>

                        <div class="text-center button-group">
                            <button type="submit" class="btn-submit">Update Information</button>
                            <a href="../client/clientboard.htm" class="btn-submit btn-secondary">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>


        <!-- FOOTER -->
        <footer id="footer" class="footer layout-03">
            <div class="footer-content background-footer-03">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-4 col-md-4 col-sm-9">
                            <section class="footer-item">
                                <a href="#" class="logo footer-logo"><img src="../assets/client/images/NestMart_logo.png" alt="nestmart logo" width="220" height="34" /></a>
                                <div class="footer-phone-info">
                                    <i class="nestmart-icon icon-head-phone"></i>
                                    <p class="r-info">
                                        <span>Got Questions ?</span>
                                        <span>(700) 9001-1909 (900) 689 -66</span>
                                    </p>
                                </div>
                                <div class="newsletter-block layout-01">
                                    <h4 class="title">Newsletter Signup</h4>
                                    <div class="form-content">
                                        <form action="#" name="new-letter-footer">
                                            <input type="email" class="input-text email" value="" placeholder="Your email here..." />
                                            <button type="submit" class="bnt-submit" name="ok">Sign up</button>
                                        </form>
                                    </div>
                                </div>
                            </section>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-6 md-margin-top-5px sm-margin-top-50px xs-margin-top-40px">
                            <section class="footer-item">
                                <h3 class="section-title">Useful Links</h3>
                                <div class="row">
                                    <div class="col-lg-6 col-sm-6 col-xs-6">
                                        <div class="wrap-custom-menu vertical-menu-2">
                                            <ul class="menu">
                                                <li><a href="#">About Us</a></li>
                                                <li><a href="#">About Our Shop</a></li>
                                                <li><a href="#">Secure Shopping</a></li>
                                                <li><a href="#">Delivery information</a></li>
                                                <li><a href="#">Privacy Policy</a></li>
                                                <li><a href="#">Our Sitemap</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 col-sm-6 col-xs-6">
                                        <div class="wrap-custom-menu vertical-menu-2">
                                            <ul class="menu">
                                                <li><a href="#">Who We Are</a></li>
                                                <li><a href="#">Our Services</a></li>
                                                <li><a href="#">Projects</a></li>
                                                <li><a href="#">Contacts Us</a></li>
                                                <li><a href="#">Innovation</a></li>
                                                <li><a href="#">Testimonials</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </section>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-6 md-margin-top-5px sm-margin-top-50px xs-margin-top-40px">
                            <section class="footer-item">
                                <h3 class="section-title">Transport Offices</h3>
                                <div class="contact-info-block footer-layout xs-padding-top-10px">
                                    <ul class="contact-lines">
                                        <li>
                                            <p class="info-item">
                                                <i class="nestmart-icon icon-location"></i>
                                                <b class="desc">7563 St. Vicent Place, Glasgow, Greater Newyork NH7689, UK </b>
                                            </p>
                                        </li>
                                        <li>
                                            <p class="info-item">
                                                <i class="nestmart-icon icon-phone"></i>
                                                <b class="desc">Phone: (+067) 234 789 (+068) 222 888</b>
                                            </p>
                                        </li>
                                        <li>
                                            <p class="info-item">
                                                <i class="nestmart-icon icon-letter"></i>
                                                <b class="desc">Email: contact@company.com</b>
                                            </p>
                                        </li>
                                        <li>
                                            <p class="info-item">
                                                <i class="nestmart-icon icon-clock"></i>
                                                <b class="desc">Hours: 7 Days a week from 10:00 am</b>
                                            </p>
                                        </li>
                                    </ul>
                                </div>
                                <div class="nestmart-social inline">
                                    <ul class="socials">
                                        <li><a href="#" title="twitter" class="social-btn"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
                                        <li><a href="#" title="facebook" class="social-btn"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
                                        <li><a href="#" title="pinterest" class="social-btn"><i class="fa fa-pinterest" aria-hidden="true"></i></a></li>
                                        <li><a href="#" title="youtube" class="social-btn"><i class="fa fa-youtube" aria-hidden="true"></i></a></li>
                                        <li><a href="#" title="instagram" class="social-btn"><i class="fa fa-instagram" aria-hidden="true"></i></a></li>
                                    </ul>
                                </div>
                            </section>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="separator sm-margin-top-70px xs-margin-top-40px"></div>
                        </div>
                        <div class="col-lg-6 col-sm-6 col-xs-12">
                            <div class="copy-right-text"><p><a href="#">NestMart</a></p></div>
                        </div>
                        
                    </div>
                </div>
            </div>
        </footer>

        <!-- Scroll Top Button -->
        <a class="btn-scroll-top"><i class="nestmart-icon icon-left-arrow"></i></a>

        <script src="../assets/js/jquery-3.4.1.min.js"></script>
        <script src="../assets/js/bootstrap.min.js"></script>
        <script src="../assets/js/jquery.countdown.min.js"></script>
        <script src="../assets/js/jquery.nice-select.min.js"></script>
        <script src="../assets/js/jquery.nicescroll.min.js"></script>
        <script src="../assets/js/slick.min.js"></script>
        <script src="../assets/js/nestmart.framework.js"></script>
        <script src="../assets/js/functions.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.29.2/feather.min.js"></script>
        <link rel="stylesheet" href="../assets/admin/css/app.css"/>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                const usernameTab = document.getElementById('username-tab');
                const otpTab = document.getElementById('otp-tab');
                const usernameForm = document.getElementById('username-form');
                const otpForm = document.getElementById('otp-form');

                function showForm(formToShow) {
                    // Ẩn tất cả các form và xóa lớp active khỏi tất cả các tab
                    usernameForm.style.display = 'none';
                    otpForm.style.display = 'none';
                    usernameTab.classList.remove('active');
                    otpTab.classList.remove('active');

                    // Hiển thị form cụ thể và thêm lớp active cho tab tương ứng
                    if (formToShow === 'username') {
                        usernameForm.style.display = 'block';
                        usernameTab.classList.add('active');
                    } else if (formToShow === 'otp') {
                        otpForm.style.display = 'block';
                        otpTab.classList.add('active');
                    }
                }

                // Thay đổi form khi tab username được nhấn
                usernameTab.addEventListener('click', function () {
                    showForm('username');
                });

                // Thay đổi form khi tab OTP được nhấn
                otpTab.addEventListener('click', function () {
                    showForm('otp');
                });

                // Thiết lập mặc định để hiển thị form đăng nhập
                showForm('username');
            });


        </script>
        <script>
            $(document).ready(function () {
                // Initially, show the email form and hide the OTP form
                $("#otpForm").hide();

                $("#emailForm").submit(function (event) {
                    event.preventDefault();
                    $.post("${pageContext.request.contextPath}/sendOtp", {email: $("#email").val()}, function () {
                        // Hide email form and show OTP form
                        $("#emailForm").hide();
                        $("#otpForm").show();
                    });
                });

                // On click of the back button, show email form and hide OTP form
                $("#backToEmail").click(function () {
                    $("#otpForm").hide();
                    $("#emailForm").show();
                });
            });
        </script>
    </body>
</html>
