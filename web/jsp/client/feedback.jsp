<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en" class="no-js">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>NestMart - Feedback</title>
        <link href="https://fonts.googleapis.com/css?family=Cairo:400,600,700&amp;display=swap" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css?family=Poppins:600&amp;display=swap" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css?family=Playfair+Display:400i,700i" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css?family=Ubuntu&amp;display=swap" rel="stylesheet" />
        <link rel="shortcut icon" type="image/x-icon" href="assets/client/images/NestMart_icon.png" />
        <link rel="stylesheet" href="../assets/client/css/bootstrap.min.css" />
        <link rel="stylesheet" href="../assets/client/css/animate.min.css" />
        <link rel="stylesheet" href="../assets/client/css/font-awesome.min.css" />
        <link rel="stylesheet" href="../assets/client/css/nice-select.css" />
        <link rel="stylesheet" href="../assets/client/css/slick.min.css" />
        <link rel="stylesheet" href="../assets/client/css/style.css" />
        <link rel="stylesheet" href="../assets/client/css/main-color.css" />
        <style>
            .rating {
                direction: rtl;
                unicode-bidi: bidi-override;
                display: inline-block;
            }
            .rating input {
                position: absolute;
                left: -9999px;
            }
            .rating label {
                color: #ddd;
                float: right;
                font-size: 24px;
                padding: 0 5px;
                cursor: pointer;
            }
            .rating input:checked ~ label {
                color: #ffc700;
            }
            .rating label:hover,
            .rating label:hover ~ label {
                color: #ffc700;
            }
            .product-image {
                position: relative;
            }
            .image-count {
                position: absolute;
                top: 10px;
                right: 10px;
                background: rgba(0, 0, 0, 0.7);
                color: white;
                padding: 5px 10px;
                border-radius: 50%;
                font-size: 14px;
            }
            .container {
                padding: 20px;
                background-color: #f9f9f9;
                border-radius: 8px;
                box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            }
            .row.mb-4 {
                margin-bottom: 20px;
            }
            .btn-primary {
                background-color: #007bff;
                border: none;
            }
            .btn-secondary {
                background-color: #6c757d;
                border: none;
            }
            .orange-outline-btn {
                background-color: #fff;
                color: #ff9702;
                border: 1px solid #ff9702;
                padding: 8px 10px;
                border-radius: 50px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                cursor: pointer;
                transition: background-color 0.3s ease, color 0.3s ease;
            }

            .orange-outline-btn:hover {
                background-color: #ff9702;
                color: #fff;
            }

            .categories {
                font-size: 14px;
                color: #333;
            }

            .product-title {
                font-size: 18px;
                margin: 10px 0;
            }

            .price {
                margin-top: 10px;
                color: #666;
                font-size: 16px;
            }

            .price-amount {
                font-weight: bold;
                color: #333;
            }

            .total-amount-container {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-top: 15px;
            }

            .total-amount-label {
                font-size: 18px;
                font-weight: bold;
                color: #333;
            }

            .total-amount-value {
                font-size: 24px;
                color: #FF6347;
                font-weight: bold;
            }

            .buttons {
                margin-top: 20px;
            }

            .info {
                padding: 0px;
                margin-bottom: 0px;
            }

            .btn {
                padding: 8px 15px;
                border-radius: 50px;
                margin-right: 10px;
                text-align: center;
                text-decoration: none;
                cursor: pointer;
            }

            .wishlist-btn {
                background-color: #ff6347;
                color: white;
                border: none;
            }

            .add-to-cart-btn {
                background-color: #FF9702;
                color: white;
                border: none;
            }

            .orange-outline-btn {
                background-color: #fff;
                color: #ff9702;
                border: 1px solid #ff9702;
                padding: 8px 15px;
                border-radius: 50px;
                cursor: pointer;
                transition: background-color 0.3s ease, color 0.3s ease;
            }

            .orange-outline-btn:hover {
                background-color: #ff9702;
                color: #fff;
            }

            .status-item {
                display: flex;
                flex-direction: column;
                align-items: center;
                padding: 5px;
                border-radius: 8px;
                text-align: center;
                transition: background-color 0.3s ease;
                width: 100px;
                margin: 0 5px;
            }

            .status-item .icon {
                font-size: 20px;
                color: #555;
                margin-bottom: 3px;
            }

            .status-item:hover {
                background-color: #f8f8f8;
            }

            .status-item .pr-name {
                color: #333;
                font-weight: bold;
                text-decoration: none;
            }

            .status-item .pr-name:hover {
                color: #007bff;
            }

            .status-item .icon, .status-item .pr-name {
                display: block;
            }
            
            .product-container {
                display: flex;
                align-items: center;
            }

            .product-image {
                width: 250px;
                height: auto;
            }

            .product-name {
                margin-bottom: 190px;
                margin-left: 20px;
                font-size: 20px;
                color: #333;
            }
        </style>
    </head>
    <body class="nestmart-body">
        <!--         Preloader -->        
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

        <!-- Page Contain -->
        <div class="page-contain">

            <!-- Main content -->
            <div id="main-content" class="main-content">
                <div class="hero-section hero-background">
                    <h1 class="page-title">Rate Product</h1>
                </div>

                <!--Navigation section-->
                <div class="container">
                    <nav class="nestmart-nav">
                        <ul>
                            <li class="nav-item"><a href="clientboard.htm" class="permal-link">Home</a></li>
                            <li class="nav-item"><span class="current-page">My Purchase</span></li>
                        </ul>
                    </nav>
                </div>

                <div class="page-contain category-page no-sidebar">
                    <div class="container">
                        <div class="row">

                            <!-- Main content -->
                            <div id="main-content" class="main-content col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div class="container">
                                    <!-- Product Information Section -->
                                    <div class="row mb-4">
                                        <div class="col-md-4">
                                            <!-- Product Image -->
                                            <div class="product-container">
                                                <img src="${pageContext.request.contextPath}/assets/admin/images/uploads/products/${product.images[0].images}" alt="alt" class="product-image"/>
                                                <div class="product-name">
                                                    <span>${product.productName}</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-8">
                                            <!-- Product Name -->

                                        </div>
                                    </div>

                                    <!-- Feedback Form Section -->
                                    <form action="${pageContext.request.contextPath}/client/feedbacks.htm" method="post" enctype="multipart/form-data">
                                        <input type="hidden" name="productID" value="${product.productID}" />
                                        <!-- Rating Section -->
                                        <div class="row mb-4">
                                            <div class="col-md-12">
                                                <h5>Rate this product:</h5>
                                                <div class="rating">
                                                    <input type="radio" id="star5" name="rating" value="5" required />
                                                    <label for="star5" title="5 stars"><i class="fa fa-star"></i></label>
                                                    <input type="radio" id="star4" name="rating" value="4" />
                                                    <label for="star4" title="4 stars"><i class="fa fa-star"></i></label>
                                                    <input type="radio" id="star3" name="rating" value="3" />
                                                    <label for="star3" title="3 stars"><i class="fa fa-star"></i></label>
                                                    <input type="radio" id="star2" name="rating" value="2" />
                                                    <label for="star2" title="2 stars"><i class="fa fa-star"></i></label>
                                                    <input type="radio" id="star1" name="rating" value="1" />
                                                    <label for="star1" title="1 star"><i class="fa fa-star"></i></label>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Feedback Content Section -->
                                        <div class="row mb-4">
                                            <div class="col-md-12">
                                                <label for="feedbackContent">Your Feedback:</label>
                                                <textarea name="feedbackContent" class="form-control" rows="4" placeholder="Write your feedback here..." required></textarea>
                                            </div>
                                        </div>

                                        <!-- Submit and Cancel Buttons -->
                                        <div class="row mb-4">
                                            <div class="col-md-12 text-center">
                                                <button type="submit" class="btn btn-primary">Submit Feedback</button>
                                                <button onclick="window.history.back();" class="btn btn-close-quickview">Cancel</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>




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
                                    <a href="#" class="logo footer-logo"><img src="assets/client/images/NestMart_logo.png" alt="nestmart logo" width="220" height="36"/></a>
                                    <div class="footer-phone-info">
                                        <i class="nestmart-icon icon-head-phone"></i>
                                        <p class="r-info">
                                            <span>Got Questions ?</span>
                                            <span>(700)  9001-1909  (900) 689 -66</span>
                                        </p>
                                    </div>
                                    <div class="newsletter-block layout-01">
                                        <h4 class="title">Newsletter Signup</h4>
                                        <div class="form-content">
                                            <form action="#" name="new-letter-foter">
                                                <input type="email" class="input-text email" value="" placeholder="Your email here..."/>
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
                                                    <li><a href="#">Delivery infomation</a></li>
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
                                                    <b class="desc">Phone: (+067) 234 789  (+068) 222 888</b>
                                                </p>
                                            </li>
                                            <li>
                                                <p class="info-item">
                                                    <i class="nestmart-icon icon-letter"></i>
                                                    <b class="desc">Email:  contact@company.com</b>
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
                                            <li><a href="#" title="twitter" class="socail-btn"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
                                            <li><a href="#" title="facebook" class="socail-btn"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
                                            <li><a href="#" title="pinterest" class="socail-btn"><i class="fa fa-pinterest" aria-hidden="true"></i></a></li>
                                            <li><a href="#" title="youtube" class="socail-btn"><i class="fa fa-youtube" aria-hidden="true"></i></a></li>
                                            <li><a href="#" title="instagram" class="socail-btn"><i class="fa fa-instagram" aria-hidden="true"></i></a></li>
                                        </ul>
                                    </div>
                                </section>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="separator sm-margin-top-62px xs-margin-top-40px"></div>
                            </div>
                            <div class="col-lg-6 col-sm-6 col-xs-12">
                                <div class="copy-right-text"><p><a href="#">NestMart</a></p></div>
                            </div>
                            <div class="col-lg-6 col-sm-6 col-xs-12">
                                <div class="payment-methods">
                                    <ul>
                                        <li><a href="#" class="payment-link"><img src="assets/client/images/card1.jpg" width="51" height="36" alt=""/></a></li>
                                        <li><a href="#" class="payment-link"><img src="assets/client/images/card2.jpg" width="51" height="36" alt=""/></a></li>
                                        <li><a href="#" class="payment-link"><img src="assets/client/images/card3.jpg" width="51" height="36" alt=""/></a></li>
                                        <li><a href="#" class="payment-link"><img src="assets/client/images/card4.jpg" width="51" height="36" alt=""/></a></li>
                                        <li><a href="#" class="payment-link"><img src="assets/client/images/card5.jpg" width="51" height="36" alt=""/></a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </footer>

            <!--Footer For Mobile-->
            <div class="mobile-footer">
                <div class="mobile-footer-inner">
                    <div class="mobile-block block-menu-main">
                        <a class="menu-bar menu-toggle btn-toggle" data-object="open-mobile-menu" href="javascript:void(0)">
                            <span class="fa fa-bars"></span>
                            <span class="text">Menu</span>
                        </a>
                    </div>
                    <div class="mobile-block block-sidebar">
                        <a class="menu-bar filter-toggle btn-toggle" data-object="open-mobile-filter" href="javascript:void(0)">
                            <i class="fa fa-sliders" aria-hidden="true"></i>
                            <span class="text">Sidebar</span>
                        </a>
                    </div>
                    <div class="mobile-block block-minicart">
                        <a class="link-to-cart" href="#">
                            <span class="fa fa-shopping-bag" aria-hidden="true"></span>
                            <span class="text">Cart</span>
                        </a>
                    </div>
                    <div class="mobile-block block-global">
                        <a class="menu-bar myaccount-toggle btn-toggle" data-object="global-panel-opened" href="javascript:void(0)">
                            <span class="fa fa-globe"></span>
                            <span class="text">Global</span>
                        </a>
                    </div>
                </div>
            </div>

            <div class="mobile-block-global">
                <div class="nestmart-mobile-panels">
                    <span class="nestmart-current-panel-title">Global</span>
                    <a class="nestmart-close-btn" data-object="global-panel-opened" href="#"></a>
                </div>
                <div class="block-global-contain">
                    <div class="glb-item my-account">
                        <b class="title">My Account</b>
                        <ul class="list">
                            <li class="list-item"><a href="#">Login/register</a></li>
                            <li class="list-item"><a href="#">Wishlist <span class="index">(8)</span></a></li>
                            <li class="list-item"><a href="#">Checkout</a></li>
                        </ul>
                    </div>
                    <div class="glb-item currency">
                        <b class="title">Currency</b>
                        <ul class="list">
                            <li class="list-item"><a href="#">€ EUR (Euro)</a></li>
                            <li class="list-item"><a href="#">$ USD (Dollar)</a></li>
                            <li class="list-item"><a href="#">£ GBP (Pound)</a></li>
                            <li class="list-item"><a href="#">¥ JPY (Yen)</a></li>
                        </ul>
                    </div>
                    <div class="glb-item languages">
                        <b class="title">Language</b>
                        <ul class="list inline">
                            <li class="list-item"><a href="#"><img src="assets/client/images/languages/us.jpg" alt="flag" width="24" height="18"/></a></li>
                            <li class="list-item"><a href="#"><img src="assets/client/images/languages/fr.jpg" alt="flag" width="24" height="18"/></a></li>
                            <li class="list-item"><a href="#"><img src="assets/client/images/languages/ger.jpg" alt="flag" width="24" height="18"/></a></li>
                            <li class="list-item"><a href="#"><img src="assets/client/images/languages/jap.jpg" alt="flag" width="24" height="18"/></a></li>
                        </ul>
                    </div>
                </div>
            </div>

            <!--Quickview Popup-->
            <div id="nestmart-quickview-block" class="nestmart-quickview-block">
                <div class="quickview-container">
                    <a href="#" class="btn-close-quickview" data-object="open-quickview-block"><span class="nestmart-icon icon-close-menu"></span></a>
                    <div class="nestmart-quickview-inner">
                        <div class="media">
                            <ul class="nestmart-carousel quickview-for" data-slick='{"arrows":false,"dots":false,"slidesMargin":30,"slidesToShow":1,"slidesToScroll":1,"fade":true,"asNavFor":".quickview-nav"}'>
                                <li><img src="assets/client/images/details-product/detail_01.jpg" alt="" width="500" height="500"/></li>
                                <li><img src="assets/client/images/details-product/detail_02.jpg" alt="" width="500" height="500"/></li>
                                <li><img src="assets/client/images/details-product/detail_03.jpg" alt="" width="500" height="500"/></li>
                                <li><img src="assets/client/images/details-product/detail_04.jpg" alt="" width="500" height="500"/></li>
                                <li><img src="assets/client/images/details-product/detail_05.jpg" alt="" width="500" height="500"/></li>
                                <li><img src="assets/client/images/details-product/detail_06.jpg" alt="" width="500" height="500"/></li>
                                <li><img src="assets/client/images/details-product/detail_07.jpg" alt="" width="500" height="500"/></li>
                            </ul>
                            <ul class="nestmart-carousel quickview-nav" data-slick='{"arrows":true,"dots":false,"centerMode":false,"focusOnSelect":true,"slidesMargin":10,"slidesToShow":3,"slidesToScroll":1,"asNavFor":".quickview-for"}'>
                                <li><img src="assets/client/images/details-product/thumb_01.jpg" alt="" width="88" height="88"/></li>
                                <li><img src="assets/client/images/details-product/thumb_02.jpg" alt="" width="88" height="88"/></li>
                                <li><img src="assets/client/images/details-product/thumb_03.jpg" alt="" width="88" height="88"/></li>
                                <li><img src="assets/client/images/details-product/thumb_04.jpg" alt="" width="88" height="88"/></li>
                                <li><img src="assets/client/images/details-product/thumb_05.jpg" alt="" width="88" height="88"/></li>
                                <li><img src="assets/client/images/details-product/thumb_06.jpg" alt="" width="88" height="88"/></li>
                                <li><img src="assets/client/images/details-product/thumb_07.jpg" alt="" width="88" height="88"/></li>
                            </ul>
                        </div>
                        <div class="product-attribute">
                            <h4 class="title"><a href="#" class="pr-name">National Fresh Fruit</a></h4>
                            <div class="rating">
                                <p class="star-rating"><span class="width-80percent"></span></p>
                            </div>

                            <div class="price price-contain">
                                <ins><span class="price-amount"><span class="currencySymbol">£</span>85.00</span></ins>
                                <del><span class="price-amount"><span class="currencySymbol">£</span>95.00</span></del>
                            </div>
                            <p class="excerpt">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris vel maximus lacus. Duis ut mauris eget justo dictum tempus sed vel tellus.</p>
                            <div class="from-cart">
                                <div class="qty-input">
                                    <input type="text" name="qty12554" value="1" data-max_value="20" data-min_value="1" data-step="1"/>
                                    <a href="#" class="qty-btn btn-up"><i class="fa fa-caret-up" aria-hidden="true"></i></a>
                                    <a href="#" class="qty-btn btn-down"><i class="fa fa-caret-down" aria-hidden="true"></i></a>
                                </div>
                                <div class="buttons">
                                    <a href="#" class="btn add-to-cart-btn btn-bold">add to cart</a>
                                </div>
                            </div>

                            <div class="product-meta">
                                <div class="product-atts">
                                    <div class="product-atts-item">
                                        <b class="meta-title">Categories:</b>
                                        <ul class="meta-list">
                                            <li><a href="#" class="meta-link">Milk &amp; Cream</a></li>
                                            <li><a href="#" class="meta-link">Fresh Meat</a></li>
                                            <li><a href="#" class="meta-link">Fresh Fruit</a></li>
                                        </ul>
                                    </div>
                                    <div class="product-atts-item">
                                        <b class="meta-title">Tags:</b>
                                        <ul class="meta-list">
                                            <li><a href="#" class="meta-link">food theme</a></li>
                                            <li><a href="#" class="meta-link">organic food</a></li>
                                            <li><a href="#" class="meta-link">organic theme</a></li>
                                        </ul>
                                    </div>
                                    <div class="product-atts-item">
                                        <b class="meta-title">Brand:</b>
                                        <ul class="meta-list">
                                            <li><a href="#" class="meta-link">Fresh Fruit</a></li>
                                        </ul>
                                    </div>
                                </div>
                                <span class="sku">SKU: N/A</span>
                                <div class="nestmart-social inline add-title">
                                    <span class="fr-title">Share:</span>
                                    <ul class="socials">
                                        <li><a href="#" title="twitter" class="socail-btn"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
                                        <li><a href="#" title="facebook" class="socail-btn"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
                                        <li><a href="#" title="pinterest" class="socail-btn"><i class="fa fa-pinterest" aria-hidden="true"></i></a></li>
                                        <li><a href="#" title="youtube" class="socail-btn"><i class="fa fa-youtube" aria-hidden="true"></i></a></li>
                                        <li><a href="#" title="instagram" class="socail-btn"><i class="fa fa-instagram" aria-hidden="true"></i></a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Scroll Top Button -->
            <a class="btn-scroll-top"><i class="nestmart-icon icon-left-arrow"></i></a>

            <script src="../assets/client/js/jquery-3.4.1.min.js"></script>
            <script src="../assets/client/js/bootstrap.min.js"></script>
            <script src="../assets/client/js/jquery.countdown.min.js"></script>
            <script src="../assets/client/js/jquery.nice-select.min.js"></script>
            <script src="../assets/client/js/jquery.nicescroll.min.js"></script>
            <script src="../assets/client/js/slick.min.js"></script>
            <script src="../assets/client/js/nestmart.framework.js"></script>
            <script src="../assets/client/js/functions.js"></script>

            <script>
                $(document).ready(function () {
                    $('.nestmart-carousel').slick();
                });
            </script>
            <jsp:include page="livechat.jsp" />
    </body>

</html>
