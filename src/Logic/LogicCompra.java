package Logic;

import java.util.LinkedList;

import Data.DataCompra;
import Entidades.Compra;

public class LogicCompra {
	DataCompra dc;


	public LogicCompra() {
		dc=new DataCompra();
	}

	public LinkedList<Compra> getAll(){
		return dc.getAll();

	}

	public void nuevo(Compra c) {
		dc.add(c);
	}
	public Compra getOne(int id,String fecha) {
		return dc.getByCompra(id,fecha);
	}
	
	
}
