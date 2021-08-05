<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<head>

	<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">

		<!-- Login -->
		<link type="text/css" rel="stylesheet" href="css/login.css"/> 
    
   		<!-- Google font -->
		<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet"> 

		<!-- Bootstrap -->
		<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/> 
		
		<!-- Slick -->
		<link type="text/css" rel="stylesheet" href="css/slick.css"/>
		<link type="text/css" rel="stylesheet" href="css/slick-theme.css"/> 

		<!-- nouislider -->
		<link type="text/css" rel="stylesheet" href="css/nouislider.min.css"/> 

		<!-- Font Awesome Icon -->
		<link rel="stylesheet" href="css/font-awesome.min.css"> 

		<!-- Custom stlylesheet -->
		<link type="text/css" rel="stylesheet" href="css/style.css"/>  

<%
    	String msg = (String)request.getAttribute("error");
	%>
  </head>

  <body>
  
  <div style="text-align:center">
    <form action="signin"  method="post">
      <img src="./img/LOGO_LOGIN.png" alt="" width="100" height="100">
      <h1 class="h3 mb-3 font-weight-normal">Iniciar Sesión</h1>

      <br><label for="email" >Email</label>
      <input  name="email" class="input" style="width:500px;heigth:70px;border-radius: 40px ;outline: none;"  placeholder="pablo@gmail.com" required autofocus type="email"></br>
      <br><label for="password">Clave</label>
      <input  name="password" class="input" style="width:500px;heigth:70px;border-radius: 40px ;outline: none;" required type="password"></br>
      <br><button class="primary-btn" type="submit">Ingresar</button></br>
      <br><p class="mt-5 mb-3 text-muted">© 2020-2021</p>
     
     <h1><%
     if(msg == null){
    	 msg = "";
     }%>
     <%=msg  %>
     
     
     </h1>
    </form>
   </div>
    
    
    
</body>
</html>
