<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <%@page import="br.edu.insper.*,java.util.*"%>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>CRUD: Edit</title>
  </head>
  <body class="bg-light">
    <%
		//String id = (String) request.getAttribute("id");
    	String deadline = (String) request.getAttribute("deadline");
    	Task task = (Task) request.getAttribute("task");
  	%>
  <div class="container d-flex flex-column align-items-center justify-content-center">
    <h1 class="display-4 mt-5">Edit Task</h1>

		<div class="bg-white p-3 form-group input-group input-group-sm mb-3">
    <form action="./Edit" class="form-group input-group input-group-sm mb-3"  method="post">
			<div class="input-group mb-3">
				  <div class="input-group-prepend">
				    <label class="input-group-text">Title</label>
				  </div>
				  <input type="text" value="<%=task.getTitle()%>" name="taskTitle" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
			</div>
			
			<div class="input-group mb-3">
				  <div class="input-group-prepend">
				    <label class="input-group-text">Type</label>
				  </div>
				  <select class="custom-select"  name="taskType" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
					  <option selected><%=task.getType()%></option>
					  <option value="personal">personal</option>
					  <option value="work">work</option>
					  <option value="health">health</option>
					  <option value="hobbies">hobbies</option>
				</select>
			</div>
			
			<div class="input-group mb-3">
				  <div class="input-group-prepend">
				    <label class="input-group-text">Deadline</label>
				  </div>
				  <input type="date" name="taskDeadline" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
			</div>
			 <p class="text-info">current deadline: <%=task.getDeadline()%></p>
			

			<input type="submit" name="login" value="Edit" class="btn btn-primary btn-lg btn-block">
			<input type="hidden" name="taskId" value="<%=task.getId()%>">
			<input type="hidden" name="userId" value="<%=task.getUserId()%>">
    </form>
			<button onclick="window.history.back()" class="btn btn-secondary btn-lg btn-block">Back</button>
    	</div>
  </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  </body>
</html>