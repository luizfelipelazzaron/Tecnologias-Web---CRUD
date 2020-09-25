<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <%@page import="br.edu.insper.*,java.util.*"%>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

    <title>CRUD: Tasks</title>
  </head>
  <body class="bg-light">
  	<%
  		User user = (User) request.getAttribute("name");
  		List<Task> tasks = (List<Task>) request.getAttribute("tasks");
  	%>
    <div class="container">
    	<div class="d-flex align-items-center p-2 m-2">
    		<p class="text-primary">hello, <%=user.getName()%></p>
    	</div>
    	<div class="d-flex align-items-center justify-content-around p-2 m-2">
	    	<div class="input-group mb-3">
		    	<!--<a href="create" type="button" class="btn btn-success">Create +</a> -->
		    	<form action="./Create" method="get">
		    		<input type="submit" value = "Create +" class="btn btn-success">
		    		<input type="hidden" name="id" value="<%=user.getId()%>">
		    	</form>
		    </div>
			<form action="./Search" method="post"> 
		    	<div class="input-group mb-3">
					  <div class="input-group-prepend">
					    <span class="input-group-text" id="basic-addon1">Search</span>
					  </div>
					  	<input type="text" name="searchedTitle" class="form-control" placeholder="search by title" aria-label="Username" aria-describedby="basic-addon1">
					  	<input type="hidden" name="userId" value="<%=user.getId()%>">
				</div>
			</form>
    	</div>
    	<div class="d-flex align-items-center justify-content-between p-2 m-2">
	    	<div class="d-flex align-items-center p-2 m-2">
		    	<form action="./Filter" method="post">
		    		<div class="dropdown">
					  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					    filter by
					  </button>
					  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						  <input type="submit" name="type" value = "personal" class="dropdown-item">
						  <input type="submit" name="type" value = "work" class="dropdown-item">
						  <input type="submit" name="type" value = "health" class="dropdown-item">
						  <input type="submit" name="type" value = "hobbies" class="dropdown-item">
						  <input type="hidden" name="userId" value="<%=user.getId()%>">
					  </div>
					</div>
				</form>
	    	</div>
	    	  <div class="d-flex align-items-center justify-content-around p-2 m-2">
	    	  	<p class="badge bg-white text-dark mt-3 mr-3">Order by</p>
	    	  	<div>
		    	  	<form action="./Order" method="post">
						<input type="submit" name="title" value = "Title" class="btn btn-outline-dark">
						<input type="hidden" name="userId" value="<%=user.getId()%>">
					</form>
	    	  	</div>
				<!--  
				<form action="./OrderTimestamp" method="post">
					<input type="submit" name="type" value = "Creation" class=" btn btn-outline-info">
					<input type="submit" name="type" value = "Deadline" class=" btn btn-outline-warning">
				</form>
				-->
	    	</div>
    	</div>
    	<div class="container p-2 m-2">
    		<c:forEach var="task" items="${tasks}">
				  <div class="border row rounded mb-2">
				    <div class="col m-2">
				    	<div class="row justify-content-md-center align-items-center">
				      			<div class="col-sm"><p class="pt-3">${task.title}</p></div>
				      			<div class="col-sm d-flex flex-column"><p class="badge bg-white text-secondary">category</p><p class="badge bg-secondary text-white">${task.type}</p></div>
				      			<div class="col-sm"><p class="badge bg-white text-info">creation</p><p class="badge bg-info text-white">${task.date}</p></div>
				      			<div class="col-sm"><p class="badge bg-white text-warning">deadline</p><p class="badge bg-warning text-white">${task.deadline}</p></div>
				      			<div class="col-sm">
					      			<form action="./Edit" method="get">
						      			<input type="submit" value = "Edit" class="col-sm btn btn-primary">
				    					<input type="hidden" name="taskId" value="${task.id}">
				    				</form>
				      			</div>
				      			<div class="col-sm">
				 					<form action="./Delete" method="post">
				      					<input type="submit" value = "Delete" class="col-sm btn btn-danger">
				      					<input type="hidden" name="taskId" value="${task.id}">
				      				</form>
				      			</div>
				    	</div>
				    </div>
				</div>
    		</c:forEach> 

		</div>
    </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
  </body>
</html>