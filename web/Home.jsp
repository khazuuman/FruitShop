<%-- 
    Document   : Home
    Created on : Jan 9, 2024, 8:43:20 PM
    Author     : MM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Product" %>
<%@page import="model.Blog" %>
<%@page import="model.Feedback" %>
<%@page import="model.Slider" %>
<%@page import="model.Price" %>
<%@page import="model.Categories" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="dal.ProductDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://kit.fontawesome.com/33f9434037.js" crossorigin="anonymous"></script>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Merriweather&family=Open+Sans:wght@300&family=Roboto:wght@100&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="./css/app.css" />
        <link rel="stylesheet" href="./css/Home/HomeCss.css"/>
        <link rel="stylesheet" href="./css/Home/bass.css"/>
        <link rel="stylesheet" href="./css/Home/grid.css"/>
        <link rel="stylesheet" href="./css/ProductList/side-bar.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />

        <link rel="preconnect" href="https://fonts.gstatic.com" />
        <link
            href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap"
            rel="stylesheet"
            />
        <script
            type="module"
            src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"
        ></script>
        <script
            nomodule
            src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"
        ></script>
        <link
            rel="stylesheet"
            type="text/css"
            href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"
            />


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>


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
                            <a href="#">Blog</a>
                            <ul class="navbar-blog-list">
                                <li class="navbar-blog-list-item">
                                    <a class="navbar-blog-item-link" href="Blog">Blog List</a>
                                </li>
                                <li class="navbar-blog-list-item">
                                    <a class="navbar-blog-item-link" href="url">Blog Single</a>
                                </li>
                            </ul>
                        </li>
                    <c:if test="${sessionScope.acc==null}">
                        <li class="navbar-list-item">
                            <a href="Login.jsp">Login</a>
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
        <div class="home-intro">
            <div class="grid wide">
                <div class="row ">
                    <div class="col l-2 home-intro-sider">
                        <ul class="intro-sider-list"> 
                            <% 
                            List<Blog> bList = (List<Blog>)request.getAttribute("bList");
                            for (int i = 0; i < bList.size(); i++) { 
                            %>

                            <a href="blogdetail?id=${bList.get(i).getBlogID()}" class="intro-sider-item">
                                <img src="./img/<%= bList.get(i).getImg()%>" alt="alt"/>
                            </a>
                            <%}%>
                        </ul>
                    </div>
                    <div class="col l-4 home-intro-left">
                        <div class="slideshow-container">

                            <% 
                            List<Slider> sList = (List<Slider>)request.getAttribute("sList");
                            
                            for (int i = 0; i < sList.size(); i++) { 
                            %>
                            <a class="mySlides fade" href="ProductDetailController?productId=<%=sList.get(i).getProduct().getProductID()%>">
                                <img src="./img/<%= sList.get(i).getSliderImg()%>" style="width:100%">
                                <div class="text"><%= sList.get(i).getContent()%></div>
                            </a>

                            <%}%>

                            <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
                            <a class="next" onclick="plusSlides(1)">&#10095;</a>
                        </div>
                    </div>



                    <div class="col l-6 home-intro-right">
                        <img class="intro-right-leaf" src="./img/leaf.png" alt="alt"/>
                        <div class="intro-right-text">
                            <h3 class="intro-right-heading">Best Quality Products</h3>
                            <h1 class="intro-right-heading-main">Join The Organic Movement!</h1>
                            <p class="intro-right-des">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut elit tellus, luctus nec ullamcorper mattis, pulvinar dapibus leo.</p>
                        </div>
                        <a href="ProductListController" class="home-intro-button">
                            <i class="fa-solid fa-cart-plus intro-button-cart"></i>
                            SHOP NOW
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <div class="home-policy">
            <div class="grid wide">
                <div class="row">
                    <div class="col l-3">
                        <div class="home-policy-item">
                            <i class="fa-solid fa-truck home-policy-item-icon"></i>
                            <div class="home-policy-item-text">
                                <h1 class="policy-item-heading">Free Shiping</h1>
                                <p class="policy-item-des">Above $5 Only</p>
                            </div>

                        </div>

                    </div>
                    <div class="col l-3">
                        <div class="home-policy-item">
                            <i class="fa-solid fa-truck home-policy-item-icon"></i>
                            <div class="home-policy-item-text">
                                <h1 class="policy-item-heading">Free Shiping</h1>
                                <p class="policy-item-des">Above $5 Only</p>
                            </div>

                        </div>

                    </div>
                    <div class="col l-3">
                        <div class="home-policy-item">
                            <i class="fa-solid fa-truck home-policy-item-icon"></i>
                            <div class="home-policy-item-text">
                                <h1 class="policy-item-heading">Free Shiping</h1>
                                <p class="policy-item-des">Above $5 Only</p>
                            </div>

                        </div>

                    </div>
                    <div class="col l-3">
                        <div class="home-policy-item">
                            <i class="fa-solid fa-truck home-policy-item-icon"></i>
                            <div class="home-policy-item-text">
                                <h1 class="policy-item-heading">Free Shiping</h1>
                                <p class="policy-item-des">Above $5 Only</p>
                            </div>

                        </div>

                    </div>

                </div>
            </div>
        </div>
        <div class="grid wide">
            <div class="row">
                <div class="col l-4">
                    <div class="slide-bar-area" style="margin: 0px 0px 40px 0px;">
                        <div class="slide-bar">
                            <div class="search-box" >
                                <form class="search-form" action="ProductListController" style="display: flex">
                                    <input class="text-input" type="text" placeholder="Search product..." value="${search}" name="search">
                                    <input type="hidden" name="action" value="search">
                                    <button type="submit" class="search-btn"><i class="fa-solid fa-chevron-up fa-rotate-90"
                                                                                style="color: #ffffff;"></i></button>
                                </form>
                            </div>
                            <div class="category">
                                <h3>Categories</h3>
                                <c:forEach var="c" items="${cateList}">
                                    <span><a href="ProductListController?cate=${c.getCateName()}&action=cate">${c.getCateName()}</a> (${c.getQuantity()})</span>
                                </c:forEach>
                            </div>
                            <div class="star-rating-slider">
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
                                <div class="pricing-slider">
                                    <h3>Pricing</h3>
                                    <div class="price-input">
                                        <span>From</span> <input type="number" value="${minPrice}" name="minPrice"> <span>To</span> <input type="number" value="${maxPrice}" name="maxPrice"> 
                                    </div>
                                </div>
                                <div class="search-btn-box"><button type="submit" class="price-search">Search</button>
                                </div>
                                <input type="hidden" name="action" value="price">
                            </form>
                            <div class="slide-bar-products">
                                <c:forEach var="p3" items="${newest3products}">
                                    <div class="slide-product">
                                        <div class="slide-product-img">
                                            <a href="">
                                                <img src="./ProductListImg/${p3.getImage()}"
                                                     alt="">
                                            </a>
                                            <div class="slide-onsale">
                                                <span>Sale!</span>
                                            </div>
                                        </div>
                                        <h2>${p3.getProductName()}</h2>
                                        <div class="slide-bar-product-price">
                                            <span class="oldprice">$${p3.getMainPrice()}</span>
                                            <span class="onprice">$${p3.getSalePrice()}</span>
                                        </div>
                                    </div>
                                </c:forEach>                        
                            </div>
                        </div>
                    </div>
                </div>    

                <div class="col l-8">
                    <h1 class="home-best-selling-heading">Best Selling Products</h1>
                    <img class="home-best-selling-leaf" src="./img/leaf.png" alt="alt"/>

                    <div class="image-slider">
                        <% 
                                List<Product> ListP = (List<Product>)request.getAttribute("ListP");
                                List<Price> Price = (List<Price>)request.getAttribute("Price");
                                for (int i = 0; i < ListP.size(); i++) { 
                    
                        %>


                        <div class="image-item">
                            <div class="image">
                                <a href="ProductDetailController?productId=<%=ListP.get(i).getProductID()%>" class="home-best-selling-item">
                                    <img class="home-item-img" src="./img/<%= ListP.get(i).getImage()%>" alt="alt"/>
                                    <h2 class="home-item-cate"><%= ListP.get(i).getCategory().getCateName()%></h2>
                                    <h1 class="home-item-name">
                                        <%=  ListP.get(i).getProductName()%>
                                    </h1>
                                    <%
                                    int rating = (int)Math.ceil(ListP.get(i).getRating());
                                    if(rating == 5){%>

                                    <div class="home-item-rate">
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                    </div>
                                    <%}%>
                                    <%
                                                if(rating == 4){%>
                                    <div class="home-item-rate">

                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-regular fa-star"></i>
                                    </div>

                                    <%}%>
                                    <%
                                                if(rating == 3){%>
                                    <div class="home-item-rate">

                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-regular fa-star"></i>
                                        <i class="fa-regular fa-star"></i>
                                    </div>

                                    <%}%>
                                    <%
                                                if(rating == 2){%>
                                    <div class="home-item-rate">
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-regular fa-star"></i>
                                        <i class="fa-regular fa-star"></i>
                                        <i class="fa-regular fa-star"></i>
                                    </div>

                                    <%}%>
                                    <%
                                                if(rating == 1){%>
                                    <div class="home-item-rate">
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-regular fa-star"></i>
                                        <i class="fa-regular fa-star"></i>
                                        <i class="fa-regular fa-star"></i>
                                        <i class="fa-regular fa-star"></i>
                                    </div>

                                    <%}%>
                                    <%
                                                if(rating == 0){%>
                                    <div class="home-item-rate">
                                        <i class="fa-regular fa-star"></i>
                                        <i class="fa-regular fa-star"></i>
                                        <i class="fa-regular fa-star"></i>
                                        <i class="fa-regular fa-star"></i>
                                        <i class="fa-regular fa-star"></i>
                                    </div>

                                    <%}%>



                                    <div class="home-item-price">
                                        <% if (Price.get(i).getRootPrice() != Price.get(i).getSellPrice()) { %>
                                        <span class="home-item-price-old">£<%= Price.get(i).getRootPrice() %></span>
                                        <% } %>
                                        <span class="home-item-price-current"> £<%= Price.get(i).getSellPrice() %></span>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <% } %>
                    </div>    



                </div>


            </div>
        </div>






        <div class="home-cate">
            <img class="home-cate-leaf" src="./img/basil-leaf.png" alt="alt"/>
            <div class="grid wide">

                <div class="image-slider">
                    <% 
                        List<Categories> cate = (List<Categories>)request.getAttribute("cate");
                            
                        for (int i = 0; i < cate.size(); i++) { 
                    
                    
                    %>
                    <div class="image-item">
                        <div class="image">
                            <div class="col l-12">
                                <div class="home-cate-item">
                                    <div class="home-cate-text">
                                        <h1 class="home-cate-name"><%= cate.get(i).getCateName()%></h1>
                                        <p class="home-cate-des">Ut sollicitudin quam vel purus tempus, vel eleifend felis varius.</p>  

                                        <a href="ProductListController" class="home-cate-but">Shop now
                                            <i class="fa-solid fa-arrow-right home-cate-but-icon"></i>
                                        </a>
                                    </div>

                                    <img class="home-cate-img" src="./img/cate-img.jpg" alt="alt"/>
                                </div>

                            </div>

                        </div>
                    </div>
                    <%}%>    

                </div>    


            </div>
        </div>
        <div class="home-review">
            <div class="grid wide">
                <h1 class="home-review-heading">Customers Reviews</h1>
                <img class="home-review-leaf" src="./img/leaf.png" alt="alt"/>
                <div class="row">

                    <% 
                            List<Feedback> fList = (List<Feedback>)request.getAttribute("fList");
                            
                            for (int i = 0; i < fList.size(); i++) { 
                    
                    
                    %>

                    <div class="col l-4">
                        <div class="home-review-detail home-review-detail-sub">
                            <div class="home-review-rating">
                                <i class="fa-solid fa-star review-star"></i>
                                <i class="fa-solid fa-star review-star"></i>
                                <i class="fa-solid fa-star review-star"></i>
                                <i class="fa-solid fa-star review-star"></i>
                                <i class="fa-solid fa-star review-star"></i>
                            </div>
                            <p class="home-review-des"><%= fList.get(i).getContent()%></p>
                            <div class="home-review-user">
                                <img class="home-review-user-img" src="./img/<%= fList.get(i).getAccount().getAccImg()%>" alt="alt"/>
                                <h2 class="home-review-user-name"><%= fList.get(i).getAccount().getPassword()%></h2>
                            </div>
                        </div>

                    </div>

                    <%}%>





                </div>
            </div>
        </div>

        <footer class="footer">

            <div class="grid wide">
                <div class="row">

                    <div class="col l-5">
                        <div class="footer-intro">
                            <img src="./img/logo-footer.png" alt="alt"/>
                            <p class="footer-intro-des">Maecenas mi justo, interdum at consectetur vel, tristique et arcu. Ut quis eros blandit, ultrices diam in, elementum ex. Suspendisse quis faucibus urna. Suspendisse pellentesque.</p>
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
                            <p class="footer-download-des">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec aliquam gravida sollicitudin. Praesent porta enim mi, non tincidunt libero interdum sit amet.</p>
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

        <script src="./JS/SlideJS.js"></script>
        <script
            type="text/javascript"
            src="https://code.jquery.com/jquery-1.11.0.min.js"
        ></script>
        <script
            type="text/javascript"
            src="https://code.jquery.com/jquery-migrate-1.2.1.min.js"
        ></script>
        <script
            type="text/javascript"
            src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"
        ></script>
        <script src="./JS/app.js"></script>
    </body>
</html>
