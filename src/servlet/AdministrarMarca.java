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



@WebServlet({ "/administrarmarca", "/ADMINISTRARMARCA", "/AdministrarMarca", "/Administrarmarca", "/administrarMarca" })// todas las urls que se van a aceptar
public class AdministrarMarca extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public AdministrarMarca() {
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

	private void buscar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		int id=(Integer.parseInt(request.getParameter("idMarca"))); 
		Marca marc = new Marca();
		LogicMarca ctrlMarca = new LogicMarca();
		marc = ctrlMarca.getOne(id);
		request.setAttribute("Marca", marc);
		request.getRequestDispatcher("EditarMarca.jsp").forward(request, response);
	}
	private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		LogicMarca ctrlMarca = new LogicMarca();
		LinkedList<Marca> marcas=ctrlMarca.getAll();
		request.setAttribute("listaMarcas", marcas);
		request.getRequestDispatcher("WEB-INF/UserMarcas.jsp").forward(request, response);
		}
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		
		Marca marc = new Marca();
		LogicMarca ctrlMarca = new LogicMarca();
		marc.setId(Integer.parseInt(request.getParameter("idMarca")));
		String alerta= ctrlMarca.borrar(marc);
		LinkedList<Marca> marcas=ctrlMarca.getAll();
		request.setAttribute("alerta", alerta);
		request.setAttribute("listaMarcas", marcas);
		request.getRequestDispatcher("WEB-INF/UserMarcas.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int var =(Integer.parseInt(request.getParameter("var")));
		if (var == 1 ) {
			LogicMarca ctrlMarca = new LogicMarca();
			Marca marc = new Marca();
			marc.setId(Integer.parseInt(request.getParameter("idmarca2")));
			marc.setDescripcion(request.getParameter("descripcionMarca"));
			String alerta = ctrlMarca.actualizar(marc);
			LinkedList<Marca> marcas=ctrlMarca.getAll();
			request.setAttribute("alerta", alerta);
			request.setAttribute("listaMarcas", marcas);
			request.getRequestDispatcher("WEB-INF/UserMarcas.jsp").forward(request, response);
		}
		else {
			LogicMarca ctrlMarca = new LogicMarca();
			Marca marc = new Marca();
			marc.setDescripcion(request.getParameter("descripcionMarc"));
			String alerta= ctrlMarca.nuevo(marc);
			LinkedList<Marca> marcas=ctrlMarca.getAll();
			request.setAttribute("alerta", alerta);
			request.setAttribute("listaMarcas", marcas);
			request.getRequestDispatcher("WEB-INF/UserMarcas.jsp").forward(request, response);
		}
	}
	

}
