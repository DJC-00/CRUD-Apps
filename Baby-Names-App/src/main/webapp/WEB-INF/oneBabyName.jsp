<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <!-- c:out ; c:forEach ; c:if -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
   <!-- Formatting (like dates) -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
   <!-- form:form -->
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
   <!-- for rendering errors on PUT routes -->
 <%@ page isErrorPage="true" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Baby Info</title>
  <!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
      rel="stylesheet" 
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
      crossorigin="anonymous">

</head>
<body>
	<div class="container">
		<div class="container d-flex gap-3 pt-3 pb-3">
			<a class="btn btn-primary" href="/dashboard">Dashboard</a>
		</div>
        <hr>
		<h1 class="text-center">Baby Information</h1>
        <h3 class="text-center">(submitted by ${baby.user.username})</h3>
		<br>
		<div class="container border shadow w-50">
			<table class="table">
				<tbody>
					<tr>
					<th scope="row">Name</th>
					<td>${baby.babyName}</td>
					</tr>
					<tr>
					<th scope="row">Gender</th>
					<td>${baby.gender}</td>
					</tr>
					<tr>
					<th scope="row">Origin</th>
					<td>${baby.origin}</td>
					</tr>
					<tr>
					<th scope="row">Meaning</th>
					<td>${baby.meaning}</td>
					</tr>

                    <tr>
					<th scope="row">vote count</th>
					<td>${votes}</td>
					</tr>
                    <tr>
                    <c:set var="voteFound" value="${false}" scope="page"/>
                    <c:forEach var="userVoteInList" items="${userVotes}">
                        <c:if test="${baby.id == userVoteInList.baby.id}" >
                            <c:set var="voteFound" value="${true}" scope="page"/>
                        </c:if>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${voteFound == true}" >
                            <th scope="row">You have upvoted this name</th>
                            <td></td>
                        </c:when>
                        
                        <c:otherwise>
                            <th scope="row">You have not upvoted this name</th>
                            <td><a class="btn btn-success" href="/changeVoteFromBaby/${baby.id}">Upvote</a></td>
                        </c:otherwise>
                    </c:choose>
					</tr>
				</tbody>
			</table>
		</div>
        <br>
        <hr>
        <div class="container d-flex gap-3 w-50 ">
            <c:if test="${baby.user.id == user_id}">
                <h2>Creator Actions:</h2>
                <a class="btn btn-primary" href="/oneBabyName/${baby.id}/edit">Edit</a>
            </c:if>
        </div>
	</div>
</body>
</html>