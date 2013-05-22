<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="<%=request.getContextPath()%>" />

<div id="m_uprGeralOverlay" class="carregaLoader bgLoader" style="display: none;">
    <div id="m_uprGeralLoader" class="loader">
        Carregando...
        <br/>
        <img style="padding-left: 10px" id="m_imgLoader" src="${ctx}/img/loader2.gif" />
    </div>
</div>

<script src="js/jquery-1.8.3.js"></script>

<script type="text/javascript">
	$(function () {
		$("form").bind("submit", function() {
	    	$(".carregaLoader").show();
    	});

    	$.ajaxPrefilter(function(options, _, jqXHR) {
			$(".carregaLoader").show();

			jqXHR.complete(function() {
				$(".carregaLoader").hide();
			});
		});
	});
</script>