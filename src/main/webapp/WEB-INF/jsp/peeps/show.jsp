<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${peeps}" var="peep">
	<div class="row">
	  <div class="span8">
	    <div class="row">
	      <div class="span1">
	        <a href="/piupiu/profiles/${peep.author}" class="thumbnail">
	            <img src="http://www.gravatar.com/avatar/${peep.hash}?s=200" alt="">
	        </a>
	      </div>
	      <div class="span4">
	        <p> ${peep.formattedText}
	        </p>
	      </div>
	    </div>
	    <div class="row">
	      <div class="span8">
	        <p></p>
	        <p>
	          <i class="icon-user"></i> by <a href="/piupiu/profiles/${peep.author}">${peep.author}</a>
	          | <i class="icon-calendar"></i> ${peep.date}
	          | <a title="RePiar" class="icon-random" href="javascript: showRepeepDialog('${peep.author}','${peep.text}')"></a>
	          | <a title="Quem Curte" href="javascript: showLikers('${peep.id}')">
	          		<img src="img/favicon.ico" alt="Quem Curte" height="16" width="16">
	          	</a>
	       		<c:choose>
					<c:when test="${peep.likers.size() > 0}">
						<span class=" badge badge-info"><c:out value="${peep.likers.size()}"/></span>
					</c:when>
					<c:otherwise>
						<span class=" badge badge-info">0</span>
					</c:otherwise>
				</c:choose>
	        </p>
	      </div>
	    </div>
	  </div>
	</div>
	<hr>
	<script>
		updateTotalPeeps();
	</script>
</c:forEach>