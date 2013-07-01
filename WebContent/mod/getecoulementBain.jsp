<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
	<c:when test="${param.valeur != null }">
		<span class="getProtocole">Ecoulement :</span> <c:out value="${param.valeur}" />
	</c:when>
	<c:when test="${objet != null }">
		<p>Ecoulement :</p>
		<div class="span2">
			<p>
				<c:out value="${objet.getEtatEcoulement() }" />
			</p>
		</div>
	</c:when>
</c:choose>