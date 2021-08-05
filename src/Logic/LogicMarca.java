package Logic;


import java.util.LinkedList;

import Data.DataMarca;
import Entidades.Marca;


public class LogicMarca {
	DataMarca dm;


	public LogicMarca() {
		dm=new DataMarca();
	}

	public LinkedList<Marca> getAll(){
		return dm.getAll();

	}
	public String nuevo(Marca m) {
		return dm.add(m);
	}
	public String actualizar(Marca m) {
		return dm.update(m);
	}
	public String borrar(Marca m) {
		return dm.delete(m);
	}
	public Marca getOne(int id_m) {
		return dm.getByMarca(id_m);
	}
	public  Marca buscar(Marca m) {
		return dm.search(m);
	}
	public  Marca getOneDesc(String m) {
		return dm.getByDescripcion(m);
	}
}
