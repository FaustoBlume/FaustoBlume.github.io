package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.*;
import Logic.*;


@WebServlet({ "/administrarcompra", "/ADMINISTRARCOMPRA", "/AdministrarCompra" })
public class AdministrarCompra extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdministrarCompra() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action"); 
		try {
			switch (action) { 		
			case "comprar":
				Comprar(request, response);
				break;
			case "finalizar":
				Finalizar(request, response);
				break;
			default:
				break;
			}			
		} catch (SQLException e) {
			e.getStackTrace();
		}

	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String action = request.getParameter("action"); 
		try {
			switch (action) { 		
			case "agregar":
				agregar(request, response);
				break;
			case "eliminar":
				eliminar(request, response);
				break;
			default:
				break;
			}			
		} catch (SQLException e) {
			e.getStackTrace();
		}

		}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		List<Linea_Pedido> lineas = (List<Linea_Pedido>)request.getSession().getAttribute("lineas");
		int index = Existe(Integer.parseInt(request.getParameter("idprodu")),lineas);
		lineas.remove(index);
		request.getSession().setAttribute("lineas",lineas);
		LogicMarca ctrlMarca = new LogicMarca();
		LogicTipoProducto ctrlTipoProducto = new LogicTipoProducto();
		LogicProducto ctrlProducto = new LogicProducto();
		LinkedList<Marca> marcas=ctrlMarca.getAll();
		LinkedList<Tipo_Producto> tps=ctrlTipoProducto.getAll();
		LinkedList<Producto> productos=ctrlProducto.getAll();
		request.setAttribute("listaMarcas", marcas);
		request.setAttribute("listaTipoProductos", tps);
		request.setAttribute("listaProductos", productos);
		request.getRequestDispatcher("WEB-INF/Store.jsp").forward(request, response);
	}
	
	private void agregar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Cliente cli = (Cliente)request.getSession().getAttribute("cliente");
		if (cli == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		else {
			LogicProducto ctrlProducto = new LogicProducto();
			Producto prod = ctrlProducto.getOne(Integer.parseInt(request.getParameter("idprodu")));
			if(request.getSession().getAttribute("lineas")== null) {
				List<Linea_Pedido> lineas = new ArrayList<Linea_Pedido>();
				Linea_Pedido ln = new Linea_Pedido();
				ln.setProd(prod);
				ln.setCantidad(1);
				ln.setSubtotal(prod.getPrecio());
				lineas.add(ln);
				request.getSession().setAttribute("lineas",lineas);
			}
			else {
				List<Linea_Pedido> lineas = (List<Linea_Pedido>)request.getSession().getAttribute("lineas");
				int index = Existe(prod.getId(),lineas);
				if (index == -1) {
					Linea_Pedido ln = new Linea_Pedido();
					ln.setProd(prod);
					ln.setCantidad(1);
					ln.setSubtotal(prod.getPrecio());
					lineas.add(ln);
				}
				else {
					int cantidad = lineas.get(index).getCantidad()+1;
					lineas.get(index).setCantidad(cantidad);
					double total = lineas.get(index).getSubtotal()+prod.getPrecio();
					lineas.get(index).setSubtotal(total);
				}
				request.getSession().setAttribute("lineas",lineas);
			}
			LogicMarca ctrlMarca = new LogicMarca();
			LogicTipoProducto ctrlTipoProducto = new LogicTipoProducto();
			LinkedList<Marca> marcas=ctrlMarca.getAll();
			LinkedList<Tipo_Producto> tps=ctrlTipoProducto.getAll();
			LinkedList<Producto> productos=ctrlProducto.getAll();
			request.setAttribute("listaMarcas", marcas);
			request.setAttribute("listaTipoProductos", tps);
			request.setAttribute("listaProductos", productos);
			request.getRequestDispatcher("WEB-INF/Store.jsp").forward(request, response);
		}
		
	}
	
	private int Existe(int id, List<Linea_Pedido> lineas) {
		for (int i = 0; i < lineas.size(); i++) {
			if (lineas.get(i).getProd().getId()== id) {
				return i;
			}
		}
		return -1;
	}
	
	private void Comprar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		LogicProducto ctrlProducto = new LogicProducto();
		LogicMarca ctrlMarca = new LogicMarca();
		LogicTipoProducto ctrlTipoProducto = new LogicTipoProducto();
		LinkedList<Marca> marcas=ctrlMarca.getAll();
		LinkedList<Tipo_Producto> tps=ctrlTipoProducto.getAll();
		LinkedList<Producto> productos=ctrlProducto.getAll();
		request.setAttribute("listaMarcas", marcas);
		request.setAttribute("listaTipoProductos", tps);
		request.setAttribute("listaProductos", productos);
		request.getRequestDispatcher("WEB-INF/CheckOut.jsp").forward(request, response);
	}
	
	private void Finalizar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Cliente cli = (Cliente)request.getSession().getAttribute("cliente");
		Compra comp= new Compra();
		comp.setTotal(Double.parseDouble(request.getParameter("totalcompra")));
		comp.setUsu(cli);
		Date fecha = new java.util.Date();
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fecha2 = formatter.format(fecha);
		comp.setFecha(fecha2);
		LogicCompra ctrlCompra = new LogicCompra();
		ctrlCompra.nuevo(comp);
		LogicLinea_Pedido ctrlLineaPedido = new LogicLinea_Pedido();
		List<Linea_Pedido> lineas = (List<Linea_Pedido>)request.getSession().getAttribute("lineas");
		for (Linea_Pedido linea : lineas) {
		linea.setCompra(ctrlCompra.getOne(comp.getUsu().getId(), fecha2));
		ctrlLineaPedido.nuevo(linea);
		 }
		request.getSession().setAttribute("lineas",null);
		request.getRequestDispatcher("Index.jsp").forward(request, response);
	}
}

