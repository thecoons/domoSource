<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>

<c:import url="mod/getvolume.jsp" varReader="monReader">
	 <x:parse var="doc" doc="${monReader}" />
	 <x:out select="$doc/*"/>
</c:import>