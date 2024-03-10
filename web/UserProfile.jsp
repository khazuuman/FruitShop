<%-- 
    Document   : UserProfile
    Created on : Jan 15, 2024, 10:02:51 PM
    Author     : Thanh Hai
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Profile</title>

        <link rel="stylesheet" href="./css/Home/HomeCss.css"/>
        <link rel="stylesheet" href="./css/Home/bass.css"/>
        <link rel="stylesheet" href="./css/Home/grid.css"/>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
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
            <div class="main-body">




                <div class="row gutters-md">
                    <div class="col-md-4 mb-3">
                        <div class="card">
                            <div class="card-body">
                                <div class="d-flex flex-column align-items-center text-center">
                                    <c:choose>
                                        <c:when test="${not empty requestScope.acc.accImg}">
                                            <img src="img/${requestScope.acc.accImg}" alt="User" class="rounded-circle" width="150">
                                        </c:when>
                                        <c:otherwise>
                                            <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="Admin" class="rounded-circle" width="150">
                                        </c:otherwise>
                                    </c:choose>
                                    <div class="mt-3">
                                        <h4>${requestScope.acc.username}</h4>




                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card mt-3">

                        </div>
                    </div>
                    <div class="col-md-8">
                        <div class="card mb-3">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-sm-3">
                                        <h6 class="mb-0">Full Name</h6>
                                    </div>

                                    <div class="col-sm-9 text-secondary">
                                        ${requestScope.acc.username}
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <h6 class="mb-0">Email</h6>
                                    </div>
                                    <div class="col-sm-9 text-secondary">
                                        ${requestScope.acc.email}
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <h6 class="mb-0">Phone</h6>
                                    </div>
                                    <div class="col-sm-9 text-secondary">
                                        <div class="col-sm-9 text-secondary">
                                            ${requestScope.acc.phone != null ? requestScope.acc.phone : ""}
                                        </div>

                                    </div>

                                </div>
                                <hr>

                                <hr>

                                <div class="row">
                                    <div class="col-sm-3">
                                        <h6 class="mb-0">Address</h6>
                                    </div>
                                    <div class="col-sm-9 text-secondary">${requestScope.acc.address}


                                    </div>
                                </div>



                                <hr>
                                <div class="row">

                                    <div class="col-sm-12">

                                        <input type="radio" name="gender" value="Male" ${requestScope.acc.gender == true ? 'checked' : '' } disabled/>Male
                                        <input type="radio" name="gender" value="Female" ${requestScope.acc.gender == false ? 'checked' : ''} disabled/>Female


                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-2">
                                        <a class="btn btn-outline-success" href="edit-profile">Edit Profile</a>
                                    </div>

                                    <div class="col-sm-6">
                                        <a class="btn btn-outline-success" href="change-pass">Change Password</a>
                                    </div>

                                </div>



                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <footer class="footer" style=" margin-top:200px;">

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


        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-eMNCOe7tC1doHpGoJtKh7z7lGz7fuP4F8nfdFvAOA6Gg/z6Y5J6XqqyGXYM2ntX8" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-pzjw8f+ua7Kw1TIq0v8FqFjcJ6pajs/rfdfs3SO+kD4Ck5BdPtF+to8xMp9MvcJ4JjM9VZbzWd7mhXyKb46v5M1Qxz2OZbXsF3d1JWfHpYf2xT0xK" crossorigin="anonymous"></script>
    </body>
</html>
