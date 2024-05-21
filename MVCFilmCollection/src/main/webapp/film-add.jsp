<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 20px;
        }
        h1 {
            text-align: center;
            color: #007bff;
            margin-bottom: 30px;
        }
        form {
            max-width: 600px;
            margin: auto;
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        label {
            display: block;
            margin-bottom: 10px;
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
        }
        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 12px 20px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
    <title>Add New Film</title>
</head>
<body>
    <h1>Add New Film</h1>
    <form method="POST" action="./FilmAddServlet" >
        <label for="id">ID:</label>
        <input type="number" id="id" name="id" required><br>
        
        <label for="title">Title:</label>
        <input type="text" id="title" name="title" required><br>

        <label for="year">Year:</label>
        <input type="number" id="year" name="year" required><br>

        <label for="director">Director:</label>
        <input type="text" id="director" name="director" required><br>

        <label for="stars">Stars:</label>
        <input type="text" id="stars" name="stars" required><br>

        <label for="review">Review:</label>
        <textarea id="review" name="review" rows="4" cols="50"></textarea><br>

        <input type="submit" value="Submit">
    </form>
</body>
</html>
