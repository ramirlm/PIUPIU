<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${isALiker}">
	<button class="btn btn-info" type="button" onclick="dislike('${peep.id}');">Descurtir</button>
</c:if>
<c:if test="${not isALiker}">
	<button class="btn btn-info" type="button" onclick="like('${peep.id}');">Curtir</button>
</c:if>
<c:choose>
	<c:when test="${peep.likers.size() > 0}">
		<p align="right"> <span class=" badge badge-info"><c:out value="${peep.likers.size()}"/> Curtem esse post</span> </p>
	</c:when>
	<c:otherwise>
		<p align="right"> <span class=" badge badge-info">0 Curtem esse post</span> </p>
	</c:otherwise>
</c:choose>
<br clear="all">
<c:forEach items="${peep.likers}" var="liker">
	<div class="row">
	  <div class="span8">
	    <div class="row">
	      <div class="span1">
	        <a href="#" class="thumbnail">
	            <img src="http://www.gravatar.com/avatar/${liker.hash}?s=200" alt="">
	        </a>
	      </div>
	      <div class="span4">
	        <p> ${liker.userEmail}
	        </p>
	      </div>
	    </div>
	  </div>
	</div>
	<hr>
</c:forEach>