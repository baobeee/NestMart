<!-- WEB-INF/jsp/includes/header.jsp -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css?family=Cairo:400,600,700&amp;display=swap" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Poppins:600&amp;display=swap" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Playfair+Display:400i,700i" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Ubuntu&amp;display=swap" rel="stylesheet" />
    <link rel="shortcut icon" type="image/x-icon" href="../assets/client/images/NestMart_icon.png" />
    <link rel="stylesheet" href="../assets/client/css/bootstrap.min.css" />
    <link rel="stylesheet" href="../assets/client/css/animate.min.css" />
    <link rel="stylesheet" href="../assets/client/css/font-awesome.min.css" />
    <link rel="stylesheet" href="../assets/client/css/nice-select.css" />
    <link rel="stylesheet" href="../assets/client/css/slick.min.css" />
    <link rel="stylesheet" href="../assets/client/css/style.css" />
    <link rel="stylesheet" href="../assets/client/css/main-color04.css" />
</head>
<style>
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

    .error-message {
        color: #dc3545;
        font-size: 0.875em;
        margin-top: 5px;
        font-style: italic;
    }

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
        color: #FF9702;
        font-weight: 600;
        padding: 12px 16px;
        text-decoration: none;
        display: block;
        transition: background-color 0.3s ease, color 0.3s ease;
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
                                    <a href="../login.htm" class="login-link">
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
                        <a href="clientboard.htm" class="nestmart-logo">
                            <img src="../assets/client/images/NestMart_logo.png" alt="nestmart logo" width="220" height="36"/>
                        </a>
                    </div>
                    <div class="col-lg-6 col-md-7 hidden-sm hidden-xs">
                        <div class="primary-menu">
                            <ul class="menu nestmart-menu clone-main-menu clone-primary-menu" id="primary-menu" data-menuname="main menu">
                                    <li class="menu-item"><a href="clientboard.htm">Home</a>

                                    </li>
                                    <li class="menu-item">
                                       
                                        <a href="product.htm" class="menu-name" data-title="Product">Product</a>
                                      
                                    </li>
                                <li class="menu-item">
                                
                                            <a href="discount.htm" class="menu-name" data-title="Discount">Discount</a>
                                    
                                </li>
                                <li class="menu-item">
                                    <a href="about.htm" class="menu-name" data-title="About">About</a>
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
                                            <a href="orderHistory.htm" class="menu-name" data-title="Order History">My Purchase</a>
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
                            <form action="product.htm" class="form-search" name="desktop-search" method="get">
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

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script>
            document.querySelector('.dropdown-toggle').addEventListener('click', function () {
                var menu = document.querySelector('.dropdown-menu');
                menu.classList.toggle('show');
            });

            document.addEventListener('click', function (event) {
                var isClickInside = document.querySelector('.dropdown').contains(event.target);
                if (!isClickInside) {
                    document.querySelector('.dropdown-menu').classList.remove('show');
                }
            });
    </script>