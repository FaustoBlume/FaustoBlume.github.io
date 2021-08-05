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


@WebServlet({ "/editarproducto", "/EDITARPRODUCTO", "/EditarProducto", "/Editarproducto", "/editarProducto" })// todas las urls que se van a aceptar
public class EditarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EditarProducto() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LogicProducto ctrlProducto = new LogicProducto();
		Producto prod = new Producto();
		prod.setId(Integer.parseInt(request.getParameter("idprodu2")));
		prod.setDescripcion(request.getParameter("descripcion2"));
		prod.setPrecio(Double.parseDouble(request.getParameter("precio2")));
		prod.setStock(Integer.parseInt(request.getParameter("stock2")));
		prod.setFoto(request.getParameter("foto2"));
		LogicTipoProducto ctrlTipoProducto = new LogicTipoProducto();
		prod.setTp(ctrlTipoProducto.getOne(Integer.parseInt(request.getParameter("tp2"))));
		LogicMarca ctrlMarca = new LogicMarca();
		prod.setMarca(ctrlMarca.getOne(Integer.parseInt(request.getParameter("marca2"))));
		LinkedList<Marca> marcas=ctrlMarca.getAll();
		LinkedList<Tipo_Producto> tps=ctrlTipoProducto.getAll();
		request.setAttribute("listaMarcas", marcas);
		request.setAttribute("listaTipoProductos", tps);
		String alerta=ctrlProducto.actualizar(prod);
		LinkedList<Producto> productos=ctrlProducto.getAll();
		request.setAttribute("alerta", alerta);
		request.setAttribute("listaProductos", productos);
		request.getRequestDispatcher("WEB-INF/Userproducts.jsp").forward(request, response);
	}
	

}