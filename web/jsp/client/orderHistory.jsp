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
        <title>nestmart - Organic Food</title>
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
            .orange-outline-btn {
                background-color: #fff;
                color: #ff9702;
                border: 1px solid #666;
                padding: 1px;
                border-radius: 50px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 14px;
                cursor: pointer;
                transition: background-color 0.3s ease, color 0.3s ease;
                min-width: 100px;
                margin: 5px;
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
                font-size: 12px;
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
                font-size: 15px;
                font-weight: bold;
                color: #333;
            }

            .total-amount-container2 {
                display: flex;
                justify-content: right;
                align-items: center;
                /*margin-top: 15px;*/
            }

            .total-amount-label2 {
                font-size: 16px;
                margin-bottom: -3px;
                font-weight: bold;
                color: #333;
            }

            .total-amount-value {
                font-size: 22px;
                font-weight: bold;
                color: #FF6347;
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
                margin-right: 5px;
                margin-bottom: 5px;
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
                padding: 5px;
                text-transform: uppercase;
                border-radius: 50px;
                cursor: pointer;
                transition: background-color 0.3s ease, color 0.3s ease;
                min-width: 100px;
                margin: 5px;
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
            .product-item-vertical {
                display: flex;
                flex-direction: column;
                margin-bottom: 20px;
            }

            .product-item-vertical .contain-product {
                display: flex;
                flex-direction: row;
                align-items: flex-start;
            }

            .product-item-vertical .image-container {
                margin-right: 20px;
            }

            .product-item-vertical .info {
                flex-grow: 1;
            }

            .order-item {
                border: 1px solid #ddd;
                padding: 15px;
                margin-bottom: 30px;
                /*background-color: #f9f9f9;*/
            }
        </style>
    </head>
    <body class="nestmart-body">
        <!-- Preloader -->


        <jsp:include page="/jsp/client/header.jsp" />

        <!-- Page Contain -->
        <div class="page-contain">

            <!-- Main content -->
            <div id="main-content" class="main-content">
                <div class="hero-section hero-background">
                    <h1 class="page-title">My Purchase</h1>
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

                                <div class="block-item md-margin-bottom-30">
                                    <ul class="products-list nestmart-carousel nav-center-02 nav-none-on-mobile" data-slick='{"rows":1,"arrows":true,"dots":false,"infinite":false,"speed":400,"slidesMargin":0,"slidesToShow":10, "responsive":[{"breakpoint":1200, "settings":{ "slidesToShow": 3}},{"breakpoint":992, "settings":{ "slidesToShow": 3, "slidesMargin": 10}},{"breakpoint":768, "settings":{ "slidesToShow": 2, "slidesMargin":10 }}]}' >
                                        <li class="product-item">
                                            <div class="contain-product status-item">
                                                <div class="icon">
                                                    <i class="fa fa-list-alt"></i> 
                                                </div>
                                                <div class="info">
                                                    <h4 class="product-title"><a href="../client/orderHistory.htm" class="pr-name">All</a></h4>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="product-item">
                                            <div class="contain-product status-item">
                                                <div class="icon">
                                                    <i class="fa fa-clock-o"></i> 
                                                </div>
                                                <div class="info">
                                                    <h4 class="product-title"><a href="../client/orderHistory.htm?status=Pending" class="pr-name">Pending</a></h4>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="product-item">
                                            <div class="contain-product status-item">
                                                <div class="icon">
                                                    <i class="fa fa-truck"></i> 
                                                </div>
                                                <div class="info">
                                                    <h4 class="product-title"><a href="../client/orderHistory.htm?status=Confirmed" class="pr-name">Confirmed</a></h4>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="product-item">
                                            <div class="contain-product status-item">
                                                <div class="icon">
                                                    <i class="fa fa-car"></i> 
                                                </div>
                                                <div class="info">
                                                    <h4 class="product-title"><a href="../client/orderHistory.htm?status=On%20Delivering" class="pr-name">On Delivering</a></h4>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="product-item">
                                            <div class="contain-product status-item">
                                                <div class="icon">
                                                    <i class="fa fa-check-circle"></i> 
                                                </div>
                                                <div class="info">
                                                    <h4 class="product-title"><a href="../client/orderHistory.htm?status=Completed" class="pr-name">Completed</a></h4>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="product-item">
                                            <div class="contain-product status-item">
                                                <div class="icon">
                                                    <i class="fa fa-times-circle"></i> 
                                                </div>
                                                <div class="info">
                                                    <h4 class="product-title"><a href="../client/orderHistory.htm?status=Cancelled" class="pr-name">Cancelled</a></h4>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="product-item">
                                            <div class="contain-product status-item">
                                                <div class="icon">
                                                    <i class="fa fa-undo"></i> 
                                                </div>
                                                <div class="info">
                                                    <h4 class="product-title"><a href="../client/orderHistory.htm?status=Return Requested" class="pr-name">Returned</a></h4>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="product-item">
                                            <div class="contain-product status-item">
                                                <div class="icon">
                                                    <i class="fa fa-check-circle"></i>
                                                </div>
                                                <div class="info">
                                                    <h4 class="product-title"><a href="../client/orderHistory.htm?status=Approved" class="pr-name">Return Request Approved</a></h4>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="product-item">
                                            <div class="contain-product status-item">
                                                <div class="icon">
                                                    <i class="fa fa-times-circle"></i>
                                                </div>
                                                <div class="info">
                                                    <h4 class="product-title"><a href="../client/orderHistory.htm?status=Denied" class="pr-name">Return request Denied</a></h4>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="product-item">
                                            <div class="contain-product status-item">
                                                <div class="icon">
                                                    <i class="fa fa-check-circle"></i>
                                                </div>
                                                <div class="info">
                                                    <h4 class="product-title"><a href="../client/orderHistory.htm?status=Return%20Completed" class="pr-name">Return Completed</a></h4>
                                                </div>
                                            </div>
                                        </li>
                                    </ul>
                                </div>

                                <div class="product-category list-style">
                                    <div class="products-list">
                                        <div class="row">
                                            <c:forEach var="order" items="${ordersList}">
                                                <div class="order-item">
                                                    <div class="row">
                                                        <c:forEach var="orderDetail" items="${order.orderDetails}">
                                                            <div class="product-item-vertical col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                                <div class="contain-product pr-detail-layout">
                                                                    <div class="order-detail">
                                                                        <c:choose>
                                                                            <c:when test="${not empty orderDetail.images}">
                                                                                <c:set var="imageCount" value="${fn:length(orderDetail.images)}" />
                                                                                <c:forEach var="image" items="${orderDetail.images}" varStatus="status">
                                                                                    <div class="product-thumb">
                                                                                        <c:if test="${status.index == 0}">
                                                                                            <div class="image-container">
                                                                                                <a href="../client/productDetails.htm?productID=${orderDetail.product.productID}" class="link-to-product">
                                                                                                    <img src="../assets/client/images/uploads/products/${image.images}" 
                                                                                                         alt="${orderDetail.product.productName}" width="200" height="auto" class="product-thumnail"/>
                                                                                                </a>
                                                                                            </div> 
                                                                                        </c:if>
                                                                                    </div>
                                                                                </c:forEach>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                No image available
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                        <!-- Thông tin sản phẩm -->
                                                                        <div class="info">
                                                                            <b class="categories" style="text-transform: uppercase">${order.orderStatus}</b>
                                                                            <h4 class="product-title"><a href="../client/productDetails.htm?productID=${orderDetail.product.productID}" class="pr-name">${orderDetail.product.productName}</a></h4>
                                                                            <div class="price">
                                                                                <div class="total-amount-container">
                                                                                    <span class="price-amount">x${orderDetail.quantity}</span><br/>
                                                                                    <ins><span class="price-amount">$${orderDetail.unitPrice}</span></ins>
                                                                                </div>
                                                                            </div>
                                                                            <div class="buttons">
                                                                                <c:if test="${order.orderStatus == 'Pending' || order.orderStatus == 'Confirmed'}">
                                                                                    <a href="clientboard.htm" class="orange-outline-btn">Contact Seller</a>
                                                                                </c:if>
                                                                                <c:if test="${order.orderStatus == 'On Delivering'}">
                                                                                    <a href="clientboard.htm" class="orange-outline-btn">Contact Seller</a>
                                                                                </c:if>
                                                                                <c:if test="${order.orderStatus eq 'Completed'}">
                                                                                    <c:if test="${orderDetail.hasFeedback == false}">
                                                                                        <a href="../client/feedback.htm?productID=${orderDetail.product.productID}" class="btn add-to-cart-btn">Rate</a>
                                                                                    </c:if>
                                                                                    <a href="../client/productDetails.htm?productID=${orderDetail.product.productID}" class="btn add-to-cart-btn">Buy Again</a>
                                                                                    <a href="clientboard.htm" class="orange-outline-btn">Contact Seller</a>
                                                                                    <c:if test="${orderDetail.hasFeedback == true}">
                                                                                        <a href="../client/viewFeedback.htm?feedbackID=${orderDetail.feedbackID}" class="orange-outline-btn">View Shopping Rating</a>
                                                                                    </c:if>
                                                                                </c:if>
                                                                                <c:if test="${order.orderStatus == 'Cancelled'}">
                                                                                    <a href="../client/productDetails.htm?productID=${orderDetail.product.productID}" class="btn add-to-cart-btn">Buy Again</a>
                                                                                    <a href="clientboard.htm" class="orange-outline-btn">Home page</a>
                                                                                </c:if>
                                                                                <c:if test="${order.orderStatus == 'Return Requested'}">
                                                                                    <a href="../client/productDetails.htm?productID=${orderDetail.product.productID}" class="btn add-to-cart-btn">Buy Again</a>
                                                                                    <a href="clientboard.htm" class="orange-outline-btn">Contact Seller</a>
                                                                                </c:if>
                                                                                <c:if test="${order.orderStatus == 'Approved' || order.orderStatus == 'Denied' || order.orderStatus == 'Return Completed'}">
                                                                                    <div class="return-status">
                                                                                        <c:choose>
                                                                                            <c:when test="${order.orderStatus == 'Approved'}">
                                                                                                <h4>Return Request Status: ${order.returnRequestStatus}</h4>
                                                                                                <p>Reason: ${order.returnRequestReason}</p>
                                                                                                <p>Request Date: ${order.returnRequestDate}</p>
                                                                                            </c:when>
                                                                                            <c:when test="${order.orderStatus == 'Denied'}">
                                                                                                <h4>Return Request Status: ${order.returnRequestStatus}</h4>
                                                                                                <p>Reason: ${order.returnRequestReason}</p>
                                                                                                <p>Request Date: ${order.returnRequestDate}</p>
                                                                                            </c:when>
                                                                                            <c:when test="${order.orderStatus == 'Return Completed'}">
                                                                                                <h4>Return Completed</h4>
                                                                                                <a href="clientboard.htm" class="orange-outline-btn">Contact Seller</a>
                                                                                                <a href="../client/productDetails.htm?productID=${orderDetail.product.productID}" class="btn add-to-cart-btn">Buy Again</a>
                                                                                            </c:when>
                                                                                        </c:choose>
                                                                                    </div>
                                                                                </c:if>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </c:forEach>
                                                        <div class="product-item-vertical contain-product pr-detail-layout col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-left: 21%">
                                                            <div class="info">
                                                                <div class="total-amount-container2">
                                                                    <span class="total-amount-label2">Order Total:</span> &nbsp;
                                                                    <span class="total-amount-value">$${order.totalAmount}</span> 
                                                                </div>
                                                                <small style="margin-left: 81%">${order.formattedOrderDate}</small>
                                                            </div>
                                                        </div>
                                                        <c:if test="${order.orderStatus eq 'Completed'}">
                                                            <div class="return-order-form col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-top: 10px; margin-left: 28%">
                                                                <form action="../client/returnOrder.htm" method="post" onsubmit="return confirmReturn(this);">
                                                                    <input type="hidden" name="orderId" value="${order.orderID}" />
                                                                    <textarea name="reason" placeholder="Reason for return" required cols="75" rows="3"></textarea>
                                                                    <button type="submit" class="orange-outline-btn">Return Order</button>
                                                                </form>
                                                            </div>
                                                        </c:if>

                                                        <c:if test="${order.orderStatus eq 'Pending'}">
                                                            <div style="margin: 0% 0% 0% 60%;">
                                                                <a href="updateOrder.htm?orderID=${order.orderID}" class="btn add-to-cart-btn">Cancel Order</a>
                                                            </div>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div class="nestmart-panigations-block">
                                        <ul class="panigation-contain">
                                            <li><span class="current-page">1</span></li>
                                            <li><a href="#" class="link-page">2</a></li>
                                            <li><a href="#" class="link-page">3</a></li>
                                            <li><span class="sep">....</span></li>
                                            <li><a href="#" class="link-page">20</a></li>
                                            <li><a href="#" class="link-page next"><i class="fa fa-angle-right" aria-hidden="true"></i></a></li>
                                        </ul>
                                    </div>
                                </div>

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
