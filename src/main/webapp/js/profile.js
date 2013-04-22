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
	      }
   });
}