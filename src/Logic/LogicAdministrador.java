package Logic;

import java.util.LinkedList;

import Data.DataAdministrador;
import Entidades.Administrador;

public class LogicAdministrador {
	DataAdministrador da;
	
	
	public LogicAdministrador() {
		da=new DataAdministrador();		
	}
	
	public void nuevo(Administrador admin) {
		da.add(admin);
	}
	public Administrador getOne(int id_a) {
		return da.getByUser(id_a);
	}
	
	public LinkedList<Administrador> getAll(){
		return da.getAll();
	
	}
	public void actualizar(Administrador a) {
		da.update(a);
	}
	
	public void borrar(Administrador a) {
		da.delete(a);
	}
}
