<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : services
    Created on : Feb 24, 2016, 7:10:26 AM
    Author     :Stephane Ehu Alias RigtheousByGod 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Carousel
    ================================================== -->
<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <img class="first-slide" src="images/banner4.jpg" alt=""/>
            <div class="container">
                <div class="carousel-caption">

                    <p><a class="btn btn-lg btn-primary" href="#" role="button">Sign up today</a></p>
                </div>
            </div>
        </div>
        <div class="item">
            <img class="second-slide" src="images/BillPay.png" alt=""/>
            <div class="container">
                <div class="carousel-caption">
                    <h1>Pay Your Bill or Recharge Your Phone .</h1>              
                    <p><a class="btn btn-lg btn-primary" href="#" role="button">Learn more</a></p>
                </div>
            </div>
        </div>
        <div class="item">
            <img class="third-slide" src="images/ibill.jpg" alt=""/>
            <div class="container">
                <div class="carousel-caption">
                    <h1>Services.</h1>             
                    <p><a class="btn btn-lg btn-primary" href="#" role="button">Browse gallery</a></p>
                </div>
            </div>
        </div>
    </div>
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div><!-- /.carousel -->

    <div class="container marketing">
        <c:forEach var="service" items="${bankServices}">
            <div class="col-sm-3 clearfix">
                <div class=" panel-default">
                    <div class="panel-heading">
                        <a href="payment">${service.serviceName}</a>
                    </div>
                    <div class="panel-body">
                        ${service.serviceDescription}
                    </div>
                </div>
            </div>   
        </c:forEach>
    </div>