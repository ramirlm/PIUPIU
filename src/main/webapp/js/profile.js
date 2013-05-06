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

function updateTotalPeeps(){
	var total = $('#totalPeeps').val();
	var count = eval(total) + 1;
    $('#totalPeeps').val(count);	
}

function loadMorePeepsProfile(usermail){
	var skip = $('#totalPeeps').val();
	
	$.ajax({
	      url: "/piupiu/profiles/peeps/"+ usermail+ "/skip/" +skip,
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
