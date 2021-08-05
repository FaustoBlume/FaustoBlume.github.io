<%@page import="java.util.LinkedList"%>
<%@page import="Entidades.Usuario"%>
<%@page import="Entidades.Cliente"%>
<%@page import="Entidades.Producto"%>
<%@page import="Entidades.Marca"%>
<%@page import="Entidades.Tipo_Producto"%>
<%@page import="Entidades.Linea_Pedido"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<%
    	LinkedList<Producto> lp = (LinkedList<Producto>)request.getAttribute("listaProductos");
		LinkedList<Tipo_Producto> ltps = (LinkedList<Tipo_Producto>)request.getAttribute("listaTipoProductos");
		LinkedList<Marca> lm = (LinkedList<Marca>)request.getAttribute("listaMarcas");
		Cliente usu = (Cliente)request.getSession().getAttribute("cliente");
		List<Linea_Pedido> lineas = (List<Linea_Pedido>)request.getSession().getAttribute("lineas");
	%>
	
	
	<%String msg;
    if(usu == null){
  	  msg ="Ingresar";
   }
   else{msg = usu.getNombreUsuario();} %>
   
    
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		 <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

		<title>Koto - Store</title>

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

		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->

    </head>
	<body>
		<!-- HEADER -->
		<header>
			<!-- TOP HEADER -->
			<div id="top-header">
				<div class="container">
					<ul class="header-links pull-left">
						<li><a href="#"><i class="fa fa-phone"></i>011-4586-7777</a></li>
						<li><a href="#"><i class="fa fa-envelope-o"></i> koto@supermercados.com.ar</a></li>
						<li><a href="#"><i class="fa fa-map-marker"></i>Paysandú 1842, Capital Federal, Buenos Aires.</a></li>
					</ul>
					<ul class="header-links pull-right">
						<li><a href="#"><i class="fa fa-dollar"></i> ARS</a></li>
						<li><a href="login.jsp"><i class="fa fa-user-o"></i><%=msg %></a></li>
					</ul>
					
				</div>
			</div>
			<!-- /TOP HEADER -->

			<!-- MAIN HEADER -->
			<div id="header">
				<!-- container -->
				<div class="container">
					<!-- row -->
					<div class="row">
						<!-- LOGO -->
						<div class="col-md-3">
							<div class="header-logo">
								<a href="Signin" class="logo">
									<img src="./img/logo.png" alt="" width="250" height="100">
								</a>
							</div>
						</div>
						<!-- /LOGO -->

						<!-- SEARCH BAR -->
						<div class="col-md-6">
							<div class="header-search">
								<form class="form-Agregar" action="AdministrarStore" method="post">
								<input name="var" value=1 type="text" class="form-control" style="visibility:hidden">
									<input name="buscar" class="input"style="border-radius: 40px 0px 0px 40px" placeholder="Buscar...">
									<button class="search-btn">Buscar</button>
								</form>
							</div>
						</div>
						<!-- /SEARCH BAR -->

						<!-- ACCOUNT -->
						<div class="col-md-3 clearfix">
							<div class="header-ctn">

								<!-- Cart -->
								<%if (lineas!= null) {  %>
								<% int cantidad = 0;%>
								<% for (Linea_Pedido linea : lineas) { %>
								<% cantidad = cantidad + linea.getCantidad();}%>
								<div class="dropdown">
									<a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
										<i class="fa fa-shopping-cart"></i>
										<span>Carrito</span>
										<div class="qty"><%=cantidad %></div>
									</a>
									<div class="cart-dropdown">
										<div class="cart-list">
										<% double total=0;%>
										<% for (Linea_Pedido linea : lineas) { %>
										<% total = total+linea.getSubtotal(); %>
										<form class="form-Agregar" action="AdministrarCompra?idprodu=<%=linea.getProd().getId()%>&action=eliminar" method="post">
											<div class="product-widget">
												<div class="product-img">
													<img src=<%=linea.getProd().getFoto() %> alt="">
												</div>
												<div class="product-body">
													<h3 class="product-name"><a href="#"><%=linea.getProd().getDescripcion() %></a></h3>
													<h4 class="product-price"><span class="qty"><%=linea.getCantidad()%>x</span><%=linea.getSubtotal() %></h4>
												</div>
												<button class="delete"><i class="fa fa-close"></i></button>
				
											</div>
										</form>
										<%} %>
										
										</div>
										<div class="cart-summary">
											<small><%=cantidad %> Productos seleccionados</small>
											<h5>SUBTOTAL: $<%=total %></h5>
										</div>
									</div>
								</div>
								<%} %>
								<!-- /Cart -->

								<!-- Menu Toogle -->
								<div class="menu-toggle">
									<a href="#">
										<i class="fa fa-bars"></i>
										<span>Menu</span>
									</a>
								</div>
								<!-- /Menu Toogle -->
							</div>
						</div>
						<!-- /ACCOUNT -->
					</div>
					<!-- row -->
				</div>
				<!-- container -->
			</div>
			<!-- /MAIN HEADER -->
		</header>
		<!-- /HEADER -->

		<!-- NAVIGATION -->
		<nav id="navigation">
			<!-- container -->
			<div class="container">
				<!-- responsive-nav -->
				<div id="responsive-nav">
					<!-- NAV -->
					<ul class="main-nav nav navbar-nav">
						<li class="active"><a href="AdministrarStore?action=todo">Todos los Productos</a></li>
						<li><a href="AdministrarStore?action=almacen">Almacen</a></li>
						<li><a href="AdministrarStore?action=bebidas">Bebidas</a></li>
						<li><a href="AdministrarStore?action=frescos">Frescos</a></li>
						<li><a href="AdministrarStore?action=electro">Electro</a></li>
						<li><a href="AdministrarStore?action=limpieza">Limpieza</a></li>
						
					</ul>
					<!-- /NAV -->
				</div>
				<!-- /responsive-nav -->
			</div>
			<!-- /container -->
		</nav>
		<!-- /NAVIGATION -->



		<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">

					<div class="col-md-7">
						<!-- Billing Details -->
						<div class="billing-details">
							<div class="section-title">
								<h3 class="title">TUS DATOS</h3>
							</div>
							<div class="form-group">
								<label>Nombre</label>
								<input class="input" type="text" name="first-name" value="<%=usu.getNombre()%>" readonly>
							</div>
							<div class="form-group">
								<label>Apellido</label>
								<input class="input" type="text" name="last-name" value="<%=usu.getApellido()%>"  readonly>
							</div>
							<div class="form-group">
							<label>Email</label>
								<input class="input" type="email" name="email" value="<%=usu.getE_mail()%>" readonly>
							</div>
							<div class="form-group">
							<label>Domicilio</label>
								<input class="input" type="text" name="address" value="<%=usu.getDomicilio()%>" readonly>
							</div>
				
							<div class="form-group">
							<label>Edad</label>
								<input class="input" type="text" name="edad"value="<%=usu.getEdad()%>" readonly>
							</div>
							<div class="form-group">
							<label>Telefono</label>
								<input class="input" type="tel" name="tel" value="<%=usu.getTelefono()%>" readonly>
							</div>
		
						</div>
						<!-- /Billing Details -->

					</div>

					<!-- Order Details -->
					<div class="col-md-5 order-details">
						<div class="section-title text-center">
							<h3 class="title">TU COMPRA</h3>
						</div>
						<div class="order-summary">
							<div class="order-col">
								<div><strong>PRODUCTO</strong></div>
								<div><strong>SUBTOTAL</strong></div>
							</div>
							<div class="order-products">
							<% double total=0;%>
							<%for (Linea_Pedido linea : lineas) { %>
							<% total = total+linea.getSubtotal(); %>
								<div class="order-col">
									<div><%=linea.getCantidad()%>x <%=linea.getProd().getDescripcion() %></div>
									<div>$<%=linea.getSubtotal() %></div>
								</div>
								<%} %>
							</div>
							<div class="order-col">
								<div>Envío</div>
								<div><strong>GRATIS</strong></div>
							</div>
							<div class="order-col">
								<div><strong>TOTAL</strong></div>
								<div><strong class="order-total">$<%=total%></strong></div>
							</div>
						</div>
						<div class="payment-method">
							<div class="input-radio">
								<input type="radio" name="payment" id="payment-1" >
								<label for="payment-1">
									<span></span>
									Efectivo
								</label>
							</div>
							<div class="input-radio">
								<input type="radio" name="payment" id="payment-2" disabled>
								<label for="payment-2">
									<span></span>
									Transferencia bancaria
								</label>
							</div>
							<div class="input-radio">
								<input type="radio" name="payment" id="payment-3" disabled>
								<label for="payment-3">
									<span></span>
									Tarjeta de crédito 
								</label>
							</div>
						</div>
						<a href="AdministrarCompra?action=finalizar&totalcompra=<%=total%>" class="primary-btn order-submit">Finalizar Compra</a>
					</div>
					<!-- /Order Details -->
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->

		<!-- NEWSLETTER -->
		<div id="newsletter" class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-12">
						<div class="newsletter">
							<p>Sign Up for the <strong>NEWSLETTER</strong></p>
							<form>
								<input class="input" type="email" placeholder="Enter Your Email">
								<button class="newsletter-btn"><i class="fa fa-envelope"></i> Subscribe</button>
							</form>
							<ul class="newsletter-follow">
								<li>
									<a href="#"><i class="fa fa-facebook"></i></a>
								</li>
								<li>
									<a href="#"><i class="fa fa-twitter"></i></a>
								</li>
								<li>
									<a href="#"><i class="fa fa-instagram"></i></a>
								</li>
								<li>
									<a href="#"><i class="fa fa-pinterest"></i></a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /NEWSLETTER -->

		<!-- FOOTER -->
		<footer id="footer">
			<!-- top footer -->
			<div class="section">
				<!-- container -->
				<div class="container">
					<!-- row -->
					<div class="row">
						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">About Us</h3>
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut.</p>
								<ul class="footer-links">
									<li><a href="#"><i class="fa fa-map-marker"></i>1734 Stonecoal Road</a></li>
									<li><a href="#"><i class="fa fa-phone"></i>+021-95-51-84</a></li>
									<li><a href="#"><i class="fa fa-envelope-o"></i>email@email.com</a></li>
								</ul>
							</div>
						</div>

						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">Categories</h3>
								<ul class="footer-links">
									<li><a href="#">Hot deals</a></li>
									<li><a href="#">Laptops</a></li>
									<li><a href="#">Smartphones</a></li>
									<li><a href="#">Cameras</a></li>
									<li><a href="#">Accessories</a></li>
								</ul>
							</div>
						</div>

						<div class="clearfix visible-xs"></div>

						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">Information</h3>
								<ul class="footer-links">
									<li><a href="#">About Us</a></li>
									<li><a href="#">Contact Us</a></li>
									<li><a href="#">Privacy Policy</a></li>
									<li><a href="#">Orders and Returns</a></li>
									<li><a href="#">Terms & Conditions</a></li>
								</ul>
							</div>
						</div>

						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">Service</h3>
								<ul class="footer-links">
									<li><a href="#">My Account</a></li>
									<li><a href="#">View Cart</a></li>
									<li><a href="#">Wishlist</a></li>
									<li><a href="#">Track My Order</a></li>
									<li><a href="#">Help</a></li>
								</ul>
							</div>
						</div>
					</div>
					<!-- /row -->
				</div>
				<!-- /container -->
			</div>
			<!-- /top footer -->

			<!-- bottom footer -->
			<div id="bottom-footer" class="section">
				<div class="container">
					<!-- row -->
					<div class="row">
						<div class="col-md-12 text-center">
							<ul class="footer-payments">
								<li><a href="#"><i class="fa fa-cc-visa"></i></a></li>
								<li><a href="#"><i class="fa fa-credit-card"></i></a></li>
								<li><a href="#"><i class="fa fa-cc-paypal"></i></a></li>
								<li><a href="#"><i class="fa fa-cc-mastercard"></i></a></li>
								<li><a href="#"><i class="fa fa-cc-discover"></i></a></li>
								<li><a href="#"><i class="fa fa-cc-amex"></i></a></li>
							</ul>
							<span class="copyright">
								<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
								Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							</span>
						</div>
					</div>
						<!-- /row -->
				</div>
				<!-- /container -->
			</div>
			<!-- /bottom footer -->
		</footer>
		<!-- /FOOTER -->

		<!-- jQuery Plugins -->
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/slick.min.js"></script>
		<script src="js/nouislider.min.js"></script>
		<script src="js/jquery.zoom.min.js"></script>
		<script src="js/main.js"></script>

	</body>
</html>
