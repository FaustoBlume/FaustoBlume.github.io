package Logic;

import Data.DataUsuario;
import Entidades.Usuario;

public class LogicUsuario {
DataUsuario du;


public LogicUsuario() {
	du=new DataUsuario();
	

}
public Usuario validate(Usuario u) {
	return du.getByUser(u);
}
}