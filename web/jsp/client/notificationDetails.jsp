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
        <title>NestMart - Notifications</title>
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
            .feedback-container {
                margin: 20px;
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 5px;
            }
            .feedback-item {
                margin-bottom: 15px;
                padding: 10px;
                border-bottom: 1px solid #eee;
            }
            .feedback-item:last-child {
                border-bottom: none;
            }
            .product-image {
                max-width: 150px;
                max-height: 150px;
            }
            .feedback-content {
                margin: 10px 0;
            }
            .feedback-date {
                color: #888;
            }
            .rating {
                color: #f5c518;
            }
            .close-btn {
                cursor: pointer;
                padding: 5px 10px;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 5px;
                margin-top: 10px;
            }
            .notification-container {
                border: 1px solid #ddd;
                padding: 10px;
                margin-bottom: 30px;
                /*background-color: #f9f9f9;*/
            }
            .row {
                margin-left: -500px;
            }
            .ok-btn {
                cursor: pointer;
                padding: 9px 40px;
                background-color: #f5c518;
                color: white;
                border: none;
                border-radius: 5px;
                margin: 1px 0% 0% 20%;
            }
        </style>
    </head>
    <body class="nestmart-body">

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

        <!-- HEADER -->
        <jsp:include page="/jsp/client/header.jsp" />

        <!-- Page Contain -->
        <div class="page-contain blog-page left-sidebar">
            <div class="container">
                <div class="row">
                    <!-- Main content -->
                    <div id="main-content" class="main-content col-lg-9 col-md-8 col-sm-12 col-xs-12">
                        <!--Single Post Contain-->
                        <div class="single-post-contain">
                            <div class="post-head">
                                <!--                                <div class="thumbnail">
                                                                    <figure><img src="assets/images/blogpost/post_thumbnail.jpg" width="870" height="635" alt=""></figure>
                                                                </div>-->
                                <h2 class="post-name">${notification.title}</h2>
                                <p class="post-archive"><b class="post-cat">${notification.notificationType}</b><span class="post-date"> / ${notification.sendDate}</span><span class="author">Send By: NestMart</span></p>
                            </div>

                            <div class="post-content">
                                <p>${notification.message}</p>
                            </div>
                            <button class="ok-btn" onclick="window.history.back();">OK</button>
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
                                <a href="#" class="logo footer-logo"><img src="../assets/client/images/NestMart_logo.png" alt="nestmart logo" width="220" height="36"/></a>
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
                                    <li><a href="#" class="payment-link"><img src="../assets/client/images/card1.jpg" width="51" height="36" alt=""/></a></li>
                                    <li><a href="#" class="payment-link"><img src="../assets/client/images/card2.jpg" width="51" height="36" alt=""/></a></li>
                                    <li><a href="#" class="payment-link"><img src="../assets/client/images/card3.jpg" width="51" height="36" alt=""/></a></li>
                                    <li><a href="#" class="payment-link"><img src="../assets/client/images/card4.jpg" width="51" height="36" alt=""/></a></li>
                                    <li><a href="#" class="payment-link"><img src="../assets/client/images/card5.jpg" width="51" height="36" alt=""/></a></li>
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
                        <li class="list-item"><a href="#"><img src="../assets/client/images/languages/us.jpg" alt="flag" width="24" height="18"/></a></li>
                        <li class="list-item"><a href="#"><img src="../assets/client/images/languages/fr.jpg" alt="flag" width="24" height="18"/></a></li>
                        <li class="list-item"><a href="#"><img src="../assets/client/images/languages/ger.jpg" alt="flag" width="24" height="18"/></a></li>
                        <li class="list-item"><a href="#"><img src="../assets/client/images/languages/jap.jpg" alt="flag" width="24" height="18"/></a></li>
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