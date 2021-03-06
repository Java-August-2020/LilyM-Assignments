<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css" />
<title>Programming Languages App</title>
</head>
<body>
<div class="container">
		<h1>History of Programming Languages</h1>	
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Name</th>
					<th>Creator</th>
					<th>Version</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${ languages }" var="lang">
				<tr>
					<td><a href="/${ lang.id }">${ lang.name }</a></td>
					<td>${ lang.creator }</td>
					<td>${ lang.currentVersion }</td>
					<td>				
						<a class="btn btn-success" href="/new">Add a language</a>	
						<a class="btn btn-primary" href="/${ lang.id }/edit">Edit</a>	
						<a class="btn btn-danger" href="${ lang.id }/remove">Delete</a>						
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>