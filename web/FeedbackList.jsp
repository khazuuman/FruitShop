

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
        <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
        <link rel="stylesheet" href="css/bass.css"/>  
        <link rel="stylesheet" href="css/userlist.css"/>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://kit.fontawesome.com/33f9434037.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />

        <script src="JS/Feedback.js"></script>
        <style>
            .primary-color{
                color: var(--primary-color);
            }
        </style>

    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <header class="main-header">
            <!-- Logo -->
            <a href="index2.html" class="logo">

                <span class="logo-mini"><b>A</b>LT</span>

                <span class="logo-lg"><b>Admin</b>LTE</span>
            </a>
            <nav class="navbar navbar-static-top">
                <!-- Navbar Right Menu -->
                <div class="navbar-custom-menu">
                    <ul class="nav navbar-nav">

                        <li class="dropdown user user-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="align-items: center;display: flex;margin-right: 60px"> 
                                <img src="./img/user1_image.jpg" class="user-image" alt="User Image" style="object-fit: cover;">
                                <span class="hidden-xs">Alexander Pierce</span>
                            </a>
                            <ul class="dropdown-menu" style="margin-right: 40px">
                                <!-- User image -->
                                <li class="user-header" style="height: inherit;">
                                    <img src="./img/user1_image.jpg" class="img-circle" alt="User Image" style="object-fit: cover;width: 40px;height: 40px;">

                                    <p>
                                        Alexander Pierce
                                    </p>
                                </li>
                                <!-- Menu Footer-->
                                <li class="user-footer">
                                    <div class="pull-left">
                                        <a href="#" class="btn btn-default btn-flat">Profile</a>
                                    </div>
                                    <div class="pull-right">
                                        <a href="#" class="btn btn-default btn-flat">Sign out</a>
                                    </div>
                                </li>
                            </ul>
                        </li>


                    </ul>
                </div>

            </nav>
        </header>

        <div class="row">

            <aside class="main-sidebar">
                <section class="sidebar">
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="./img/user1_image.jpg" class="img-circle" alt="User Image" style="object-fit: cover;width: 40px;height: 40px;">
                        </div>
                        <div class="pull-left info">
                            <p style="color: white">Alexander Pierce</p>
                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>
                    <ul class="sidebar-menu">
                        <li class="header">MAIN NAVIGATION</li>
                        <li class="">
                            <a href="MKTDashboard.jsp">
                                <i class="fa fa-dashboard"></i> <span>Dashboard</span>

                            </a>

                        </li>

                        <li>
                            <a href="#">
                                <i class="fa fa-th"></i> <span>Manage Post</span>
                                <span class="pull-right-container">
                                    <small class="label pull-right bg-green">new</small>
                                </span>
                            </a>
                        </li>

                        <li class="active treeview">
                            <a href="sliderlist">
                                <i class="fa fa-picture-o" aria-hidden="true"></i> <span>Manage Sliders</span>
                                <span class="pull-right-container">
                                    <small class="label pull-right bg-green">new</small>
                                </span>
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                <i class="fa fa-comment" aria-hidden="true"></i> <span>Manage Feedbacks</span>
                                <span class="pull-right-container">
                                    <small class="label pull-right bg-green">new</small>
                                </span>
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                <i class="fa fa-th"></i> <span>Manage Customers</span>
                                <span class="pull-right-container">
                                    <small class="label pull-right bg-green">new</small>
                                </span>
                            </a>
                        </li>
                        <li><a href="#"><i class="fa fa-product-hunt" aria-hidden="true"></i> <span>Manage Products</span></a></li>

                    </ul>
                </section>
            </aside>
            <div class="content-wrapper" style="padding: 15px">
                <section class="content-header">
                    <h1>Feedback List</h1>
                    <div class="filter-area">
                        <div class="form-area">

                            <div class="status-filter">
                                <label for="status-filter">Statuses:</label>
                                <select id="status-filter" name="stt" onchange="getFil()">
                                    <option value="${null}">All Statuses</option>
                                    <option value="1" ${(stt==1)?"selected":""}>Show</option>
                                    <option value="0" ${(stt==0)?"selected":""}>Hide</option>
                                </select>
                            </div>

                            <div class="star-filter">
                                <label for="star-filter">Stars:</label>
                                <select id="star-filter" name="star" onchange="getFil()">
                                    <option value="${null}">All Stars</option>
                                    <option value="1" ${(star==1)?"selected":""}>&#9733;</option>
                                    <option value="2" ${(star==2)?"selected":""}>&#9733;&#9733;</option>
                                    <option value="3" ${(star==3)?"selected":""}>&#9733;&#9733;&#9733;</option>
                                    <option value="4" ${(star==4)?"selected":""}>&#9733;&#9733;&#9733;&#9733;</option>
                                    <option value="5" ${(star==5)?"selected":""}>&#9733;&#9733;&#9733;&#9733;&#9733;</option>
                                </select>
                            </div>        


                            <div class="search-filter">
                                <input type="text" id="search" placeholder="" name="txtSearch"
                                       value="${txt}" onkeyup="getFil()">
                                <i class="fas fa-search search-icon"></i>
                            </div>
                        </div>

                    </div>

                    <div class="user-list">
                        <table class="table-list">
                            <thead>
                                <tr>
                                    <th id="so" data-column="FeedbackID" data-order="desc">ID</th>
                                    <th id="so" data-column="Username" data-order="desc">Name</th>
                                    <th id="so" data-column="Email" data-order="desc">Email</th>
                                    <th id="so" data-column="Phone" data-order="desc">Mobile</th>
                                    <th id="so" data-column="ProductName" data-order="desc">Product Name</th>
                                    <th id="so" data-column="Content" data-order="desc">Feedback Content</th>
                                    <th id="so" data-column="Rating" data-order="desc">Rate Star</th>
                                    <th id="so" data-column="Status" data-order="desc">Status</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${listFeedback}" var="ul">
                                    <tr>
                                        <td>${ul.getFeedbackID()}</td>
                                        <td>${ul.getAccount().username}</td>
                                        <td>${ul.getAccount().email}</td>
                                        <td>${ul.getAccount().phone}</td>
                                        <td>${ul.getProduct().productName}</td>
                                        <td>${ul.getContent()}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${ul.getRating() == 5}">
                                                    <i class="fa-solid fa-star primary-color"></i>
                                                    <i class="fa-solid fa-star primary-color"></i>
                                                    <i class="fa-solid fa-star primary-color"></i>
                                                    <i class="fa-solid fa-star primary-color"></i>
                                                    <i class="fa-solid fa-star primary-color"></i>
                                                </c:when>
                                                <c:when test="${ul.getRating() == 4}">
                                                    <i class="fa-solid fa-star primary-color"></i>
                                                    <i class="fa-solid fa-star primary-color"></i>
                                                    <i class="fa-solid fa-star primary-color"></i>
                                                    <i class="fa-solid fa-star primary-color"></i>
                                                    <i class="fa-regular fa-star primary-color"></i>
                                                </c:when>
                                                <c:when test="${ul.getRating() == 3}">
                                                    <i class="fa-solid fa-star primary-color"></i>
                                                    <i class="fa-solid fa-star primary-color"></i>
                                                    <i class="fa-solid fa-star primary-color"></i>
                                                    <i class="fa-regular fa-star primary-color"></i>
                                                    <i class="fa-regular fa-star primary-color"></i>
                                                </c:when>
                                                <c:when test="${ul.getRating() == 2}">
                                                    <i class="fa-solid fa-star primary-color"></i>
                                                    <i class="fa-solid fa-star primary-color"></i>
                                                    <i class="fa-regular fa-star primary-color"></i>
                                                    <i class="fa-regular fa-star primary-color"></i>
                                                    <i class="fa-regular fa-star primary-color"></i>
                                                </c:when>
                                                <c:when test="${ul.getRating() == 1}">
                                                    <i class="fa-solid fa-star primary-color"></i>
                                                    <i class="fa-regular fa-star primary-color"></i>
                                                    <i class="fa-regular fa-star primary-color"></i>
                                                    <i class="fa-regular fa-star primary-color"></i>
                                                    <i class="fa-regular fa-star primary-color"></i>
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${ul.isIsActive()}">
                                                    <a class="" id="so1"><span class="label label-success">Show</span></a>
                                                    <input type="hidden" name="status" id="statusInput" value="${ul.getFeedbackID()}0"/>

                                                </c:when>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${!ul.isIsActive()}">
                                                    <a class="" id="so1"><span class="label label-danger">Hide</span></a>
                                                    <input type="hidden" name="status" id="statusInput" value="${ul.getFeedbackID()}1"/>
                                                    
                                                </c:when>
                                            </c:choose>

                                        </td>

                                        <td>
                                            <div class="action-btn">
                                                <button class="btn-edit"
                                                        onclick="window.location.href = '/Fruitshop/feedbackdetail?id=${ul.getFeedbackID()}'"><i
                                                        class="fa-solid fa-info"></i></button>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <br>
                        <div class="paging">
                            <ul class="page-list">
                                <c:if test="${p.index>1}">
                                    <li class="onclick">
                                        <p index="1"><i class="fa-solid fa-angles-left"></i></p>
                                    </li>
                                    <li class="onclick">
                                        <p index="${p.index-1}"><i class="fa-solid fa-arrow-right fa-rotate-180"></i></p>
                                    </li>
                                </c:if>
                                <c:forEach var="i" begin="${p.pageStart}" end="${p.pageEnd}">
                                    <li class="${(p.index == i)?" notclick":"onclick"}">
                                        <p index='${i}'>${i}</p>
                                    </li>
                                </c:forEach>
                                <c:if test="${p.index<p.totalPage}">
                                    <li class="onclick">
                                        <p index="${p.index+1}"><i class="fa-solid fa-arrow-right"></i></p>
                                    </li>
                                    <li class="onclick">
                                        <p index="${p.totalPage}"><i class="fa-solid fa-angles-right"></i></p>
                                    </li>
                                </c:if>
                            </ul>
                        </div>
                    </div>
                </section>
            </div>
        </div>


    </body>
</html>
