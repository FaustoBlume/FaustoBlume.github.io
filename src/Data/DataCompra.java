package Data;
import Entidades.*;

import java.sql.*;
import java.util.LinkedList;

public class DataCompra {

	
		
		public LinkedList<Compra> getAll(){
		
			Statement stmt=null;
			ResultSet rs=null;
			LinkedList<Compra> compras= new LinkedList<>();
			
			try {
				stmt= DbConnector.getInstancia().getConn().createStatement();
				rs= stmt.executeQuery("select compra.id,total,fecha_de_compra,u.id, u.nombre, u.apellido, u.e_mail, u.nombreUsuario, u.password, u.rol, u.domicilio, u.telefono, u.edad from compra"
						+ "inner join usuario u on u.id=compra.id_usuario"
					);
				if(rs!=null) {
					while(rs.next()) {
						Compra c=new Compra();
						c.setId(rs.getInt("compra.id"));
						c.setTotal(rs.getDouble("total"));
						c.setFecha(rs.getString("fecha"));
						c.getUsu().setId(rs.getInt("u.id"));
						c.getUsu().setNombre(rs.getString("u.nombre"));
						c.getUsu().setApellido(rs.getString("u.apellido"));
						c.getUsu().setE_mail(rs.getString("u.e_mail"));
						c.getUsu().setNombreUsuario(rs.getString("u.nombreUsuario"));
						c.getUsu().setPass(rs.getString("u.password"));
						c.getUsu().setRol(rs.getString("u.rol"));
						c.getUsu().setDomicilio(rs.getString("u.domicilio"));
						c.getUsu().setTelefono(rs.getString("u.telefono"));
						c.getUsu().setEdad(rs.getString("u.edad"));
						compras.add(c);
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			} finally {
				try {
					if(rs!=null) {rs.close();}
					if(stmt!=null) {stmt.close();}
					DbConnector.getInstancia().releaseConn();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			
			return compras;
		}
		
		public void add(Compra c) {
	        PreparedStatement stmt= null;
	            ResultSet keyResultSet=null;
	        try {
	        stmt=DbConnector.getInstancia().getConn().
	                    prepareStatement(
	                            "insert into compra(total, fecha_de_compra, id_usuario) values(?,?,?)",
	                            PreparedStatement.RETURN_GENERATED_KEYS
	                            );
	            stmt.setDouble(1, c.getTotal());
	            stmt.setString(2, c.getFecha());
	            stmt.setInt(3, c.getUsu().getId());
	            stmt.executeUpdate();
	                keyResultSet=stmt.getGeneratedKeys();
	                if(keyResultSet!=null && keyResultSet.next()){
	                c.setId(keyResultSet.getInt(1));
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
		
		public Compra getByCompra(int id , String fecha) {
			
			Compra c=null;
			PreparedStatement stmt=null;
			ResultSet rs=null;
			try {
					stmt=DbConnector.getInstancia().getConn().prepareStatement(
							"select c.id,c.total,c.fecha_de_compra,c.id_usuario,u.apellido,u.e_mail,u.nombre,u.nombreUsuario,u.password,u.telefono,u.edad,u.domicilio" + 
							" from compra c inner join usuario u on u.id = c.id_usuario" + 
							" where c.id_usuario=? and c.fecha_de_compra=?"
					);
					stmt.setInt(1, id);
					stmt.setString(2, fecha);
					rs=stmt.executeQuery();
					if(rs!=null && rs.next()) {
						c=new Compra();
						c.setId(rs.getInt("c.id"));
						c.setUsu(new Cliente());
						c.getUsu().setId(rs.getInt("c.id_usuario"));
						c.getUsu().setApellido(rs.getString("u.apellido"));
						c.getUsu().setE_mail(rs.getString("u.e_mail"));
						c.getUsu().setNombre(rs.getString("u.nombre"));
						c.getUsu().setNombreUsuario(rs.getString("u.nombreUsuario"));
						c.getUsu().setPass(rs.getString("u.password"));
						c.getUsu().setRol("cliente");
						c.getUsu().setTelefono(rs.getString("u.telefono"));
						c.getUsu().setEdad(rs.getString("u.edad"));
						c.getUsu().setDomicilio(rs.getString("u.domicilio"));
						c.setFecha(rs.getString("c.fecha_de_compra"));
						c.setTotal(rs.getDouble("c.total"));
						
						
						
					}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return c;
		
		
}	
}