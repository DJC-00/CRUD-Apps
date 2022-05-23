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
    <title>Title Here</title>
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
        crossorigin="anonymous" />
</head>

<body>
    <div class="container text-center">
        <h1>Login and Registration</h1>
    </div>
    <div class="container">
        <div class="row justify-content-md-center">
            <div class="col-3 border mx-3 shadow">
                <form:form action="/register" method="post" modelAttribute="newUser">
                    <div class="form-group">
                        <label>Username:</label>
                        <form:input path="username" class="form-control" />
                        <form:errors path="username" class="text-danger" />
                    </div>
                    <div class="form-group">
                        <label>Email:</label>
                        <form:input path="email" class="form-control" />
                        <form:errors path="email" class="text-danger" />
                    </div>
                    <div class="form-group">
                        <label>Password:</label>
                        <form:password path="password" class="form-control" />
                        <form:errors path="password" class="text-danger" />
                    </div>
                    <div class="form-group pb-3">
                        <label>Confirm Password:</label>
                        <form:password path="confirm" class="form-control" />
                        <form:errors path="confirm" class="text-danger" />
                    </div>
                    <input type="submit" value="Register" class="btn btn-primary mb-3" />
                </form:form>

            </div>
            <div class="col-3 border mx-3 shadow">
                <form:form action="/login" method="post" modelAttribute="newLogin">
                    <div class="form-group">
                        <label>Email:</label>
                        <form:input path="email" class="form-control" />
                        <form:errors path="email" class="text-danger" />
                    </div>
                    <div class="form-group pb-3">
                        <label>Password:</label>
                        <form:password path="password" class="form-control" />
                        <form:errors path="password" class="text-danger" />
                    </div>
                    <input type="submit" value="Login" class="btn btn-success mb-3" />
                </form:form>
            </div>
            <h3 class="text-danger pt-5 text-center">${sessionError}</h3>
        </div>
</body>

</html>