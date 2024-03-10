<%-- 
    Document   : cartContact1
    Created on : Feb 22, 2024, 9:52:34 PM
    Author     : Thanh Hai
--%>

<!DOCTYPE html>
<html lang="en"
      xmlns:h="http://java.sun.com/jsf/html"
      xmlns:f="http://java.sun.com/jsf/core">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <script src="https://kit.fontawesome.com/33f9434037.js" crossorigin="anonymous"></script>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link
            href="https://fonts.googleapis.com/css2?family=Merriweather&family=Open+Sans:wght@300&family=Roboto:wght@100&display=swap"
            rel="stylesheet">
        <link
            href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
            rel="stylesheet">
        <link rel="stylesheet" href="css/CartContactcss/content.css">
        <link rel="stylesheet" href="css/CartContactcss/grid-bass.css">
        <link rel="stylesheet" href="css/CartContactcss/navbar-footer-body.css">
        <link rel="stylesheet" href="css/CartContactcss/side-bar.css">

    </head>

    <body>
        <section class="header">
            <div class="grid wide menu">
                <nav class="navbar">
                    <ul class="navbar-list">
                        <li class="navbar-list-item">
                            <a href="home">Home</a>
                        </li>
                        <li class="navbar-list-item header__navbar-user">
                            <a href="ProductListController">Shop</a>
                            <ul class="navbar-blog-list">
                                <li class="navbar-blog-list-item">
                                    <a class="navbar-blog-item-link" href="ProductListController">Products</a>
                                </li>

                                <li class="navbar-blog-list-item">
                                    <a class="navbar-blog-item-link" href="url">Cart</a>
                                </li>
                                <li class="navbar-blog-list-item">
                                    <a class="navbar-blog-item-link" href="url">Checkout</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                    <a href="home" class="navbar_logo">
                        <img src="./img/logo-img.png" alt="anh logo" />
                    </a>
                    <ul class="navbar-list">
                        <li class="navbar-list-item header__navbar-user">
                            <a href="Blog">Blog</a>
                            <ul class="navbar-blog-list">
                                <li class="navbar-blog-list-item">
                                    <a class="navbar-blog-item-link" href="url">Blog List</a>
                                </li>
                                <li class="navbar-blog-list-item">
                                    <a class="navbar-blog-item-link" href="url">Blog Single</a>
                                </li>
                            </ul>
                        </li>
                        <c:if test="${sessionScope.acc==null}">
                            <li class="navbar-list-item">
                                <a href="LoginController">Login</a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.acc!=null}">
                            <li class="navbar-list-item header__navbar-user">
                                <!--                            <img src="./assest/img/${sessionScope.acc.accImg}" class="account-logo" alt="123"/>-->
                                <a href="#" class="account-name">${sessionScope.acc.username}</a>
                                <ul class="header__navbar-user-menu">
                                    <li class="header__navbar-user-item">
                                        <a href="userprofile?id=${sessionScope.acc.getAccID()}"
                                           class="header__navbar-user-item-link">Profile</a>
                                    </li>
                                    <li class="header__navbar-user-item header__navbar-user-item--separate">
                                        <a href="logout" class="header__navbar-user-item-link">Logout</a>
                                    </li>
                                </ul>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </section>

        <section class="content">
            <div class="side-bar-area">
                <div class="side-bar">
                    <div class="search-box">
                        <form class="search-form" action="ProductListController">
                            <input class="text-input" type="text" placeholder="Search product..." value="${search}"
                                   name="search">
                            <input type="hidden" name="action" value="search">
                            <button type="submit" class="search-btn"><i class="fa-solid fa-chevron-up fa-rotate-90"
                                                                        style="color: #ffffff;"></i></button>
                        </form>
                    </div>
                    <div class="category">
                        <h3>Categories</h3>
                        <c:forEach var="c" items="${cateList}">
                            <span><a href="ProductListController?cate=${c.getCateName()}&action=cate">${c.getCateName()}</a>
                                (${c.getQuantity()})</span>
                            </c:forEach>
                    </div>
                    <div class="star-rating-sider">
                        <h3>Rating</h3>
                        <div class="rating-list">
                            <a href="ProductListController?rating=5&action=rate">
                                <div class="five-stars">
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                </div>
                            </a>
                            <a href="ProductListController?rating=4&action=rate">
                                <div class="four-stars">
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-regular fa-star"></i><span>or more</span>
                                </div>
                            </a>
                            <a href="ProductListController?rating=3&action=rate">
                                <div class="three-stars">
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-regular fa-star"></i>
                                    <i class="fa-regular fa-star"></i><span>or more</span>
                                </div>
                            </a>
                            <a href="ProductListController?rating=2&action=rate">
                                <div class="two-stars">
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-regular fa-star"></i>
                                    <i class="fa-regular fa-star"></i>
                                    <i class="fa-regular fa-star"></i><span>or more</span>
                                </div>
                            </a>
                            <a href="ProductListController?rating=1&action=rate">
                                <div class="one-star">
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-regular fa-star"></i>
                                    <i class="fa-regular fa-star"></i>
                                    <i class="fa-regular fa-star"></i>
                                    <i class="fa-regular fa-star"></i><span>or more</span>
                                </div>
                            </a>
                        </div>
                    </div>
                    <form class="price-form" action="ProductListController">
                        <div class="pricing-sider">
                            <h3>Pricing</h3>
                            <div class="price-input">
                                <span>From</span> <input type="number" value="${minPrice}" name="minPrice"> <span>To</span>
                                <input type="number" value="${maxPrice}" name="maxPrice">
                            </div>
                        </div>
                        <div class="search-btn-box"><button type="submit" class="price-search">Search</button>
                        </div>
                        <input type="hidden" name="action" value="price">
                    </form>
                    <div class="side-bar-products">
                        <h3>Lasted Products</h3>
                        <c:forEach var="p3" items="${newest3products}">
                            <div class="side-product">
                                <div class="side-product-img">
                                    <a href="ProductDetailController?productId=${p3.getProductID()}">
                                        <img src="./ProductListImg/${p3.getImage()}" alt="">
                                    </a>
                                </div>
                                <div class="side-product-content">
                                    <a style="text-decoration: none; font-family: 'Open Sans', sans-serif; font-size: 1.2rem; color: black;"
                                       href="ProductDetailController?productId=${p3.getProductID()}">
                                        <h2 style="margin: 0">${p3.getProductName()}</h2>
                                    </a>
                                    <span style="margin-bottom: 5px" class="cate-title">${p3.getCateName()}</span>
                                    <div style="margin-bottom: 5px" class="star-rating">
                                        <c:forEach begin="1" end="${p3.getRating()}" step="1">
                                            <i style="color: #9fe252;" class="fa-solid fa-star"></i>
                                        </c:forEach>
                                        <c:forEach begin="${p3.getRating()}" end="4" step="1">
                                            <i class="fa-sharp fa-regular fa-star" style="color: #000000;"></i>
                                        </c:forEach>
                                    </div>
                                    <div class="side-bar-product-price">
                                        <span class="oldprice " style=" text-decoration: line-through;
                                              color: #9e9e9e;
                                              padding-right: 5px;">$${p3.getMainPrice()}</span>
                                        <span class="onprice">$${p3.getSalePrice()}</span>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>

            <div class="right-content">
                <table class="cart-table">
                    <thead>
                        <tr>
                            <th></th>
                            <th></th>
                            <th>Product name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="cart" items="${requestScope.ListCart}" >

                            <tr>
                                <td class="td-id">425</td>
                                <td class="td-img">
                                    <div class="pro-img">
                                        <img src="img/${cart.image}"
                                             alt="Image">
                                    </div>
                                </td>
                                <td class="td-name">${cart.productName}</td>
                                <td class="td-price">$${cart.sellPrice}</td>
                                <td class="td-quantity">
                                    <input type="number" value="${cart.quantity}" name="" id="" readonly>
                                </td>
                                <td class="td-total">${cart.sellPrice*cart.quantity}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                
                    <div class="contact-container">
                        <div class="contact-input-box">
                            <button style="margin-top: 2rem;" class="checkoutBtn" id="changeBtn1" onclick="window.location.href = 'CartDetailController'">
                                <span>Change</span>
                            </button>
                            <form method="POST" action="cart-contact">

                            <h3>Billing Details</h3>
                            <div class="input-box">
                                <span>Full Name</span>
                                <input class="fullName" type="text" name="name" id="" value="${requestScope.acc.username}"required >
                            </div>
                            <div class="input-box">
                                <span>Email</span>
                                <input class="fullName" type="text" name="email" id="email" value="${requestScope.acc.email}" readonly>

                            </div>
                            <div class="input-box">
                                <div class="child-input">
                                    <span>Gender</span>
                                    <select class="gender" name="gender" id="">
                                        <option value="1">Male</option>
                                        <option value="0">Female</option>
                                    </select>
                                </div>
                                <div class="child-input">
                                    <span>Phone</span>
                                    <input type="text" name="phone" value="${requestScope.acc.phone}"

                                           required pattern="\d{10}" title="Please enter 10 digits "/>

                                </div>
                            </div>
                            <div class="input-box">
                                <span>Address</span>
                                <input type="text" name="address" id="" value="${requestScope.acc.address}" required="">
                            </div>
                            <div class="input-box">
                                <span>Note</span>
                                <input type="text" name="note" id="">
                            </div>
                            <div class="input-box" style="display: none;">
                                <span>Note</span>
                                <input type="number" name="cartTotals" id="" value="${requestScope.cartTotals}">
                            </div>


                        </div>
                        <div class="cart-total">
                            <table class="total-table">
                                <thead>
                                    <tr>
                                        <th>Cart Totals</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <th>Subtotal</th>
                                        <th>$${requestScope.cartTotals}</th>
                                    </tr>
                                    <tr>
                                        <th>Delivery</th>
                                        <th>$0.00</th>
                                    </tr>
                                    <tr>
                                        <th style="font-weight: 600;">TOTAL</th>
                                        <th class="th-total">$${requestScope.cartTotals}</th>
                                    </tr>
                                </tbody>
                            </table>
                            <button class="checkoutBtn"><span>Submit</span></button>
                            <button style="margin-top: 2rem;" class="checkoutBtn" id="changeBtn1" onclick="window.location.href='home'">
    <span>Submit</span>
</button>

                        </div>
                    </div>
                </form>

            </div>
        </section>

        <footer class="footer">
            <div class="grid wide">
                <div class="row">

                    <div class="col l-5">
                        <div class="footer-intro">
                            <img src="./assets/img/logo-footer.png" alt="alt" />
                            <p class="footer-intro-des">Maecenas mi justo, interdum at consectetur vel, tristique et
                                arcu.
                                Ut quis eros blandit, ultrices diam in, elementum ex. Suspendisse quis faucibus urna.
                                Suspendisse pellentesque.</p>
                        </div>
                    </div>
                    <div class="col l-3">
                        <div class="footer-link">
                            <h1 class="footer-link-heading">Quick Links</h1>
                            <ul class="footer-link-list">
                                <li class="footer-link-item">
                                    <a href="#">About</a>
                                </li>
                                <li class="footer-link-item">
                                    <a href="#">About</a>
                                </li>
                                <li class="footer-link-item">
                                    <a href="#">About</a>
                                </li>
                                <li class="footer-link-item">
                                    <a href="#">About</a>
                                </li>
                                <li class="footer-link-item">
                                    <a href="#">About</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col l-4">
                        <div class="footer-download">
                            <h1 class="footer-download-heading">Download Our Mobile App</h1>
                            <p class="footer-download-des">Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                                Donec
                                aliquam gravida sollicitudin. Praesent porta enim mi, non tincidunt libero interdum sit
                                amet.</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="footer-copyright">
                <div class="grid wide">
                    <h1 class="copyright-text">Copyright © 2024 | Organic Store</h1>
                </div>
            </div>
        </footer>
    
    </body>

</html>