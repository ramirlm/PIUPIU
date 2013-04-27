function follow(email){
	$.ajax({
		url: "/piupiu/users/follow",
		type: "POST",
		data: '{ "userToFollow":{ "email":  "' + email + '" }}',
		contentType: "application/json",
		async: true,
		success: function(html){
			$("#pIsFollowing").empty();
			$("#pIsFollowing").append("<a></a>");
			$("#pIsFollowing a").attr("href","javascript:unfollow('${user.email }')").attr("class","badge badge-important").text("Deixar de Seguir");
			
			var followersCounter = $("#followersCounter");
			var followersCounterInt = parseInt(followersCounter.text());
			followersCounter.text(followersCounterInt + 1);
		}
   });
}

function unfollow(email){
	$.ajax({
	      url: "/piupiu/users/unfollow",
	      type: "POST",
	      data: '{ "userToUnfollow":{ "email":  "' + email + '" }}',
	      contentType: "application/json",
	      async: true,
	      success: function(html){
	    	  $("#pIsFollowing").empty();
	    	  $("#pIsFollowing").append("<a></a>");
	    	  $("#pIsFollowing a").attr("href","javascript:follow('${user.email }')").attr("class","badge badge-success").text("Seguir");
	    	  
	    	  var followersCounter = $("#followersCounter");
			  var followersCounterInt = parseInt(followersCounter.text());
			  followersCounter.text(followersCounterInt - 1);
	      }
   });
}

function loadProfilePeeps(usermail) {
	$.ajax({
      url: "/piupiu/profiles/peeps/" + usermail, 
      type: "GET",
      async: true,
      success: function(html){
    	  $('#wall').append(html);
      },
      error: function(data, status, e) {
    	  //Show Error Div 
	  }
   });
}