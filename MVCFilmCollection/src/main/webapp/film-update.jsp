<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import= "models.Film"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Film</title>
     <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f9fa;
            padding: 20px;
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
        }
        form {
            max-width: 500px;
            margin: auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        label {
            display: block;
            margin-bottom: 8px;
            color: #333;
            font-weight: bold;
        }
        input[type="text"],
        input[type="number"],
        textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        textarea {
            resize: vertical;
            min-height: 100px;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h1>Update Film</h1>
    <form action="./FilmUpdateServlet" method="post">
    
        <label for="id"><b>ID</b></label>
        <input type="number" name="id" value="${film.id}" required><br>

        <label for="title"><b>Title</b></label>
        <input type="text" id="title" name="title" value="${film.title}" required><br>

        <label for="year"><b>Year</b></label>
        <input type="number" id="year" name="year" value="${film.year}" required><br>

        <label for="director"><b>Director</b></label>
        <input type="text" id="director" name="director" value="${film.director}" required><br>

        <label for="stars"><b>Stars:</b></label>
        <input type="text" id="stars" name="stars" value="${film.stars}" required><br>

        <label for="review"><b>Review:</b></label>
        <textarea id="review" name="review" rows="4" cols="50">${film.review}</textarea><br>

        <input type="submit" value="Save">
    </form>
</body>
</html>
