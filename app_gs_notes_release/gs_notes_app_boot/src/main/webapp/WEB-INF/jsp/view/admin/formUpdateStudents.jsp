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
	
	<div class="container">
		<div class="row">
			<br/>
			<br/>
			<br/>
			<br/>
			<p>Les données des étudiants suivants ne sont pas les mêmes que celles présentes dans l'application.</p>
			<p>Veuillez décocher les étudiants à ne pas mettre à jour.</p>
		</div>
		
		<div class="row">
			<f:form action="${pageContext.request.contextPath}/admin/updateEtudiantsFromExcel"
				method="POST">
				
				<table class="table">
					<thead>
						<tr>
							<th scope="col">Données du fichier</th>
							<th scope="col">Données en base</th>
							<th scope="col">Mettre à jour?</th>
						</tr>
					</thead>
					<c:forEach items="${listStudents}" var="data">
						<tr>
							<td><c:out value="${data.nomEtudiant}, ${data.cneEtudiant}" /></td>
							<td><c:out value="${data.ancienNomEtudiant}, ${data.ancienCneEtudiant}" /></td>
							<td>
								<c:choose>
						            <c:when test="${data.update==true}">
						            	<input type="checkbox" name="dance" checked/>
						            </c:when>
						            <c:otherwise>
						            	<input type="checkbox" name="dance"/>
						            </c:otherwise>
						        </c:choose>
						       </td>
						</tr>
		
					</c:forEach>
		
				</table>
				
				<br/>
				<br/>
				<button type="submit" class="btn btn-primary">Update</button>
	
			</f:form>
		</div>
	</div>
</div>
	<jsp:include page="../fragments/adminfooter.jsp" />
