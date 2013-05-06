function follow(email){
	$.ajax({
		url: "/piupiu/users/follow",
		type: "POST",
		data: '{ "userToFollow":{ "email":  "' + email + '" }}',
		contentType: "application/json",
		async: true,
		success: function(html){
			location.reload();
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
	    	  location.reload();
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