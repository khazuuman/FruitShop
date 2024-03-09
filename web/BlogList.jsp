<%-- 
    Document   : BlogList
    Created on : Jan 15, 2024, 10:39:59 PM
    Author     : MM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Product" %>
<%@page import="model.Blog" %>
<%@page import="model.BlogCategory" %>
<%@page import="model.BlogCmtCount" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="./css/Home/BlogList.css"/>
        <link rel="stylesheet" href="./css/Home/HomeCss.css"/>
        <link rel="stylesheet" href="./css/Home/bass.css"/>
        <link rel="stylesheet" href="./css/Home/grid.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@500&display=swap" rel="stylesheet">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
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
        <div class="header-blog">
            <h1 class="header-blog-heading">Blog</h1>
            <img class="header-blog-img" src="./img/blog-list.jpg" alt="alt"/>
        </div>
        <div class="blog-content">
            <div class="grid wide">
                <div class="row">
                    <div class="col l-6">
                        <div class="blog-content-title">
                            <div class="blog-content-title-icon"></div>
                            <h2 class="blog-content-title-text">What We Are Offer</h2>
                        </div>

                        <h1 class="blog-content-heading">Latest Blog Post & Update Articles</h1>
                    </div>
                    <div class="col l-6">
                        <p class="blog-content-des">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmo tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation .</p>
                    </div>
                </div>


                <div class="row blog-content-list">
                    <div class="col l-8">
                        <div class="row">


                            <% 
                            List<Blog> bpBlog = (List<Blog>)request.getAttribute("bpBlog");
                            for (int i = 0; i < bpBlog.size(); i++) { 
                                
                    
                            %>
                            <div class="col l-6">
                                <a href="blogdetail?id=${bpBlog.get(i).getBlogID()}" class="blog-content-item">
                                    <div class="img-wrap">
                                        <img class="blog-content-item-img" src="./img/<%= bpBlog.get(i).getImg()%>" alt="alt"/>
                                    </div>
                                    <div class="blog-content-item-detail">
                                        <div class="content-item-time">
                                            <i class="fa-regular fa-calendar"></i>
                                            <span><%= bpBlog.get(i).getTime()%></span>
                                        </div>
                                        <div class="content-item-cmtCount">
                                            <i class="fa-regular fa-comment"></i>
                                            <span><%=bpBlog.get(i).getBcount().getCount()%></span>
                                        </div>
                            
                                    </div>
                                    <h2 class="blog-content-item-heading"><%= bpBlog.get(i).getTitle()%></h2>
                                    <h3 class="blog-content-readmore">Read more
                                        <i class="fa-solid fa-right-long"></i></h3>
                                </a>

                            </div>
                            <%}%>
                        </div>
                        <div class="paging-button-list">
                            <c:forEach begin="1" end="${endP}" var="i">
                            <a class="paging-button-item" href="Blog?index=${i}&searchBlog=${key}">${i}</a>
                            </c:forEach>
                        </div>
                        
                        

                    </div>
                    <div class="col l-4">
                        <div class="row">
                            <div class="blog-content-sider">
                                <form id="blogSearchForm" action="Blog">
                                    <input class="sider-search" type="text" placeholder="Search" name="searchBlog" value="${key}"/>
                                    <a href="#" class="sider-search-icon" id="submitFormLink">
                                        <i class="fa-solid fa-magnifying-glass"></i>
                                    </a>
                                </form>

                                <div class="content-recent-post">
                                    <h1 class="recent-post-heading">Recent Post</h1>
                                    <% 
                                    List<Blog> bNList = (List<Blog>)request.getAttribute("bNList");
                            
                                    for (int i = 0; i < bNList.size(); i++) { 
                            
                    
                                    %>
                                    <a href="blogdetail?id=${bNList.get(i).getBlogID()}" class="recent-post-item">
                                        <img class="recent-post-item-img" src="./img/<%= bNList.get(i).getImg()%>" alt="alt"/>
                                        <div class="recent-post-item-detail">
                                            <h2 class="recent-post-item-title"><%= bNList.get(i).getTitle()%></h2>    
                                            <p class="recent-post-item-time">January 06, 2023</p>
                                        </div>

                                    </a>
                                    <%}%>


                                </div>
                                <div class="content-cate">
                                    <h1 class="recent-post-heading">Categories</h1>

                                    <% 
                                    
                                    List<BlogCategory> bcList = (List<BlogCategory>)request.getAttribute("bcList");
                                    for (int i = 0; i < bcList.size(); i++) { 
                                    %>
                                    
                                    <c:set value="${key}" var="s"></c:set>
                                    <c:set value="<%= bcList.get(i).getBlogCateName()%>" var="t"></c:set>
                                    <div class="content-cate-search">
                                        <form class="content-cate-check">
                                            <input type="checkbox" class="checkcate" name="searchBlog" value="<%= bcList.get(i).getBlogCateName()%>" ${(s==t)?'checked':''}/>
                                            <label for="checkcate" class="checkbox-cate" >
                                            </label>
                                            <span><%= bcList.get(i).getBlogCateName()%></span>
                                        </form>
                                    </div>
                                    <% } %>  
                                </div>
                            </div>
                        </div>

                    </div>


                </div>

            </div>
        </div>
        <div class="grid wide">
            <div class="blog-product-thumb-list">
                <div class="row">

                    <% 
                                    List<Product> ListP = (List<Product>)request.getAttribute("ListP");
                            
                                    for (int i = 0; i < ListP.size(); i++) { 
                            
                    
                    %>
                    <div class="col l-3">
                        <div class="blog-product-thumb">
                            <img class="blog-product-thumb-img" src="./img/<%= ListP.get(i).getImage()%>" alt="alt"/>
                            <a href="ProductDetailController?productId=<%= ListP.get(i).getProductID()%>" class="blog-product-thumb-icon"> 
                                <i class="fa-solid fa-cart-plus"></i></a>
                        </div>
                    </div>
                    <% } %>  
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
            document.addEventListener('DOMContentLoaded', function () {
                var checkboxes = document.querySelectorAll('.checkcate');

                checkboxes.forEach(function (checkbox) {
                    checkbox.addEventListener('click', function () {
                        // Lấy ra đối tượng form chứa checkbox đang được click
                        var form = checkbox.closest('.content-cate-check');

                        // Submit form
                        form.submit();
                    });
                });
            });



            document.addEventListener('DOMContentLoaded', function () {
                // Bắt sự kiện click trên thẻ <a>
                document.getElementById('submitFormLink').addEventListener('click', function (event) {
                    // Ngăn chặn hành động mặc định của thẻ <a> (chẳng hạn, chuyển hướng trang)
                    event.preventDefault();

                    // Lấy ra đối tượng form và submit nó
                    var form = document.getElementById('blogSearchForm');
                    form.submit();
                });
            });
        </script>

    </body>
</html>
