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
    
    <%@ include file="../template/_style.jsp" %>
    
    <link href="../css/bootstrap-responsive.css" rel="stylesheet">
	<link href="../css/typeahead.js-bootstrap.css" rel="stylesheet">
    <link rel="shortcut icon" href="../img/favicon.ico" type="image/x-icon" />
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
              <a href="" class="thumbnail"><img src="http://www.gravatar.com/avatar/${user.hashFoto}?s=200" alt="photo_profile"></a>
              <p>${user.email }</p>
            </div>
            <div class="span3">
        			<p><strong>${user.name }</strong></p>
					    <span class=" badge badge-warning">0 Mensagens Pessoais</span>
					    <p>
	  				    <span class=" badge badge-info">Seguindo ${following}</span>
  					    <span class=" badge badge-info">${followers} Seguidores</span>
					    </p>
					    <p id="pIsFollowing">
							<c:if test="${isFollowing}">
		  				    	<a href="javascript:unfollow('${user.email }')" class=" badge badge-important">Deixar de Seguir</a>
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
				            <img src="http://www.gravatar.com/avatar/${peep.hash}?s=200" alt="">
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
				          <i class="icon-user"></i> by <a href="/piupiu/profiles/${peep.author}">${peep.author}</a>
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
      
      <%@ include file="../template/_footer.jsp" %>

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="../js/jquery-1.8.3.js"></script>
    <script src="../js/jquery-ui.js"></script>
    <script src="../js/bootstrap.js"></script>
    <script src="../js/bootstrap-typeahead.js"></script>
    <script src="../js/profile.js"></script>
    <script src="../js/search.js"></script>
    <script>
    $(function() {
    	charactersCount();
    	userSearchAutoComplete();
    });
    </script>
 </div>
  </body>
</html>