
<%-- 
    Document   : login
    Created on : Aug 16, 2024, 3:06:58 PM
    Author     : Win10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <title>Nestmart - Forgot Password</title>
        <link href="https://fonts.googleapis.com/css?family=Cairo:400,600,700&amp;display=swap" rel="stylesheet"/>
        <link href="https://fonts.googleapis.com/css?family=Poppins:600&amp;display=swap" rel="stylesheet"/>
        <link href="https://fonts.googleapis.com/css?family=Playfair+Display:400i,700i" rel="stylesheet"/>
        <link href="https://fonts.googleapis.com/css?family=Ubuntu&amp;display=swap" rel="stylesheet"/>
        <link rel="shortcut icon" type="image/x-icon" href="assets/images/NestMart_icon.png" />
        <link rel="stylesheet" href="assets/client/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="assets/client/css/animate.min.css"/>
        <link rel="stylesheet" href="assets/client/css/font-awesome.min.css"/>
        <link rel="stylesheet" href="assets/client/css/nice-select.css"/>
        <link rel="stylesheet" href="assets/css/slick.min.css"/>
        <link rel="stylesheet" href="assets/client/css/style.css"/>
        <link rel="stylesheet" href="assets/client/css/main-color.css"/>
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

            .txt-input:focus {
                border-color: #007bff;
                box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
                outline: none;
            }

            /* Style cho nhãn (labels) */
            .form-group {
                margin-bottom: 15px;
            }

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


        </style>
    </head>
    <body>

        <!-- Preloader -->
        <div id="biof-loading">
            <div class="biof-loading-center">
                <div class="biof-loading-center-absolute">
                    <div class="dot dot-one"></div>
                    <div class="dot dot-two"></div>
                    <div class="dot dot-three"></div>
                </div>
            </div>
        </div>
        <jsp:include page="/jsp/client/header.jsp" />
        <!--Hero Section-->
        <div class="hero-section hero-background">
            <h1 class="page-title">Organic Fruits</h1>
        </div>
        <!--Navigation section-->
        <div class="container">
            <nav class="nestmart-nav">
                <ul>
                    <li class="nav-item"><a href="index.xhtml" class="permal-link">Home</a></li>
                    <li class="nav-item"><span class="current-page">Authentication</span></li>
                </ul>
            </nav>
        </div>
        <div id="main-content" class="main-content">
            <div class="form-wrapper">
                <div class="form-container">
                    <h2>Forgot Password</h2>
                    <c:if test="${not empty message}">
                        <div class="alert alert-success">
                            ${message}
                        </div>
                    </c:if>
                    <form action="forgotPassword.htm" method="post">
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" id="email" name="email" placeholder="Enter your email" class="txt-input" required />
                        </div>
                        <button type="submit" class="btn-submit">Send Reset Link</button>
                        <c:if test="${not empty errorMessage}">
                            <p style="color:red;">${errorMessage}</p>
                        </c:if>
                    </form>
                    <div class="form-row wrap-btn">
                        <a href="login.htm" class="link-to-help">Back to Login</a>
                    </div>
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
                                <a href="#" class="logo footer-logo"><img src="assets/images/NestMart_logo.png" alt="nestmart logo" width="220" height="34" /></a>
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
                        <div class="col-lg-6 col-sm-6 col-xs-12">
                            <div class="payment-methods">
                                <ul>
                                    <li><a href="#" class="payment-link"><img src="assets/images/card1.jpg" width="51" height="36" alt="" /></a></li>
                                    <li><a href="#" class="payment-link"><img src="assets/images/card2.jpg" width="51" height="36" alt="" /></a></li>
                                    <li><a href="#" class="payment-link"><img src="assets/images/card3.jpg" width="51" height="36" alt="" /></a></li>
                                    <li><a href="#" class="payment-link"><img src="assets/images/card4.jpg" width="51" height="36" alt="" /></a></li>
                                    <li><a href="#" class="payment-link"><img src="assets/images/card5.jpg" width="51" height="36" alt="" /></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>

        <!-- Scroll Top Button -->
        <a class="btn-scroll-top"><i class="nestmart-icon icon-left-arrow"></i></a>

        <script src="assets/js/jquery-3.4.1.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/jquery.countdown.min.js"></script>
        <script src="assets/js/jquery.nice-select.min.js"></script>
        <script src="assets/js/jquery.nicescroll.min.js"></script>
        <script src="assets/js/slick.min.js"></script>
        <script src="assets/js/nestmart.framework.js"></script>
        <script src="assets/js/functions.js"></script>
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
