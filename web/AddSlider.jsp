<%-- 
    Document   : AddSlider
    Created on : Feb 27, 2024, 11:33:43 AM
    Author     : MM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Slider" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./css/bass.css"/>
        <!--        <link rel="stylesheet" href="./css/Home/grid.css"/>-->
        <link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
        <link rel="stylesheet" href="plugins/jvectormap/jquery-jvectormap-1.2.2.css">

        <link rel="stylesheet" href="dist/css/AdminLTE.min.css">

        <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">
        <style>
            .slider-detail{
                list-style: none;
                max-width: 800px;
                border-radius: 4px;
                box-shadow: 1px 1px 1px #ccc;
                padding: 10px 40px;
                border: 1px solid #ccc;
            }
            .slider-item{
                display: flex;
                justify-content: center;
                align-items: center;
            }
            .slider-item-left{
                width: 200px;
                border-right: 1px solid #ccc;
            }
            .slider-item-right{
                text-align: left;
                width: 50%;
                margin: 12px 100px;
            }
        </style>
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
                            <a href="#">
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



            <div class="col-10">
                <form action="sliderlist" method="post" style="text-align: -webkit-center;
                      right: 0;
                      margin: 20px;
                      left: 230px;
                      position: absolute;">

                    <ul class="slider-detail">
                        <li class="slider-item">
                            <span class="slider-item-left">Title</span>
                            <span class="slider-item-right">
                                <textarea rows="4" cols="22" name="content" required=""></textarea>
                            </span>
                        </li>
                        <li class="slider-item">
                            <span class="slider-item-left">Image</span>
                            <span class="slider-item-right">
                                <input type="file" id="uploadInput" accept="image/*" onchange="previewImage(event)" name="img" required="">
                                <img id="preview" style="width: 50%; margin-top: 20px;" src="">
                            </span>
                        </li>

                        <li class="slider-item">
                            <span class="slider-item-left">Status</span>
                            <a class="slider-item-right" onclick="toggleStatus(this, document.getElementById('statusInput'))" style="align-items: center;height: 30px;display: flex;"><span class="label label-danger">Hide</span></a>
                            <input type="hidden" name="status" id="statusInput" value="0"/>                            
                        </li>
                        <li class="slider-item">
                            <span class="slider-item-left">Backlink</span>
                            <span class="slider-item-right"><input type="text" name="pid" value="" required=""></span>
                        </li>
                        <button type="submit" class="btn btn-primary" style="background-color: var(--primary-color);margin: 30px 76px;">Add</button>
                    </ul>
                </form>
            </div>


        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-U9Yi14bGYPMjYfJAZGuh6o3JODvw/+T2BE6ZU7izjKT1VaVZfU9R8IBqweNkOF1d" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


        <script>
                                function previewImage(event) {
                                    var input = event.target;
                                    var preview = document.getElementById('preview');

                                    var reader = new FileReader();

                                    reader.onload = function () {
                                        preview.src = reader.result;
                                    };

                                    reader.readAsDataURL(input.files[0]);
                                }
                                function toggleStatus(element, input) {
                                    var label = element.querySelector('.label');
                                    if (label.classList.contains('label-success')) {
                                        label.textContent = 'Hide';
                                        label.classList.remove('label-success');
                                        label.classList.add('label-danger');
                                        input.value = '0';
                                    } else {
                                        label.textContent = 'Show';
                                        label.classList.remove('label-danger');
                                        label.classList.add('label-success');
                                        input.value = '1';
                                    }
                                }

                                function toggleTreeView(element) {
                                    var treeviewMenu = element.nextElementSibling;
                                    var angleIcon = element.querySelector('.fa-angle-left');
                                    treeviewMenu.classList.toggle('active');
                                    angleIcon.classList.toggle('rotate-icon');
                                }
                                function submitForm() {
                                    document.getElementById("fStatus").submit();
                                }


        </script>  
    </body>
</html>
