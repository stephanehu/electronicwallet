$(function(){
    
     $('#hide-sidebar').click(function(){
      
      if($(this).text() == 'hide filter')
      {
        $('.sidebar').animate({left: '-=25%'}, 500);
        //$('.main').animate({left: '-=15%'},500);
        //var newid = wid * 1.17
        //$('.main').width(newid);
        $('div.main').removeClass('col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2').delay(500)
        $('div.main').addClass('col-md-12').delay(500)
        $(this).text('show filter');
      }else{
     
        //$('.main').animate({left: '+=15%'},500);
        //$('.main').width(wid);
        $('div.main').delay(500).addClass('col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2')
        $('div.main').delay(500).removeClass('col-md-12')
        $('.sidebar').animate({left: '+=25%'}, 400);
        $(this).text('hide filter');
    
      }
    })
})