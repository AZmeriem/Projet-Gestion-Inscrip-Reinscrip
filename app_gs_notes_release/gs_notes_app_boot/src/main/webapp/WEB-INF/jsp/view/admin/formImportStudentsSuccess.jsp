<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>


<jsp:include page="../fragments/adminHeader.jsp" />


<div class="container">

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">

			<jsp:include page="../fragments/menu.jsp" />

		</div>
	</nav>






	<div>
		<h3>Import Students From Excel Form</h3>
	</div>
	<div>
		<br/>
		<br/>
		<br/>
		<h3>Fichier importée avec succés</h3>
		<br/>
		<br/>
		<br/>
	</div>

	<jsp:include page="../fragments/adminfooter.jsp" />
