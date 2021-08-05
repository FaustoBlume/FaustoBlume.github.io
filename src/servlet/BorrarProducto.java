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


@WebServlet({ "/borrarproducto", "/BORRARPRODUCTO", "/BorrarProducto", "/Borrarproducto", "/borrarProducto" })// todas las urls que se van a aceptar
public class BorrarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BorrarProducto() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LogicProducto ctrlProducto = new LogicProducto();
		Producto prod = new Producto();
		LogicMarca ctrlMarca = new LogicMarca();
		LogicTipoProducto ctrlTipoProducto = new LogicTipoProducto();
		prod.setId(Integer.parseInt(request.getParameter("idProdu")));
		ctrlProducto.borrar(prod);
		LinkedList<Marca> marcas=ctrlMarca.getAll();
		LinkedList<Tipo_Producto> tps=ctrlTipoProducto.getAll();
		request.setAttribute("listaMarcas", marcas);
		request.setAttribute("listaTipoProductos", tps);
		LinkedList<Producto> productos=ctrlProducto.getAll();
		request.setAttribute("listaProductos", productos);
		request.getRequestDispatcher("WEB-INF/Userproducts.jsp").forward(request, response);	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}

