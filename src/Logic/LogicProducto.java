package Logic;

import java.util.LinkedList;

import Data.DataProducto;
import Entidades.Producto;


public class LogicProducto {
	DataProducto dp;


	public LogicProducto() {
		dp=new DataProducto();
	}

	public LinkedList<Producto> getAll(){
		return dp.getAll();

	}
	public String nuevo(Producto p) {
		return dp.add(p);
	}
	public String actualizar(Producto p) {
		return dp.update(p);
	}
	public void borrar(Producto p) {
		dp.delete(p);
	}
	public Producto getOne(int id) {
		return dp.getByProducto(id);
	}
	public LinkedList<Producto> buscar(String desc) {
		return dp.search(desc);
	}
	
	public LinkedList<Producto> buscarAlmacen() {
		return dp.searchAlmacen();
	}
	
	public LinkedList<Producto> buscarBebidas() {
		return dp.searchBebidas();
	}
	
	public LinkedList<Producto> buscarElectro() {
		return dp.searchElectro();
	}
	
	public LinkedList<Producto> buscarLimpieza() {
		return dp.searchLimpieza();
	}
	
	public LinkedList<Producto> buscarFrescos() {
		return dp.searchFrescos();
	}
	
	public LinkedList<Producto> buscarCategoria(String categoria) {
		return dp.searchCategoria(categoria);
	}
	public LinkedList<Producto> buscarMarca(String categoria) {
		return dp.searchMarca(categoria);
	}
	
	public LinkedList<Producto> ordenarAsc() {
		return dp.orderByAsc();
	}
	
	public LinkedList<Producto> ordenarDesc() {
		return dp.orderByDesc();
	}
}