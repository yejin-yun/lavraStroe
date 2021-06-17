function moveTarget(targetUri) {
		//alert(targetUri);
		form1.action = targetUri;  //쿼리스트링 사라짐. get은 사라지는데, post는 사라지지 않음. https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=damuri1&logNo=100157143687
		form1.submit();
	}
	
	function changeLikeImg(itemId, wish) {
		 //$('#like_img'+itemId).click(function() {
             if( $('#like_img'+itemId).attr('src') == "/images/bagic/heart-thin.png"){
                 $('#like_img'+itemId).attr('src','/images/bagic/heart-full.png'); 
             }else {
                 $('#like_img'+itemId).attr('src','/images/bagic/heart-thin.png'); 
             }
    	//});
		 
             wishItem(itemId, wish);
	}
	
	function wishItem(itemId, wish){
		//var msg = itemId; //itemId 하나만 json으로 보낼때. 아래 방식은 객체라서  int로 변환 못시킴
		
		var msg = {
			"itemId":itemId,
			"isInWishlist":wish
		};
		var jsonStr = JSON.stringify(msg);
	
		$.ajax({
			type: "POST",
			url: "/accessory/wish",
			contentType : "application/json",
			data: jsonStr,
			processData: false,
			success: function(response) {
				if(response === "success") {
					//alert(wish);
				}
				else if(response === "LoginForm") {
					var allow = confirm('로그인을 먼저 해주세요');
					if(allow){
						location.href="/shop/loginForm.do";
					}
					
					
				}
				else {
					location.href="/error";
				}
				
			},
			error: function(){
				//alert(wish);
				alert("이미 위시리스트에 담겨 있습니다.", arguments);
			}
		});
	}