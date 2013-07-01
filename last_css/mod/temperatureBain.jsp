<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach begin="20" end="42" var="i">
	<option value="${i}"> ${i}° C </option>  
</c:forEach>