package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.*;
import Logic.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@WebServlet({ "/administrarstore", "/ADMINISTRARSTORE", "/AdministrarStore" })
public class AdministrarStore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdministrarStore() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action"); 
		try {
			switch (action) { 		
			case "todo":
				todo(request, response);
				break;
			case "almacen":
				almacen(request, response);
				break;
			case "bebidas":
				bebidas(request, response);
				break;
			case "frescos":
				frescos(request, response);
				break;
			case "limpieza":
				limpieza(request, response);
				break;
			case "electro":
				electro(request, response);
				break;
			case "categorias":
				categoria(request, response);
			case "marca":
				marca(request, response);
			default:
				break;
			}			
		} catch (SQLException e) {
			e.getStackTrace();
		}

		}
		
	private void todo(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		LogicMarca ctrlMarca = new LogicMarca();
		LogicProducto ctrlProducto = new LogicProducto();
		LogicTipoProducto ctrlTipoProducto = new LogicTipoProducto();
		LinkedList<Marca> marcas=ctrlMarca.getAll();
		LinkedList<Tipo_Producto> tps=ctrlTipoProducto.getAll();
		LinkedList<Producto> productos=ctrlProducto.getAll();
		request.getSession().setAttribute("productos",productos);
		request.setAttribute("listaMarcas", marcas);
		request.setAttribute("listaTipoProductos", tps);
		request.setAttribute("listaProductos", productos);
		request.getRequestDispatcher("WEB-INF/Store.jsp").forward(request, response);
	}
		
	private void almacen(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		LogicMarca ctrlMarca = new LogicMarca();
		LogicProducto ctrlProducto = new LogicProducto();
		LogicTipoProducto ctrlTipoProducto = new LogicTipoProducto();
		LinkedList<Marca> marcas=ctrlMarca.getAll();
		LinkedList<Tipo_Producto> tps=ctrlTipoProducto.getAll();
		LinkedList<Producto> productos=ctrlProducto.buscarAlmacen();
		request.getSession().setAttribute("productos",productos);
		request.setAttribute("listaMarcas", marcas);
		request.setAttribute("listaTipoProductos", tps);
		request.setAttribute("listaProductos", productos);
		request.getRequestDispatcher("WEB-INF/Store.jsp").forward(request, response);
		}
		
	private void bebidas(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		LogicMarca ctrlMarca = new LogicMarca();
		LogicProducto ctrlProducto = new LogicProducto();
		LogicTipoProducto ctrlTipoProducto = new LogicTipoProducto();
		LinkedList<Marca> marcas=ctrlMarca.getAll();
		LinkedList<Tipo_Producto> tps=ctrlTipoProducto.getAll();
		LinkedList<Producto> productos=ctrlProducto.buscarBebidas();
		request.getSession().setAttribute("productos",productos);
		request.setAttribute("listaMarcas", marcas);
		request.setAttribute("listaTipoProductos", tps);
		request.setAttribute("listaProductos", productos);
		request.getRequestDispatcher("WEB-INF/Store.jsp").forward(request, response);
		}
	
	private void frescos(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		LogicMarca ctrlMarca = new LogicMarca();
		LogicProducto ctrlProducto = new LogicProducto();
		LogicTipoProducto ctrlTipoProducto = new LogicTipoProducto();
		LinkedList<Marca> marcas=ctrlMarca.getAll();
		LinkedList<Tipo_Producto> tps=ctrlTipoProducto.getAll();
		LinkedList<Producto> productos=ctrlProducto.buscarFrescos();
		request.getSession().setAttribute("productos",productos);
		request.setAttribute("listaMarcas", marcas);
		request.setAttribute("listaTipoProductos", tps);
		request.setAttribute("listaProductos", productos);
		request.getRequestDispatcher("WEB-INF/Store.jsp").forward(request, response);
		}
	
	private void electro(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		LogicMarca ctrlMarca = new LogicMarca();
		LogicProducto ctrlProducto = new LogicProducto();
		LogicTipoProducto ctrlTipoProducto = new LogicTipoProducto();
		LinkedList<Marca> marcas=ctrlMarca.getAll();
		LinkedList<Tipo_Producto> tps=ctrlTipoProducto.getAll();
		LinkedList<Producto> productos=ctrlProducto.buscarElectro();
		request.getSession().setAttribute("productos",productos);
		request.setAttribute("listaMarcas", marcas);
		request.setAttribute("listaTipoProductos", tps);
		request.setAttribute("listaProductos", productos);
		request.getRequestDispatcher("WEB-INF/Store.jsp").forward(request, response);
		}

	private void limpieza(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		LogicMarca ctrlMarca = new LogicMarca();
		LogicProducto ctrlProducto = new LogicProducto();
		LogicTipoProducto ctrlTipoProducto = new LogicTipoProducto();
		LinkedList<Marca> marcas=ctrlMarca.getAll();
		LinkedList<Tipo_Producto> tps=ctrlTipoProducto.getAll();
		LinkedList<Producto> productos=ctrlProducto.buscarLimpieza();
		request.getSession().setAttribute("productos",productos);
		request.setAttribute("listaMarcas", marcas);
		request.setAttribute("listaTipoProductos", tps);
		request.setAttribute("listaProductos", productos);
		request.getRequestDispatcher("WEB-INF/Store.jsp").forward(request, response);
		}

	private void categoria(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		String marc = request.getParameter("categoria");
		LogicMarca ctrlMarca = new LogicMarca();
		LogicProducto ctrlProducto = new LogicProducto();
		LogicTipoProducto ctrlTipoProducto = new LogicTipoProducto();
		LinkedList<Marca> marcas=ctrlMarca.getAll();
		LinkedList<Tipo_Producto> tps=ctrlTipoProducto.getAll();
		LinkedList<Producto> productos=ctrlProducto.buscarCategoria(marc);
		request.getSession().setAttribute("productos",productos);
		request.setAttribute("listaMarcas", marcas);
		request.setAttribute("listaTipoProductos", tps);
		request.setAttribute("listaProductos", productos);
		request.getRequestDispatcher("WEB-INF/Store.jsp").forward(request, response);
		}

	private void marca(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		String marc = request.getParameter("marca");
		LogicMarca ctrlMarca = new LogicMarca();
		LogicProducto ctrlProducto = new LogicProducto();
		LogicTipoProducto ctrlTipoProducto = new LogicTipoProducto();
		LinkedList<Marca> marcas=ctrlMarca.getAll();
		LinkedList<Tipo_Producto> tps=ctrlTipoProducto.getAll();
		LinkedList<Producto> productos=ctrlProducto.buscarMarca(marc);
		request.getSession().setAttribute("productos",productos);
		request.setAttribute("listaMarcas", marcas);
		request.setAttribute("listaTipoProductos", tps);
		request.setAttribute("listaProductos", productos);
		request.getRequestDispatcher("WEB-INF/Store.jsp").forward(request, response);
		}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int var =(Integer.parseInt(request.getParameter("var"))); 
		if (var == 1 ) {
		LogicMarca ctrlMarca = new LogicMarca();
		LogicTipoProducto ctrlTipoProducto = new LogicTipoProducto();
		LogicProducto ctrlProducto = new LogicProducto();
		String desc = request.getParameter("buscar");
		LinkedList<Marca> marcas=ctrlMarca.getAll();
		LinkedList<Tipo_Producto> tps=ctrlTipoProducto.getAll();
		LinkedList<Producto> productos=ctrlProducto.buscar("%"+desc+"%");
		request.getSession().setAttribute("productos",productos);
		request.setAttribute("listaMarcas", marcas);
		request.setAttribute("listaTipoProductos", tps);
		request.setAttribute("listaProductos", productos);
		request.getRequestDispatcher("WEB-INF/Store.jsp").forward(request, response);
		}
		
		else {
				LogicMarca ctrlMarca = new LogicMarca();
				LogicTipoProducto ctrlTipoProducto = new LogicTipoProducto();
				LinkedList<Marca> marcas=ctrlMarca.getAll();
				LinkedList<Tipo_Producto> tps=ctrlTipoProducto.getAll();
				int var2 = Integer.parseInt((request.getParameter("precio")));
				LinkedList<Producto> productos =(LinkedList<Producto>)request.getSession().getAttribute("productos");
				if (var2 == 1){
					productos.sort(Comparator.comparing(p -> p.getPrecio()));
				}
				else {
					Comparator<Producto> comparator = Comparator.comparing(p -> p.getPrecio());
					productos.sort(comparator.reversed());
					}
					request.setAttribute("listaMarcas", marcas);
					request.setAttribute("listaTipoProductos", tps);
					request.setAttribute("listaProductos", productos);
					request.getRequestDispatcher("WEB-INF/Store.jsp").forward(request, response);
			
		}
	}
}
