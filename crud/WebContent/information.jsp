<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@page import="br.edu.insper.*,java.util.*"%>
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>CRUD: Information</title>
</head>
<body class="bg-light">
  	<%
  		String information = (String) request.getAttribute("information");
  	%>
  	<div class="container d-flex flex-column align-items-center justify-content-center">
  		<h1 class="display-4 mt-5">Message</h1>
  		<h2 class="display-6 mt-5"><%=information %></h2>
  		<button onclick="window.history.back()" class="mt-5 btn btn-secondary btn-lg">Ok!</button>
  	</div>
</body>
</html>