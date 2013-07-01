<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach begin="0" end="100" step="10" var="i">
	<option value="${i}"> ${i}%</option>
</c:forEach>