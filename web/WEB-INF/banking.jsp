<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : banking
    Created on : Feb 24, 2016, 7:11:01 AM
    Author     : Stephane
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
    <div class="row">
        <div class="col col-lg-9">
            <h1>Banking Page</h1>
            <p> This page contains the form that allows the customer or business to :
                Create an bank account - Make a deposit  - Check his balance -    
            </p>
            <a href="services">Please Check Available Account</a>
        </div>
        <c:if test="${!empty validationErrorFlag}">

            <span class="error smallText"><c:set var="validationErrorMessage" value="${'Please Fill properly the mentioned fields'}"/>

                <c:if test="${!empty firstnameError}">
                    <br><span class="indent"><c:set var="firstnameErrorMessage" value="${'First Name length must be less than 45 but not empty'}"/></span>
                </c:if>
                <c:if test="${!empty lastnameError}">
                    <br><span class="indent"><c:set var="lastnameErrorMessage" value="${'Last Name length must be less than 45 but not empty'}"/></span>
                </c:if>
                <c:if test="${!empty emailError}">
                    <br><span class="indent"><c:set var="emailErrorMessage" value="${' Email must be on xxxxx@xxxxxx.xxx format'}"/></span>
                </c:if>
                <c:if test="${!empty phoneError}">
                    <br><span class="indent"><c:set var="phoneErrorMessage" value="${' Phone number must be on ddd-(ddd)-dddd format'}"/></span>
                </c:if>
                <c:if test="${!empty addressError}">
                    <br><span class="indent"> <c:set var="addressErrorMessage" value="${'Address lenght must be less 130'}"/> </span>
                </c:if>
                <c:if test="${!empty passwordError}">
                    <br><span class="indent"><c:set var="passwordErrorMessage" value="${'Password must not exceed 15'}"/></span>
                </c:if>
                <c:if test="${!empty confirmError}">
                    <br><span class="indent"><c:set var="confirmErrorMessage" value="${'Confirm must match Password'}"/></span>
                </c:if>

            </span> 

            <a href="#openModal">Please Check what went wrong </a>

            <div id="openModal" class="modalDialog">
                <div>
                    <a href="#close" title="Close" class="close">X</a>
                    <table class="table table-striped">
                        <thead>
                        <th>Field</th>
                        <th>Mistake</th>
                        </thead>
                        <tbody>
                            <tr>
                                <td>First Name</td>
                                <td>${firstnameErrorMessage}</td>
                            </tr>
                            <tr>
                                <td>Last Name</td>
                                <td>${lastnameErrorMessage}</td>
                            </tr>
                            <tr>
                                <td>Email Id</td>
                                <td>${emailErrorMessage}</td>
                            </tr>
                            <tr>
                                <td>Phone Number</td>
                                <td>${phoneErrorMessage} </td>
                            </tr>
                            <tr>
                                <td>Address</td>
                                <td>${addressErrorMessage}</td>
                            </tr>
                            <tr>
                                <td>PassWord</td>
                                <td>${passwordErrorMessage}</td>
                            </tr>
                            <tr>
                                <td>Confirm</td>
                                <td>${confirmErrorMessage}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:if>   
        <c:if test="${empty validationErrorFlag}">
            <c:set var="success" value="${'Successful Registration Please login'}"/>
        </c:if>
        <div class="col col-lg-3 responsive-utilities">
            <form class="form-control" action="selecttype">              
                <select class="form-group" name="acctype" required="true">
                    <option>Selection le type de business:</option>

                    <option value="customer">Customer</option>
                    <option value="business">Business</option>
                </select>

                <input type="submit" value="Account Type"/>

            </form>
        </div>
    </div>
    <div class="row">
        <c:choose>
            <c:when test="${acctype ne null and acctype eq 'customer' }">
                <div class="col col-lg-12"> 

                    <form id="createCustomer" role="form" action="create" method="POST" >
                        <div class="row">
                            <div class="col col-lg-5 ">
                                <div class="form-group">
                                    <input type="hidden" value="${acctype}" name="accounttype"/>
                                    <label> FName:</label><input  class="form-control" type="text"  name="fname"  placeholder=" ${firstnameErrorMessage} First Name" required="true" />
                                </div>
                                <div class="form-group">
                                    <label>LName:</label><input  class="form-control" type="text" name="lname" placeholder="${lasttnameErrorMessage} Last Name" required="true"/>
                                </div>
                                <div class="form-group">
                                    <label> Email:</label><input  class="form-control" type="email"  name="emailid"  placeholder="${emailErrorMessage} Email Address" required="true"/>
                                </div>
                                <div class="form-group">
                                    <label> Phone:</label><input class="form-control" type="text" name="phonenumber"  placeholder="Phone Number" required="true"/>
                                </div>
                                <div class="form-group">
                                    <label> Addrs:</label><input class="form-control" type="text" required="true"  name="address"  placeholder="${addressErrorMessage} Address" />
                                </div>
                            </div>
                            <div class="col col-lg-2 form-group">

                            </div>

                            <div class="col col-lg-5">
                                <div class="form-group">
                                    <label> Account Type:</label>
                                    <select class="form-control" name="accntype" required="true">
                                        <option>Selection le type de business:</option>
                                        <c:forEach var="accounttype" items="${applicationScope.accountTypes}">
                                            <option value="${accounttype.id}">${accounttype.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>   Amount:</label><input  class="form-control" type="number" name="deposit"  placeholder="Minimum Amount" required="true"/>
                                </div>
                                <div class="form-group">
                                    <label> Password:</label><input id="password" class="form-control" type="password"  name="pssword"  placeholder=" Password" required="true"/>
                                </div>
                                <div class="form-group">
                                    <label>  Confirm:</label><input  class="form-control"  type="password" name="confirm"  placeholder="${confirmErrorMessage} Password Corfimation" required="true"/>
                                </div>

                                <div class="form-group">
                                    <span>
                                        <input type="checkbox"  class="checkbox"> 
                                        Keep me signed in ${success}
                                    </span>
                                    <button type="submit" class="btn btn-default">Create Account</button>
                                </div>
                            </div>
                        </div>

                    </form> 

                </c:when>
                <c:when test="${acctype ne null and acctype eq 'business'}">
                    <form role="form" action="create" method="POST">
                        <c:if test="${!empty validationErrorFlag}">

                            <span class="error smallText"><c:set var="validationErrorMessage" value="${'Please Fill properly the mentioned fields'}"/>

                                <c:if test="${!empty firstnameError}">
                                    <br><span class="indent"><c:set var="firstnameErrorMessage" value="${'First Name length must be less than 45 but not empty'}"/></span>
                                </c:if>
                                <c:if test="${!empty lastnameError}">
                                    <br><span class="indent"><c:set var="lastnameErrorMessage" value="${'Last Name length must be less than 45 but not empty'}"/></span>
                                </c:if>
                                <c:if test="${!empty emailError}">
                                    <br><span class="indent"><c:set var="emailErrorMessage" value="${' Email must be on xxxxx@xxxxxx.xxx format'}"/></span>
                                </c:if>
                                <c:if test="${!empty phoneError}">
                                    <br><span class="indent"><c:set var="phoneErrorMessage" value="${' Phone number must be on ddd-(ddd)-dddd format'}"/></span>
                                </c:if>
                                <c:if test="${!empty addressError}">
                                    <br><span class="indent"> <c:set var="addressErrorMessage" value="${'Address lenght must be less 130'}"/> </span>
                                </c:if>
                                <c:if test="${!empty passwordError}">
                                    <br><span class="indent"><c:set var="passwordErrorMessage" value="${'Password must not exceed 15'}"/></span>
                                </c:if>
                                <c:if test="${!empty confirmError}">
                                    <br><span class="indent"><c:set var="confirmErrorMessage" value="${'Confirm must match Password'}"/></span>
                                </c:if>

                            </span> 

                            <a href="#openModal">Please Check what went wrong </a>

                            <div id="openModal" class="modalDialog">
                                <div>
                                    <a href="#close" title="Close" class="close">X</a>
                                    <table class="table table-striped">
                                        <thead>
                                        <th>Field</th>
                                        <th>Mistake</th>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>First Name</td>
                                                <td>${firstnameErrorMessage}</td>
                                            </tr>
                                            <tr>
                                                <td>Last Name</td>
                                                <td>${lastnameErrorMessage}</td>
                                            </tr>
                                            <tr>
                                                <td>Email Id</td>
                                                <td>${emailErrorMessage}</td>
                                            </tr>
                                            <tr>
                                                <td>Phone Number</td>
                                                <td>${phoneErrorMessage} </td>
                                            </tr>
                                            <tr>
                                                <td>Address</td>
                                                <td>${addressErrorMessage}</td>
                                            </tr>
                                            <tr>
                                                <td>PassWord</td>
                                                <td>${passwordErrorMessage}</td>
                                            </tr>
                                            <tr>
                                                <td>Confirm</td>
                                                <td>${confirmErrorMessage}</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </c:if>   
                        <c:if test="${empty validationErrorFlag}">
                            <c:set var="success" value="${'Successful Registration Please login'}"/>
                        </c:if>
                        <div class="row">
                            <div class="col col-lg-5 ">
                                <div class="form-group">
                                    <input type="hidden" value="${acctype}" name="accounttype"/>
                                    Company Name:<input class="form-control" type="text"  name="fname" placeholder="First Name" required="true"/>
                                </div>
                                <div class="form-group">
                                    Business Type:
                                    <select class="form-control" name="lname" required="true">
                                        <option></option>          
                                        <option value="E-Billing">E-Billing</option> 
                                        <option value="E-Recharge">E-Recharge</option>    
                                        <option value="E-Billing &  E-Recharge">E-Billing &  E-Recharge</option>    
                                    </select>
                                </div>
                                <div class="form-group">
                                    Email:<input class="form-control" type="text"  name="emailid"  placeholder="Email Address" required="true"/>
                                </div>
                                <div class="form-group">
                                    Password:<input class="form-control" type="password"  name="pssword"  placeholder="Password" required="true"/>
                                </div>
                            </div>
                            <div class="col col-lg-2">
                            </div>
                            <div class="col col-lg-5">
                                <div class="form-group">
                                    Confirm:<input   class="form-control" type="password"  name="confirm"  placeholder="Confirm Password" required="true"/>
                                </div>
                                <div class="form-group">
                                    <label>Amount:</label><input class="form-control" type="number" name="deposit"  placeholder="Minimum Amount" required="true"/>
                                </div>
                                <div class="form-group">
                                    <label> Account Type:</label>
                                    <select class="form-control" name="accntype" required="true">
                                        <option>Selection le type de business:</option>
                                        <c:forEach var="accounttype" items="${applicationScope.accountTypes}">
                                            <option value="${accounttype.id}">${accounttype.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    Phone Number:<input class="form-control" type="text" name="phonenumber"  placeholder="Phone Number" required="true" />
                                </div>
                                <div class="form-group">
                                    Address:<input class="form-control" type="text"  name="address"  placeholder="Address" required="true"/>
                                </div>


                                <div class="form-group">
                                    <span>
                                        <input type="checkbox" class="checkbox"> <br/>  
                                        Keep me signed in
                                    </span>                                  
                                    <button type="submit"  class="btn btn-default">Create Account</button>

                                </div>
                            </div>
                    </form> 
                </c:when>
            </c:choose>

        </div>
    </div>
</div>