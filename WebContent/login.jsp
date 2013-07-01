<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="W3C.jsp"/>
<body background="./inc/background.jpg" style="background-repeat: no-repeat" >
<form class="well span6 myClass" method="post" action="servletLogin" id="csslogin">
		<h3>Connexion :</h3>
		<label>Identifiant :</label>
			<input type="text" class="span3" align="middle" placeholder="Login..." id="identifiant" name="identifiant"/></br
		<label>Password :</label></br>
			<input type="password" class="span3" align="middle" placeholder="Password..." id="password" name="password"/></br>
		<c:if test="${warningConnexion!=null}"><span style="color:red;"><c:out value="${warningConnexion}"/></span></c:if></br>
		<input type="submit" class="btn btn-primary" value="Submit" align="right"/>
		<input type="hidden" name="go" value="info"/>
		&nbsp;&nbsp;&nbsp;
		<input type="reset" class="btn" name="cancel" value="Cancel"align="left"/>
		</form>
</body>
</html>

