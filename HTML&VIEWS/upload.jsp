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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/upload.css">
        <link href="https://fonts.googleapis.com/css2?family=Ubuntu&display=swap" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src ="resources/hompage.js"></script>
	    <style type="text/css">
            body {
                font-family: 'Ubuntu', sans-serif;
                font-size: 14pt;
            }
	    </style>
    </head>
    <body>


        <ul class="topnav">
            <li><a class = "nav-button"class="active" href="${pageContext.request.contextPath}/home">Home</a></li>
            <li><a class = "nav-button"href="${pageContext.request.contextPath}/account/${userLogin.getId()}" style ="color:#4ca0af">Account</a></li>
            <a class = "nav-button"href="${pageContext.request.contextPath}/signout">Sign out</a>
            <div class="search-container">
                <form action="${pageContext.request.contextPath}/search"method="post">
                  <input type="text" placeholder="Search.." name="search">
                  <button type="submit">GO</button>
                </form>
              </div>
          </ul>





        <div class = "container">
            <div class = "row">
                <div class="col-sm-12" id = "row1_body_div">
                    <form action = "uploadfile" method="post">
                        <p style="color:red;">* all feilds with this is are required</p>
						 <h4>Title</h4>
                        <input type ="text" required name = "title">
                        <br>
                         <br>
                        <h4>Description</h4>
                        <input type="text" id="descript" name="descript" rows="8" cols="60" style="width: 50%">
                
                        <br>
                        <br>
                        <h4>HashTag *</h4>
                        <input type ="radio" name ="tag" value = "#Action"><label>#Action</label>
                        <input type ="radio" name ="tag" value = "#Epic"><label>#Epic</label>
                        <input type ="radio" name ="tag" value = "#Cute"><label>#Cute</label>
                        <input type ="radio" name ="tag" value = "#Sci-Fy"><label>#Sci-Fy</label>
                        <br>
                        <input type ="radio" name ="tag" value = "#Fantasy"><label>#Fantasy</label>
                        <input type ="radio" name ="tag" value = "#Modern"><label>#Modern</label>
                        <input type ="radio" name ="tag" value = "#Digital"><label>#Digital</label>
                        <input type ="radio" name ="tag" value = "#Noir"><label>#Noir</label>
                        <br>
                        <br>
                        <input type = "submit">
                    </form>
                   
            </div>
        </div>
        
       
    </body>
</html>


