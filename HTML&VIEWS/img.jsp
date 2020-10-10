<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="CaseStudy.entities.Users"%>
     
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <link rel="stylesheet" href="../resources/homepage.css">
        <link href="https://fonts.googleapis.com/css2?family=Ubuntu&display=swap" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <style type="text/css">
            body {
                font-family: 'Ubuntu', sans-serif;
                font-size: 14pt;
            }
	    </style>
    </head>
    <body>
	

        <nav class="topnav">
            <a class = "nav-button" href="${pageContext.request.contextPath}/upload" >Upload</a></li>
            <a class = "nav-button"href="${pageContext.request.contextPath}/home" >Home</a></li>
            <a class = "nav-button" href="${pageContext.request.contextPath}/account/${userLogin.getId()}">Account</a></li>
            <div class="search-container">
                <form action="${pageContext.request.contextPath}/search"method="post">
                  <input type="text" placeholder="Search.." name="search">
                  <button type="submit">GO</button>
                </form>
              </div>
          </nav>
	
	<br>
	<br>
        <div class = "container">
            <div class = "row">
                <div class="col-sm-12" id = "row1_body_div">
                     
                      <div id="img"  value="img" name ="img">
                       <c:set var = "poster" value = "${isUser}"/>
					      <c:if test = "${poster == true}">
					      <form action="${pageContext.request.contextPath}/deletePost/${selectedpost.getId()}" method ="post">
					      		<input type = "submit" name = "Delete" value = "Delete" class = "btn btn-primary" style="display:inline; float: right;">
					      </form>
					        
					      </c:if>
                      
                        <h3 style = "color: #2c79cf;">Title: ${selectedpost.getTitle()}</h3>   
                        
                       
                         <hr style = "background-color: white;">
                        <img src="${pageContext.request.contextPath}/resources/uploadimgs/${selectedpost.getFile()}" style="height:80%;width:80%;" alt= "img here">
                       
                       <br>
                       <br>
                       <a style="color:white;" href="${pageContext.request.contextPath}/account/${selectedpost.getUser_id().getId()}"><h3 style = "color: white;">Created by ${selectedpost.getUser_id().getUsername()}</h3></a> 
                       
                       <p style = "color: white;">${selectedpost.getDescription()}</p>
						
						 <form action ="${pageContext.request.contextPath}/imgl/${selectedpost.getId()}" method = "post"> <p style="color: white;display:inline;">${likes}</p>
	                     	<input type = "submit" name = "Like" value = "Like">
	                     </form>
								
                        </div>
                        <form action ="${pageContext.request.contextPath}/follow/${selectedpost.getUser_id().getId()}" method = "post"> <p style="color: white;display:inline;">
	                     	<input type = "submit" name = "follow" value = "follow">
	                     </form>
            				
                        <br>
                        <br>
                      	<hr style = "background-color: white;">
	                     <p style = "color: white;"> Comment on the Post</p>
	                     <form action ="${pageContext.request.contextPath}/imga/${selectedpost.getId()}" method = "post">
	                     	<input type = "text" name = "comment" style="width:80%;">
	                     	<input type = "submit" name = "Comment" value = "Comment">
	                     </form>
	                     <br>
	                     <br>
	                     <div class = "comments">
	                     	<c:forEach var = "comment" items="${postcomments}">
	                     	
	                     	
	                     	  	<c:set var = "poster" value = "${userLogin.getId()}"/>
							      <c:if test = "${poster == comment.getUser_id().getId()}">
							      <form action="${pageContext.request.contextPath}/deletec" method ="post">
							      		<input type = "number" name = "comment" value = "${comment.getId()}" readonly style = "display: none;">
							      		<input type = "number" name = "photoid" value = "${selectedpost.getId()}" readonly style = "display: none;">
 							      		<input type = "submit" name = "Delete" value = "Delete" class = "btn btn-primary" style="display:inline; float: right;">
							      </form>
							        
							    </c:if>
	                     		<p style = "color: #2c79cf;">  ${comment.getUser_id().getUsername()}:  <b style = "color: white;">${comment.getComment()}</b></p>
	                     	
	                     	</c:forEach>
	                     </div>
	                 </div>

            </div>
        </div>
        
        
    </body>
</html>