<%-- 
    Document   : MKTOrderDetail
    Created on : Jun 29, 2022, 5:39:43 AM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>OrderDetai Page</title>
        <link rel="stylesheet" href="./assets/css/style.css">
        <link rel="stylesheet" href="./assets/fonts/themify-icons/themify-icons.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />

        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

        <link rel="stylesheet" href="./assets/fonts/themify-icons/themify-icons.css">
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <style>
            body {
                min-height: 100vh;
                background-size: cover;
                font-family: 'Lato', sans-serif;
                color: rgba(116, 116, 116, 0.667);
                background: linear-gradient(140deg , #fff , 50% , blue);

            }
            .container-fluid-detail {
                margin-top: 200px ;
            }

            p {
                font-size: 14px;
                margin-bottom: 7px;

            }

            .small {
                letter-spacing: 0.5px !important;
            }

            .card-1 {
                box-shadow: 2px 2px 10px 0px rgb(190, 108, 170);
            }

            hr {
                background-color: rgba(248, 248, 248, 0.667);
            }


            .bold {
                font-weight: 500;
            }

            .change-color {
                color: #AB47BC !important;
            }

            .card-2 {
                box-shadow: 1px 1px 3px 0px rgb(112, 115, 139);

            }

            .fa-circle.active {
                font-size: 8px;
                color: #AB47BC;
            }

            .fa-circle {
                font-size: 8px;
                color: #aaa;
            }

            .rounded {
                border-radius: 2.25rem !important;
            }


            .progress-bar {
                background-color: #AB47BC !important;
            }


            .progress {
                height: 5px !important;
                margin-bottom: 0;
            }

            .invoice {
                position: relative;
                top: -70px;
            }

            .Glasses {
                position: relative;
                top: -12px !important;
            }

            .card-footer {
                background-color: #6c757d;
                color: #fff;
            }

            .cart-footer h2 {
                color: rgb(78, 0, 92);
                letter-spacing: 2px !important;
            }

            .display-3 {
                font-weight: 500 !important;
            }

            @media (max-width: 479px) {
                .invoice {
                    position: relative;
                    top: 7px;
                }

                .border-line {
                    border-right: 0px solid rgb(226, 206, 226) !important;
                }

            }

            @media (max-width: 700px) {

                .cart-footer h2 {
                    color: rgb(78, 0, 92);
                    font-size: 17px;
                }

                .display-3 {
                    font-size: 28px;
                    font-weight: 500 !important;
                }
            }

            .card-footer small {
                letter-spacing: 7px !important;
                font-size: 12px;
            }

            .border-line {
                border-right: 1px solid rgb(226, 206, 226)
            }

            .cus-fontsize {
                font-size: 20px;
            }

            .payment-method__item-name {
                font-size: 20px;
                padding-left: 20px;
            }

            .payment-method__item {
                display: flex;
                align-items: center;
                border: 1px solid #D9D9D9;
                border-radius: 16px;
                padding: 15px 20px;
                margin-bottom: 1rem;
            }
            .mtop {
                margin-top: 2%;
            }


            .payment-method__item-icon-wrapper img {
                min-width: 100px;
                max-height: 100px;
                max-width: 100px;
                padding-left: 40px;
                image-rendering: -webkit-optimize-contrast;
            }
            .body_cartCompletion {
                font-family: sans-serif;
                background: linear-gradient(110deg, #fdfdbe 60%, #f9f86c 60%);
            }
            .groundy{
                font-family: sans-serif;
                background: linear-gradient(110deg, #fdfdbe 60%, #f9f86c 60%);
            }

            .circle {
                height: 10px;
                width: 10px;
                border: 50%;
            }
        </style>
    </head>
    <body class="sb-nav-fixed">

        <div id="layoutSidenav">
            <div class="groundy" id="layoutSidenav_content">
                <div class="container-fluid-detail my-5  d-flex  justify-content-center" >
                    <div class="card card-1">

                        <div class="card-body">
                            <div class="row justify-content-between mb-3">
                                <div class="col-auto"> <h6 class="color-1 mb-0 change-color">Receipt</h6> </div>
                            </div>

                            <div class="row">
                                <div class="col">
                                    <div class="card card-2">
                                        <div class="card-body">
                                            <div class="media">
                                                <table class="table table-striped">
                                                    <thead>
                                                        <tr class="table-primary">
                                                            <th scope="col" table-success>#</th>
                                                            <th scope="col">Image</th>
                                                            <th scope="col">Product Name </th>
                                                            <th scope="col">Price</th>
                                                            <th scope="col">Quantity</th>
                                                            <th scope="col">Total</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach var="o" items="${listOrder}" varStatus="loopStatus">

                                                            <tr>
                                                                <th scope="row">${loopStatus.index + 1}</th>
                                                                <td> <img src="img/${o.image}" alt="Product" class="rounded-circle" style="width:35px;height: 35px" accept=".jpg"></td>
                                                                <td>${o.productName}</td>
                                                                <td>${o.quantity}</td>
                                                                <td>${o.sellPrice}</td>
                                                                <td>${o.sellPrice * o.quantity}</td>
                                                            </tr>

                                                        </c:forEach>


                                                    </tbody>
                                                </table>

                                            </div>
                                            <hr class="my-3 ">

                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div id="layoutSidenav">

                                <form action ="OrderDetailSaleController" method="post">


                                    <div class="row">
                                        <div class="col">

                                            <div class="row justify-content-between">
                                                <div class="col-auto"><p class="mb-1 text-dark cus-fontsize"><b>Order Details</b></p></div>
                                                <div class="col-auto">
                                                    Trạng thái
                                                    <select class="form-control"  name="status" aria-label="Default select example" > Trạng thái
                                                        <option value="1"${status=="Wait_Confirm"?"Selected":""} >
                                                            Wait Confirm
                                                        </option>
                                                        <option value="3"  ${status=="Completed"?"Selected":""}>
                                                            Completed
                                                        </option>
                                                        <option value="4" ${status=="Shipped"?"Selected":""}>
                                                            Shipped
                                                        </option>
                                                        <option value="9" ${status=="Cancelled"?"Selected":""}>
                                                            Cancelled
                                                        </option>

                                                    </select>
                                                </div>


                                                <div class="col-auto">
                                                    Người bán hàng
                                                    <select class="form-control"  name="salerName" aria-label="Default select example" >
                                                        <c:forEach var="sale" items="${saleList}">
                                                            <option value="${sale.username}"  ${sale.username==saleName?"Selected":""} >
                                                                ${sale.username}
                                                            </option>
                                                        </c:forEach>


                                                    </select>
                                                </div>

                                                
                                            </div>
                                            
                                            
                                            <div class="row justify-content-between">
                                                <div class="flex-sm-col text-left col-lg-4"> <p class="mb-1 cus-fontsize me-2"><b>Nguoi mua</b></p> </div>
                                                <div class="flex-sm-col col-lg-8 text-left"> <p class="mb-1 cus-fontsize">${orderDetail.username}</p> </div>

                                            </div>
                                            
                                            
                                            
                                            <div class="row justify-content-between">
                                                <div class="flex-sm-col text-left col-lg-4"><p class="mb-1 cus-fontsize"> <b>Số điện thoại</b></p> </div>
                                                <div class="flex-sm-col col-lg-8 text-left"><p class="mb-1 cus-fontsize">${orderDetail.phone}</p></div>

                                            </div>


                                            <div class="row justify-content-between">
                                                <div class="flex-sm-col text-left col-lg-4"><p class="mb-1 cus-fontsize me-5"><b>Địa chỉ</b></p></div>
                                                <div class="flex-sm-col col-lg-8 text-left"><p class="mb-1 cus-fontsize">${orderDetail.address}</p></div>
                                            </div>
                                            <div class="row justify-content-between">
                                                <div class="flex-sm-col text-left col-lg-4"><p class="mb-1 cus-fontsize me-5"><b>Email</b></p></div>
                                                <div class="flex-sm-col col-lg-8 text-left"><p class="mb-1 cus-fontsize">${orderDetail.email}</p></div>
                                            </div>
                                            <div class="row justify-content-between">
                                                <div class="flex-sm-col text-left col-lg-4"><p class="mb-1 cus-fontsize me-5"><b>Note</b></p></div>
                                                <div class="flex-sm-col col-lg-8 text-left"><p class="mb-1 cus-fontsize">${orderDetail.note}</p></div>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="row mt-4 justify-content-between">
                                            <p class="flex-sm-col text-left col-lg-4"> OrderId:${orderId}</p>
                                            <p class="flex-sm-col col-lg-8 text-left">Ngày mua hàng :${orderDetail.orderDate} </p></div>
                                   
                            </div>
                            <div class="card-footer">
                                <div class="jumbotron-fluid">
                                    <div class="row justify-content-between ">
                                        <div class="col-sm-auto col-auto my-auto"><img class="img-fluid my-auto align-self-center " src="https://www.ngoisaoso.vn/uploads/news/2015/03/08/10.png" width="115" height="115"></div>
                                        <div class="col-auto my-auto "><h2 class="mb-0 font-weight-bold">TOTAL PAID</h2></div>
                                        <div class="col-auto my-auto ml-auto"><h1 class="display-3 ">${cartTotals}$</h1></div>
                                    </div>
                                    <div class="row mb-3 mt-3 mt-md-0">
                                        <div class="col-auto border-line"> <small class="text-white">We</small></div>
                                        <div class="col-auto border-line"> <small class="text-white">Love</small></div>
                                        <div class="col-auto "><small class="text-white">FruiShop<3 </small> </div>
                                    </div>
                                </div>
                            </div>
                            <input type="hidden" name="orderId" value="${orderId}"/>
                            <input class="btn btn-dark" type="submit" value="submit">

                        </div>
                        </form>

                    </div>


                </div>
            </div>

        </div>



        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="assets/demo/chart-area-demo.js"></script>
        <script src="assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
    </body>
</html>
