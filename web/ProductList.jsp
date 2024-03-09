<%-- 
    Document   : ProductList
    Created on : 20 thg 1, 2024, 19:06:22
    Author     : nguye
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <script src="https://kit.fontawesome.com/33f9434037.js" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link
            href="https://fonts.googleapis.com/css2?family=Merriweather&family=Open+Sans:wght@300&family=Roboto:wght@100&display=swap"
            rel="stylesheet">
        <link rel="stylesheet" href="./css/ProductList/navbar-footer.css"/>
        <link rel="stylesheet" href="./css/ProductList/side-bar.css">
        <link rel="stylesheet" href="./css/ProductList/content.css">
        <link rel="stylesheet" href="./css/grid.css">
        <link rel="stylesheet" href="./css/bass.css">
    </head>

    <body>
        <div style="position: absolute; width: 100%; height: 100%; pointer-events: none;" id="noti-ajax-box">
        </div>

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
                                    <a class="navbar-blog-item-link" href="<c:if test="${sessionScope.acc!=null}">CartDetailController</c:if><c:if test="${sessionScope.acc==null}">LoginController</c:if>">Cart</a>
                                    </li>
                                    <li class="navbar-blog-list-item">
                                        <a class="navbar-blog-item-link" href="url">Checkout</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                        <a href="home" class="navbar_logo">
                            <img src="./img/logo-img.png" alt="anh logo"/>
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
                                        <a href="userprofile?id=${sessionScope.acc.getAccID()}" class="header__navbar-user-item-link">Profile</a>
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
                            <input class="text-input" type="text" placeholder="Search product..." value="${search}" name="search">
                            <button type="submit" class="search-btn"><i class="fa-solid fa-chevron-up fa-rotate-90"
                                                                        style="color: #ffffff;"></i></button>
                        </form>
                    </div>
                    <div class="category">
                        <h3>Categories</h3>
                        <c:forEach var="c" items="${cateList}">
                            <span><a href="ProductListController?cate=${c.getCateName()}">${c.getCateName()}</a> (${c.getQuantity()})</span>
                        </c:forEach>
                    </div>
                    <div class="star-rating-sider">
                        <h3>Rating</h3>
                        <div class="rating-list">
                            <a href="ProductListController?rating=5">
                                <div class="five-stars">
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                </div>
                            </a>
                            <a href="ProductListController?rating=4">
                                <div class="four-stars">
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-regular fa-star"></i><span>or more</span>
                                </div>
                            </a>
                            <a href="ProductListController?rating=3">
                                <div class="three-stars">
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-regular fa-star"></i>
                                    <i class="fa-regular fa-star"></i><span>or more</span>
                                </div>
                            </a>
                            <a href="ProductListController?rating=2">
                                <div class="two-stars">
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-solid fa-star"></i>
                                    <i class="fa-regular fa-star"></i>
                                    <i class="fa-regular fa-star"></i>
                                    <i class="fa-regular fa-star"></i><span>or more</span>
                                </div>
                            </a>
                            <a href="ProductListController?rating=1">
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
                                <span>From</span> <input type="number" value="${minPrice}" name="minPrice"> <span>To</span> <input type="number" value="${maxPrice}" name="maxPrice"> 
                            </div>
                        </div>
                        <div class="search-btn-box"><button type="submit" class="price-search">Search</button>
                        </div>
                    </form>
                    <div class="reset-box"><button onclick="window.location.href = 'ProductListController'" class="reset-side-bar">Reset</button> </div>  
                    <div class="side-bar-products">
                        <h3>Lasted Products</h3>
                        <c:forEach var="p3" items="${newest3products}">
                            <div class="side-product">
                                <div class="side-product-img">
                                    <a href="ProductDetailController?productId=${p3.getProductID()}">
                                        <img src="./ProductListImg/${p3.getImage()}"
                                             alt="">
                                    </a>
                                </div>
                                <div class="side-product-content">
                                    <a style="text-decoration: none; font-family: 'Open Sans', sans-serif; font-size: 1.2rem; color: black;" href="ProductDetailController?productId=${p3.getProductID()}"><h2 style="margin: 0">${p3.getProductName()}</h2></a>
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
                                        <span class="oldprice">$${p3.getMainPrice()}</span>
                                        <span class="onprice">$${p3.getSalePrice()}</span>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>                        
                    </div>
                </div>
            </div>
            <div class="product-list">
                <div class="product-list-text1">
                    <span><a href="home">Home</a> / Shop</span>
                </div>
                <div class="product-list-text2">
                    <h1>Shop</h1>
                </div>
                <div class="text-sort">
                    <span>Showing 1-9 of ${productCount} results</span>
                    <div class="sort-box">
                        <select class="select-sort" id="select" onchange="window.location.href = 'ProductListController?search=${search}&cate=${cate}&minPrice=${minPrice}&maxPrice=${maxPrice}&rating=${rating}&sort=' + document.getElementById('select').value;">
                            <option <c:if test="${sort eq ''}">selected=""</c:if> value="">Default</option>
                            <option <c:if test="${sort eq 'asc'}">selected=""</c:if> value="asc">Low</option>
                            <option <c:if test="${sort eq 'desc'}">selected=""</c:if> value="desc">High</option>
                            </select>
                        </div>
                    </div>
                    <div class="products">
                    <c:forEach var="p" items="${products}">
                        <div class="product">
                            <div class="image">
                                <a href="ProductDetailController?productId=${p.getProductID()}">
                                    <img src="./ProductListImg/${p.getImage()}"
                                         alt="404">
                                </a>
                                <div class="onsale">
                                    <span>Sale!</span>
                                </div>
                            </div>
                            <div class="product-infor">
                                <span class="cate-title">${p.getCateName()}</span>
                                <a class="product-name" href="ProductDetailController?productId=${p.getProductID()}">${p.getProductName()}</a>
                                <div class="star-rating">
                                    <c:forEach begin="1" end="${p.getRating()}" step="1">
                                        <i style="color: #9fe252;" class="fa-solid fa-star"></i>
                                    </c:forEach>
                                    <c:forEach begin="${p.getRating()}" end="4" step="1">
                                        <i class="fa-sharp fa-regular fa-star" style="color: #000000;"></i>
                                    </c:forEach>
                                </div>
                                <div class="price">
                                    <span class="oldprice">$${p.getMainPrice()}</span>
                                    <span class="onprice">$${p.getSalePrice()}</span>
                                </div>
                            </div>
                            <div class="add-cart-box">
<!--                                <button class="add-cart-btn" onclick=<c:if test="${sessionScope.acc!=null}">"addCart(${p.getProductID()})"</c:if> <c:if test="${sessionScope.acc==null}"></c:if> ><i class="fa-solid fa-cart-arrow-down"></i> <span>Add to cart</span></button>-->
                                <button class="add-cart-btn" onclick="addCart(${p.getProductID()})"><i class="fa-solid fa-cart-arrow-down"></i> <span>Add to cart</span></button>
                            </div>
                        </div>  
                    </c:forEach>                   
                </div>
                <div class="paging">
                    <ul class="page-list">
                        <c:if test="${index != 1}">
                            <li class="onclick"><a href="ProductListController?search=${search}&cate=${cate}&minPrice=${minPrice}&maxPrice=${maxPrice}&rating=${rating}&sort=${sort}&index=${index-1}"><i class="fa-solid fa-arrow-right fa-rotate-180"></i></a></li>
                                </c:if>   
                                <c:forEach var="i" begin="1" end="${page}">
                                    <c:choose>
                                        <c:when test="${index == i}">
                                    <li class="notclick"><span>${i}</span></li>
                                        </c:when>
                                        <c:otherwise>
                                    <li class="onclick"><a href="ProductListController?search=${search}&cate=${cate}&minPrice=${minPrice}&maxPrice=${maxPrice}&rating=${rating}&sort=${sort}&index=${i}">${i}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:if test="${index != page}">
                            <li class="onclick"><a href="ProductListController?search=${search}&cate=${cate}&minPrice=${minPrice}&maxPrice=${maxPrice}&rating=${rating}&sort=${sort}&index=${index+1}"><i class="fa-solid fa-arrow-right"></i></a></li>
                                </c:if> 
                    </ul>
                </div>
            </div>
        </section>
        <footer class="footer">
            <div class="grid wide">
                <div class="row">

                    <div class="col l-5">
                        <div class="footer-intro">
                            <img src="./img/logo-footer.png" alt="alt" />
                            <p class="footer-intro-des">Maecenas mi justo, interdum at consectetur vel, tristique et arcu.
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
                            <p class="footer-download-des">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec
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
        <script src="JS/ProductListNoti.js"></script>
    </body>
</html>