<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import= "models.Film"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<title>Film List</title>
<!-- Bootstrap CSS -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<!-- Google Font - Lora -->
    <link href="https://fonts.googleapis.com/css2?family=Lora:wght@400;700&display=swap" rel="stylesheet">
<!-- DataTables CSS -->
<link href="https://cdn.datatables.net/1.10.24/css/dataTables.bootstrap4.min.css" rel="stylesheet">
<style>
 body {
            background-color: #e0ffff; /* Aqua color */
        }
        .container {
            background-color: #ffffff; /* White background for the container */
            padding: 20px; /* Add some padding */
            border-radius: 10px; /* Add border-radius for a rounded look */
            margin: 20px auto; /* Center the container */
            width: 115%; /* Extend the width */
        }
        h1 {
            text-align: center; /* Center-align the title */
            font-family: 'Ubuntu Sans', sans-serif; /* Change font */
        }
        .btn-fab {
            position: fixed;
            bottom: 20px;
            right: 20px;
            border-radius: 50%;
            padding: 15px 20px;
            background-color: #007bff;
            color: #fff;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            transition: all 0.3s ease;
        }
        .btn-fab:hover {
            background-color: #0056b3;
        }
        #filmTable {
            width: 100%;
            margin: 0 auto; /* Center the table */
        }
        /* Custom CSS for table header */
        .thead-custom {
            background-color: #5aded3; /* Pastel turquoise */
            font-weight: normal; /* Not bold */
        }
        /* Custom CSS for table data */
        #filmTable td {
            font-family: 'Ubuntu Sans', Thin; /* Change to the desired elegant font */
            font-style: italic; /* Italic */
            font-weight: 300; /* Light */
        }
</style>
</head>
<body>
     <div class="container">
        <h1>Film Collection</h1>
        <a href="film-add.jsp" class="btn btn-primary btn-fab">+</a>

        <!-- Bootstrap table -->
        <table class="table table-bordered table-striped" id="filmTable">
            <thead class="thead-custom">
        <tr>
            <th><b>ID</b></th>
            <th><b>Title</b></th>
            <th><b>Year</b></th>
            <th><b>Director</b></th>
            <th><b>Stars</b></th>
            <th><b>Review</b></th>
            <th><b>View</b></th>
            <th><b>Edit</b></th>
            <th><b>Delete</b></th>           
        </tr>
        </thead>
            <tbody>
        <c:forEach items="${films}" var="film">
            <tr>
                <td><b>${film.getId()}</b></td>
                <td><b>${film.getTitle()}</b></td>
                <td><b>${film.getYear()}</b></td>
                <td><b>${film.getDirector()}</b></td>
                <td><b>${film.getStars()}</b></td>
                <td><b>${film.getReview()}</b></td>
                <td><b><a href="FilmRetrieveServlet?id=${film.getId()}">View</a></b></td>
                <td><b><a href="FilmUpdateServlet?id=${film.getId()}">Edit</a></b></td>
                <td><b><a href="FilmDeleteServlet?id=${film.getId()}">Delete</a></b></td>   
            </tr>
        </c:forEach>
        </tbody>
    </table>
 </div>
    
<!-- Bootstrap JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<!-- DataTables JS -->
<script src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.24/js/dataTables.bootstrap4.min.js"></script>
<script>
$(document).ready(function(){
    // Initialize DataTable
    var table = $('#filmTable').DataTable({
        "paging": true, // Enable pagination
        "lengthChange": true, // Enable the ability to change the number of items per page
        "searching": true, // Enable search functionality
        "ordering": true, // Enable sorting
        "info": true, // Enable table information
        "autoWidth": false // Disable auto width calculation
    });
});
</script>
</body>
</html>