<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!-- est un string qui renvoie l'�tat de la luminosite -->


<p>Luminosit� :</p>
<div class="span2">
	<p>
		<c:out value="${objet.getLuminosite() }" />
		%
	</p>
</div>