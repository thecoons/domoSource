<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<p>Température :</p>
<div class="span2">
<p> 
<c:out value="${objet.getTemperatureBain() }"/>° C
</p>
</div>