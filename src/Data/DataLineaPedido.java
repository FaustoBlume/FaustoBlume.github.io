package Data;
import Entidades.*;

import java.sql.*;

public class DataLineaPedido {
	
	public void add(Linea_Pedido lp) {
        PreparedStatement stmt= null;
            ResultSet keyResultSet=null;
        try {
        stmt=DbConnector.getInstancia().getConn().
                    prepareStatement(
                    		"insert into linea_pedido(subtotal, cantidad, id_producto,id_compra) values(?,?,?,?)"
                            );
            stmt.setDouble(1, lp.getSubtotal());
            stmt.setInt(2, lp.getCantidad());
            stmt.setInt(3, lp.getProd().getId());
            stmt.setInt(4, lp.getCompra().getId()); 
            stmt.executeUpdate();
                keyResultSet=stmt.getGeneratedKeys();
                if(keyResultSet!=null && keyResultSet.next()){
                lp.setId(keyResultSet.getInt(1));
           }


            }  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                    if(stmt!=null)stmt.close();
                    DbConnector.getInstancia().releaseConn();
                } catch (SQLException e) {

        }}

  

}

}
