<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Piu-Piu</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

	<c:set var="ctx" value="<%= request.getContextPath() %>"/>

    <!-- CSS -->
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/jquery-ui.css" rel="stylesheet">
    <link href="../css/jquery.ui.theme.css" rel="stylesheet">
    <link rel="shortcut icon" href="../img/favicon.ico" type="image/x-icon" />
    <style type="text/css">

      /* Sticky footer styles
      -------------------------------------------------- */

      html,
      body {
        height: 100%;
        /* The html and body elements cannot have any padding or margin. */
      }

      /* Wrapper for page content to push down footer */
      #wrap {
        min-height: 100%;
        height: auto !important;
        height: 100%;
        /* Negative indent footer by it's height */
        margin: 0 auto -60px;
      }

      /* Set the fixed height of the footer here */
      #push,
      #footer {
        height: 60px;
      }
      #footer {
        background-color: #f5f5f5;
      }

      /* Lastly, apply responsive CSS fixes as necessary */
      @media (max-width: 767px) {
        #footer {
          margin-left: -20px;
          margin-right: -20px;
          padding-left: 20px;
          padding-right: 20px;
        }
      }



      /* Custom page CSS
      -------------------------------------------------- */
      /* Not required for template or sticky footer method. */

      #wrap > .container {
        padding-top: 60px;
      }
      .container .credit {
        margin: 20px 0;
      }

      code {
        font-size: 80%;
      }

    </style>
    <link href="../css/bootstrap-responsive.css" rel="stylesheet">
	<link href="../css/typeahead.js-bootstrap.css" rel="stylesheet">

    <link rel="shortcut icon" href="../assets/ico/favicon.png">
  </head>

  <body>


    <!-- Part 1: Wrap all page content here -->
    <div id="wrap">

      <!-- Fixed navbar -->
      <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="navbar-inner">
          <div class="container">
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="brand" href="/piupiu/profiles">Piu-Piu</a>
            <div class="nav-collapse collapse">
            	<ul class="nav">
                	<li class="active"><a href="/piupiu/profiles">Home</a></li>
                	<li><a href="#sobre">Sobre</a></li>
              	</ul>
            	<form class="navbar-search pull-left" action="/piupiu/profiles/" method="get">
		          <input type="text" class="search-query" placeholder="Pesquisar" id="search" autocomplete="off">
		          <span class="icon-search"></span>
		          <input type="hidden" name="email" id="emailUser">
		        </form>
              	<p class="navbar-text pull-right">
              		Logado como ${userSession.user.email}
              		<a class="icon-white icon-off" style="margin-left: 20px;" href="${ctx}/logout" title="Sair"></a>
            	</p>
            </div><!--/.nav-collapse -->
          </div>
        </div>
      </div>

      <!-- Begin page content -->
      <div class="container">
        <div class="span3 well">
          <div class="row">
            <div class="span1">
              <a href="" class="thumbnail"><img src="http://www.gravatar.com/avatar/${user.hashFoto}?s=200" alt="photo_profile"></a>
              <p>${user.email }</p>
            </div>
            <div class="span3">
        			<p><strong>${user.name }</strong></p>
					    <span class=" badge badge-warning">0 Mensagens Pessoais</span>
					    <p>
	  				    <span class=" badge badge-follow">Seguindo ${following}</span>
  					    <span class=" badge badge-info">${followers} Seguidores</span>
					    </p>
					    <p id="pIsFollowing">
							<c:if test="${isFollowing}">
		  				    	<a href="javascript:unfollow('${user.email }')" class=" badge badge-inverse">Deixar de Seguir</a>
		  				    </c:if>
		  				    <c:if test="${not isFollowing && user.email != userSession.user.email}">
		  				    	<a href="javascript:follow('${user.email }')" class=" badge badge-success">Seguir</a>
		  				    </c:if>
					    </p>
				    </div>
          </div>
        </div>
        <div class="span6 well">
			<c:forEach items="${peeps}" var="peep">
				<div class="row">
				  <div class="span8">
				    <div class="row">
				      <div class="span1">
				        <a href="#" class="thumbnail">
				            <img src="http://placehold.it/140x100" alt="">
				        </a>
				      </div>
				      <div class="span4">
				        <p> ${peep.text}
				        </p>
				      </div>
				    </div>
				    <div class="row">
				      <div class="span8">
				        <p></p>
				        <p>
				          <i class="icon-user"></i> by <a href="#">${peep.author}</a>
				          | <i class="icon-calendar"></i> ${peep.date}
				        </p>
				      </div>
				    </div>
				  </div>
				</div>
				<hr>
			</c:forEach>
    	    
        </div>

      </div> <!-- container -->
      
      <div id="push"></div>

      <div id="footer">
        <div class="container">
          <p class="muted credit">&copy; Piu-Piu Company 2013</p>
        </div>
      </div>


    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="../js/jquery-1.8.3.js"></script>
    <script src="../js/jquery-ui.js"></script>
    <script src="../js/bootstrap.js"></script>
    <script src="../js/bootstrap-typeahead.js"></script>
    <script>
    $(function() {
    	
        $(".maxlength").keyup(function(event){
            var target    = $("#content-countdown");
            var max        = 140;
            var len     = $(this).val().length;
            var remain    = max - len;
            if(len > max) {
                var val = $(this).val();
                $(this).val(val.substr(0, max));
                remain = 0;
            }
            target.html(remain + ' caracteres restantes');
        });
        
        $('#search').typeahead({
        	minLength : 3,
            source: function (query, process) {
                return $.get('/piupiu/users/find', { query: query }, function (data) {
                	 names = new Array();
                     emails    = new Array();
             
                     for (var i= 0; i < data.list.length; i++) {
                        var user = data.list[i];
                        names[i] = user.name;
                        emails[i] = user.email;
                     }
                    return process(names);
                });
            },
  
            updater: function(item) {
				for ( var i = 0; i < names.length; i++) {
					if(names[i] === item) {
						$('#emailUser').val(emails[i]);
						var email = emails[i];
						break;
					}
				}
            	var url = "/piupiu/profiles/" + email ;
            	window.location = url;
            	return item;
            }
        });
    });
    
    function follow(email){
    	$.ajax({
		      url: "${ctx}/users/follow",
		      type: "POST",
		      data: '{ "userToFollow":{ "email":  "' + email + '" }}',
		      contentType: "application/json",
		      async: true,
		      success: function(html){
		    	  $("#pIsFollowing").empty();
		    	  $("#pIsFollowing").append("<a></a>");
		    	  $("#pIsFollowing a").attr("href","javascript:unfollow('${user.email }')").attr("class","badge badge-inverse").text("Deixar de Seguir");
		      },
		      error: function(data, status, e) {
		    	  //Show Error Div 
			  }
		   });
    }
    
    function unfollow(email){
    	$.ajax({
		      url: "${ctx}/users/unfollow",
		      type: "POST",
		      data: '{ "userToUnfollow":{ "email":  "' + email + '" }}',
		      contentType: "application/json",
		      async: true,
		      success: function(html){
		    	  $("#pIsFollowing").empty();
		    	  $("#pIsFollowing").append("<a></a>");
		    	  $("#pIsFollowing a").attr("href","javascript:follow('${user.email }')").attr("class","badge badge-success").text("Seguir");
		      },
		      error: function(data, status, e) {
		    	  //Show Error Div 
			  }
		   });
    }
    
    </script>

  </body>
</html>