package Logic;


import java.util.LinkedList;

import Data.DataTipoProducto;
import Entidades.Tipo_Producto;


public class LogicTipoProducto {
	DataTipoProducto dtp;


	public LogicTipoProducto() {
		dtp=new DataTipoProducto();
	}

	public LinkedList<Tipo_Producto> getAll(){
		return dtp.getAll();

	}
	public String nuevo(Tipo_Producto tp) {
		return dtp.add(tp);
	}
	public String actualizar(Tipo_Producto tp) {
		return dtp.update(tp);
	}
	public String borrar(Tipo_Producto tp) {
		return dtp.delete(tp);
	}
	public Tipo_Producto getOne(int id_tp) {
		return dtp.getByTipoProducto(id_tp);
	}
	public  Tipo_Producto buscar(Tipo_Producto tp) {
		return dtp.search(tp);
	}
	public  Tipo_Producto getOneDesc(String m) {
		return dtp.getByDescripcion(m);
	}
}

