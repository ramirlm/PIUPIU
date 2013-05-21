<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
      <%@ include file="../template/_style.jsp" %>
      <link href="css/bootstrap-responsive.css" rel="stylesheet">
      <link href="css/typeahead.js-bootstrap.css" rel="stylesheet">
      <link href="css/jquery-ui.css" rel="stylesheet">
      <link href="css/jquery.ui.theme.css" rel="stylesheet">
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
                        <span class=" badge badge-info">Seguindo ${fn:length(following)}</span>
                        <span class=" badge badge-info">${fn:length(followers)} Seguidores</span>
                     </p>
                  </div>
               </div>
               <hr>
               <div class="row">
                  <div class="span3">
                  	<p class="sub-text"><a href="javascript:listAll('${userSession.user.email }','following','divListAllFollowing');">Seguindo</a></p>
                     <ul class="thumbnails">
	                     <c:forEach items="${following}" var="seguindo">
	                        <li class="span1">
	                           <a href="${ctx}/profiles/${seguindo.userEmail}" class="thumbnail">
	                           <img src="http://www.gravatar.com/avatar/${seguindo.hash}?s=50" alt="${seguindo.userEmail}">
	                           </a>
	                        </li>
	                     </c:forEach>
                     </ul>
                  </div>
               </div>
               <hr>
               <div class="row">
                  <div class="span3">
                  	<p class="sub-text"><a href="javascript:listAll('${userSession.user.email }','followers','divListAllFollowers');">Seguidores</a></p>
                     <ul class="thumbnails">
	                     <c:forEach items="${followers}" var="seguidor">
	                        <li class="span1">
	                           <a href="${ctx}/profiles/${seguidor.userEmail}" class="thumbnail">
	                           <img src="http://www.gravatar.com/avatar/${seguidor.hash}?s=50" alt="${seguidor.userEmail}">
	                           </a>
	                        </li>
	                     </c:forEach>
                     </ul>
                  </div>
               </div>
            </div>
            <div class="span6 well">
               <form accept-charset="UTF-8" id="peepForm">
               	  <input type="hidden" name="totalPeeps" id="totalPeeps" value="0">
                  <textarea class="span6 maxlength" id="new_message" name="new_message" placeholder="Escreva sua mensagem" rows="5"></textarea>
                  <h6 class="pull-right" id="content-countdown">140 caracteres restantes</h6>
                  <button class="btn btn-info" type="button" onclick="peep();">Pie para seus amigos</button>
                  <button class="btn btn-mini" type="button" onclick="openDialogShortUrl();">Encurte</button>
               </form>
               <form id="form_photo" method="post" enctype="multipart/form-data" action="/piupiu/profiles/uploadImage">
	               	<input id="file" type="file" name="photo"/>
	               	<input id="photo_message" type="hidden" name="message" value="">
               </form>
               <hr>
               <div id="wall">
               </div>
               <div>
	          	<button class="btn btn-info" type="button" onclick="loadMorePeeps();">Veja mais</button>
	           </div>
            </div>
         </div>
         <!-- container -->
         <div id="idShowLikes" title="Curtidas" style="display: none; overflow-x: hidden;">
         </div>
         <div id="divListAllFollowers" style="display: none; overflow-x: hidden;" title="Usuários Seguidores">
         </div>
         <div id="divListAllFollowing" style="display: none; overflow-x: hidden;" title="Seguindo">
         </div>
         <div id="confirmationDialog" title="Atenção" style="display: none;">
         	<p>Tem certeza que deseja excluir este post?</p>
         	<input id="peepId" type="hidden"/>
         </div>
         <div id="idShortUrl" title="Short Url" style="display: none">
         	<form accept-charset="UTF-8" id="shortUrlForm">
         		<p class="sub-text">Url a ser Encurtada</p>
         		<input id="url" class="span3" type="text" placeholder="Url a ser Encurtada" name="url">
         		<button class="btn btn-success" type="button" onclick="shortUrl();">Encurtar</button>
         	</form>
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