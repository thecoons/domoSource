<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach begin="1" end="18" var="i">
	<option value="${i}">Canal ${i} </option>  
</c:forEach>