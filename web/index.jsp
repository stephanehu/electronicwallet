<%-- 
    Document   : index
    Created on : Feb 24, 2016, 7:09:00 AM
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
            <img class="first-slide" src="images/Small-Biz-banner-721x238-r2.jpg" alt=""/>
          <div class="container">
            <div class="carousel-caption">
              
              <p><a class="btn btn-lg btn-primary" href="banking" role="button">Sign up today</a></p>
            </div>
          </div>
        </div>
        <div class="item">
            <img class="second-slide" src="images/int.jpg" alt=""/>
          <div class="container">
            <div class="carousel-caption">
              <h1>Pay Your Bill or Recharge Your Phone .</h1>              
              <p><a class="btn btn-lg btn-primary" href="contact.jsp" role="button">Learn more</a></p>
            </div>
          </div>
        </div>
        <div class="item">
            <img class="third-slide" src="images/tba.jpg" alt=""/>
          <div class="container">
            <div class="carousel-caption">
              <h1>Services.</h1>             
              <p><a class="btn btn-lg btn-primary" href="service" role="button">Browse gallery</a></p>
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


    <!-- Marketing messaging and featurettes
    ================================================== -->
    <!-- Wrap the rest of the page in another container to center all the content. -->

    <div class="container marketing">
        <c:if test="${not empty isCreated}">
           ${message}
        </c:if>
      <!-- Three columns of text below the carousel -->
      <div class="row">
        <div class="col-lg-4">
          <img class="img-circle" src="images/service/choose.png" alt="Generic placeholder image" width="140" height="140">
          <h2>Choose</h2>
          <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. Nullam id dolor id nibh ultricies vehicula ut id elit. Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Praesent commodo cursus.</p>
          <p><a class="btn btn-primary" href="banking" role="button">Open Account &raquo;</a></p>
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
          <img class="img-circle" src="images/service/Pay-Invoice-Icon.png" alt="Generic placeholder image" width="140" height="140">
          <h2>Cash Facilty</h2>
          <p>Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Cras mattis consectetur purus sit amet fermentum. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum.</p>
          <p><a class="btn btn-primary" href="services" role="button">Transaction &raquo;</a></p>
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
          <img class="img-circle" src="images/service/Icon_CommunityPortal.png" alt="Generic placeholder image" width="140" height="140">
          <h2>Business</h2>
          <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh.</p>
          <p><a class="btn btn-primary" href="banking" role="button">Make Profit &raquo;</a></p>
        </div><!-- /.col-lg-4 -->
      </div><!-- /.row -->


      <!-- START THE FEATURETTES -->

      <hr class="featurette-divider">

      <div class="row featurette">
        <div class="col-md-7">
          <h2 class="featurette-heading">Safety is our priority <span class="text-muted">.Feel like your money is always in your pocket </span></h2>
          <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
        </div>
        <div class="col-md-5">
          <img class="featurette-image img-responsive center-block" src="images/service/500/onlinebanking.jpg" alt="Generic placeholder image">
        </div>
      </div>

      <hr class="featurette-divider">

      <div class="row featurette">
        <div class="col-md-7 col-md-push-5">
          <h2 class="featurette-heading">Oh yeah, it's that good. <span class="text-muted">See for yourself.</span></h2>
          <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
        </div>
        <div class="col-md-5 col-md-pull-7">
         
          <img class="featurette-image img-responsive center-block" src="images/service/500/Online-Banking.jpg" alt="Generic placeholder image">
        </div>
      </div>

      <hr class="featurette-divider">

      <div class="row featurette">
        <div class="col-md-7">
          <h2 class="featurette-heading">Anywhere, Anytime, Anyhow. <span class="text-muted">Access to your account.</span></h2>
          <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
        </div>
        <div class="col-md-5">
          <img class="featurette-image img-responsive center-block" src="images/service/500/everyday.jpg" alt="Generic placeholder image">
        </div>
      </div>

      <hr class="featurette-divider">

      <!-- /END THE FEATURETTES -->