<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="<%= request.getContextPath() %>"/>
<c:forEach items="${peeps}" var="peep">
	<div class="row">
	  <div class="span8">
	    <div class="row">
	      <div class="span1">
	        <a href="${ctx}/profiles/${peep.author}" class="thumbnail">
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
	          <i class="icon-user"></i> by <a href="${ctx}/profiles/${peep.author}">${peep.author}</a>
	          <br/><i class="icon-calendar"></i> ${peep.date}
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