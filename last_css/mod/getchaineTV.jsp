<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<p>Chaine : </p>
<div class="span2">
<p>
Canal <c:out value="${objet.getChaine()}"/>
</p>
</div>