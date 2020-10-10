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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/homepage.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/modal.css">
        <link href="https://fonts.googleapis.com/css2?family=Ubuntu&display=swap" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src ="${pageContext.request.contextPath}/resources/modal.js"></script>
	    <style type="text/css">
            body {
                font-family: 'Ubuntu', sans-serif;
                font-size: 14pt;
            }
	    </style>
    </head>
    <body>



	  <nav class="topnav">
	  		<a class = "nav-button"href="${pageContext.request.contextPath}/home">Home</a></li>
            <a class = "nav-button" href="${pageContext.request.contextPath}/upload" >Upload</a></li>
            <a class = "nav-button" href="${pageContext.request.contextPath}/signout">Sign out</a>
            <div class="search-container">
                <form action="${pageContext.request.contextPath}/search"method="post">
                  <input type="text" placeholder="Search.." name="search">
                  <button type="submit">GO</button>
                </form>
              </div>
          </nav>

		

	 
        <div class = "modal" id = "modal">
            
            <div class = "modalboady" >
                <div class = "close" id ="close" onclick="changemodaldisplay(this.id)">x</div>
                <div class = "body">
                    <form  id = "descriptionUpdater" action="${pageContext.request.contextPath}/updateDescription/${userLogin.getId()}">
                        <h3>Edit description</h3>
                        <textarea cols="40" rows="6" id = "des" name = "des"></textarea>
                        <br>
                        <input type ="submit" >
                    </form>
                    
                     <form id = "pfpupdater"action = "${pageContext.request.contextPath}/updatepfp/${userLogin.getId()}" method="post" enctype="multipart/form-data" style = "width: 100%">
                     	<h3>Update Profile Image</h3>
		                        <input type="file" id="myfile" name="file" multiple>
		                        <button type="submit">GO</button>
	                
                    </form>
                </div>
            </div>
        </div>

	
        <div class = "container">
            <div class = "row">
                <div class="col-sm-12" id = "row1_body_div">
                <br>
                <br>
                    <div class= "profileimage" id = "profileimage" style ="display:inline; float:left; padding-right: 10px; height: 15%; width: 15%;">
                    
                    
                     <c:set var = "User" value = "${isUser}"/>
					      <c:if test = "${User == true}">
					     <img alt="pfp" onclick="changemodaldisplay(this.id)" id = "pfp" src="${pageContext.request.contextPath}/resources/profileimages/${pfp}" style="height: 100%; width: 100%; border-style: solid; border-color: blue; border-radius: 50%; cursor: pointer;">
					        
					</c:if>
					
					 <c:set var = "User" value = "${isUser}"/>
					      <c:if test = "${User == false}">
					     <img alt="pfp" id = "pfp" src="${pageContext.request.contextPath}/resources/profileimages/${pfp}" style="height: 80%; width: 100%; border-style: solid; border-color: blue; border-radius: 50%; cursor: pointer;">
					        
					</c:if>
                    
                    	
                    	 
                    </div>
                    	
                    	
                    <h3 style="color:white;">User name: ${userLogin.getUsername()}</h3>
                    <c:set var = "User" value = "${isUser}"/>
					      <c:if test = "${User == true}">
					      <form action="${pageContext.request.contextPath}/deleteAccount" method="post">
					      		<input type = "submit" name = "Delete" value = "Delete" class = "btn btn-primary" style="display:inline; float: right;">
					      </form>
					        
					</c:if>
					
                    <h5 style="color:white;">Joined on: ${userLogin.getDate()}</h5>
                    <h5 style="color:white;">Total Likes: ${likes}</h5>
                    
                    
                    <br>
                    

                    
          
                    <br>
                       <c:set var = "User" value = "${isUser}"/>
					      <c:if test = "${User == true}">
			
					        	   <button class = "btn btn-primary"  id="mybtn"  onClick="changemodaldisplay(this.id)" style = "display:inline; float: right;">Edit Description</button>
	
					</c:if>
					 
                    	<h4 style ="color:white;">Description</h4>
                    	
                    	<p style ="color:white;"> ${description}</p>   
                    	
               
                    <br>
                    <h4 style="color:white;"> All Posts</h4>
                    <hr style = "background-color: white;">
                    
                    <br>
                    
                    <div id = "recenttabbody">
							<c:forEach var="posts" items="${userposts}">
								<br>
								<div class="userpost">
									<img class="imgpost" src="${pageContext.request.contextPath}/resources/uploadimgs/${posts.getFile()}" alt = "recent imgs here" >
									<div class = "postboy">
										<h5  style = "color:#2c79cf;">Poster: ${posts.getUser_id().getUsername()}</h5>
										<h6 style = "color:#2c79cf;">${posts.getTitle()}</h6>
										<a href="${pageContext.request.contextPath}/img/${posts.getId()}" class="btn btn-primary">See More</a>
									</div>
								</div>
								<br>
							
							</c:forEach>
							
								
                        </div>
                </div>
            </div>
                      

  </div>
   
       
       
        
    </body>
</html>