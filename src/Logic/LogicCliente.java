package Logic;

import java.util.LinkedList;

import Data.DataCliente;
import Entidades.Cliente;

public class LogicCliente {
	DataCliente dc;


	public LogicCliente() {
		dc=new DataCliente();

	}

	public LinkedList<Cliente> getAll(){
		return dc.getAll();

	}
	
	public Cliente getOne(int id_a) {
		return dc.getByUser(id_a);
	}
	
	public void nuevo(Cliente c){
		
		dc.add(c);
		
	}
	
	

	public void actualizar(Cliente c) {
	dc.update(c);
	}

	public void borrar(Cliente c) {
	dc.delete(c);
	}
}

