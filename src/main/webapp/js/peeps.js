function peep(){
    	var message = $('#new_message').val();
    	$.ajax({
		      url: "/piupiu/peeps",
		      type: "POST",
		      data: '{ "peep":{ "text":  "' + message + '" }}',
		      contentType: "application/json",
		      async: true,
		      success: function(html){
		    	  $('#new_message').val('');
		    	  $('#wall').prepend(html);
		      },
		      error: function(data, status, e) {
		    	  //Show Error Div 
			  }
		   });
    }
    
    function loadPeeps(author) {
    	$.ajax({
		      url: "/piupiu/peeps/show/"+author,
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
    function mudarIMG(author){
    	
    }
    
