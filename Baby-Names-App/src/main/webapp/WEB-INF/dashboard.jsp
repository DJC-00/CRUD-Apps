<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- c:out ; c:forEach ; c:if -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Formatting (like dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8" />
	<title>Dashboard</title>
	<!-- Bootstrap -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
		rel="stylesheet"
		integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
		crossorigin="anonymous" />
</head>

<body>
	<div class="d-flex justify-content-between bg-secondary px-5">
		<div class="d-flex gap-3 pb-3 bg-secondary pt-3 ">
			<h3 class="text-white"><c:out value="Current User: ${currentUser.username}" /></h3>
			<a class="btn btn-primary" href="/dashboard">Dashboard</a>
		</div>
		<div class="d-flex gap-3 pb-3 bg-secondary pt-3">
			<a class="btn btn-danger" href="/logout">Logout</a>
		</div>
	</div>
	<div class="container">
		<h1>Hello <span class="text-primary">${currentUser.username}</span>, here are some name suggestions </h1>
		<hr>
		<h1 class="text-center">Baby Names</h1>

		<br>

		<table class="table table-hover border shadow">
			<thead>
				<tr>
					<th class="align-middle">Submitted by: </th>
					<th class="align-middle">Name: </th>
					<th class="align-middle">Gender: </th>
					<th class="align-middle">Origin</th>
					<th class="align-middle">Votes</th>
					<th class="align-middle"></th>

				</tr>
			</thead>
			<tbody>
				<c:forEach var="babyInList" items="${allBabies}">
					<tr>
						<td>
							<c:out value="${babyInList.user.username}"></c:out>
						</td>
						<td>
							<a href="/oneBabyName/${babyInList.id}">
								<c:out value="${babyInList.babyName}"></c:out>
							</a>
						</td>
						<td>
							<c:out value="${babyInList.gender}"></c:out>
						</td>
						<td>
							<c:out value="${babyInList.origin}"></c:out>
						</td>
						</td>
						<td>
						<c:set var="babyVotes" value="${0}" scope="page"/>
						<c:forEach var="voteInList" items="${allVotes}">
							<c:if test="${babyInList.id == voteInList.baby.id}" >
								<c:set var="babyVotes" value="${babyVotes + 1}" scope="page"/>
							</c:if>
						</c:forEach>
						<c:out value="${babyVotes}"></c:out>
						</td>
						<td>
						<c:set var="voteFound" value="${false}" scope="page"/>
						<c:forEach var="userVoteInList" items="${userVotes}">
							<c:if test="${babyInList.id == userVoteInList.baby.id}" >
								<c:set var="voteFound" value="${true}" scope="page"/>
							</c:if>
						</c:forEach>
						<c:choose>
							
							<c:when test="${voteFound == true}" >
								<a class="btn btn-danger" href="/changeVote/${babyInList.id}">Remove Vote</a>
							</c:when>
							
							<c:otherwise>
								<a class="btn btn-success" href="/changeVote/${babyInList.id}">Upvote</a>
							</c:otherwise>
						</c:choose>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="container pt-5">
		<a class="btn btn-primary" href="/babyName/new">Submit a Name</a>
	</div>
</body>

</html>