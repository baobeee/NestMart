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
        <title>Nestmart - Login Page</title>
        <link href="https://fonts.googleapis.com/css?family=Cairo:400,600,700&amp;display=swap" rel="stylesheet"/>
        <link href="https://fonts.googleapis.com/css?family=Poppins:600&amp;display=swap" rel="stylesheet"/>
        <link href="https://fonts.googleapis.com/css?family=Playfair+Display:400i,700i" rel="stylesheet"/>
        <link href="https://fonts.googleapis.com/css?family=Ubuntu&amp;display=swap" rel="stylesheet"/>
        <link rel="shortcut icon" type="image/x-icon" href="assets/images/NestMart_icon.png" />
        <link rel="stylesheet" href="assets/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="assets/css/animate.min.css"/>
        <link rel="stylesheet" href="assets/css/font-awesome.min.css"/>
        <link rel="stylesheet" href="assets/css/nice-select.css"/>
        <link rel="stylesheet" href="assets/css/slick.min.css"/>
        <link rel="stylesheet" href="assets/css/style.css"/>
        <link rel="stylesheet" href="assets/admin/css/main-color.css"/>
        <style>
            /* Container cho form */
            .form-container {
                background-color: #f8f9fa;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                max-width: 500px;
                margin: 0 auto;
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
                color: #333; /* Màu chữ tối hơn */
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
        <c:if test="${not empty message}">
            <div class="alert alert-success">
                ${message}
            </div>
        </c:if>
        <!-- Preloader -->
        <!--        <div id="biof-loading">
                    <div class="biof-loading-center">
                        <div class="biof-loading-center-absolute">
                            <div class="dot dot-one"></div>
                            <div class="dot dot-two"></div>
                            <div class="dot dot-three"></div>
                        </div>
                    </div>
                </div>-->
        <header id="header" class="header-area style-01 layout-03">
            <div class="header-top bg-main hidden-xs">
                <div class="container">
                    <div class="top-bar left">
                        <ul class="horizontal-menu">
                            <li><a href="#"><i class="fa fa-envelope" aria-hidden="true"></i>Organic@company.com</a></li>
                            <li><a href="#">Free Shipping for all Order of $99</a></li>
                        </ul>
                    </div>
                    <div class="top-bar right">
                        <ul class="horizontal-menu">

                            <li>
                                <a href="login" class="login-link" >
                                    <i class="nestmart-icon icon-login"></i>Login&#160;|
                                </a>
                                <a href="register" class="login-link">
                                    &#160;Register
                                </a>

                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="header-middle nestmart-sticky-object ">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-3 col-md-2 col-sm-6 col-xs-6">
                            <a href="index.xhtml" class="nestmart-logo">
                                <img src="assets/images/NestMart_logo.png" alt="nestmart logo" width="220" height="36"/>
                            </a>
                        </div>
                        <div class="col-lg-6 col-md-7 hidden-sm hidden-xs">
                            <div class="primary-menu">
                                <ul class="menu nestmart-menu clone-main-menu clone-primary-menu" id="primary-menu" data-menuname="main menu">
                                    <li class="menu-item"><a href="index.xhtml">Home</a></li>
                                    <li class="menu-item menu-item-has-children has-megamenu">
                                        <a href="#" class="menu-name" data-title="Shop">Shop</a>
                                        <div class="wrap-megamenu lg-width-900 md-width-750">
                                            <div class="mega-content">
                                                <div class="col-lg-3 col-md-3 col-xs-12 md-margin-bottom-0 xs-margin-bottom-25">
                                                    <div class="wrap-custom-menu vertical-menu">
                                                        <h4 class="menu-title">Fresh Berries</h4>
                                                        <ul class="menu">
                                                            <li><a href="#">Fruit &amp; Nut Gifts</a></li>
                                                            <li><a href="#">Mixed Fruits</a></li>
                                                            <li><a href="#">Oranges</a></li>
                                                            <li><a href="#">Bananas &amp; Plantains</a></li>
                                                            <li><a href="#">Fresh Gala Apples</a></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div class="col-lg-3 col-md-3 col-xs-12 md-margin-bottom-0 xs-margin-bottom-25">
                                                    <div class="wrap-custom-menu vertical-menu">
                                                        <h4 class="menu-title">Vegetables</h4>
                                                        <ul class="menu">
                                                            <li><a href="#">Berries</a></li>
                                                            <li><a href="#">Pears</a></li>
                                                            <li><a href="#">Chili Peppers</a></li>
                                                            <li><a href="#">Fresh Avocado</a></li>
                                                            <li><a href="#">Grapes</a></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div class="col-lg-3 col-md-3 col-xs-12 md-margin-bottom-0 xs-margin-bottom-25">
                                                    <div class="wrap-custom-menu vertical-menu">
                                                        <h4 class="menu-title">Fresh Fruits</h4>
                                                        <ul class="menu">
                                                            <li><a href="#">Basket of Apples</a></li>
                                                            <li><a href="#">Strawberry</a></li>
                                                            <li><a href="#">Blueberry</a></li>
                                                            <li><a href="#">Orange</a></li>
                                                            <li><a href="#">Pineapple</a></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div class="col-lg-3 col-md-3 col-xs-12 md-margin-bottom-0 xs-margin-bottom-25">
                                                    <div class="wrap-custom-menu vertical-menu">
                                                        <h4 class="menu-title">Featured Products</h4>
                                                        <ul class="menu">
                                                            <li><a href="#">Coffee Creamers</a></li>
                                                            <li><a href="#">Mayonnaise</a></li>
                                                            <li><a href="#">Almond Milk</a></li>
                                                            <li><a href="#">Fruit Jam</a></li>
                                                            <li><a href="#">Beverages</a></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="menu-item menu-item-has-children has-child">
                                        <a href="#" class="menu-name" data-title="Products">Products</a>
                                        <ul class="sub-menu">
                                            <li class="menu-item"><a href="#">Omelettes</a></li>
                                            <li class="menu-item"><a href="#">Breakfast Scrambles</a></li>
                                            <li class="menu-item menu-item-has-children has-child">
                                                <a href="#" class="menu-name" data-title="Eggs &amp; other considerations">Eggs &amp; Other Considerations</a>
                                                <ul class="sub-menu">
                                                    <li class="menu-item"><a href="#">Classic Breakfast</a></li>
                                                    <li class="menu-item"><a href="#">Huevos Rancheros</a></li>
                                                    <li class="menu-item"><a href="#">Everything Egg Sandwich</a></li>
                                                    <li class="menu-item"><a href="#">Egg Sandwich</a></li>
                                                    <li class="menu-item"><a href="#">Vegan Burrito</a></li>
                                                    <li class="menu-item"><a href="#">Biscuits and Gravy</a></li>
                                                    <li class="menu-item"><a href="#">Bacon Avo Egg Sandwich</a></li>
                                                </ul>
                                            </li>
                                            <li class="menu-item"><a href="#">Griddle</a></li>
                                            <li class="menu-item menu-item-has-children has-child">
                                                <a href="#" class="menu-name" data-title="Sides &amp; Extras">Sides &amp; Extras</a>
                                                <ul class="sub-menu">
                                                    <li class="menu-item"><a href="#">Breakfast Burrito</a></li>
                                                    <li class="menu-item"><a href="#">Crab Cake Benedict</a></li>
                                                    <li class="menu-item"><a href="#">Corned Beef Hash</a></li>
                                                    <li class="menu-item"><a href="#">Steak &amp; Eggs</a></li>
                                                    <li class="menu-item"><a href="#">Oatmeal</a></li>
                                                    <li class="menu-item"><a href="#">Fruit &amp; Yogurt Parfait</a></li>
                                                </ul>
                                            </li>
                                            <li class="menu-item"><a href="#">Biscuits</a></li>
                                            <li class="menu-item"><a href="#">Seasonal Fruit Plate</a></li>
                                        </ul>
                                    </li>
                                    <li class="menu-item menu-item-has-children has-megamenu">
                                        <a href="#" class="menu-name" data-title="Demo">Demo</a>
                                        <div class="wrap-megamenu lg-width-800 md-width-750">
                                            <div class="mega-content">
                                                <div class="col-lg-3 col-md-3 col-xs-12 md-margin-bottom-0 xs-margin-bottom-25">
                                                    <div class="wrap-custom-menu vertical-menu">
                                                        <h4 class="menu-title">Home Page</h4>
                                                        <ul class="menu">
                                                            <li><a href="home-01.xhtml">Home 01</a></li>
                                                            <li><a href="home-02.xhtml">Home 02</a></li>
                                                            <li><a href="index.xhtml">Home 03</a></li>
                                                            <li><a href="home-03-green.xhtml">Home 03 Green</a></li>
                                                            <li><a href="home-04.xhtml">Home 04</a></li>
                                                            <li><a href="home-04-light.xhtml">Home 04 Light</a></li>
                                                            <li><a href="home-05.xhtml">Home 05</a></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div class="col-lg-3 col-md-3 col-xs-12 md-margin-bottom-0 xs-margin-bottom-25">
                                                    <div class="wrap-custom-menu vertical-menu">
                                                        <h4 class="menu-title">Inner Pages</h4>
                                                        <ul class="menu">
                                                            <li class="menu-item" ><a href="blog-post.html">Blog Single</a></li>
                                                            <li class="menu-item" ><a href="blog-v01.html">Blog Style 01</a></li>
                                                            <li class="menu-item" ><a href="blog-v02.html">Blog Style 02</a></li>
                                                            <li class="menu-item" ><a href="blog-v03.html">Blog Style 03</a></li>
                                                            <li class="menu-item" ><a href="contact.html">Contact Us</a></li>
                                                            <li class="menu-item" ><a href="about-us.html">About Us</a></li>
                                                            <li class="menu-item" ><a href="checkout.html">Checkout</a></li>
                                                            <li class="menu-item" ><a href="shopping-cart.html">Shopping Cart</a></li>
                                                            <li class="menu-item" ><a href="login.xhtml">Login/Register</a></li>
                                                            <li class="menu-item" ><a href="404.html">404</a></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div class="col-lg-3 col-md-3 col-xs-12 md-margin-bottom-0 xs-margin-bottom-25">
                                                    <div class="wrap-custom-menu vertical-menu">
                                                        <h4 class="menu-title">Category Pages</h4>
                                                        <ul class="menu">
                                                            <li class="menu-item" ><a href="category-grid-3-cols.html">Grid 3 Cols</a></li>
                                                            <li class="menu-item" ><a href="category-grid.html">Grid 4 Cols</a></li>
                                                            <li class="menu-item" ><a href="category-grid-6-cols.html">Grid 6 Cols</a></li>
                                                            <li class="menu-item" ><a href="category-grid-left-sidebar.html">Grid Left Sidebar</a></li>
                                                            <li class="menu-item" ><a href="category-grid-right-sidebar.html">Grid Right Sidebar</a></li>
                                                            <li class="menu-item" ><a href="category-list.html">List Full</a></li>
                                                            <li class="menu-item" ><a href="category-list-left-sidebar.html">List Left Sidebar</a></li>
                                                            <li class="menu-item" ><a href="category-list-right-sidebar.html">List Right Sidebar</a></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div class="col-lg-3 col-md-3 col-xs-12 md-margin-bottom-0 xs-margin-bottom-25">
                                                    <div class="wrap-custom-menu vertical-menu">
                                                        <h4 class="menu-title">Product Pages</h4>
                                                        <ul class="menu">
                                                            <li class="menu-item" ><a href="single-product-simple.html">Simple</a></li>
                                                            <li class="menu-item" ><a href="single-product-grouped.html">Grouped</a></li>
                                                            <li class="menu-item" ><a href="single-product.html">Variable</a></li>
                                                            <li class="menu-item" ><a href="single-product-external.html">External/Affiliate</a></li>
                                                            <li class="menu-item" ><a href="single-product-onsale.html">Countdown</a></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="menu-item"><a href="contact.html">Contact</a></li>
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
                                            <select name="category">
                                                <option value="-1" >All Categories</option>
                                                <option value="vegetables">Vegetables</option>
                                                <option value="fresh_berries">Fresh Berries</option>
                                                <option value="ocean_foods">Ocean Foods</option>
                                                <option value="butter_eggs">Butter &amp; Eggs</option>
                                                <option value="fastfood">Fastfood</option>
                                                <option value="fresh_meat">Fresh Meat</option>
                                                <option value="fresh_onion">Fresh Onion</option>
                                                <option value="papaya_crisps">Papaya &amp; Crisps</option>
                                                <option value="oatmeal">Oatmeal</option>
                                            </select>
                                            <button type="submit" class="btn-submit">Go</button>
                                        </form>
                                    </div>
                                </div>
                                <div class="wishlist-block hidden-sm hidden-xs">
                                    <a href="#" class="link-to">
                                        <span class="icon-qty-combine">
                                            <i class="icon-heart-bold nestmart-icon"></i>
                                            <span class="qty">4</span>
                                        </span>
                                    </a>
                                </div>
                                <div class="minicart-block">
                                    <div class="minicart-contain">
                                        <a href="javascript:void(0)" class="link-to">
                                            <span class="icon-qty-combine">
                                                <i class="icon-cart-mini nestmart-icon"></i>
                                                <span class="qty">8</span>
                                            </span>
                                            <span class="title">My Cart -</span>
                                            <span class="sub-total">$0.00</span>
                                        </a>
                                        <div class="cart-content">
                                            <div class="cart-inner">
                                                <ul class="products">
                                                    <li>
                                                        <div class="minicart-item">
                                                            <div class="thumb">
                                                                <a href="#"><img src="assets/images/minicart/pr-01.jpg" width="90" height="90" alt="National Fresh"/></a>
                                                            </div>
                                                            <div class="left-info">
                                                                <div class="product-title"><a href="#" class="product-name">National Fresh Fruit</a></div>
                                                                <div class="price">
                                                                    <ins><span class="price-amount"><span class="currencySymbol">£</span>85.00</span></ins>
                                                                    <del><span class="price-amount"><span class="currencySymbol">£</span>95.00</span></del>
                                                                </div>
                                                                <div class="qty">
                                                                    <label for="cart[id123][qty]">Qty:</label>
                                                                    <input type="number" class="input-qty" name="cart[id123][qty]" id="cart[id123][qty]" value="1" />
                                                                </div>
                                                            </div>
                                                            <div class="action">
                                                                <a href="#" class="edit"><i class="fa fa-pencil" aria-hidden="true"></i></a>
                                                                <a href="#" class="remove"><i class="fa fa-trash-o" aria-hidden="true"></i></a>
                                                            </div>
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="minicart-item">
                                                            <div class="thumb">
                                                                <a href="#"><img src="assets/images/minicart/pr-02.jpg" width="90" height="90" alt="National Fresh"/></a>
                                                            </div>
                                                            <div class="left-info">
                                                                <div class="product-title"><a href="#" class="product-name">National Fresh Fruit</a></div>
                                                                <div class="price">
                                                                    <ins><span class="price-amount"><span class="currencySymbol">£</span>85.00</span></ins>
                                                                    <del><span class="price-amount"><span class="currencySymbol">£</span>95.00</span></del>
                                                                </div>
                                                                <div class="qty">
                                                                    <label for="cart[id124][qty]">Qty:</label>
                                                                    <input type="number" class="input-qty" name="cart[id124][qty]" id="cart[id124][qty]" value="1" />
                                                                </div>
                                                            </div>
                                                            <div class="action">
                                                                <a href="#" class="edit"><i class="fa fa-pencil" aria-hidden="true"></i></a>
                                                                <a href="#" class="remove"><i class="fa fa-trash-o" aria-hidden="true"></i></a>
                                                            </div>
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="minicart-item">
                                                            <div class="thumb">
                                                                <a href="#"><img src="assets/images/minicart/pr-03.jpg" width="90" height="90" alt="National Fresh"/></a>
                                                            </div>
                                                            <div class="left-info">
                                                                <div class="product-title"><a href="#" class="product-name">National Fresh Fruit</a></div>
                                                                <div class="price">
                                                                    <ins><span class="price-amount"><span class="currencySymbol">£</span>85.00</span></ins>
                                                                    <del><span class="price-amount"><span class="currencySymbol">£</span>95.00</span></del>
                                                                </div>
                                                                <div class="qty">
                                                                    <label for="cart[id125][qty]">Qty:</label>
                                                                    <input type="number" class="input-qty" name="cart[id125][qty]" id="cart[id125][qty]" value="1" />
                                                                </div>
                                                            </div>
                                                            <div class="action">
                                                                <a href="#" class="edit"><i class="fa fa-pencil" aria-hidden="true"></i></a>
                                                                <a href="#" class="remove"><i class="fa fa-trash-o" aria-hidden="true"></i></a>
                                                            </div>
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="minicart-item">
                                                            <div class="thumb">
                                                                <a href="#"><img src="assets/images/minicart/pr-04.jpg" width="90" height="90" alt="National Fresh"/></a>
                                                            </div>
                                                            <div class="left-info">
                                                                <div class="product-title"><a href="#" class="product-name">National Fresh Fruit</a></div>
                                                                <div class="price">
                                                                    <ins><span class="price-amount"><span class="currencySymbol">£</span>85.00</span></ins>
                                                                    <del><span class="price-amount"><span class="currencySymbol">£</span>95.00</span></del>
                                                                </div>
                                                                <div class="qty">
                                                                    <label for="cart[id126][qty]">Qty:</label>
                                                                    <input type="number" class="input-qty" name="cart[id126][qty]" id="cart[id126][qty]" value="1" />
                                                                </div>
                                                            </div>
                                                            <div class="action">
                                                                <a href="#" class="edit"><i class="fa fa-pencil" aria-hidden="true"></i></a>
                                                                <a href="#" class="remove"><i class="fa fa-trash-o" aria-hidden="true"></i></a>
                                                            </div>
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="minicart-item">
                                                            <div class="thumb">
                                                                <a href="#"><img src="assets/images/minicart/pr-05.jpg" width="90" height="90" alt="National Fresh"/></a>
                                                            </div>
                                                            <div class="left-info">
                                                                <div class="product-title"><a href="#" class="product-name">National Fresh Fruit</a></div>
                                                                <div class="price">
                                                                    <ins><span class="price-amount"><span class="currencySymbol">£</span>85.00</span></ins>
                                                                    <del><span class="price-amount"><span class="currencySymbol">£</span>95.00</span></del>
                                                                </div>
                                                                <div class="qty">
                                                                    <label for="cart[id127][qty]">Qty:</label>
                                                                    <input type="number" class="input-qty" name="cart[id127][qty]" id="cart[id127][qty]" value="1" />
                                                                </div>
                                                            </div>
                                                            <div class="action">
                                                                <a href="#" class="edit"><i class="fa fa-pencil" aria-hidden="true"></i></a>
                                                                <a href="#" class="remove"><i class="fa fa-trash-o" aria-hidden="true"></i></a>
                                                            </div>
                                                        </div>
                                                    </li>
                                                </ul>
                                                <p class="btn-control">
                                                    <a href="#" class="btn view-cart">View Cart</a>
                                                    <a href="#" class="btn">Checkout</a>
                                                </p>
                                            </div>
                                        </div>
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
                                <div class="block-title">
                                    <span class="menu-icon">
                                        <span class="line-1"></span>
                                        <span class="line-2"></span>
                                        <span class="line-3"></span>
                                    </span>
                                    <span class="menu-title">All departments</span>
                                    <span class="angle" data-tgleclass="fa fa-caret-down"><i class="fa fa-caret-up" aria-hidden="true"></i></span>
                                </div>
                                <div class="wrap-menu">
                                    <ul class="menu clone-main-menu">
                                        <li class="menu-item menu-item-has-children has-megamenu">
                                            <a href="#" class="menu-name" data-title="Fruit &#160;&amp; Nut Gifts"><i class="nestmart-icon icon-fruits"></i>Fruit &#160;&amp; Nut Gifts</a>
                                            <div class="wrap-megamenu lg-width-900 md-width-640">
                                                <div class="mega-content">
                                                    <div class="row">
                                                        <div class="col-lg-3 col-md-4 col-sm-12 xs-margin-bottom-25 md-margin-bottom-0">
                                                            <div class="wrap-custom-menu vertical-menu">
                                                                <h4 class="menu-title">Fresh Fruits</h4>
                                                                <ul class="menu">
                                                                    <li><a href="#">Fruit &#160;&amp; Nut Gifts</a></li>
                                                                    <li><a href="#">Mixed Fruits</a></li>
                                                                    <li><a href="#">Oranges</a></li>
                                                                    <li><a href="#">Bananas &#160;&amp; Plantains</a></li>
                                                                    <li><a href="#">Fresh Gala Apples</a></li>
                                                                    <li><a href="#">Berries</a></li>
                                                                    <li><a href="#">Pears</a></li>
                                                                    <li><a href="#">Produce</a></li>
                                                                    <li><a href="#">Snack Foods</a></li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-3 col-md-4 col-sm-12 lg-padding-left-23 xs-margin-bottom-25 md-margin-bottom-0">
                                                            <div class="wrap-custom-menu vertical-menu">
                                                                <h4 class="menu-title">Nut Gifts</h4>
                                                                <ul class="menu">
                                                                    <li><a href="#">Non-Dairy Coffee Creamers</a></li>
                                                                    <li><a href="#">Coffee Creamers</a></li>
                                                                    <li><a href="#">Mayonnaise</a></li>
                                                                    <li><a href="#">Almond Milk</a></li>
                                                                    <li><a href="#">Ghee</a></li>
                                                                    <li><a href="#">Beverages</a></li>
                                                                    <li><a href="#">Ranch Salad Dressings</a></li>
                                                                    <li><a href="#">Hemp Milk</a></li>
                                                                    <li><a href="#">Nuts &#160;&amp; Seeds</a></li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-6 col-md-4 col-sm-12 lg-padding-left-50 xs-margin-bottom-25 md-margin-bottom-0">
                                                            <div class="nestmart-products-block max-width-270">
                                                                <h4 class="menu-title">Bestseller Products</h4>
                                                                <ul class="products-list default-product-style nestmart-carousel nav-none-after-1k2 nav-center" data-slick='{"rows":1,"arrows":true,"dots":false,"infinite":false,"speed":400,"slidesMargin":30,"slidesToShow":1, "responsive":[{"breakpoint":767, "settings":{ "arrows": false}}]}' >
                                                                    <li class="product-item">
                                                                        <div class="contain-product none-overlay">
                                                                            <div class="product-thumb">
                                                                                <a href="#" class="link-to-product">
                                                                                    <img src="assets/images/products/p-08.jpg" alt="dd" width="270" height="270" class="product-thumnail"/>
                                                                                </a>
                                                                            </div>
                                                                            <div class="info">
                                                                                <b class="categories">Fresh Fruit</b>
                                                                                <h4 class="product-title"><a href="#" class="pr-name">National Fresh Fruit</a></h4>
                                                                                <div class="price">
                                                                                    <ins><span class="price-amount"><span class="currencySymbol">£</span>85.00</span></ins>
                                                                                    <del><span class="price-amount"><span class="currencySymbol">£</span>95.00</span></del>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </li>
                                                                    <li class="product-item">
                                                                        <div class="contain-product none-overlay">
                                                                            <div class="product-thumb">
                                                                                <a href="#" class="link-to-product">
                                                                                    <img src="assets/images/products/p-11.jpg" alt="dd" width="270" height="270" class="product-thumnail"/>
                                                                                </a>
                                                                            </div>
                                                                            <div class="info">
                                                                                <b class="categories">Fresh Fruit</b>
                                                                                <h4 class="product-title"><a href="#" class="pr-name">National Fresh Fruit</a></h4>
                                                                                <div class="price">
                                                                                    <ins><span class="price-amount"><span class="currencySymbol">£</span>85.00</span></ins>
                                                                                    <del><span class="price-amount"><span class="currencySymbol">£</span>95.00</span></del>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </li>
                                                                    <li class="product-item">
                                                                        <div class="contain-product none-overlay">
                                                                            <div class="product-thumb">
                                                                                <a href="#" class="link-to-product">
                                                                                    <img src="assets/images/products/p-15.jpg" alt="dd" width="270" height="270" class="product-thumnail"/>
                                                                                </a>
                                                                            </div>
                                                                            <div class="info">
                                                                                <b class="categories">Fresh Fruit</b>
                                                                                <h4 class="product-title"><a href="#" class="pr-name">National Fresh Fruit</a></h4>
                                                                                <div class="price">
                                                                                    <ins><span class="price-amount"><span class="currencySymbol">£</span>85.00</span></ins>
                                                                                    <del><span class="price-amount"><span class="currencySymbol">£</span>95.00</span></del>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-lg-12 col-md-12 col-sm-12 md-margin-top-9">
                                                            <div class="nestmart-brand">
                                                                <ul class="brands">
                                                                    <li><a href="#"><img src="assets/images/megamenu/brand-organic.png" width="161" height="136" alt="organic"/></a></li>
                                                                    <li><a href="#"><img src="assets/images/megamenu/brand-explore.png" width="160" height="136" alt="explore"/></a></li>
                                                                    <li><a href="#"><img src="assets/images/megamenu/brand-organic-2.png" width="99" height="136" alt="organic 2"/></a></li>
                                                                    <li><a href="#"><img src="assets/images/megamenu/brand-eco-teas.png" width="164"  height="136" alt="eco teas"/></a></li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="menu-item menu-item-has-children has-megamenu">
                                            <a href="#" class="menu-name" data-title="Vegetables"><i class="nestmart-icon icon-broccoli-1"></i>Vegetables</a>
                                            <div class="wrap-megamenu lg-width-900 md-width-640 background-mega-01">
                                                <div class="mega-content">
                                                    <div class="row">
                                                        <div class="col-lg-3 col-md-4 col-sm-12 xs-margin-bottom-25 md-margin-bottom-0">
                                                            <div class="wrap-custom-menu vertical-menu">
                                                                <h4 class="menu-title">Vegetables</h4>
                                                                <ul class="menu">
                                                                    <li><a href="#">Fruit &#160;&amp; Nut Gifts</a></li>
                                                                    <li><a href="#">Mixed Fruits</a></li>
                                                                    <li><a href="#">Oranges</a></li>
                                                                    <li><a href="#">Bananas &#160;&amp; Plantains</a></li>
                                                                    <li><a href="#">Fresh Gala Apples</a></li>
                                                                    <li><a href="#">Berries</a></li>
                                                                    <li><a href="#">Pears</a></li>
                                                                    <li><a href="#">Produce</a></li>
                                                                    <li><a href="#">Snack Foods</a></li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-3 col-md-4 col-sm-12 lg-padding-left-23 xs-margin-bottom-25 md-margin-bottom-0">
                                                            <div class="wrap-custom-menu vertical-menu">
                                                                <h4 class="menu-title">Top Products</h4>
                                                                <ul class="menu">
                                                                    <li><a href="#">Non-Dairy Coffee Creamers</a></li>
                                                                    <li><a href="#">Coffee Creamers</a></li>
                                                                    <li><a href="#">Mayonnaise</a></li>
                                                                    <li><a href="#">Almond Milk</a></li>
                                                                    <li><a href="#">Ghee</a></li>
                                                                    <li><a href="#">Beverages</a></li>
                                                                    <li><a href="#">Ranch Salad Dressings</a></li>
                                                                    <li><a href="#">Hemp Milk</a></li>
                                                                    <li><a href="#">Nuts &#160;&amp; Seeds</a></li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-6 col-md-4 col-sm-12 lg-padding-left-50 xs-margin-bottom-25 md-margin-bottom-0">
                                                            <div class="nestmart-products-block max-width-270">
                                                                <h4 class="menu-title">Bestseller Products</h4>
                                                                <ul class="products-list default-product-style nestmart-carousel nav-none-after-1k2 nav-center" data-slick='{"rows":1,"arrows":true,"dots":false,"infinite":false,"speed":400,"slidesMargin":30,"slidesToShow":1, "responsive":[{"breakpoint":767, "settings":{ "arrows": false}}]}' >
                                                                    <li class="product-item">
                                                                        <div class="contain-product none-overlay">
                                                                            <div class="product-thumb">
                                                                                <a href="#" class="link-to-product">
                                                                                    <img src="assets/images/products/p-08.jpg" alt="dd" width="270" height="270" class="product-thumnail"/>
                                                                                </a>
                                                                            </div>
                                                                            <div class="info">
                                                                                <b class="categories">Fresh Fruit</b>
                                                                                <h4 class="product-title"><a href="#" class="pr-name">National Fresh Fruit</a></h4>
                                                                                <div class="price">
                                                                                    <ins><span class="price-amount"><span class="currencySymbol">£</span>85.00</span></ins>
                                                                                    <del><span class="price-amount"><span class="currencySymbol">£</span>95.00</span></del>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </li>
                                                                    <li class="product-item">
                                                                        <div class="contain-product none-overlay">
                                                                            <div class="product-thumb">
                                                                                <a href="#" class="link-to-product">
                                                                                    <img src="assets/images/products/p-11.jpg" alt="dd" width="270" height="270" class="product-thumnail"/>
                                                                                </a>
                                                                            </div>
                                                                            <div class="info">
                                                                                <b class="categories">Fresh Fruit</b>
                                                                                <h4 class="product-title"><a href="#" class="pr-name">National Fresh Fruit</a></h4>
                                                                                <div class="price">
                                                                                    <ins><span class="price-amount"><span class="currencySymbol">£</span>85.00</span></ins>
                                                                                    <del><span class="price-amount"><span class="currencySymbol">£</span>95.00</span></del>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </li>
                                                                    <li class="product-item">
                                                                        <div class="contain-product none-overlay">
                                                                            <div class="product-thumb">
                                                                                <a href="#" class="link-to-product">
                                                                                    <img src="assets/images/products/p-15.jpg" alt="dd" width="270" height="270" class="product-thumnail"/>
                                                                                </a>
                                                                            </div>
                                                                            <div class="info">
                                                                                <b class="categories">Fresh Fruit</b>
                                                                                <h4 class="product-title"><a href="#" class="pr-name">National Fresh Fruit</a></h4>
                                                                                <div class="price">
                                                                                    <ins><span class="price-amount"><span class="currencySymbol">£</span>85.00</span></ins>
                                                                                    <del><span class="price-amount"><span class="currencySymbol">£</span>95.00</span></del>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-lg-12 col-md-12 col-sm-12 md-margin-top-9">
                                                            <div class="nestmart-brand">
                                                                <ul class="brands">
                                                                    <li><a href="#"><img src="assets/images/megamenu/brand-organic.png" width="161" height="136" alt="organic"/></a></li>
                                                                    <li><a href="#"><img src="assets/images/megamenu/brand-explore.png" width="160" height="136" alt="explore"/></a></li>
                                                                    <li><a href="#"><img src="assets/images/megamenu/brand-organic-2.png" width="99" height="136" alt="organic 2"/></a></li>
                                                                    <li><a href="#"><img src="assets/images/megamenu/brand-eco-teas.png" width="164"  height="136" alt="eco teas"/></a></li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="menu-item menu-item-has-children has-megamenu">
                                            <a href="#" class="menu-name" data-title="Vegetables">
                                                <i class="nestmart-icon icon-broccoli-1"></i>Vegetables
                                            </a>
                                            <div class="wrap-megamenu lg-width-900 md-width-640 background-mega-01">
                                                <div class="mega-content">
                                                    <div class="row">
                                                        <div class="col-lg-3 col-md-4 col-sm-12 xs-margin-bottom-25 md-margin-bottom-0">
                                                            <div class="wrap-custom-menu vertical-menu">
                                                                <h4 class="menu-title">Vegetables</h4>
                                                                <ul class="menu">
                                                                    <li><a href="#">Fruit &#160;&amp; Nut Gifts</a></li>
                                                                    <li><a href="#">Mixed Fruits</a></li>
                                                                    <li><a href="#">Oranges</a></li>
                                                                    <li><a href="#">Bananas &#160;&amp; Plantains</a></li>
                                                                    <li><a href="#">Fresh Gala Apples</a></li>
                                                                    <li><a href="#">Berries</a></li>
                                                                    <li><a href="#">Pears</a></li>
                                                                    <li><a href="#">Produce</a></li>
                                                                    <li><a href="#">Snack Foods</a></li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-4 col-md-4 col-sm-12 lg-padding-left-23 xs-margin-bottom-25 md-margin-bottom-0">
                                                            <div class="wrap-custom-menu vertical-menu">
                                                                <h4 class="menu-title">Gifts</h4>
                                                                <ul class="menu">
                                                                    <li><a href="#">Non-Dairy Coffee Creamers</a></li>
                                                                    <li><a href="#">Coffee Creamers</a></li>
                                                                    <li><a href="#">Mayonnaise</a></li>
                                                                    <li><a href="#">Almond Milk</a></li>
                                                                    <li><a href="#">Ghee</a></li>
                                                                    <li><a href="#">Beverages</a></li>
                                                                    <li><a href="#">Ranch Salad Dressings</a></li>
                                                                    <li><a href="#">Hemp Milk</a></li>
                                                                    <li><a href="#">Nuts &#160;&amp; Seeds</a></li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-5 col-md-4 col-sm-12 lg-padding-left-57 md-margin-bottom-30">
                                                            <div class="nestmart-brand vertical md-boder-left-30">
                                                                <h4 class="menu-title">Hot Brand</h4>
                                                                <ul class="brands">
                                                                    <li><a href="#"><img src="assets/images/megamenu/v-brand-organic.png" width="167" height="74" alt="organic"/></a></li>
                                                                    <li><a href="#"><img src="assets/images/megamenu/v-brand-explore.png" width="167" height="72" alt="explore"/></a></li>
                                                                    <li><a href="#"><img src="assets/images/megamenu/v-brand-organic-2.png" width="167" height="99" alt="organic 2"/></a></li>
                                                                    <li><a href="#"><img src="assets/images/megamenu/v-brand-eco-teas.png" width="167" height="67" alt="eco teas"/></a></li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>

                                        <li class="menu-item menu-item-has-children has-megamenu">
                                            <a href="#" class="menu-name" data-title="Fresh Berries">
                                                <i class="nestmart-icon icon-grape"></i>Fresh Berries
                                            </a>
                                            <div class="wrap-megamenu lg-width-900 md-width-640 background-mega-02">
                                                <div class="mega-content">
                                                    <div class="row">
                                                        <div class="col-lg-3 col-md-4 sm-col-12 md-margin-bottom-83 xs-margin-bottom-25">
                                                            <div class="wrap-custom-menu vertical-menu">
                                                                <h4 class="menu-title">Fresh Berries</h4>
                                                                <ul class="menu">
                                                                    <li><a href="#">Fruit &#160;&amp; Nut Gifts</a></li>
                                                                    <li><a href="#">Mixed Fruits</a></li>
                                                                    <li><a href="#">Oranges</a></li>
                                                                    <li><a href="#">Bananas &#160;&amp; Plantains</a></li>
                                                                    <li><a href="#">Fresh Gala Apples</a></li>
                                                                    <li><a href="#">Berries</a></li>
                                                                    <li><a href="#">Pears</a></li>
                                                                    <li><a href="#">Produce</a></li>
                                                                    <li><a href="#">Snack Foods</a></li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-3 col-md-4 sm-col-12 lg-padding-left-23 xs-margin-bottom-36px md-margin-bottom-0">
                                                            <div class="wrap-custom-menu vertical-menu">
                                                                <h4 class="menu-title">Gifts</h4>
                                                                <ul class="menu">
                                                                    <li><a href="#">Non-Dairy Coffee Creamers</a></li>
                                                                    <li><a href="#">Coffee Creamers</a></li>
                                                                    <li><a href="#">Mayonnaise</a></li>
                                                                    <li><a href="#">Almond Milk</a></li>
                                                                    <li><a href="#">Ghee</a></li>
                                                                    <li><a href="#">Beverages</a></li>
                                                                    <li><a href="#">Ranch Salad Dressings</a></li>
                                                                    <li><a href="#">Hemp Milk</a></li>
                                                                    <li><a href="#">Nuts &#160;&amp; Seeds</a></li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-6 col-md-4 sm-col-12 lg-padding-left-25 md-padding-top-55">
                                                            <div class="nestmart-banner layout-01">
                                                                <h3 class="top-title">Farm Fresh</h3>
                                                                <p class="content">All the Lorem Ipsum generators on the Internet tend.</p>
                                                                <b class="bottomm-title">Berries Series</b>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="menu-item">
                                            <a href="#" class="menu-name" data-title="Ocean Foods">
                                                <i class="nestmart-icon icon-fish"></i>Ocean Foods
                                            </a>
                                        </li>
                                        <li class="menu-item menu-item-has-children has-child">
                                            <a href="#" class="menu-name" data-title="Butter &amp; Eggs">
                                                <i class="nestmart-icon icon-honey"></i>Butter &amp; Eggs
                                            </a>
                                            <ul class="sub-menu">
                                                <li class="menu-item"><a href="#">Omelettes</a></li>
                                                <li class="menu-item"><a href="#">Breakfast Scrambles</a></li>
                                                <li class="menu-item menu-item-has-children has-child">
                                                    <a href="#" class="menu-name" data-title="Eggs &amp; other considerations">
                                                        Eggs &amp; other considerations
                                                    </a>
                                                    <ul class="sub-menu">
                                                        <li class="menu-item"><a href="#">Classic Breakfast</a></li>
                                                        <li class="menu-item"><a href="#">Huevos Rancheros</a></li>
                                                        <li class="menu-item"><a href="#">Everything Egg Sandwich</a></li>
                                                        <li class="menu-item"><a href="#">Egg Sandwich</a></li>
                                                        <li class="menu-item"><a href="#">Vegan Burrito</a></li>
                                                        <li class="menu-item"><a href="#">Biscuits and Gravy</a></li>
                                                        <li class="menu-item"><a href="#">Bacon Avo Egg Sandwich</a></li>
                                                    </ul>
                                                </li>
                                                <li class="menu-item"><a href="#">Griddle</a></li>
                                                <li class="menu-item menu-item-has-children has-child">
                                                    <a href="#" class="menu-name" data-title="Sides &amp; Extras">Sides &amp; Extras</a>
                                                    <ul class="sub-menu">
                                                        <li class="menu-item"><a href="#">Breakfast Burrito</a></li>
                                                        <li class="menu-item"><a href="#">Crab Cake Benedict</a></li>
                                                        <li class="menu-item"><a href="#">Corned Beef Hash</a></li>
                                                        <li class="menu-item"><a href="#">Steak &amp; Eggs</a></li>
                                                        <li class="menu-item"><a href="#">Oatmeal</a></li>
                                                        <li class="menu-item"><a href="#">Fruit &amp; Yogurt Parfait</a></li>
                                                    </ul>
                                                </li>
                                                <li class="menu-item"><a href="#">Biscuits</a></li>
                                                <li class="menu-item"><a href="#">Seasonal Fruit Plate</a></li>
                                            </ul>
                                        </li>
                                        <li class="menu-item"><a href="#" class="menu-title"><i class="nestmart-icon icon-fast-food"></i>Fastfood</a></li>
                                        <li class="menu-item"><a href="#" class="menu-title"><i class="nestmart-icon icon-beef"></i>Fresh Meat</a></li>
                                        <li class="menu-item"><a href="#" class="menu-title"><i class="nestmart-icon icon-onions"></i>Fresh Onion</a></li>
                                        <li class="menu-item"><a href="#" class="menu-title"><i class="nestmart-icon icon-avocado"></i>Papaya &amp; Crisps</a></li>
                                        <li class="menu-item"><a href="#" class="menu-title"><i class="nestmart-icon icon-contain"></i>Oatmeal</a></li>
                                        <li class="menu-item"><a href="#" class="menu-title"><i class="nestmart-icon icon-fresh-juice"></i>Fresh Bananas &amp; Plantains</a></li>

                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-9 col-md-8 padding-top-2px">
                            <div class="header-search-bar layout-01">
                                <form action="#" class="form-search" name="desktop-search" method="get">
                                    <input type="text" name="s" class="input-text" value="" placeholder="Search here..." />
                                    <select name="category">
                                        <option value="-1" >All Categories</option>
                                        <option value="vegetables">Vegetables</option>
                                        <option value="fresh_berries">Fresh Berries</option>
                                        <option value="ocean_foods">Ocean Foods</option>
                                        <option value="butter_eggs">Butter &amp; Eggs</option>
                                        <option value="fastfood">Fastfood</option>
                                        <option value="fresh_meat">Fresh Meat</option>
                                        <option value="fresh_onion">Fresh Onion</option>
                                        <option value="papaya_crisps">Papaya &amp; Crisps</option>
                                        <option value="oatmeal">Oatmeal</option>
                                    </select>
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
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-8 col-md-10 col-sm-12">
                        <div class="form-container">
                            <!-- Hiển thị thông báo lỗi nếu có -->
                            <c:if test="${not empty error}">
                                <div class="alert alert-danger">
                                    ${error}
                                </div>
                            </c:if>

                            <form action="${pageContext.request.contextPath}/login.htm" method="post">
                                <div class="form-group">
                                    <label for="fid-email">Email:</label>
                                    <input type="email" id="fid-email" name="email" class="txt-input" placeholder="Enter your email" value="${email}" />
                                    <c:if test="${not empty emailError}">
                                        <div class="error-message">${emailError}</div>
                                    </c:if>
                                </div>

                                <div class="form-group">
                                    <label for="fid-password">Password:</label>
                                    <input type="password" id="fid-password" name="password" class="txt-input" placeholder="Enter your password" />
                                </div>

                                <div class="form-row wrap-btn">
                                    <button class="btn-submit" type="submit">Login</button>
                                    <p><a href="${pageContext.request.contextPath}/forgotPassword.htm" class="link-to-help">Forgot your password?</a></p>

                                    <p><a href="${pageContext.request.contextPath}/signup.htm" class="link-to-help">Don't have an account? Register</a></p>
                                </div>
                            </form>
                        </div>
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
