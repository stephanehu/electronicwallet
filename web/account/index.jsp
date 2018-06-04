<%-- 
    Document   : account
    Created on : Feb 24, 2016, 7:11:38 AM
    Author     :Stephane Ehu Alias RigtheousByGod 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<div class="container-fluid">
    <c:if test="${clientType eq 'C'}">
        <div class="row">
            <div class="col-sm-3 col-md-2 sidebar">
                <ul class="nav nav-sidebar">
                    <li class="active"><a href="<c:url value='viewProfile?${customer.id}'/>" class="btn btn-primary">Customer Details</a></li>                
                    <li><a href=""></a></li>
                </ul>
                <ul class="nav nav-sidebar">
                    <li class="active"><a href="<c:url value='viewAccount?${customer.id}'/>" class="btn btn-primary">Account Details</a></li>
                    <li><a href=""></a></li>

                </ul>
                
            </div>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

                <c:choose>
                    <c:when test="${!empty customer}">
                        <c:if test="${!empty customer and userPath eq 'customer' }">
                            <h1 class="page-header"> <button type="button" id='hide-sidebar' class="btn btn-info btn-sm" >hide filter</button>Welcome ${customer.firstname}</h1>
                            <h2 class="sub-header">Profile</h2>
                            <div class="row placeholders">
                                <form  action="updateCustomer" method="get" role="form">
                                    <div class="row">
                                        <div class="col-sm-5 form-group">
                                            <input class="form-control" name="fname" type="text" placeholder="${customer.firstname}" tabindex="3" required="true"/>
                                            <input class="form-control"name="accountType" type="hidden" value="customer" tabindex="3"/>
                                            <input class="form-control"name="clientid" type="hidden" value="${customer.id}" tabindex="3"/>
                                        </div>
                                        <div class="col-sm-5 form-group">
                                            <input class="form-control" name="lname" type="text" placeholder="${customer.lastname}" tabindex="3" required="true"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-5 form-group">
                                            <input class="form-control" name="emailid" type="email" placeholder="${customer.emailId}" tabindex="3" required="true"/>
                                        </div>
                                        <div class="col-sm-5 form-group">
                                            <input class="form-control" name="phonenumber" type="text" placeholder="${customer.phone}" tabindex="3" required="true"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-5 form-group">
                                            <input class="form-control" name="address" type="text" placeholder="${customer.address}" tabindex="3" required="true"/>
                                        </div>
                                        <div class="col-sm-5 form-group">
                                            <input class="form-control" name="pssword" type="password" placeholder="${customer.password}" tabindex="3" required="true"/>
                                        </div>
                                        <div class="col-sm-2 form-group">
                                            <input type="submit" class="btn btn-primary"  value="Update" tabindex="3" required="true"/>
                                        </div>
                                    </div>

                                </form>
                            </div>
                            
                        </c:if>
                        <c:if test="${!empty customer and  (!empty accountDetails)}">
                            <h1 class="page-header"> <button type="button" id='hide-sidebar' class="btn btn-info btn-sm" >hide filter</button>Welcome ${customer.firstname}, Account No:${accountDetails.typeId.name} ${accountDetails.id}</h1>                        <div class="row placeholders">
                                <form   action="<c:url value='deposit'/>" method="" role="form">
                                    <div class="row">
                                        <div class="col-sm-5 form-group">
                                            <div class="col-sm-4">
                                                <label class="form-control">Deposit</label> 
                                            </div>
                                            <div class="col-sm-6">
                                                <input class="form-control" name="deposit" type="number" placeholder="${accountDetails.deposit}" tabindex="3" required="true"/>   
                                                <input class="form-control" name="accountId" type="hidden" value="${accountDetails.id}" tabindex="3"/>                                                                             
                                            </div>
                                        </div>
                                        <div class="col-sm-5 form-group">
                                            <div class="col-sm-4">
                                                <label class="form-control">Balance</label>  
                                            </div>
                                            <div class="col-sm-6">
                                                <input class="form-control" disabled="true"  placeholder="${accountDetails.balance}" tabindex="3"/>                                                                              
                                            </div>
                                            <div class="col-sm-2"></div>
                                        </div>  
                                        <div class="col-sm-2 form-group">
                                            <input type="submit" class="btn btn-primary"  value="Deposit" tabindex="3" required="true"/>
                                        </div>
                                    </div>                                
                                </form>
                                <div class="row">                                  
                                    <div class="col-sm-12 form-group">                                        
                                        
                                        <span class="text-muted">${accountDetails.typeId.serviceId.serviceDescription}</span>                                  
                                    </div>
                                </div>

                                <div class="row">

                                    <div class="col-xs-6 col-sm-4 placeholder">
                                        <form class="form-control" method="post" action="<c:url value="selectbusiness"/>">
                                            <select class="form-group" name="busname" required="true">
                                                <option>Selection le type de business:</option>
                                                <c:forEach var="business" items="${businessList}">
                                                    <option value="${business.id}">${business.businessName}</option>
                                                </c:forEach>

                                            </select>

                                            <input type="submit" value="Start Payment"/>

                                        </form>
                                    </div>

                                    <c:if test="${businessId ne null}">
                                        <div class="col-xs-6 col-sm-8 placeholder">
                                            <form role="form" method="post" class="form-inline" action="transact">
                                                <div class="form-group col-sm-4">
                                                    <input name="number" class="form-control" type="number" placeholder=" Number " tabindex="3" required="true" />
                                                    <input name="busid" class="form-control" type="hidden" value="${businessId}" tabindex="3" required="true" />  
                                                    <input name="custid" class="form-control" type="hidden" value="${customer.id}" tabindex="3" required="true" />                                                
                                                </div>    
                                                <div class="form-group col-sm-5">
                                                    <input name="amount" class="form-control" type="number" placeholder="Amount" tabindex="3" required="true" />
                                                </div>
                                                <div class="form-group col-sm-2">
                                                    <input class="btn btn-primary " type="submit" value="Pay" tabindex="3" required="true" />
                                                </div>
                                            </form>

                                        </div>  
                                    </c:if>

                                </div>




                                <h2 class="sub-header"><c:if test="${!empty message}">
                                        ${message}
                                    </c:if></h2>

                                <c:if test="${!empty transactions}">
                                    <div class="table-responsive">
                                        <table class="table table-striped table-bordered">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>Amount</th>
                                                    <th>Bill Number</th>
                                                    <th>Date Transact</th>
                                                    <th>Customer</th>
                                                    <th>Business Name</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="transaction" items="${transactions}">
                                                    <tr>
                                                        <td>${transaction.id}</td>
                                                        <td>${transaction.amount}</td>
                                                        <td>${transaction.detail}</td>
                                                        <td>${transaction.dateTransacted}</td>
                                                        <td>${transaction.customersId.firstname}</td>
                                                        <td>${transaction.businessId.businessName}</td>
                                                    </tr>
                                                </c:forEach>


                                            </tbody>
                                        </table>
                                    </div>
                                </c:if>   

                            </c:if>

                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>
    </c:if>

    <c:if test="${clientType eq 'B'}">
        <div class="row">
            <div class="col-sm-3 col-md-2 sidebar">
                <ul class="nav nav-sidebar">
                    <li class="active"><a href="<c:url value='viewProfile?${business.id}' />" class="btn btn-primary">Customer Details</a></li>                
                    <li><a href=""></a></li>
                </ul>
                <ul class="nav nav-sidebar">
                    <li class="active"><a href="<c:url value='viewAccount?${business.id}'/>" class="btn btn-primary">Account Details</a></li>
                    <li><a href=""></a></li>

                </ul>
              
            </div>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

                <c:choose>
                    <c:when test="${!empty business}">
                        <c:if test="${!empty business and userPath eq 'business' }">
                            <h1 class="page-header"> <button type="button" id='hide-sidebar' class="btn btn-info btn-sm" >hide filter</button>Welcome ${business.businessName}</h1>
                            <h2 class="sub-header">Profile</h2>
                            <div class="row placeholders">
                                <form  action="updateCustomer" method="get" role="form">
                                    <div class="row">
                                        <div class="col-sm-5 form-group">
                                            <input class="form-control"name="fname" type="text" placeholder="${business.businessName}" tabindex="3" required="true"/>
                                            <input class="form-control"name="accountType" type="hidden" value="business" tabindex="3"/>
                                        <input class="form-control"name="clientid" type="hidden" value="${business.id}" tabindex="3"/>
                                        </div>
                                        <div class="col-sm-5 form-group">
                                            <input class="form-control" name="lname" type="text"  placeholder="${business.businessType}" tabindex="3" required="true"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-5 form-group">
                                            <input class="form-control" name="emailid" type="email" placeholder="${business.emailid}" tabindex="3" required="true"/>
                                        </div>
                                        <div class="col-sm-5 form-group">
                                            <input class="form-control" name="phonenumber" type="text" placeholder="${business.phonenumber}" tabindex="3" required="true"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-5 form-group">
                                            <input class="form-control" name="address" type="text" placeholder="${business.address}" tabindex="3" required="true"/>
                                        </div>
                                        <div class="col-sm-5 form-group">
                                            <input class="form-control" name="pssword" type="text" placeholder="" tabindex="3" required="true"/>
                                        </div>
                                        <div class="col-sm-2 form-group">
                                            <input type="submit" class="btn btn-primary"  value="Update" tabindex="3" required="true"/>
                                        </div>
                                    </div>

                                </form>
                            </div>
                            
                        </c:if>
                        <c:if test="${!empty business and  (!empty accountDetails)}">
                            <h1 class="page-header"> <button type="button" id='hide-sidebar' class="btn btn-info btn-sm" >hide filter</button>Welcome ${business.businessName},Account No:${accountDetails.typeId.name} ${accountDetails.id}</h1>                        <div class="row placeholders">
                                <form   action="<c:url value='deposit'/>" method="get" role="form">
                                    <div class="row">
                                        <div class="col-sm-5 form-group">
                                            <div class="col-sm-4">
                                                <label class="form-control">Deposit</label> 
                                            </div>
                                            <div class="col-sm-6">
                                                <input class="form-control" name="deposit" type="number" placeholder="${accountDetails.deposit}" tabindex="3" required="true"/>                                                                              
                                            </div>
                                        </div>
                                        <div class="col-sm-5 form-group">
                                            <div class="col-sm-4">
                                                <label class="form-control">Balance</label>  
                                            </div>
                                            <div class="col-sm-6">
                                                <input disabled="true" class="form-control" placeholder="${accountDetails.balance}" tabindex="3" />  
                                                <input class="form-control" name="accountId" type="hidden" value="${accountDetails.id}" tabindex="3"/>   
                                            </div>
                                            <div class="col-sm-2"></div>
                                        </div>  
                                        <div class="col-sm-2 form-group">
                                            <input type="submit" class="btn btn-primary"  value="deposit" tabindex="3" required="true"/>
                                        </div>
                                    </div>                                
                                </form>
                                <div class="row">                                  
                                    <div class="col-sm-12 form-group">                                        
                                        <h4><a class="btn btn-info" href="../services">Services</a></h4>
                                        <span class="text-muted">${accountDetails.typeId.serviceId.serviceDescription}</span>                                  
                                    </div>
                                </div>

                                <div class="row">

                                    <div class="col-xs-6 col-sm-4 placeholder">
                                        <form class="form-control" method="post" action="<c:url value="historic"/>">
                                            <select class="form-group" name="busname" required="true">                                              
                                                <option value="${business.id}">${business.businessName}</option>                                               
                                            </select>

                                            <input type="submit" value="Historic"/>

                                        </form>
                                    </div>


                                </div>


                                <c:if test="${!empty transactions}">

                                    <div class="table-responsive">
                                        <table class="table table-striped table-bordered">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>Amount</th>
                                                    <th>Bill Number</th>
                                                    <th>Date Transact</th>                                                    
                                                    <th>Business Name</th>
                                                    <th>Customer</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="transaction" items="${transactions}">
                                                    <tr>
                                                        <td>${transaction.id}</td>
                                                        <td>${transaction.amount}</td>
                                                        <td>${transaction.detail}</td>
                                                        <td>${transaction.dateTransacted}</td>                                                        
                                                        <td>${transaction.businessId.businessName}</td>
                                                        <td>${transaction.customersId.firstname}</td>
                                                    </tr>
                                                </c:forEach>


                                            </tbody>
                                        </table>
                                    </div>
                                </c:if>   
                            </c:if>

                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>
    </c:if>
</div>