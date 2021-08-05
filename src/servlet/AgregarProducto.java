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


@WebServlet({ "/agregarproducto", "/AGREGARPRODUCTO", "/AgregarProducto", "/Agregarproducto", "/agregarProducto" })// todas las urls que se van a aceptar
public class AgregarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AgregarProducto() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LogicMarca ctrlMarca = new LogicMarca();
		LogicProducto ctrlProducto = new LogicProducto();
		LogicTipoProducto ctrlTipoProducto = new LogicTipoProducto();
		LinkedList<Marca> marcas=ctrlMarca.getAll();
		LinkedList<Tipo_Producto> tps=ctrlTipoProducto.getAll();
		LinkedList<Producto> productos=ctrlProducto.getAll();
		request.setAttribute("listaMarcas", marcas);
		request.setAttribute("listaTipoProductos", tps);
		request.setAttribute("listaProductos", productos);
		request.getRequestDispatcher("WEB-INF/Userproducts.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LogicProducto ctrlProducto = new LogicProducto();
		Producto prod = new Producto();
		prod.setDescripcion(request.getParameter("descripcion"));
		prod.setPrecio(Double.parseDouble(request.getParameter("precio")));
		prod.setStock(Integer.parseInt(request.getParameter("stock")));
		prod.setFoto(request.getParameter("foto"));
		LogicTipoProducto ctrlTipoProducto = new LogicTipoProducto();
		prod.setTp(ctrlTipoProducto.getOne(Integer.parseInt(request.getParameter("tp"))));
		LogicMarca ctrlMarca = new LogicMarca();
		prod.setMarca(ctrlMarca.getOne(Integer.parseInt(request.getParameter("marca"))));
		String alerta=ctrlProducto.nuevo(prod);
		LinkedList<Marca> marcas=ctrlMarca.getAll();
		LinkedList<Tipo_Producto> tps=ctrlTipoProducto.getAll();
		request.setAttribute("alerta", alerta);
		request.setAttribute("listaMarcas", marcas);
		request.setAttribute("listaTipoProductos", tps);
		LinkedList<Producto> productos=ctrlProducto.getAll();
		request.setAttribute("listaProductos", productos);
		request.getRequestDispatcher("WEB-INF/Userproducts.jsp").forward(request, response);
	}
	

}
