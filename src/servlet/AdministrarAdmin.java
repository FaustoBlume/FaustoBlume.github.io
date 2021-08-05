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



@WebServlet({ "/administraradmin", "/ADMINISTRARADMIN", "/AdministrarAdmin", "/Administraradmin", "/administrarAdmin" })// todas las urls que se van a aceptar
public class AdministrarAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public AdministrarAdmin() {
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
			int id=(Integer.parseInt(request.getParameter("idAdmin"))); 
			Administrador admin = new Administrador();
			LogicAdministrador ctrlAdrministrador = new LogicAdministrador();
			admin = ctrlAdrministrador.getOne(id);
			request.setAttribute("admin", admin);
			request.getRequestDispatcher("EditarAdministrador.jsp").forward(request, response);
		}
		private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
			LogicAdministrador ctrlAdrministrador = new LogicAdministrador();
			LinkedList<Administrador> admins=ctrlAdrministrador.getAll();
			request.setAttribute("listaAdmins", admins);
			request.getRequestDispatcher("WEB-INF/UserAdministrador.jsp").forward(request, response);
			}
		
		
		
		private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
			
			Administrador admin = new Administrador();
			LogicAdministrador ctrlAdrministrador = new LogicAdministrador();
			admin.setId(Integer.parseInt(request.getParameter("idAdmin")));
			ctrlAdrministrador.borrar(admin);
			LinkedList<Administrador> admins=ctrlAdrministrador.getAll();
			request.setAttribute("listaAdmins", admins);
			request.getRequestDispatcher("WEB-INF/UserAdministrador.jsp").forward(request, response);
		}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int var =(Integer.parseInt(request.getParameter("var")));
		
		if (var == 1 ) {
			LogicAdministrador ctrlAdministrador = new LogicAdministrador();
			Administrador admin = new Administrador();
			admin.setId(Integer.parseInt(request.getParameter("idAdmin2")));
			admin.setNombre(request.getParameter("nombre2"));
			admin.setApellido(request.getParameter("apellido2"));
			admin.setRol(request.getParameter("rol2"));
			admin.setNombreUsuario(request.getParameter("nombreUsuario2"));
			admin.setE_mail(request.getParameter("e_mail2"));
			admin.setPass(request.getParameter("contrasena2"));
			admin.setSueldo(Double.parseDouble(request.getParameter("sueldo2")));
			ctrlAdministrador.actualizar(admin);
			LinkedList<Administrador> admins=ctrlAdministrador.getAll();
			request.setAttribute("listaAdmins", admins);
			request.getRequestDispatcher("WEB-INF/UserAdministrador.jsp").forward(request, response);
		}
		else{
		
		LogicAdministrador ctrlAdministrador = new LogicAdministrador();
		Administrador admin = new Administrador();
		admin.setNombre(request.getParameter("nombre"));
		admin.setApellido(request.getParameter("apellido"));
		admin.setRol(request.getParameter("rol"));
		admin.setNombreUsuario(request.getParameter("nombreUsuario"));
		admin.setE_mail(request.getParameter("e_mail"));
		admin.setPass(request.getParameter("contrasena"));
		admin.setSueldo(Double.parseDouble(request.getParameter("sueldo")));
		ctrlAdministrador.nuevo(admin);
		LinkedList<Administrador> admins=ctrlAdministrador.getAll();
		request.setAttribute("listaAdmins", admins);
		request.getRequestDispatcher("WEB-INF/UserAdministrador.jsp").forward(request, response);}
		
	}
	

}