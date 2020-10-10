<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <link rel="stylesheet" href="resources/singup.css">
        <link href="https://fonts.googleapis.com/css2?family=Ubuntu&display=swap" rel="stylesheet">
	    <style type="text/css">
            body {
                font-family: 'Ubuntu', sans-serif;
                font-size: 14pt;
            }
	    </style>
    </head>
    <body>

        <div class = "container">
            <div class = "row">
                <div class="col-sm-6" id = "row1_body_div">
                    <h1>
                        JOIN THE FASTEST GORWING ART COMMUNITY IN THE WORLD!
                    </h1>
                        <h3>
                            Sign up is free. There over 1,000,000+ artist and
                            art fans sharing their work and ideas with each 
                            other. Join the community and be come apart of the 
                            growing family users.
            
                        </h3>
                        <br>
                        <footer id="creaditer">
                            Art work by thatDosguy
                        </footer>
                    
                </div>
                <div class="col-sm-6" id = "row1_body_div2">
                    <h4>
                        JOIN ARTFLOOR
                    </h4>
                    <h6>Already a memeber? <a href="login">Login</a></h6>
                    <form action="adduser">
                    	<input type="text" name = "email" id="email" class="signup-feild" placeholder="email" required>
                        <input type="text" name = "username" id="username" class="signup-feild" placeholder="username" required>
                        <input type="text" name = "password" id="password" class="signup-feild" placeholder="password" required>
                        <br>
                        <input type="submit">
                    </form>
                    <br>
                    <p>
                        By clicking Join, I confirm that I have read and agree to the 
                        ArtFloor Terms of Service, Privacy Policy, and to receive emails and updates.
                    </p>
                </div>
                
            </div>
        </div>
        
        
    </body>
</html>
