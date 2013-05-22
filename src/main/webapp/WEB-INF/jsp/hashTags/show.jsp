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
      <link href="${ctx}/css/bootstrap.css" rel="stylesheet">
      <%@ include file="../template/_style.jsp" %>
      <link href="${ctx}/css/bootstrap-responsive.css" rel="stylesheet">
      <link href="${ctx}/css/typeahead.js-bootstrap.css" rel="stylesheet">
      <link href="${ctx}/css/jquery-ui.css" rel="stylesheet">
      <link href="${ctx}/css/jquery.ui.theme.css" rel="stylesheet">
      <link href="${ctx}/css/loading.css" rel="stylesheet">
      <link rel="shortcut icon" href="${ctx}/img/favicon.ico" type="image/x-icon" />
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
                        <span class=" badge badge-info">Seguindo ${fn:length(following)}</span>
                        <span class=" badge badge-info">${fn:length(followers)} Seguidores</span>
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
               <input type="hidden" name="hashTag" id="hashTag" value="${hashtag}">
               <input type="hidden" name="totalPeeps" id="totalPeeps" value="0">
               <p class="sub-text">Resultados para HashTag: #${hashtag}</p>
               
               <hr>
                
               <div id="wall">
               </div>
               <div>
                  <button class="btn btn-info" type="button" onclick="loadPeepsHashTag('${hashtag}');">Veja mais</button>
               </div>
            </div>
            <div id="divListAllFollowers" style="display: none; overflow-x: hidden;" title="Usuários Seguidores">
	        </div>
	        <div id="divListAllFollowing" style="display: none; overflow-x: hidden;" title="Seguindo">
	        </div>
         </div>
        
         <%@ include file="../template/_footer.jsp" %>
         <%@ include file="../template/_loading.jsp" %>
         <!-- Le javascript
            ================================================== -->
         <!-- Placed at the end of the document so the pages load faster -->
         <script src="${ctx}/js/jquery-1.8.3.js"></script>
         <script src="${ctx}/js/jquery-ui.js"></script>
         <script src="${ctx}/js/bootstrap.js"></script>
         <script src="${ctx}/js/bootstrap-typeahead.js"></script>
         <script src="${ctx}/js/profile.js"></script>
         <script src="${ctx}/js/search.js"></script>
         <script src="${ctx}/js/peeps.js"></script>
         <script>
            $(function() {
            	loadPeepsHashTag($('#hashTag').val());
            	charactersCount();
            	userSearchAutoComplete();
            });
            

            function updateTotalPeeps(){
            	var total = $('#totalPeeps').val();
            	var count = eval(total) + 1;
                $('#totalPeeps').val(count);	
            }
            
            function loadPeepsHashTag(hashtag){
				var skip = $('#totalPeeps').val();
            	
            	$.ajax({
            	      url: "/piupiu/hashtags/"+ hashtag+ "/skip/" +skip,
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

           
         </script>
      </div>
   </body>
</html>