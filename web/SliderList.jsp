
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Slider" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css">
        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
        <link rel="stylesheet" href="./css/sliderList.css">
        <link rel="stylesheet" href="./css/Home/BlogList.css"/>
        <link rel="stylesheet" href="./css/bass.css"/>
        
        <link rel="stylesheet" href="dist/css/AdminLTE.min.css">

        <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">

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

            <aside class="main-sidebar">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="./img/user1_image.jpg" class="img-circle" alt="User Image" style="object-fit: cover;width: 40px;height: 40px;">
                        </div>
                        <div class="pull-left info">
                            <p style="color: white">Alexander Pierce</p>
                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>

                    <!-- sidebar menu: : style can be found in sidebar.less -->
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
                <!-- /.sidebar -->
            </aside>
            <div class="content-wrapper">
                <div class="box" style="width: 70%;padding:40px">
                    <h3 class="text-success">${requestScope.addsuccess1}</h3>
                    <div class="box-header" style="display: flex"> 
                        <c:set value="${requestScope.sid}" var="s"/>
                        <h3 class="box-title" style="flex: 1">Slider List</h3>
                        <a href="AddSlider.jsp" style="margin-right: 100px;
                           text-align: center;
                           width: 40px;
                           height: 20px;
                           color: white;
                           background-color: var(--primary-color);">ADD</a>

                        <form action="sliderlist?searchSlider=${key}&index=${index}" id="fStatus" style="flex: 1">
                            <select name="status" onchange="submitForm()">
                                <option ${(s=="2")?'selected':''} value="2">All</option>
                                <option ${(s=="0")?'selected':''} value="0">Hide</option>
                                <option ${(s=="1")?'selected':''} value="1">Show</option>
                            </select>
                        </form>

                        <form class="box-tools" action="sliderlist">
                            <div class="input-group input-group-sm" style="width: 150px;">

                                <input type="text" name="searchSlider" class="form-control pull-right" placeholder="Search" style="height: 32px;">
                                <div class="input-group-btn">
                                    <button type="submit" class="button-search"><i class="fa fa-search" style="font-size: 12px"></i></button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body table-responsive no-padding">
                        <table class="table table-hover">
                            <tr>
                                <th>ID</th>
                                <th>Title</th>
                                <th>Image</th>
                                <th>Status</th>
                                <th>Display(hide/show)</th>
                                <th>Action</th>
                            </tr>
                            <% 
                                    List<Slider> sList = (List<Slider>)request.getAttribute("slider");
                                    for (int i = 0; i < sList.size(); i++) { 
                            %>  
                            <tr>

                                <td><a href="ProductDetailController?productId=<%=sList.get(i).getProduct().getProductID()%>" class="slider-detail"><%= sList.get(i).getSliderID()%></a></td>
                                <td><a href="ProductDetailController?productId=<%=sList.get(i).getProduct().getProductID()%>" class="slider-detail"><%= sList.get(i).getContent()%></a></td>
                                <td style="width: 40%;"><a href="ProductDetailController?productId=<%=sList.get(i).getProduct().getProductID()%>" class="slider-detail"><img src="./img/<%= sList.get(i).getSliderImg()%>" style="width:60%"></a></td>

                                <c:if test="<%= sList.get(i).getIsActive() == 1%>">
                                    <td><a href="ProductDetailController?productId=<%=sList.get(i).getProduct().getProductID()%>" class="slider-detail"><span class="label label-success">Show</span></a></td>
                                    <td><a href="slideraction?action=2&id=<%= sList.get(i).getSliderID()%>&searchSlider=${key}&index=${index}&status=${sid}" class="slider-detail"><span class="label label-danger">Hide</span></a></td>
                                </c:if>
                                <c:if test="<%= sList.get(i).getIsActive() == 0%>">
                                    <td><a href="ProductDetailController?productId=<%=sList.get(i).getProduct().getProductID()%>" class="slider-detail"><span class="label label-danger">Hide</span></a></td>
                                    <td><a href="slideraction?action=1&id=<%= sList.get(i).getSliderID()%>&searchSlider=${key}&index=${index}&status=${sid}" class="slider-detail"><span class="label label-success">Show</span></a></td>
                                </c:if>
                                <td>
                                    <a href="editslider?id=<%= sList.get(i).getSliderID()%>" class="slider-detail">Edit</a>
                                    <a href="slideraction?delete=1&id=<%= sList.get(i).getSliderID()%>&searchSlider=${key}&index=${index}&status=${sid}" class="slider-detail">Delete</a>
                                </td>
                            </tr>
                            <% }%>
                        </table>
                    </div>
                    <div class="paging-button-list">
                        <c:forEach begin="1" end="${endP}" var="i">
                            <a class="paging-button-item" href="sliderlist?index=${i}&searchSlider=${key}&status=${s}">${i}</a>
                        </c:forEach>
                    </div>
                </div>
            </div>


        </div>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-U9Yi14bGYPMjYfJAZGuh6o3JODvw/+T2BE6ZU7izjKT1VaVZfU9R8IBqweNkOF1d" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>



        <script>
                                function submitForm() {
                                    document.getElementById("fStatus").submit();
                                }
        </script>
    </body>
</html>
