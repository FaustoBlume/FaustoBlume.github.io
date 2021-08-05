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



@WebServlet({ "/administrarcliente", "/ADMINISTRARCLIENTE", "/AdministrarCliente", "/Administrarcliente", "/administrarCliente" })// todas las urls que se van a aceptar
public class AdministrarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public AdministrarCliente() {
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
		int id=(Integer.parseInt(request.getParameter("idCli"))); 
		Cliente cliente = new Cliente();
		LogicCliente ctrlCliente = new LogicCliente();
		cliente = ctrlCliente.getOne(id);
		request.setAttribute("cliente", cliente);
		request.getRequestDispatcher("EditarCliente.jsp").forward(request, response);
	}
	private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		LogicCliente ctrlCliente = new LogicCliente();
		LinkedList<Cliente> clientes=ctrlCliente.getAll();
		request.setAttribute("listaClientes", clientes);
		request.getRequestDispatcher("WEB-INF/UserClientes.jsp").forward(request, response);
		}
	
	
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		
		Cliente cliente = new Cliente();
		LogicCliente ctrlCliente = new LogicCliente();
		cliente.setId(Integer.parseInt(request.getParameter("idCli")));
		ctrlCliente.borrar(cliente);
		LinkedList<Cliente> clientes=ctrlCliente.getAll();
		request.setAttribute("listaClientes", clientes);
		request.getRequestDispatcher("WEB-INF/UserClientes.jsp").forward(request, response);
	}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	int var =(Integer.parseInt(request.getParameter("var")));
	
	if (var == 1 ) {
		Cliente cliente = new Cliente();
		LogicCliente ctrlCliente = new LogicCliente();
		cliente.setId(Integer.parseInt(request.getParameter("idCliente2")));
		cliente.setNombre(request.getParameter("nombre2"));
		cliente.setApellido(request.getParameter("apellido2"));
		cliente.setRol(request.getParameter("rol2"));
		cliente.setNombreUsuario(request.getParameter("nombreUsuario2"));
		cliente.setE_mail(request.getParameter("e_mail2"));
		cliente.setPass(request.getParameter("contrasena2"));
		cliente.setEdad(request.getParameter("edad2"));
		cliente.setTelefono(request.getParameter("telefono2"));
		cliente.setDomicilio(request.getParameter("domicilio2"));
		ctrlCliente.actualizar(cliente);
		LinkedList<Cliente> clientes=ctrlCliente.getAll();
		request.setAttribute("listaClientes", clientes);
		request.getRequestDispatcher("WEB-INF/UserClientes.jsp").forward(request, response);
	}
	else{
	
	Cliente cliente = new Cliente();
	LogicCliente ctrlCliente = new LogicCliente();
	cliente.setNombre(request.getParameter("nombre"));
	cliente.setApellido(request.getParameter("apellido"));
	cliente.setRol(request.getParameter("rol"));
	cliente.setNombreUsuario(request.getParameter("nombreUsuario"));
	cliente.setE_mail(request.getParameter("e_mail"));
	cliente.setPass(request.getParameter("contrasena"));
	cliente.setEdad(request.getParameter("edad"));
	cliente.setTelefono(request.getParameter("telefono"));
	cliente.setDomicilio(request.getParameter("domicilio"));
	ctrlCliente.nuevo(cliente);
	LinkedList<Cliente> clientes=ctrlCliente.getAll();
	request.setAttribute("listaClientes", clientes);
	request.getRequestDispatcher("WEB-INF/UserClientes.jsp").forward(request, response);}
	
}


}