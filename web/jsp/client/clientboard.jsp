<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

                <!--Block 01: Vertical Menu And Main Slide-->
                <div class="container">

                    <div class="row">


                        <div class="main-slide block-slider nav-change hover-main-color type02">
                            <ul class="nestmart-carousel" data-slick='{"arrows": true, "dots": false, "slidesMargin": 0, "slidesToShow": 1, "infinite": true, "speed": 800}' >
                                <li>
                                    <div class="slide-contain slider-opt04__layout01 light-version first-slide">
                                        <div class="media"></div>
                                        <div class="text-content">
                                            <i class="first-line">Fresh Fruits & Vegetables</i>
                                            <h3 class="second-line">Nature's Best at Your Fingertips</h3>
                                            <p class="third-line">Explore our wide selection of organic fruits and vegetables, sourced directly from local farms to ensure maximum freshness and quality.</p>
                                        </div>

                                    </div>
                                </li>
                                <li>
                                    <div class="slide-contain slider-opt04__layout01 light-version second-slide">
                                        <div class="media"></div>
                                        <div class="text-content">
                                            <i class="first-line">Convenient Grocery Delivery</i>
                                            <h3 class="second-line">Shop from Home, Enjoy Freshness</h3>
                                            <p class="third-line">With our grocery delivery service, you can shop for your favorite products from the comfort of your home and have them delivered right to your doorstep.</p>
                                        </div>

                                </li>
                                <li>
                                    <div class="slide-contain slider-opt04__layout01 light-version third-slide">
                                        <div class="media"></div>
                                        <div class="text-content">
                                            <i class="first-line">Healthy Eating Made Easy</i>
                                            <h3 class="second-line">Choose Wellness with Every Bite</h3>
                                            <p class="third-line">Our supermarket offers a variety of healthy options, including gluten-free, vegan, and organic products, making it easier for you to maintain a balanced diet.</p>
                                        </div>

                                    </div>
                                </li>
                            </ul>
                        </div>

                    </div>

                </div>

                <!--Block 02: Banners-->  <div class="product-tab z-index-20 sm-margin-top-59px xs-margin-top-20px">
                    <div class="container">
                        <div class="nestmart-title-box slim-item">
                            <span class="subtitle">All the best item for You</span>
                            <h3 class="main-title">Our Products</h3>
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
                            <div class="text-center" style="margin-top: 20px;">
                                <a href="product.htm" class="btnSeeOffers">
                                    SEE MORE
                                </a>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="banner-block sm-margin-bottom-57px xs-margin-top-80px sm-margin-top-30px">
                    <div class="container">
                        <ul class="nestmart-carousel nav-center-bold nav-none-on-mobile" data-slick='{"rows":1,"arrows":true,"dots":false,"infinite":false,"speed":400,"slidesMargin":30,"slidesToShow":3, "responsive":[{"breakpoint":1200, "settings":{ "slidesToShow": 3}},{"breakpoint":992, "settings":{ "slidesToShow": 2}},{"breakpoint":768, "settings":{ "slidesToShow": 2}}, {"breakpoint":500, "settings":{ "slidesToShow": 1}}]}'>
                            <li>
                                <div class="nestmart-banner nestmart-banner__style-08">
                                    <div class="banner-contain">
                                        <div class="media">
                                            <a href="#" class="bn-link"><img src="../assets/client/images/home-04/bn_style08.png" width="193" height="185" alt=""/></a>
                                        </div>
                                        <div class="text-content">
                                            <span class="text1">Sumer Fruit</span>
                                            <b class="text2">100% Pure Natural Fruit Juice</b>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="nestmart-banner nestmart-banner__style-09">
                                    <div class="banner-contain">
                                        <div class="media">
                                            <a href="#" class="bn-link"><img src="../assets/client/images/home-04/bn_style09.png" width="191" height="185" alt=""/></a>
                                        </div>
                                        <div class="text-content">
                                            <span class="text1">California</span>
                                            <b class="text2">Fresh Fruit</b>
                                            <span class="text3">Association</span>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="nestmart-banner nestmart-banner__style-10">
                                    <div class="banner-contain">
                                        <div class="media">
                                            <a href="#" class="bn-link"><img src="../assets/client/images/home-04/bn_style10.png" width="185" height="185" alt=""/></a>
                                        </div>
                                        <div class="text-content">
                                            <span class="text1">Naturally fresh taste</span>
                                            <p class="text2">With <span>25% Off</span> All Teas</p>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>

                <!--Block 03: Categories-->
                <!--Block 04: Product Tab-->
                <div class="product-tab z-index-20 sm-margin-top-59px xs-margin-top-20px">
                    <div class="container">
                        <div class="nestmart-title-box slim-item">
                            <span class="subtitle">All the best item for You</span>
                            <h3 class="main-title">Our Products</h3>
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
                            <div class="text-center" style="margin-top: 20px;">
                                <a href="product.htm" class="btnSeeOffers">
                                    SEE MORE
                                </a>
                            </div>
                        </div>
                    </div>
                </div>


                <!--Block 05: Banner Promotion-->
                <div class="banner-promotion-04 xs-margin-top-50px sm-margin-top-40px">
                    <div class="nestmart-banner promotion4 nestmart-banner__promotion4 v2">
                        <div class="container">
                            <div class="banner-contain">
                                <div class="media">
                                    <div class="img-moving position-1">
                                        <a href="#" class="banner-link"><img src="../assets/client/images/home-04/bn_promotion-child01-2.png" width="780" height="450" alt="img msv"/></a>
                                    </div>
                                    <div class="img-moving position-2">
                                        <img src="../assets/client/images/home-04/bn_promotion-child02-2.png" width="149" height="139" alt="img msv"/>
                                    </div>
                                </div>
                                <div class="text-content">
                                    <span class="sub-line">Special Offer!</span>
                                    <b class="first-line">Special discount<br/>every week!</b>

                                    <p class="buttons">
                                        <a href="discount.htm" class="btn btn-bold">See Offer Now!</a>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!--Block 06: Advance Box-->
                <!--Block 06: WHO WE ARE-->
                <div class="who-we-are xs-margin-top-50px sm-margin-top-40px">
                    <div class="container">
                        <div class="nestmart-title-box">
                            <h3 class="main-title">WHO WE ARE</h3>
                            <span class="subtitle">Discover the Essence of Our Supermarket</span>
                        </div>
                        <p style="margin-top: 20px">
                            Welcome to <strong>NestMart</strong>, your ultimate supermarket destination where we believe shopping should be a delightful experience! Founded over a decade ago, we have continually evolved to meet the needs of our diverse customer base while maintaining our core values of quality, affordability, and exceptional service. 

                            Our supermarket is designed with you in mind, offering an extensive range of products that cater to your every need. From farm-fresh produce and organic products to everyday household essentials and gourmet items, we strive to bring you the best selection at competitive prices. We understand that quality matters, which is why we source our products from trusted local suppliers and renowned brands, ensuring that you receive only the finest goods.

                            At <strong>NestMart</strong>, we are committed to sustainability and community support. We actively engage with local farmers and producers, fostering relationships that benefit both our customers and the community. By choosing to shop with us, you are supporting local businesses and contributing to a healthier economy. We believe in the importance of making responsible choices, and we aim to reduce our environmental impact by offering eco-friendly products and minimizing waste in our operations.

                            Our dedicated team is passionate about serving you. From the moment you walk through our doors, you’ll be greeted by friendly faces and knowledgeable staff ready to assist you in finding exactly what you need. We take pride in our customer service and are always looking for ways to enhance your shopping experience. Whether you have questions about our products, need help navigating our aisles, or are looking for recipe inspiration, we are here to help!
                            In addition to our wide selection of products, we frequently host events and promotions to engage with our customers and foster a sense of community. From cooking demonstrations and tastings to seasonal sales and special discounts, we want to ensure that every visit to <strong>NestMart</strong> is a memorable one. 

                            Our commitment to excellence doesn’t stop at our products; we also prioritize creating a welcoming and enjoyable shopping environment. Our store layout is designed for convenience, making it easy for you to find what you need quickly and efficiently. We continuously seek feedback from our customers to improve our offerings and services, ensuring that we meet and exceed your expectations.

                            Thank you for choosing <strong>NestMart</strong> as your trusted supermarket. We invite you to explore our aisles, discover new products, and enjoy an unparalleled shopping experience. Together, let’s build a healthier, happier community, one shopping trip at a time!
                        </p>
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
            </div
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
    </body>

</html>