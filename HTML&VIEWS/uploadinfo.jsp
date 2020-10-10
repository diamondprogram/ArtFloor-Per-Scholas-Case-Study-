<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import = "CaseStudy.entities.Post"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <link rel="stylesheet" href="resources/upload.css">
        <link href="https://fonts.googleapis.com/css2?family=Ubuntu&display=swap" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src ="resources/hompage.js"></script>
	    <style type="text/css">
            body {
                font-family: 'Ubuntu', sans-serif;
                font-size: 14pt;
            }
	    </style>
	    <title>Upload image</title>
    </head>
    <body>
		

        <nav class="topnav">
            <a class = "nav-button"class="active" href="${pageContext.request.contextPath}/home">Home</a>
            <a class = "nav-button"href="${pageContext.request.contextPath}/account/${userLogin.getId()}" style ="color:#4ca0af">Account</a>
            <a class = "nav-button"href="${pageContext.request.contextPath}/signout">Sign out</a>
            <div class="search-container">
                <form action="${pageContext.request.contextPath}/search"method="post">
                  <input type="text" placeholder="Search.." name="search">
                  <button type="submit">GO</button>
                 
                </form>
              </div>
          </nav>

        <div class = "container">
            <div class = "row">
                <div class="col-sm-12" id = "row1_body_div">
                    <form action = "${pageContext.request.contextPath}/finishupload" method="post" enctype="multipart/form-data">
                        <p style="color:red;">* all feilds with this is are required</p>
						
						<h4>Image *</h4>
                        <input type="file" id="myfile" name="file" multiple>
                        <br>
                        <br>
                        <input type = "submit">
                    </form>
            </div>
        </div>
        
        
    </body>
</html>