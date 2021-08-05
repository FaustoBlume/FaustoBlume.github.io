<%@page import="java.util.LinkedList"%>
<%@page import="Entidades.Usuario"%>
<%@page import="Entidades.Producto"%>
<%@page import="Entidades.Marca"%>
<%@page import="Entidades.Tipo_Producto"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
  <%
  		LinkedList<Tipo_Producto> ltps = (LinkedList<Tipo_Producto>)request.getAttribute("listaTipoProductos");
  		LinkedList<Marca> lm = (LinkedList<Marca>)request.getAttribute("listaMarcas");
    	Producto prod2 = (Producto)request.getAttribute("producto");
	%>
    <title>Editar Producto</title>
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
      <form class="form-signin" action="editarproducto" method="post">
        <h1>Editar Producto</h1>
        
        <h4>Id</h4>
        <input type="number" name="idprodu2"  value="<%=prod2.getId()%>" required  readonly/>
        <h4>Descripcion</h4>
        <input type="text" name="descripcion2" value="<%=prod2.getDescripcion() %> "  required/>
        <h4>Precio</h4>
        <input type="number" name="precio2" step="0.01" min=0 value="<%=prod2.getPrecio() %>" required/>
        <h4>Stock</h4>
        <input type="number" name="stock2" min=0 value="<%=prod2.getStock() %>" required/>
        <h4>Foto</h4>
        <input type="text" name="foto2"  value="<%=prod2.getFoto() %>" required/>
        <h4>Marca<span></h4>
        <select id="marca2" name="marca2">
   
        <option value="<%=prod2.getMarca().getId()%>" selected><%=prod2.getMarca().getDescripcion()%></option>
    
        <%for (Marca m : lm) { %>
        	<% if ((prod2.getMarca().getId()) == (m.getId())){continue;}%>
          <option  value="<%=m.getId()%>"><%=m.getDescripcion()%></option>
          <%} %>
 
        </select>
        <h4>Tipo Producto</h4>
        <select id="tp2" name="tp2">
        <option value="<%=prod2.getTp().getId()%>" selected><%=prod2.getTp().getDescripcion()%></option>
    
        <%for (Tipo_Producto tp : ltps) { %>
        	<% if ((prod2.getTp().getId()) == (tp.getId())){continue;}%>
          <option value="<%=tp.getId()%>"><%=tp.getDescripcion()%></option>
          <%} %>
 
        </select>
        <div class="btn-block">
          <button type="submit" >Editar</button>
        </div>
      </form>
    </div>
  </body>
</html>

   