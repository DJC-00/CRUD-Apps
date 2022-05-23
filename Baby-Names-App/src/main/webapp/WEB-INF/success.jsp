<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>Template for JSP</title>
    <link rel='stylesheet' href='/webjars/bootstrap/css/bootstrap.min.css'>
</head>
<body>
    <div class="container shadow border p-5">
        <div class="row">
            <h1>Welcome back <span class="text-primary"><c:out value="${currentUser.username}" /></span></h1>
        </div>
    
        <div class="row  pt-4">
            <div class="col">       
                <a class="btn btn-primary" href="/dashboard">Dashboard</a>
                <a class="btn btn-danger" href="/logout">Logout</a>
            </div>
        </div>
    </div>
</body>
