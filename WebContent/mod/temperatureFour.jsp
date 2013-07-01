<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach begin="50" end="280" step="10" var="i">
	<option value="${i}"> ${i}° C </option>  
</c:forEach>