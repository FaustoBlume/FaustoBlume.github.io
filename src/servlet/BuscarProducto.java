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


@WebServlet({ "/buscarproducto", "/BUSCARPRODUCTO", "/BuscarProducto", "/Buscarproducto", "/buscarProducto" })// todas las urls que se van a aceptar
public class BuscarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BuscarProducto() {
        super();

    }
    
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	int id=(Integer.parseInt(request.getParameter("idProdu"))); 
	LogicProducto ctrlProducto = new LogicProducto();
	Producto prod = new Producto();
	prod = ctrlProducto.getOne(id);
	LogicMarca ctrlMarca = new LogicMarca();
	LogicTipoProducto ctrlTipoProducto = new LogicTipoProducto();
	LinkedList<Marca> marcas=ctrlMarca.getAll();
	LinkedList<Tipo_Producto> tps=ctrlTipoProducto.getAll();
	request.setAttribute("listaMarcas", marcas);
	request.setAttribute("listaTipoProductos", tps);
	request.setAttribute("producto", prod);
	request.getRequestDispatcher("EditarProducto.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
