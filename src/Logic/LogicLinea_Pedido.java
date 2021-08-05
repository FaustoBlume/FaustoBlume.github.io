package Logic;

import Entidades.Linea_Pedido;
import Data.DataLineaPedido;

public class LogicLinea_Pedido {
	DataLineaPedido dlp;
	
	
	public LogicLinea_Pedido() {
		dlp=new DataLineaPedido();
	}
	public void nuevo(Linea_Pedido lp) {
		dlp.add(lp);
	}
	

}
