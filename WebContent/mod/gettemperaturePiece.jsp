<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
	<c:when test="${param.valeur != null }">
		<span class="getProtocole">Temp�rature :</span> <c:out value="${param.valeur}" />�C
	</c:when>
	<c:when test="${objet != null }">
		<p>Temp�rature :</p>
		<div class="span2">
			<p>
				<c:out value="${objet.getTemperatureChauffage() }" />�C
			</p>
		</div>
	</c:when>
</c:choose>