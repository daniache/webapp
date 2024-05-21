<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Film Details</title>
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
        .film-details {
            max-width: 600px;
            margin: auto;
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        .film-details p {
            margin: 0 0 15px;
            font-size: 18px;
            line-height: 1.6;
            color: #333;
        }
        .film-details p b {
            font-weight: bold;
        }
        .back-link {
            display: block;
            text-align: center;
            margin-top: 20px;
            text-decoration: none;
            color: #007bff;
        }
        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h1>Film Details</h1>
    
    <div class="film-details">
    <%-- Check if film object exists --%>
    <c:if test="${film ne null}">
        <p><b>ID:</b> ${film.getId()}</p>
        <p><b>Title:</b> ${film.getTitle()}</p>
        <p><b>Year:</b> ${film.getYear()}</p>
        <p><b>Director:</b> ${film.getDirector()}</p>
        <p><b>Stars:</b> ${film.getStars()}</p>
        <p><b>Review:</b> ${film.getReview()}</p>
    </c:if>
    </div>
</body>
</html>
