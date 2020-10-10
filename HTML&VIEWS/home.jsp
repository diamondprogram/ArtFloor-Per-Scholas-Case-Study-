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


        <nav class="topnav">
            <a class = "nav-button" href="${pageContext.request.contextPath}/upload" >Upload</a>
            <a class = "nav-button" href="${pageContext.request.contextPath}/account/${userLogin.getId()}">Account</a>
            <a class = "nav-button" href="${pageContext.request.contextPath}/signout">Sign out</a>
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
                    <div class="tab">
                        <button name = "Recent" class="tablinks" onclick="openTab(event, 'recent')">Recent</button>
                        <button class="tablinks" onclick="openTab(event, '#Digital')">#Digital</button>
                        <button class="tablinks" onclick="openTab(event, '#Action')">#Action</button>
                        <button class="tablinks" onclick="openTab(event, '#Epic')">#Epic</button>
                        <button class="tablinks" onclick="openTab(event, '#Cute')">#Cute</button>
                        <button class="tablinks" onclick="openTab(event, '#Sci-Fy')">#Sci-Fy</button>
                        <button class="tablinks" onclick="openTab(event, '#Fantasy')">#Fantasy</button>
                        <button class="tablinks" onclick="openTab(event, '#Modern')">#Modern</button>
                        <button class="tablinks" onclick="openTab(event, '#Noir')">#Noir</button>
                        <button class="tablinks" onclick="openTab(event, 'Favorite')">Liked Posts</button>
                      </div>
                      
                    
                      <div id="recent" class="tabcontent" value="recent" name ="recent">
                        <h3>Recent</h3>
                        <p>Located here should be the 15 most recent post made by all users</p>
                        <div id = "recenttabbody">
							<c:forEach var="posts" items="${recent}">
								<br>
								<div class="userpost">
									<img class="imgpost" src="${pageContext.request.contextPath}/resources/uploadimgs/${posts.getFile()}" alt = "recent imgs here" >
									<div class = "postboy">
										<h5  style = "color:#2c79cf;">Poster: ${posts.getUser_id().getUsername()}</h5>
										<h6 style = "color:#2c79cf;">${posts.getTitle()}</h6>
										<a href="img/${posts.getId()}" class="btn btn-primary">See More</a>
									</div>
								</div>
								<br>
							
							</c:forEach>
							
								
                        </div>
                      </div>
                      
                      <div id="#Digital" class="tabcontent">
                        <h3>#Digital</h3>
                  		 <form action="${pageContext.request.contextPath}/tags" method="post">
			                  <input type="text" value="#Digital" name="search" readonly style = "display:none;">
			                  <button type="submit">See all #Digital</button>
                			</form>
                        <div id = "digitalbody">
                            <c:forEach var="posts" items="${digital}">
                            <br>
                          
								<br>
								<br>
								<div class="userpost">
									<img class="imgpost" src="${pageContext.request.contextPath}/resources/uploadimgs/${posts.getFile()}" alt = "recent imgs here" >
									<div class = "postboy">
										<h5  style = "color:#2c79cf;">Poster: ${posts.getUser_id().getUsername()}</h5>
										<h6 style = "color:#2c79cf;">${posts.getTitle()}</h6>
										<a href="img/${posts.getId()}" class="btn btn-primary">See More</a>
									</div>
								</div>
								<br>
							
							</c:forEach>
                        </div>
                      </div>
                      
                      <div id="#Action" class="tabcontent">
                        <h3>#Action</h3>
                    		<form action="${pageContext.request.contextPath}/tags" method="post">
			                  <input type="text" value="#Action" name="search" readonly style = "display:none;">
			                  <button type="submit">See all #Action</button>
                			</form>
                        <div id = "tag2tabbody">
                            <div id = "digitalbody">
                            <c:forEach var="posts" items="${action}">
								<br>
								<div class="userpost">
									<img class="imgpost" src="${pageContext.request.contextPath}/resources/uploadimgs/${posts.getFile()}" alt = "recent imgs here" >
									<div class = "postboy">
										<h5  style = "color:#2c79cf;">Poster: ${posts.getUser_id().getUsername()}</h5>
										<h6 style = "color:#2c79cf;">${posts.getTitle()}</h6>
										<a href="img/${posts.getId()}" class="btn btn-primary">See More</a>
									</div>
								</div>
								<br>
							
							</c:forEach>
                        </div>
                        </div>
                      </div>
                      
                      
                      <div id="#Epic" class="tabcontent">
                        <h3>#Epic</h3>
                       <form action="${pageContext.request.contextPath}/tags" method="post">
			                  <input type="text" value="#Epic" name="search" readonly style = "display:none;">
			                  <button type="submit">See all #Epic</button>
                			</form>
                        <div id = "tag2tabbody">
                            <div id = "digitalbody">
                            <c:forEach var="posts" items="${epic}">
                            
								<br>
								<div class="userpost">
									<img class="imgpost" src="${pageContext.request.contextPath}/resources/uploadimgs/${posts.getFile()}" alt = "recent imgs here" >
									<div class = "postboy">
										<h5  style = "color:#2c79cf;">Poster: ${posts.getUser_id().getUsername()}</h5>
										<h6 style = "color:#2c79cf;">${posts.getTitle()}</h6>
										<a href="img/${posts.getId()}" class="btn btn-primary">See More</a>
									</div>
								</div>
								<br>
							
							</c:forEach>
                        </div>
                        </div>
                      </div>
                      
                      <div id="#Cute" class="tabcontent">
                        <h3>#Cute</h3>
                        <form action="${pageContext.request.contextPath}/tags" method="post">
			                  <input type="text" value="#Cute" name="search" readonly style = "display:none;">
			                  <button type="submit">See all #Cute</button>
                		</form>
                        <div id = "tag2tabbody">
                            <div id = "digitalbody">
                            <c:forEach var="posts" items="${cute}">
								<br>
								<div class="userpost">
									<img class="imgpost" src="${pageContext.request.contextPath}/resources/uploadimgs/${posts.getFile()}" alt = "recent imgs here" >
									<div class = "postboy">
										<h5  style = "color:#2c79cf;">Poster: ${posts.getUser_id().getUsername()}</h5>
										<h6 style = "color:#2c79cf;">${posts.getTitle()}</h6>
										<a href="img/${posts.getId()}" class="btn btn-primary">See More</a>
									</div>
								</div>
								<br>
							
							</c:forEach>
                        </div>
                        </div>
                      </div>
                      
                      <div id="#Sci-Fy" class="tabcontent">
                        <h3>#Sci-Fy</h3>
                        
                        <form action="${pageContext.request.contextPath}/tags" method="post">
			                  <input type="text" value="#Sci-Fy" name="search" readonly style = "display:none;">
			                  <button type="submit">See all #Sci-Fy</button>
                			</form>
                        
                        <div id = "tag2tabbody">
	                        <c:forEach var="posts" items="${scifi}">
									<br>
									<div class="userpost">
										<img class="imgpost" src="${pageContext.request.contextPath}/resources/uploadimgs/${posts.getFile()}" alt = "recent imgs here" >
										<div class = "postboy">
											<h5  style = "color:#2c79cf;">Poster: ${posts.getUser_id().getUsername()}</h5>
											<h6 style = "color:#2c79cf;">${posts.getTitle()}</h6>
											<a href="img/${posts.getId()}" class="btn btn-primary">See More</a>
										</div>
									</div>
									<br>
								
							</c:forEach>
                        
                            
                        </div>
                      </div>
                      
                      <div id="#Fantasy" class="tabcontent">
                        <h3>#Fantasy</h3>
                       	<form action="${pageContext.request.contextPath}/tags" method="post">
			                  <input type="text" value="#Fantasy" name="search" readonly style = "display:none;">
			                  <button type="submit">See all #Fantasy</button>
                		</form>
                        <div id = "tag2tabbody">
                            <c:forEach var="posts" items="${fantasy}">
								<br>
								<div class="userpost">
									<img class="imgpost" src="${pageContext.request.contextPath}/resources/uploadimgs/${posts.getFile()}" alt = "recent imgs here" >
									<div class = "postboy">
										<h5  style = "color:#2c79cf;">Poster: ${posts.getUser_id().getUsername()}</h5>
										<h6 style = "color:#2c79cf;">${posts.getTitle()}</h6>
										<a href="img/${posts.getId()}" class="btn btn-primary">See More</a>
									</div>
								</div>
								<br>
							
							</c:forEach>
                        </div>
                      </div>
                      <div id="#Modern" class="tabcontent">
                        <h3>#Modern</h3>
                       	<form action="${pageContext.request.contextPath}/tags" method="post">
			                  <input type="text" value="#Modern" name="search" readonly style = "display:none;">
			                  <button type="submit">See all #Modern</button>
                		</form>
                        <div id = "tag2tabbody">
                        <c:forEach var="posts" items="${modern}">
								<br>
								<div class="userpost">
									<img class="imgpost" src="${pageContext.request.contextPath}/resources/uploadimgs/${posts.getFile()}" alt = "recent imgs here" >
									<div class = "postboy">
										<h5  style = "color:#2c79cf;">Poster: ${posts.getUser_id().getUsername()}</h5>
										<h6 style = "color:#2c79cf;">${posts.getTitle()}</h6>
										<a href="img/${posts.getId()}" class="btn btn-primary">See More</a>
									</div>
								</div>
								<br>
							
							</c:forEach>
                            
                        </div>
                      </div>
                      <div id="#Noir" class="tabcontent">
                        <h3>#Noir</h3>
                       	<form action="${pageContext.request.contextPath}/tags" method="post">
			                  <input type="text" value="#Noir" name="search" readonly style = "display:none;">
			                  <button type="submit">See all #Noir</button>
                		</form>
                        <div id = "tag2tabbody">
                            <c:forEach var="posts" items="${noir}">
								<br>
								<div class="userpost">
									<img class="imgpost" src="${pageContext.request.contextPath}/resources/uploadimgs/${posts.getFile()}" alt = "recent imgs here" >
									<div class = "postboy">
										<h5  style = "color:#2c79cf;">Poster: ${posts.getUser_id().getUsername()}</h5>
										<h6 style = "color:#2c79cf;">${posts.getTitle()}</h6>
										<a href="img/${posts.getId()}" class="btn btn-primary">See More</a>
									</div>
								</div>
								<br>
							
							</c:forEach>
                        </div>
                      </div>
                      
                      
                      
                      
                      <div id="Favorite" class="tabcontent">
                        <h3>Favorited posts</h3>
     
                        <div id = "tag2tabbody">
                            <c:forEach var="posts" items="${Likedpost}">
								<br>
								<div class="userpost">
									<img class="imgpost" src="${pageContext.request.contextPath}/resources/uploadimgs/${posts.getFile()}" alt = "recent imgs here" >
									<div class = "postboy">
										<h5  style = "color:#2c79cf;">Poster: ${posts.getUser_id().getUsername()}</h5>
										<h6 style = "color:#2c79cf;">${posts.getTitle()}</h6>
										<a href="img/${posts.getId()}" class="btn btn-primary">See More</a>
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