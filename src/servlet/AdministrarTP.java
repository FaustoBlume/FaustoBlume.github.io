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



@WebServlet({ "/administrartipoproducto", "/ADMINISTRARTIPOPRODUCTO", "/AdministrarTipoProducto", "/Administrartipoproducto", "/administrarTipoproducto" })// todas las urls que se van a aceptar
public class AdministrarTP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdministrarTP() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String action = request.getParameter("action"); 
		try {
			switch (action) { 		
			case "buscar":
				buscar(request, response);
				break;
			case "eliminar":
				eliminar(request, response);
				break;
			case "mostrar":
				mostrar(request, response);
				break;
			case "editar":
				break;
			default:
				break;
			}			
		} catch (SQLException e) {
			e.getStackTrace();
		}
		
		}
	
	private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		
		LogicTipoProducto ctrlTipoProducto = new LogicTipoProducto();
		LinkedList<Tipo_Producto> tps=ctrlTipoProducto.getAll();
		request.setAttribute("listaTipoProductos", tps);
		request.getRequestDispatcher("WEB-INF/UserTipoProductos.jsp").forward(request, response);
		}
	
	private void buscar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		int id=(Integer.parseInt(request.getParameter("idTp")));
		LogicTipoProducto ctrlTipoProducto = new LogicTipoProducto();
		Tipo_Producto tp = new Tipo_Producto();
		tp = ctrlTipoProducto.getOne(id);
		LinkedList<Tipo_Producto> tps=ctrlTipoProducto.getAll();
		request.setAttribute("listaTipoProductos", tps);
		request.setAttribute("TipoProducto", tp);
		request.getRequestDispatcher("EditarTipoProducto.jsp").forward(request, response);
	}
	
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Tipo_Producto tp = new Tipo_Producto();
		LogicTipoProducto ctrlTipoProducto = new LogicTipoProducto();
		tp.setId(Integer.parseInt(request.getParameter("idTp")));
		String alerta=ctrlTipoProducto.borrar(tp);
		LinkedList<Tipo_Producto> tps=ctrlTipoProducto.getAll();
		request.setAttribute("alerta", alerta);
		request.setAttribute("listaTipoProductos", tps);
		request.getRequestDispatcher("WEB-INF/UserTipoProductos.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int var =(Integer.parseInt(request.getParameter("var")));
		if (var == 0 ) {
		LogicTipoProducto ctrlTipoProducto = new LogicTipoProducto();
		Tipo_Producto tp = new Tipo_Producto();
		tp.setDescripcion(request.getParameter("descripcionTp"));
		String alerta=ctrlTipoProducto.nuevo(tp);
		LinkedList<Tipo_Producto> tps=ctrlTipoProducto.getAll();
		request.setAttribute("alerta", alerta);
		request.setAttribute("listaTipoProductos", tps);
		request.getRequestDispatcher("WEB-INF/UserTipoProductos.jsp").forward(request, response);}
	else{
		LogicTipoProducto ctrlTipoProducto = new LogicTipoProducto();
		Tipo_Producto tp = new Tipo_Producto();
		tp.setId(Integer.parseInt(request.getParameter("idTp2")));
		tp.setDescripcion(request.getParameter("descripcionTp"));
		String alerta =ctrlTipoProducto.actualizar(tp);
		LinkedList<Tipo_Producto> tps=ctrlTipoProducto.getAll();
		request.setAttribute("alerta", alerta);
		request.setAttribute("listaTipoProductos", tps);
		request.getRequestDispatcher("WEB-INF/UserTipoProductos.jsp").forward(request, response);
	}
	}
	

}