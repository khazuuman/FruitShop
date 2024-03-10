<%-- 
    Document   : MyOrder
    Created on : Mar 6, 2024, 11:21:08 PM
    Author     : MM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="model.Order" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/Home/HomeCss.css"/>
        <link rel="stylesheet" href="./css/Home/bass.css"/>
        <link rel="stylesheet" href="./css/Home/grid.css"/>
        <link rel="stylesheet" href="./css/ProductList/side-bar.css"/>
        <link rel="stylesheet" href="./css/myorder.css"/>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://kit.fontawesome.com/33f9434037.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">

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
                                <a class="navbar-blog-item-link" href="url">Cart</a>
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
        <div class="grid wide" style="margin-top: 120px">
            <div class="row">
                <div class="col l-4">
                    <div class="side-bar-area" style="margin:0">
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
                </div>
                <div class="col l-8">
                    <div class="order-main">
                        <h1 class="order-main-header">My Order</h1>
                        <ul class="order-list">
                            <% 
                            List<Order> oList = (List<Order>)request.getAttribute("listo");
                            for (int i = 0; i < oList.size(); i++) { 
                            %>
                            <li class="order-item-view item-main">
                                <div class="Total">
                                    <span>Time: <%=oList.get(i).getTime()%></span>
                                    <span>Total: <%=oList.get(i).getTotalPrice()%>$</span>
                                </div> 
                                <div class="order-item">
                                    <h3 class="order-item-id"><%=oList.get(i).getOrderID()%></h3>
                                    <div class="img-hover">
                                        <img class="item-product-img" src="./ProductListImg/<%=oList.get(i).getProducts().get(0).getImage()%>" alt="alt"/>
                                    </div>

                                    <div class="order-item-product">
                                        <div class="item-product-content">

                                            <h1 class="item-product-name"><%=oList.get(i).getProducts().get(0).getProductName()%></h1>
                                            <p class="item-product-cate"><%=oList.get(i).getProducts().get(0).getCategory().getCateName()%></p>
                                        </div>

                                        <span class="item-product-price"><%=oList.get(i).getPrice().get(0)%>$</span>
                                        <span class="item-product-quantity">x<%=oList.get(i).getQuantity().get(0)%></span>
                                    </div>

                                    <%if(oList.get(i).getStatus() == 1){%>
                                    <span class="order-item-status span-wait">
                                        Wait Confirm
                                    </span>
                                    <%}%>
                                    <%if(oList.get(i).getStatus() == 4){%>
                                    <span class="order-item-status span-primary">
                                        Shipped
                                    </span>
                                    <%}%>
                                    <%if(oList.get(i).getStatus() == 5){%>
                                    <span class="order-item-status span-warning">
                                        Shipping
                                    </span>
                                    <%}%>
                                    <%if(oList.get(i).getStatus() == 3){%>
                                    <span class="order-item-status span-success">
                                        Completed
                                    </span>
                                    <%}%>
                                </div>



                                <ul class="order-list-treeview">

                                    <% 
                                      for (int a = 1; a < oList.get(i).getProducts().size(); a++) { 
                                    %>

                                    <li class="order-item-view">
                                        <div class="order-item" style="padding-left:44px;">
                                            <div class="img-hover">
                                                <img class="item-product-img" src="./ProductListImg/<%=oList.get(i).getProducts().get(a).getImage()%>" alt="alt"/>
                                            </div>

                                            <div class="order-item-product">
                                                <div class="item-product-content">
                                                    <h1 class="item-product-name"><%=oList.get(i).getProducts().get(a).getProductName()%></h1>
                                                    <p class="item-product-cate"><%=oList.get(i).getProducts().get(a).getCategory().getCateName()%></p>
                                                </div>
                                                <span class="item-product-price"><%=oList.get(i).getPrice().get(a)%>$</span>
                                                <span class="item-product-quantity">x<%=oList.get(i).getQuantity().get(a)%></span>
                                            </div>
                                            <%if(oList.get(i).getStatus() == 1){%>
                                            <span class="order-item-status span-wait">
                                                Wait Confirm
                                            </span>
                                            <%}%>
                                            <%if(oList.get(i).getStatus() == 3){%>
                                            <span class="order-item-status span-success">
                                                Completed
                                            </span>
                                            <%}%>
                                            <%if(oList.get(i).getStatus() == 4){%>
                                            <span class="order-item-status span-primary">
                                                Shipped
                                            </span>
                                            <%}%>
                                            <%if(oList.get(i).getStatus() == 5){%>
                                            <span class="order-item-status span-warning">
                                                Shipping
                                            </span>
                                            <%}%>
                                        </div>
                                    </li>
                                    <%}%>
                                </ul>
                                <a class="see-more" onclick="toggleTreeView(this)">See More</a>        


                            </li>

                            <% }%>

                        </ul>
                        <div class="paging">
                            <ul class="page-list">
                                <c:if test="${p.index>1}">
                                    <li class="onclick" onclick="window.location.href = '/Fruitshop/myorder?index=1'">
                                        <p index="1"><i class="fa-solid fa-angles-left"></i></p>
                                    </li>
                                    <li class="onclick" onclick="window.location.href = '/Fruitshop/myorder?index=${p.index-1}'">
                                        <p index="${p.index-1}"><i class="fa-solid fa-arrow-right fa-rotate-180"></i></p>
                                    </li>
                                </c:if>
                                <c:forEach var="i" begin="${p.pageStart}" end="${p.pageEnd}">
                                    <li class="${(p.index == i)?" notclick":"onclick"}" onclick="window.location.href = '/Fruitshop/myorder?index=${i}'">
                                        <p index='${i}'>${i}</p>
                                    </li>
                                </c:forEach>
                                <c:if test="${p.index<p.totalPage}">
                                    <li class="onclick" onclick="window.location.href = '/Fruitshop/myorder?index=${p.index+1}'">
                                        <p index="${p.index+1}"><i class="fa-solid fa-arrow-right"></i></p>
                                    </li>
                                    <li class="onclick" onclick="window.location.href = '/Fruitshop/myorder?index=${p.totalPage}'">
                                        <p index="${p.totalPage}"><i class="fa-solid fa-angles-right"></i></p>
                                    </li>
                                </c:if>
                            </ul>
                        </div>

                    </div>

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
        <script>
            function toggleTreeView(element) {
                var treeviewMenu = element.previousElementSibling;
                treeviewMenu.classList.toggle('active');
                setTimeout(function () {
                    var buttonText = element.textContent.trim();
                    element.textContent = buttonText === 'See More' ? 'Show Less' : 'See More';
                }, 500);



            }
        </script>
    </body>
</html>
