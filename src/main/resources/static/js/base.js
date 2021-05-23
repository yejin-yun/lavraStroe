
       $(function() {
        $("#work").click(function(){
            
            if( $(".work_sub_menu").is(":visible") ){
                $(".work_sub_menu").slideUp();
            }else{
                if($(".trade_sub_menu").is(":visible")){
                     $(".trade_sub_menu").slideUp();
                }
                $(".work_sub_menu").slideDown();
            }
        }).mouseover(function()
        {
            $(".work_sub_menu").slideDown();
        }).mouseleave(function()
        {
            $(".work_sub_menu").slideUp();
        });

        $("#trade").click(function(){

            if( $(".trade_sub_menu").is(":visible") ){
                $(".trade_sub_menu").slideUp();
            }else{
                if( $(".work_sub_menu").is(":visible") ){
                    $(".work_sub_menu").slideUp();
                }   
                $(".trade_sub_menu").slideDown();
            }
        }).mouseover(function()
        {
            $(".trade_sub_menu").slideDown();
        }).mouseleave(function()
        {
            $(".trade_sub_menu").slideUp();
        });
    });
   $(document).ready(function(){

        $('.menubar_btn>a').click(function(){
            $('.menu_bg').show(); 
            $('.sidebar_menu').animate({
                right:0
            });
             setTimeout(carousel, 5000);  
        });
        $('.close_btn>a').click(function(){
            $('.menu_bg').hide(); 
            $('.sidebar_menu').animate({
                right: '-' + 50 + '%'
            });
             setTimeout(carousel, 5000);  
        });

    });

    $(document).ready(function(){

        $('.search_btn>a').click(function(){
            if($('.search_header').css('display') == 'none') {
                $('.search_header').show();
            }else {
                $('.search_header').hide();
            }
        });
        /*
        $('.s_close_btn > a').click(function(){
            $('.search_header').hide();
        });
        */
    });