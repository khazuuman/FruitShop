
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css"> 
        <!--     Ionicons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css"> 
        <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
        <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">
        <link rel="stylesheet" href="css/userlist.css" />
        <link rel="stylesheet" href="css/bass.css"/>    
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://kit.fontawesome.com/33f9434037.js" crossorigin="anonymous"></script>

    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <header class="main-header">
            <!-- Logo -->
            <a href="index2.html" class="logo">
                <!-- mini logo for sidebar mini 50x50 pixels -->
                <span class="logo-mini"><b>A</b>LT</span>
                <!-- logo for regular state and mobile devices -->
                <span class="logo-lg"><b>Admin</b>LTE</span>
            </a>

            <!-- Header Navbar: style can be found in header.less -->
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
            <c:if test="${param['index']==null }">   
                <c:set var = "index" scope = "page" value = "1"/>
            </c:if>
            <c:if test="${param['index']!=null}">
                <c:set var = "index" scope = "page" value = "${param['index']}"/>
            </c:if>
            
            <aside class="main-sidebar">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                        </div>
                        <div class="pull-left info">
                            <p>Alexander Pierce</p>
                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>

                    <!-- sidebar menu: : style can be found in sidebar.less -->
                    <ul class="sidebar-menu">
                        <li class="header">MAIN NAVIGATION</li>
                        <li class="active treeview">
                            <a href="mkt-dashboard">
                                <i class="fa fa-dashboard"></i> <span>Dashboard</span>

                            </a>

                        </li>

                        <li>
                            <a href="ManagerBlog">
                                <i class="fa fa-th"></i> <span>Manage Post</span>
                                <span class="pull-right-container">
                                    <small class="label pull-right bg-green">new</small>
                                </span>
                            </a>
                        </li>

                        <li>
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
                <!-- /.sidebar -->
            </aside>
            <div class="col-lg-10 content">
                <h1>Blog List</h1>
                <form action="ManagerBlog" >
                    <div class="filter-area">
                        <div class="form-area">
                            <div class="role-filter">
                                <label for="role-filter">Categories</label>
                                <select id="role-filter" name="cid" >
                                    <option value="">All </option>
                                    <c:forEach items="${bc}" var="rl">
                                        <option value="${rl.blogCateID}" ${(rl.blogCateID==param.cid) ? "selected" :""}>
                                            ${rl.blogCateName}</option>
                                        </c:forEach>
                                </select>
                            </div>

                            <div class="status-filter">
                                <label for="status-filter">Author:</label>
                                <select id="status-filter" name="aid" >
                                    <option value="">All </option>
                                    <c:forEach items="${ac}" var="sl">
                                        <option value="${sl.accID}" ${(sl.accID==param.aid) ? "selected" :""}>
                                            ${sl.username}</option>
                                        </c:forEach>
                                </select>
                            </div>

                            <div class="gender-filter">
                                <label for="gender-filter">Status:</label>
                                <select id="gender-filter" name="status" >
                                    <option value=""${(param.status=="")?"selected":""}>All </option>
                                    <option value="1" ${(param.status=="1")?"selected":""}>Active</option>
                                    <option value="0" ${(param.status=="0")?"selected":""}>Inactive</option>
                                </select>
                            </div>

                            <div class="search-filter">
                                <input type="text" id="search" name="key"
                                       value="${key}" placeholder="Search Title" >
                                <i class="fas fa-search search-icon"></i>
                            </div>
                        </div>
                        <button type="submit">Search</button>
                    </div>
                </form>
                <a href="AddBlog" class="btn btn-primary">Add new Blog</a>
                <div class="user-list">
                    <table class="table-list">
                        <thead>
                            <tr>
                                <th id="so" data-column="AccID" data-order="desc">ID</th>
                                <th id="so" data-column="Username" data-order="desc">Thumb</th>
                                <th id="so" data-column="Gender" data-order="desc">Title</th>
                                <th id="so" data-column="Email" data-order="desc">Category</th>
                                <th id="so" data-column="Phone" data-order="desc">Author</th>
                                <th id="so" data-column="Role" data-order="desc">Create time</th>
                                <th id="so" data-column="Status" data-order="desc">Status</th>
                                <th id="so" data-column="Status" colspan="2" data-order="desc">Action</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${bl}" var="ul">
                                <tr>
                                    <td>${ul.blogID}</td>
                                   <td><img src="img/${ul.img}" width="300px"></td>

                                    <td>${ul.title}</td>
                                    <td>${ul.category.blogCateName}</td>
                                    <td>${ul.account.username}</td>
                                    <td>${DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm:ss").format(ul.time)}</td>
                                    <td>${ul.isActive==true?"Active":"Inactive"}</td>
                                    <td>${ul.isActive==true?"Active":"Inactive"}</td>
                                    <c:if test="${ul.isActive==true}">
                                        <td><a href="./ChangeBlogStatus?bid=${ul.blogID}&status=0">De-active</a></td>
                                    </c:if>
                                    <c:if test="${ul.isActive==false}">
                                        <td><a href="./ChangeBlogStatus?bid=${ul.blogID}&status=1">Active</a></td>
                                    </c:if>
                                    <td>
                                        <div class="action-btn">
                                            <button class="btn-edit"
                                                    onclick="window.location.href = './ManagerBlogDetail?bid=${ul.blogID}'"><i
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
                            <li class="onclick">
                                <a href="./ManagerBlog?index=1&cid=${param.cid}&aid=${param.aid}&status=${param.status}&key=${param.key}"><i class="fa-solid fa-angles-left"></i></a>
                            </li>
                            <c:forEach var="i" begin="1" end="${totalPage}">
                                <li class="${(index == i)?" notclick":"onclick"}">
                                    <a href="./ManagerBlog?index=${i}&cid=${param.cid}&aid=${param.aid}&status=${param.status}&key=${param.key}">${i}</a>
                                </li>
                            </c:forEach>
                            <li class="onclick">
                                <a href="./ManagerBlog?index=${totalPage}&cid=${param.cid}&aid=${param.aid}&status=${param.status}&key=${param.key}"><i class="fa-solid fa-angles-right"></i></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
