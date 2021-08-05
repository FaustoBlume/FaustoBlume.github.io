package servlet;

import java.io.IOException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Entidades.*;
import Logic.*;


@WebServlet({ "/signin", "/SIGNIN", "/SignIn", "/Signin", "/signIn" })
public class Signin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public Signin() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("lineas",null);
		request.getSession().setAttribute("cliente",null);
		request.getRequestDispatcher("Index.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LogicUsuario ctrlUsuario = new LogicUsuario();
		LogicProducto ctrlProducto = new LogicProducto();
		LinkedList<Producto> productos=ctrlProducto.getAll();
		Usuario usu = new Usuario();
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		usu.setE_mail(email);
		usu.setPass(password);
		usu=ctrlUsuario.validate(usu);
		try {
		if (usu.getRol().equals("administrador")) {
			request.getSession().setAttribute("usuario", usu);
			LogicMarca ctrlMarca = new LogicMarca();
			LogicTipoProducto ctrlTipoProducto = new LogicTipoProducto();
			LinkedList<Marca> marcas=ctrlMarca.getAll();
			LinkedList<Tipo_Producto> tps=ctrlTipoProducto.getAll();
			request.setAttribute("listaMarcas", marcas);
			request.setAttribute("listaTipoProductos", tps);
			request.setAttribute("listaProductos", productos);
			request.getRequestDispatcher("WEB-INF/Userproducts.jsp").forward(request, response);}
		else {
			LogicCliente ctrlCliente = new LogicCliente();
			Cliente cli = new Cliente();
			cli=ctrlCliente.getOne(usu.getId());
			String nombre_msg = usu.getNombreUsuario();
			if(nombre_msg!=null) {
				int var = 1;
				request.getSession().setAttribute("variable", var);
				request.getSession().setAttribute("cliente", cli);
				request.getRequestDispatcher("Index.jsp").forward(request, response);
				;};};}
		catch(NullPointerException e) {
			String login_msg="error";  
			if(login_msg!=null)
				request.setAttribute("error", login_msg);
				request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
	}

}
