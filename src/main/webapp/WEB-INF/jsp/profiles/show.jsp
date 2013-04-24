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
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/jquery-ui.css" rel="stylesheet">
    <link href="css/jquery.ui.theme.css" rel="stylesheet">
    
    <%@ include file="../template/_style.jsp" %>
    
    <link href="css/bootstrap-responsive.css" rel="stylesheet">
	<link href="css/typeahead.js-bootstrap.css" rel="stylesheet">
	<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />
  </head>

  <body>

    <!-- Part 1: Wrap all page content here -->
    <div id="wrap">

      <%@ include file="../template/_navbar.jsp" %>

      <!-- Begin page content -->
      <div class="container">
        <div class="span3 well">
          <div class="row">
            <div class="span1">
              <a href="" class="thumbnail"><img src="http://www.gravatar.com/avatar/${userSession.user.hashFoto}?s=200" alt="photo_profile"></a>
              <p>${userSession.user.email }</p>
            </div>
            <div class="span3">
        			<p><strong>${userSession.user.name }</strong></p>
					    <span class=" badge badge-warning">0 Mensagens Pessoais</span>
					    <p>
	  				    <span class=" badge badge-info">Seguindo ${following}</span>
  					    <span class=" badge badge-info">${followers} Seguidores</span>
					    </p>
				    </div>
          </div>
        </div>
        <div class="span6 well">
  	      <form accept-charset="UTF-8" id="peepForm">
            <textarea class="span6 maxlength" id="new_message" name="new_message" placeholder="Escreva sua mensagem" rows="5"></textarea>
	        <h6 class="pull-right" id="content-countdown">140 caracteres restantes</h6>
  	        <button class="btn btn-info" type="button" onclick="peep();">Pie para seus amigos</button>
   	      </form>
          <hr>
          <div id="wall">
          </div>
    	    
        </div>

      </div> <!-- container -->
      
      <div id="push"></div>

      <div id="footer">
        <div class="container">
          <p class="muted credit">&copy; Piu-Piu Company 2013</p>
        </div>
      </div>
      
      <div id="idShowLikes">
      </div>

      <%@ include file="../template/_footer.jsp" %>
      
    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/jquery-1.8.3.js"></script>
    <script src="js/jquery-ui.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/bootstrap-typeahead.js"></script>
    <script src="js/peeps.js"></script>
    <script src="js/search.js"></script>
    <script>
    $(function() {
    	loadPeeps();
    	charactersCount();
    	userSearchAutoComplete();        
    });
    
    </script>
</div>
  </body>
</html>