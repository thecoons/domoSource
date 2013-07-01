<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>Ecoulement :</p>
<div class="span2">
<p> 
<c:out value="${objet.getEtatEcoulement() }"/>%
</p>
</div>