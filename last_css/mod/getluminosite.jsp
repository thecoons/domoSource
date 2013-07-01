<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!-- est un string qui renvoie l'état de la luminosite -->


<p>Luminosité :</p>
<div class="span2">
	<p>
		<c:out value="${objet.getLuminosite() }" />
		%
	</p>
</div>