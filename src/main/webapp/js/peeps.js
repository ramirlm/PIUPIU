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
    
function loadPeeps() {
    	$.ajax({
	      url: "/piupiu/peeps/show",
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
    
function showRepeepDialog(author, peep){
	$('#new_message').val('RP @' + get_username(author) +': '+ peep);
	$('html,body').animate({scrollTop: 0},'slow');
}
    
function get_username(email) {
    var m = email.match('([^@]+)');
    return m ? m[0] : null;
}