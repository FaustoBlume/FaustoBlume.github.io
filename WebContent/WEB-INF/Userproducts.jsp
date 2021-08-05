<%@page import="java.util.LinkedList"%>
<%@page import="Entidades.Usuario"%>
<%@page import="Entidades.Producto"%>
<%@page import="Entidades.Marca"%>
<%@page import="Entidades.Tipo_Producto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<%
    	LinkedList<Producto> lp = (LinkedList<Producto>)request.getAttribute("listaProductos");
		LinkedList<Tipo_Producto> ltps = (LinkedList<Tipo_Producto>)request.getAttribute("listaTipoProductos");
		LinkedList<Marca> lm = (LinkedList<Marca>)request.getAttribute("listaMarcas");
		String alerta = (String)request.getAttribute("alerta");
	%>
	<%if(alerta != null){%>
		<%switch (alerta) { 		
			case "update":%>
				<script type = "text/javascript">
				alert("Descripción repetida");		
				</script>
				<%break;
			case "add":%>
				<script type = "text/javascript">
				alert("Descripción ya creada");		
				</script>
				<%break;
			}%>
	<%}%>
	
	
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ABM</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
    body {
        color: #566787;
		background: #f5f5f5;
		font-family: 'Varela Round', sans-serif;
		font-size: 13px;
	}
	.table-responsive {
        margin: 30px 0;
    }
	.table-wrapper {
		min-width: 1000px;
        background: #fff;
        padding: 20px 25px;
		border-radius: 3px;
        box-shadow: 0 1px 1px rgba(0,0,0,.05);
    }
	.table-title {        
		padding-bottom: 15px;
		background: #435d7d;
		color: #fff;
		padding: 16px 30px;
		margin: -20px -25px 10px;
		border-radius: 3px 3px 0 0;
    }
    .table-title h2 {
		margin: 5px 0 0;
		font-size: 24px;
	}
	.table-title .btn-group {
		float: right;
	}
	.table-title .btn {
		color: #fff;
		float: right;
		font-size: 13px;
		border: none;
		min-width: 50px;
		border-radius: 2px;
		border: none;
		outline: none !important;
		margin-left: 10px;
	}
	.table-title .btn i {
		float: left;
		font-size: 21px;
		margin-right: 5px;
	}
	.table-title .btn span {
		float: left;
		margin-top: 2px;
	}
    table.table tr th, table.table tr td {
        border-color: #e9e9e9;
		padding: 12px 15px;
		vertical-align: middle;
    }
	table.table tr th:first-child {
		width: 60px;
	}
	table.table tr th:last-child {
		width: 100px;
	}
    table.table-striped tbody tr:nth-of-type(odd) {
    	background-color: #fcfcfc;
	}
	table.table-striped.table-hover tbody tr:hover {
		background: #f5f5f5;
	}
    table.table th i {
        font-size: 13px;
        margin: 0 5px;
        cursor: pointer;
    }	
    table.table td:last-child i {
		opacity: 0.9;
		font-size: 22px;
        margin: 0 5px;
    }
	table.table td a {
		font-weight: bold;
		color: #566787;
		display: inline-block;
		text-decoration: none;
		outline: none !important;
	}
	table.table td a:hover {
		color: #2196F3;
	}
	table.table td a.edit {
        color: #FFC107;
    }
    table.table td a.delete {
        color: #F44336;
    }
    table.table td i {
        font-size: 19px;
    }
	table.table .avatar {
		border-radius: 50%;
		vertical-align: middle;
		margin-right: 10px;
	}
    .pagination {
        float: left;
        margin: 0 0 5px;
    }
    .pagination li a {
        border: none;
        font-size: 13px;
        min-width: 30px;
        min-height: 30px;
        color: #999;
        margin: 0 2px;
        line-height: 30px;
        border-radius: 2px !important;
        text-align: center;
        padding: 0 6px;
    }
    .pagination li a:hover {
        color: #666;
    }	
    .pagination li.active a, .pagination li.active a.page-link {
        background: #03A9F4;
    }
    .pagination li.active a:hover {        
        background: #0397d6;
    }
	.pagination li.disabled i {
        color: #ccc;
    }
    .pagination li i {
        font-size: 16px;
        padding-top: 6px
    }
    .hint-text {
        float: left;
        margin-top: 10px;
        font-size: 13px;
    }    
	/* Custom checkbox */
	.custom-checkbox {
		position: relative;
	}
	.custom-checkbox input[type="checkbox"] {    
		opacity: 0;
		position: absolute;
		margin: 5px 0 0 3px;
		z-index: 9;
	}
	.custom-checkbox label:before{
		width: 18px;
		height: 18px;
	}
	.custom-checkbox label:before {
		content: '';
		margin-right: 10px;
		display: inline-block;
		vertical-align: text-top;
		background: white;
		border: 1px solid #bbb;
		border-radius: 2px;
		box-sizing: border-box;
		z-index: 2;
	}
	.custom-checkbox input[type="checkbox"]:checked + label:after {
		content: '';
		position: absolute;
		left: 6px;
		top: 3px;
		width: 6px;
		height: 11px;
		border: solid #000;
		border-width: 0 3px 3px 0;
		transform: inherit;
		z-index: 3;
		transform: rotateZ(45deg);
	}
	.custom-checkbox input[type="checkbox"]:checked + label:before {
		border-color: #03A9F4;
		background: #03A9F4;
	}
	.custom-checkbox input[type="checkbox"]:checked + label:after {
		border-color: #fff;
	}
	.custom-checkbox input[type="checkbox"]:disabled + label:before {
		color: #b8b8b8;
		cursor: auto;
		box-shadow: none;
		background: #ddd;
	}
	/* Modal styles */
	.modal .modal-dialog {
		max-width: 400px;
	}
	.modal .modal-header, .modal .modal-body, .modal .modal-footer {
		padding: 20px 30px;
	}
	.modal .modal-content {
		border-radius: 3px;
	}
	.modal .modal-footer {
		background: #ecf0f1;
		border-radius: 0 0 3px 3px;
	}
    .modal .modal-title {
        display: inline-block;
    }
	.modal .form-control {
		border-radius: 2px;
		box-shadow: none;
		border-color: #dddddd;
	}
	.modal textarea.form-control {
		resize: vertical;
	}
	.modal .btn {
		border-radius: 2px;
		min-width: 100px;
	}	
	.modal form label {
		font-weight: normal;
	}	
</style>

</head>
<body>
    <div class="container">
		<div class="table-responsive">
			<div class="table-wrapper">
				<div class="table-title">
					<div class="row">
						<div class="col-xs-6">
							<h2>ABMC <b>Productos</b></h2>
						</div>
						<div class="col-xs-6">
							<a href="Index.jsp" class="btn btn-success" style="background-color:orange"><span>Home</span></a>			
							<a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Agregar</span></a>					
						</div>
					</div>
				</div>
				<div class="clearfix">
					<ul class="pagination" >
						<li class="page-item active"><a href="#" class="page-link">Producto</a></li>
						<li class="page-item"><a href="AdministrarMarca?action=mostrar" class="page-link">Marca</a></li>
						<li class="page-item"><a href="AdministrarTipoProducto?action=mostrar" class="page-link">Tipo Producto</a></li>
						<li class="page-item"><a href="AdministrarAdmin?action=mostrar" class="page-link">Administrador</a></li>
						<li class="page-item"><a href="AdministrarCliente?action=mostrar" class="page-link">Cliente</a></li>
					</ul>
				</div>
				
				<table id="tablaProductos" class="table table-striped table-hover">
					<thead>
						<tr>
							<th>
								
							</th>
							<th>Id</th>
							<th>Descripcion</th>
							<th>Precio</th>
							<th>Stock</th>
							<th>Foto</th>
							<th>Accion</th>
						</tr>
					</thead>
					<tbody>
					<% for (Producto prod : lp) { %>
						<tr id="<%=prod.getId()%>">
							<td>
								
							</td>
							<td><%=prod.getId()%></td>
							<td><%=prod.getDescripcion()%></td>
							<td><%=prod.getPrecio()%></td>
							<td><%=prod.getStock()%></td>
							<td><img class="mb-4" src=<%=prod.getFoto()%> alt="" width="120" height="120"></td>
							<td>
							
								<a href="BuscarProducto?idProdu=<%=prod.getId()%>" class="edit" data-toggle="modal"    ><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
								<a href="BorrarProducto?idProdu=<%=prod.getId()%>" class="delete" data-toggle="modal" ><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>

							</td>
							
							
						</tr>
						 <% } %>
					</tbody>
				</table>
				
			</div>
		</div>        
    </div>
	<!-- Agregar Modal HTML -->
	<form class="form-Agregar" action="AgregarProducto" method="post">
	<div id="addEmployeeModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				
					<div class="modal-header">						
						<h4 class="modal-title">Agregar Producto</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<div class="form-group">
							<label>Descripcion</label>
							<input name="descripcion"  type="text" class="form-control"  required>
						</div>
						<div class="form-group">
							<label>Precio</label>
							<input name="precio" type="number" step="0.01"  class="form-control"min=0 required>
						</div>
						<div class="form-group">
							<label>Stock</label>
							<input name="stock" type="number" class="form-control" min=0 required>
						</div>
						<div class="form-group">
							<label>Foto(Url)</label>
							<input name="foto" type="text" class="form-control" required>
						</div>	
						
						<div class="form-group">
							<label>Marca</label>
							<br>
        					<select id="marca" name="marca">
   
        					<option disabled selected > -- Seleccione una opción -- </option>
    
        					<%for (Marca m : lm) { %>
          					<option  value="<%=m.getId()%>"><%=m.getDescripcion()%></option>
          					<%} %>
          					</select>
						</div>
						
						<div class="form-group">
							<label>Tipo Producto</label>
							<br>
        					<select id="tp" name="tp">
        					<option disabled selected > -- Seleccione una opción -- </option>
    
        					<%for (Tipo_Producto tp : ltps) { %>
        	
          					<option value="<%=tp.getId()%>"><%=tp.getDescripcion()%></option>
         					<%} %>
 
       	 					</select>
						</div>
						
										
					</div>
					
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
						<input type="submit" class="btn btn-success" value="Add">
					</div>
				
			</div>
		</div>
	</div>
	</form>
	

</body>
</html>