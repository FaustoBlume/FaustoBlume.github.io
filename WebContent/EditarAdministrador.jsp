<%@page import="java.util.LinkedList"%>
<%@page import="Entidades.Usuario"%>
<%@page import="Entidades.Producto"%>
<%@page import="Entidades.Administrador"%>
<%@page import="Entidades.Tipo_Producto"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
  <%
  	
    	Administrador admin2 = (Administrador)request.getAttribute("admin");
	%>
    <title>Editar Administrador</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <style>
      html, body {
      min-height: 100%;
      }
      body, div, form, input, select { 
      padding: 0;
      margin: 0;
      outline: none;
      font-family: Roboto, Arial, sans-serif;
      font-size: 14px;
      color: #666;
      line-height: 22px;
      }
      h1, h4 {
      margin: 15px 0 4px;
      font-weight: 400;
      }
      h4 {
      margin: 20px 0 4px;
      font-weight: 400;
      }
      span {
      color: red;
      }
      .small {
      font-size: 10px;
      line-height: 18px;
      }
      .testbox {
      display: flex;
      justify-content: center;
      align-items: center;
      height: inherit;
      padding: 3px;
      }
      form {
      width: 100%;
      padding: 20px;
      background: #fff;
      box-shadow: 0 2px 5px #ccc; 
      }
      input {
      width: calc(100% - 10px);
      padding: 5px;
      border: 1px solid #ccc;
      border-radius: 3px;
      vertical-align: middle;
      }
      input:hover, textarea:hover, select:hover {
      outline: none;
      border: 1px solid #095484;
      background: #e6eef7;
      }
      .title-block select, .title-block input {
      margin-bottom: 10px;
      }
      select {
      padding: 7px 0;
      border-radius: 3px;
      border: 1px solid #ccc;
      background: transparent;
      }
      select, table {
      width: 100%;
      }
      option {
      background: #fff;
      }
      .day-visited, .time-visited {
      position: relative;
      }
      input[type="date"]::-webkit-inner-spin-button {
      display: none;
      }
      input[type="time"]::-webkit-inner-spin-button {
      margin: 2px 22px 0 0;
      }
      .day-visited i, .time-visited i, input[type="date"]::-webkit-calendar-picker-indicator {
      position: absolute;
      top: 8px;
      font-size: 20px;
      }
      .day-visited i, .time-visited i {
      right: 5px;
      z-index: 1;
      color: #a9a9a9;
      }
      [type="date"]::-webkit-calendar-picker-indicator {
      right: 0;
      z-index: 2;
      opacity: 0;
      }
      .question-answer label {
      display: block;
      padding: 0 20px 10px 0;
      }
      .question-answer input {
      width: auto;
      margin-top: -2px;
      }
      th, td {
      width: 18%;
      padding: 15px 0;
      border-bottom: 1px solid #ccc;
      text-align: center;
      vertical-align: unset;
      line-height: 18px;
      font-weight: 400;
      word-break: break-all;
      }
      .first-col {
      width: 25%;
      text-align: left;
      }
      textarea {
      width: calc(100% - 6px);
      }
      .btn-block {
      margin-top: 20px;
      text-align: center;
      }
      button {
      width: 150px;
      padding: 10px;
      border: none;
      -webkit-border-radius: 5px; 
      -moz-border-radius: 5px; 
      border-radius: 5px; 
      background-color: #095484;
      font-size: 16px;
      color: #fff;
      cursor: pointer;
      }
      button:hover {
      background-color: #0666a3;
      }
      @media (min-width: 568px) {
      .title-block {
      display: flex;
      justify-content: space-between;
      }
      .title-block select {
      width: 30%;
      margin-bottom: 0;
      }
      .title-block input {
      width: 31%;
      margin-bottom: 0;
      }
      th, td {
      word-break: keep-all;
      }
      }
    </style>
  </head>
  <body>
    <div class="testbox">
      <form class="form-signin" action="AdministrarAdmin" method="post">
        <h1>Editar Administrador</h1>
        
        <label>Id</label>
        <input type="number" name="idAdmin2"  value="<%=admin2.getId()%>" required  readonly/>
		<p>	
										
		<label>Apellido</label>
		<input name="apellido2" value="<%=admin2.getApellido()%>" type="text" class="form-control"  required>
		<p>				
						
		<label>Nombre</label>
		<input name="nombre2" value="<%=admin2.getNombre()%>"  type="text" class="form-control"  required>
		<p>			
						
		<label>Nombre de Usuario</label>
		<input name="nombreUsuario2" value="<%=admin2.getNombreUsuario()%>" type="text" class="form-control" required>
		<p>				
						
		<label>Correo Electrónico</label>
		<input name="e_mail2" value="<%=admin2.getE_mail()%>" type="text" class="form-control" required>
		<p>			
						
		<label>Contraseña</label>
		<input name="contrasena2" value="<%=admin2.getPass()%>" type="password" class="form-control" required>
		<p>				
						
		<label>Sueldo</label>
		<input name="sueldo2" value="<%=admin2.getSueldo()%>" type="text" class="form-control" required>
		<p>				
										
		<input name="rol2" value="<%=admin2.getRol()%>" type="text" class="form-control"   style="visibility:hidden">

        <div class="btn-block">
        <input name="var" value=1 type="text" class="form-control"  style="visibility:hidden">
          <button type="submit">Editar</button>
        </div>
      </form>
    </div>
  </body>
</html>
