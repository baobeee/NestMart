<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        .product-image-container {
    text-align: center;
}

.product-image {
    width: 80%;
    max-width: 400px;
    height: auto;
}

.product-thumbnails img {
    width: 80px;
    height: 80px;
    cursor: pointer;
    margin: 5px;
}

.quantity-control {
    display: flex;
    align-items: center;
    gap: 10px;
}

.quantity-control input {
    width: 50px;
    text-align: center;
}

.add-to-cart-btn {
    background-color: #ff0000;
    color: white;
    padding: 10px 20px;
    border: none;
    cursor: pointer;
    font-size: 16px;
    margin-top: 20px;
}
.product-thumbnails img {
    width: 80px;
    height: 80px;
    cursor: pointer;
    margin: 5px;
    border: 2px solid transparent; /* Viền mặc định không có màu */
    transition: border-color 0.3s ease; /* Hiệu ứng chuyển đổi mượt khi thay đổi viền */
}

.product-thumbnails img.active-thumbnail {
    border-color: red; /* Màu viền khi ảnh được chọn */
}
 h1, h2, h3, h4, h5, h6 {
            font-family: 'Roboto', sans-serif;
            color: #333;
            margin-bottom: 15px;
        }

        h1 {
            font-size: 32px;
        }

        h2 {
            font-size: 28px;
        }

        h3 {
            font-size: 24px;
        }

        button {
            background-color: #ff9404;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #e08900;
        }

        input[type="text"], input[type="password"], input[type="email"], select {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #ff9404;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #e08900;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            font-size: 16px;
            text-align: left;
        }

        table th, table td {
            padding: 12px;
            border: 1px solid #ddd;
        }

        table th {
            background-color: #f0f0f0;
        }

        table tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        a {
            color: #ff9404;
            text-decoration: none;
        }

        a:hover {
            color: #e08900;
        }

        footer a {
            color: #ff9404;
            text-decoration: none;
        }

        footer a:hover {
            color: #e08900;
        }

        .products-list {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 31px;
            padding: 40px;
        }

        @media (max-width: 1200px) {
            .products-list {
                grid-template-columns: repeat(3, 1fr);
            }
        }

        @media (max-width: 768px) {
            .products-list {
                grid-template-columns: repeat(2, 1fr);
            }
        }

        @media (max-width: 576px) {
            .products-list {
                grid-template-columns: 1fr;
            }
        }

        .product-item {
            list-style: none;
            padding: 10px;
            background-color: #f9f9f9;
            border-radius: 8px;
            transition: box-shadow 0.3s ease;
        }

        .product-item:hover {
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .product-item {
            border: 1px solid #ddd;
            padding: 10px;
            background-color: #fff;
            border-radius: 5px;
            transition: box-shadow 0.3s ease;
        }

        .product-item:hover {
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .btnSeeOffers {
            background-color: #ff9404;
            display: inline-block;
            font-size: 20px;
            line-height: 1;
            color: #ffffff;
            min-width: 100px;
            text-align: center;
            border-radius: 999px;
            font-weight: 700;
            text-transform: uppercase;
            padding: 12px 20px;
            text-decoration: none;
            transition: background-color 0.3s ease;
            margin-top: -30px;
            margin-bottom: 150px;
        }

        .btnSeeOffers:hover {
            background-color: #444444;
            color: white;
        }
    </style>
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
        <div class="page-contain">

            <!-- Main content -->
            <div id="main-content" class="main-content">

                <!--Block 04: Product Tab-->
                <div class="product-tab z-index-20 sm-margin-top-59px xs-margin-top-20px">
                    <div class="container">
                        <form action="../client/addToCart.htm" method="post">
                            <div class="container product-details">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="product-image-container">
                                            <img id="mainImage" 
                                                 src="../assets/client/images/uploads/products/${not empty product.images ? product.images[0].images : 'default.jpg'}" 
                                                 alt="${product.productName}" class="product-image img-fluid">
                                            <div class="product-thumbnails">
                                                <c:forEach var="image" items="${product.images}">
                                                    <img src="../assets/client/images/uploads/products/${image.images}" 
                                                         alt="${product.productName}" class="thumbnail-image" 
                                                         onclick="changeImage(this.src)">
                                                </c:forEach>
                                            </div>
                                            <input type="hidden" name="productImage" value="${product.images[0].images}">
                                        </div>
                                    </div>


                                    <div class="col-md-6">
                                        <h1 style="font-weight: bold">${product.productName}</h1>
                                        <div class="product-info">
                                            <p>
                                                <span class="badge bg-success">
                                                    ${averageRating} 
                                                    <img src="../assets/client/images/star-16.png" alt="Feedback Star">
                                                </span> 
                                                (${feedbackCount} feedbacks)
                                            </p>

                                            <div class="product-price-container">
                                                <c:choose>
                                                    <c:when test="${product.discount > 0}">
                                                        <p>
                                                            <span class="original-price">$${product.unitPrice}</span>
                                                            <span class="discounted-price">$${product.discount}</span>
                                                            <input type="hidden" name="productPrice" value="${product.discount}">
                                                        </p>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <p class="product-price">$${product.unitPrice}</p>
                                                        <input type="hidden" name="productPrice" value="${product.unitPrice}">
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>

                                            <p class="product-description">${product.productDescription}</p>
                                        </div>
                                        <input type="hidden" name="productId" value="${product.productID}">
                                        <input type="hidden" name="productName" value="${product.productName}">
                                        <div class="from-cart">
                                            <p class="quantity-text">Quantity:</p>
                                            <div class="qty-input">
                                                <button type="button" class="qty-btn btn-down" onclick="decreaseQuantity()">-</button>
                                                <input type="text" id="quantity" name="quantity" class="form-control quantity-input" value="1" readonly>
                                                <button type="button" class="qty-btn btn-up" onclick="increaseQuantity()">+</button>
                                            </div>
                                        </div>


                                        <div class="mt-4">
                                            <button type="submit" class="btnAddcart">Add To Cart</button>
                                        </div>
                                        <c:if test="${param.success == 'true'}">
                                            <div class="alert alert-success" style="margin-top: 30px" role="alert">
                                                Product added to cart successfully!
                                            </div>
                                        </c:if>

                                    </div>
                                </div>
                            </div>
                                <div class="product-details-section mt-4">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th colspan="2" style="text-align: center">Product Details</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${categoryDetailsList}" var="detail">
                                                <tr>
                                                    <td class="attribute-name">${detail.attributeName}</td>
                                                    <td>${detail.attributeValue}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>

                        </form>
                        <div class="feedback-section">
                            <h1>Customer Feedbacks</h1>
                            <div class="filter-section">
                                <form method="get" action="${pageContext.request.contextPath}/client/productDetails.htm">
                                    <input type="hidden" name="productID" value="${product.productID}">

                                    <div class="star-group" role="group">
                                        <button type="submit" name="starFilter" value="0" class="btn btn-outline-primary">
                                            All Ratings
                                        </button>
                                        <button type="submit" name="starFilter" value="1" class="btn btn-outline-primary">
                                            1 <img src="../assets/client/images/star-16.png" alt="1 Star">
                                        </button>
                                        <button type="submit" name="starFilter" value="2" class="btn btn-outline-primary">
                                            2 <img src="../assets/client/images/star-16.png" alt="2 Stars">
                                        </button>
                                        <button type="submit" name="starFilter" value="3" class="btn btn-outline-primary">
                                            3 <img src="../assets/client/images/star-16.png" alt="3 Stars">
                                        </button>
                                        <button type="submit" name="starFilter" value="4" class="btn btn-outline-primary">
                                            4 <img src="../assets/client/images/star-16.png" alt="4 Stars">
                                        </button>
                                        <button type="submit" name="starFilter" value="5" class="btn btn-outline-primary">
                                            5 <img src="../assets/client/images/star-16.png" alt="5 Stars">
                                        </button>

                                    </div>
                                </form>
                            </div>

                            <c:choose>
                                <c:when test="${not empty feedbacks}">
                                    <ul class="list-group">
                                        <c:forEach items="${feedbacks}" var="feedback">
                                            <li class="list-group-item">
                                                <p><strong>${feedback.customerName}</strong> rated: 
                                                    <span class="badge bg-success">${feedback.rating} 
                                                        <img src="../assets/client/images/star-16.png" alt="Rating Star">
                                                    </span>
                                                </p>
                                                <p>${feedback.feedbackContent}</p>
                                                <p class="text-muted"><small>${feedback.feedbackDate}</small></p>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </c:when>
                                <c:otherwise>
                                    <p>No feedbacks available for this product.</p>
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>



                    </div>
                </div>
            </div>

             <div class="product-tab z-index-20 sm-margin-top-59px xs-margin-top-20px">
                    <div class="container">
                        <div class="nestmart-title-box slim-item">
                            <h3 class="main-title">Another Products</h3>
                        </div>
                        <div id="tab01_1st" class="tab-contain active">
                            <c:if test="${listProducts.isEmpty()}">
                                <p>No products found.</p>
                            </c:if>

                            <c:if test="${!listProducts.isEmpty()}">
                                <ul class="products-list">
                                    <c:forEach var="product" items="${listProducts}">
                                        <li class="product-item">
                                            <div class="contain-product layout-default">
                                                <div class="product-thumb">
                                                    <c:choose>
                                                        <c:when test="${not empty product.images}">
                                                            <c:forEach var="image" items="${product.images}" varStatus="status">
                                                                <c:if test="${status.index == 0}">
                                                                    <div class="image-container">
                                                                        <a href="../client/productDetails.htm?productID=${product.productID}" class="link-to-product">
                                                                            <img src="../assets/client/images/uploads/products/${image.images}" 
                                                                                 alt="${product.productName}" width="250" height="250" class="product-thumbnail"/>
                                                                        </a>
                                                                        <c:if test="${product.images.size() > 1}">
                                                                            <span class="image-count">+${product.images.size() - 1}</span>
                                                                        </c:if>
                                                                    </div>
                                                                </c:if>
                                                            </c:forEach>
                                                        </c:when>
                                                        <c:otherwise>
                                                            No image available
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                                <div class="info">
                                                    <h4 class="product-title">
                                                        <a href="../client/productDetails.htm?productID=${product.productID}" class="pr-name">${product.productName}</a>
                                                    </h4>
                                                    <div class="price">
                                                        <div class="product-price-container">
                                                            <c:choose>
                                                                <c:when test="${product.discount > 0}">
                                                                    <p>
                                                                        <span class="original-price1">${product.unitPrice}$</span>
                                                                        <span class="discounted-price1">${product.discount}$</span>
                                                                    </p>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <p class="original-price2">${product.unitPrice}$</p>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <p>
                                                                <c:if test="${product.totalQuantitySold > 0}">
                                                                <div style="display: flex; align-items: center; justify-content: center; background-color: #f8f9fa; padding: 10px; border-radius: 5px;">
                                                                    <span class="badge bg-success" style="margin-right: 10px;">
                                                                        <c:choose>
                                                                            <c:when test="${product.averageRating > 0}">
                                                                                <fmt:formatNumber value="${product.averageRating}" type="number" maxFractionDigits="1" />
                                                                                <img src="../assets/client/images/star-16.png" alt="Feedback Star" style="vertical-align: middle;">
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                No feedback
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </span>
                                                                    <div style="width: 1px; height: 30px; background-color: #ccc; margin: 0 10px;"></div>
                                                                    <span class="badge bg-light">Sold: ${product.totalQuantitySold}</span>
                                                                </div>
                                                            </c:if>
                                                            </p>

                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                          
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
                        <li><img src="../assets/client/images/details-product/detail_01.jpg" alt="" width="500" height="500"/></li>
                        <li><img src="../assets/client/images/details-product/detail_02.jpg" alt="" width="500" height="500"/></li>
                        <li><img src="../assets/client/images/details-product/detail_03.jpg" alt="" width="500" height="500"/></li>
                        <li><img src="../assets/client/images/details-product/detail_04.jpg" alt="" width="500" height="500"/></li>
                        <li><img src="../assets/client/images/details-product/detail_05.jpg" alt="" width="500" height="500"/></li>
                        <li><img src="../assets/client/images/details-product/detail_06.jpg" alt="" width="500" height="500"/></li>
                        <li><img src="../assets/client/images/details-product/detail_07.jpg" alt="" width="500" height="500"/></li>
                    </ul>
                    <ul class="nestmart-carousel quickview-nav" data-slick='{"arrows":true,"dots":false,"centerMode":false,"focusOnSelect":true,"slidesMargin":10,"slidesToShow":3,"slidesToScroll":1,"asNavFor":".quickview-for"}'>
                        <li><img src="../assets/client/images/details-product/thumb_01.jpg" alt="" width="88" height="88"/></li>
                        <li><img src="../assets/client/images/details-product/thumb_02.jpg" alt="" width="88" height="88"/></li>
                        <li><img src="../assets/client/images/details-product/thumb_03.jpg" alt="" width="88" height="88"/></li>
                        <li><img src="../assets/client/images/details-product/thumb_04.jpg" alt="" width="88" height="88"/></li>
                        <li><img src="../assets/client/images/details-product/thumb_05.jpg" alt="" width="88" height="88"/></li>
                        <li><img src="../assets/client/images/details-product/thumb_06.jpg" alt="" width="88" height="88"/></li>
                        <li><img src="../assets/client/images/details-product/thumb_07.jpg" alt="" width="88" height="88"/></li>
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
                                                    function increaseQuantity() {
                                                        let input = document.getElementById('quantity');
                                                        input.value = parseInt(input.value) + 1;
                                                    }

                                                    function decreaseQuantity() {
                                                        let input = document.getElementById('quantity');
                                                        if (parseInt(input.value) > 1) {
                                                            input.value = parseInt(input.value) - 1;
                                                        }
                                                    }

                                                    function changeImage(imageSrc) {
                                                        document.getElementById("mainImage").src = imageSrc;
                                                    }



    </script>
        <jsp:include page="livechat.jsp" />
</body>

</html>
