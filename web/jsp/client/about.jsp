<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
      @font-face {
            font-family: 'Langdon';
            src: url('../assets/client/fonts/Langdon.otf') format('opentype');
        }
        body {
            font-family: 'Arial', sans-serif;
            line-height: 1.6;
            color: #333;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 0 20px;
        }
        .about-section {
            padding: 80px 0;
            background-color: #f8f9fa;
        }
        h1, h2, h3 {
            font-family: 'Langdon', sans-serif;
            color: #ff9404;
        }
        h1 {
            font-size: 48px;
            margin-bottom: 40px;
            text-align: center;
        }
        .about-content {
            opacity: 0;
            transform: translateY(20px);
            transition: opacity 0.5s, transform 0.5s;
        }
        .about-content.visible {
            opacity: 1;
            transform: translateY(0);
        }
        .feature-list {
            list-style-type: none;
            padding: 0;
        }
        .feature-item {
            display: flex;
            align-items: center;
            margin-bottom: 20px;
            opacity: 0;
            transform: translateX(-20px);
            transition: opacity 0.5s, transform 0.5s;
        }
        .feature-item.visible {
            opacity: 1;
            transform: translateX(0);
        }
        .feature-icon {
            background-color: #ff9404;
            color: white;
            width: 50px;
            height: 50px;
            border-radius: 50%;
            display: flex;
            justify-content: center;
            align-items: center;
            margin-right: 20px;
            font-size: 24px;
        }
        .about-us-section {
    font-family: 'Arial', sans-serif;
    text-align: center;
    padding: 50px;
}

#typing-text {
    font-size: 24px;
    white-space: nowrap;
    overflow: hidden;
    border-right: 2px solid;
    width: 100%; /* Adjust this as necessary */
    margin: 0 auto;
    animation: blink-caret 0.75s step-end infinite;
}

/* Blinking cursor */
@keyframes blink-caret {
    from, to { border-color: transparent; }
    50% { border-color: black; }
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
              <div class="about-section">
        <div class="container">
<div class="about-us-section">
    <h1>About Us</h1>
    <p id="typing-text"></p>
</div>
            <div class="about-content">
                <p>Welcome to <strong>Nestmart</strong>, your trusted online supermarket! We are a pioneering platform dedicated to bringing you the convenience of shopping for all your daily essentials from the comfort of your home. Our mission is to redefine the grocery shopping experience, offering a vast selection of high-quality products at competitive prices, with fast and reliable delivery to your door.</p>
                <p>At <strong>Nestmart</strong>, we believe in simplifying your life by providing a seamless, stress-free shopping experience. Whether you're looking for fresh produce, pantry staples, household items, or specialty products, our diverse range of categories ensures you'll find exactly what you need. We are constantly expanding our product offerings to cater to the evolving needs of our customers.</p>
            </div>
            <h2>Why Choose Nestmart?</h2>
            <ul class="feature-list">
                <li class="feature-item">
                    <div class="feature-icon">🛒</div>
                    <div>
                        <h3>Wide Variety</h3>
                        <p>From fresh fruits and vegetables to premium meats, snacks, and beverages, we have it all in one place.</p>
                    </div>
                </li>
                <li class="feature-item">
                    <div class="feature-icon">💰</div>
                    <div>
                        <h3>Affordable Prices</h3>
                        <p>We work directly with trusted suppliers to offer you the best prices without compromising on quality.</p>
                    </div>
                </li>
                <li class="feature-item">
                    <div class="feature-icon">🚚</div>
                    <div>
                        <h3>Fast & Reliable Delivery</h3>
                        <p>Our efficient delivery network ensures that your order arrives on time, every time.</p>
                    </div>
                </li>
                <li class="feature-item">
                    <div class="feature-icon">👥</div>
                    <div>
                        <h3>Customer-Centric Service</h3>
                        <p>Our dedicated support team is always ready to assist you, ensuring a smooth and pleasant shopping experience.</p>
                    </div>
                </li>
            </ul>
            <div class="about-content">
                <p>At <strong>Nestmart</strong>, we are committed to making your life easier with just a few clicks. Start your journey with us today and discover the future of online shopping!</p>
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
  document.addEventListener("DOMContentLoaded", function() {
        const text = "Discover the future of online shopping with Nestmart!";
        const typingText = document.getElementById('typing-text');
        let index = 0;

        function typeEffect() {
            if (index < text.length) {
                typingText.innerHTML += text.charAt(index);
                index++;
                setTimeout(typeEffect, 100);
            }
        }

        typeEffect();
    });      
    function isElementInViewport(el) {
            const rect = el.getBoundingClientRect();
            return (
                rect.top >= 0 &&
                rect.left >= 0 &&
                rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) &&
                rect.right <= (window.innerWidth || document.documentElement.clientWidth)
            );
        }

        function handleScroll() {
            const elements = document.querySelectorAll('.about-content, .feature-item');
            elements.forEach(el => {
                if (isElementInViewport(el)) {
                    el.classList.add('visible');
                }
            });
        }

        window.addEventListener('load', handleScroll);
        window.addEventListener('scroll', handleScroll);
        window.addEventListener('resize', handleScroll);</script>
    </body>

</html>
