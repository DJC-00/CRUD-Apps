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
<title>New Ninja Page</title>
  <!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
      rel="stylesheet" 
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
      crossorigin="anonymous">

</head>
<body>
    <div class="container">
    
    <h1>Create a new Baby Name</h1>

    <br>
    <div class="container d-flex gap-3 pb-3">
        <a class="btn btn-primary" href="/dashboard">Dashboard</a>
    </div>
    <hr>
    <div class="container text-center pt-3">
        <h3 class="text-danger">${babyDuplicate}</h3>
    </div>
    <div class="container border shadow w-50 pt-5 pb-5 col">
        <h1 class="text-center">New Baby Name Form</h1>
        <form:form class="px-5" action="/processBabyName" method="post" modelAttribute="baby">
            <p class="row">
                <form:label path="babyName">Name: </form:label>
                <form:errors class="alert-danger"  path="babyName"/>
                <form:input path="babyName"/>
            </p>
                <p class="row">
                <form:select path="gender">
                <form:label path="babyName">Name: </form:label>
                <form:option value="neutral">Neutral</form:option>
                <form:option value="male">Male</form:option>
                <form:option value="female">Female</form:option>
                </form:select>
            </p>
            <p class="row">
                <form:label path="origin">Origin: </form:label>
                <form:errors class="alert-danger"  path="origin"/>
                <form:input path="origin"/>
            </p>
            <p class="row pb-3">
                <form:label path="meaning">Meaning</form:label>
                <form:errors class="alert-danger" path="meaning"/>
                <form:textarea path="meaning"/>
            </p>
            <input type="submit" value="Submit"/>
        </form:form>  
        </div>

    </div>
</body>
</html>
