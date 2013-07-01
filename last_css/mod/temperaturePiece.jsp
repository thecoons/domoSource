<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach begin="15" end="30" var="i">
	<option value="${i}"> ${i}° C </option>  
</c:forEach>