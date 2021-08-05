package Data;
import Entidades.*;

import java.sql.*;
import java.util.LinkedList;

public class DataAdministrador {

	
		
		public LinkedList<Administrador> getAll(){
		
			Statement stmt=null;
			ResultSet rs=null;
			LinkedList<Administrador> admins= new LinkedList<>();
			
			try {
				stmt= DbConnector.getInstancia().getConn().createStatement();
				rs= stmt.executeQuery("select id,nombre,apellido,nombreUsuario,password,e_mail,rol,sueldo "
						+ "from usuario where rol = 'administrador'");
				if(rs!=null) {
					while(rs.next()) {
						Administrador a=new Administrador();
						a.setId(rs.getInt("id"));
						a.setNombre(rs.getString("nombre"));
						a.setApellido(rs.getString("apellido"));
						a.setE_mail(rs.getString("e_mail"));
						a.setNombreUsuario(rs.getString("nombreUsuario"));
						a.setPass(rs.getString("password"));
						a.setRol(rs.getString("rol"));
						a.setSueldo(rs.getDouble("sueldo"));
						admins.add(a);
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
			
			
			return admins;
		}
		
		public Administrador getByUser(int id) {
			Administrador a=null;
			PreparedStatement stmt=null;
			ResultSet rs=null;
			try {
				stmt=DbConnector.getInstancia().getConn().prepareStatement(
						"select id,nombreUsuario,password,nombre,apellido,e_mail,rol,sueldo from usuario where id=?"
						);
				stmt.setInt(1, id);
				rs=stmt.executeQuery();
				if(rs!=null && rs.next()) {
					a=new Administrador();
					a.setId(rs.getInt("id"));
					a.setPass(rs.getString("password"));
					a.setApellido(rs.getString("apellido"));
					a.setNombre(rs.getString("nombre"));
					a.setRol(rs.getString("rol"));
					a.setNombreUsuario(rs.getString("nombreUsuario"));
					a.setE_mail(rs.getString("e_mail"));
					a.setSueldo(rs.getDouble("sueldo"));
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
			
			return a;
		}
	
		public void add(Administrador admin) {
			PreparedStatement stmt= null;
			ResultSet keyResultSet=null;
			try {
				stmt=DbConnector.getInstancia().getConn().
						prepareStatement(
								"insert into usuario(nombreUsuario,password,e_mail,nombre,apellido,rol,sueldo) values(?,?,?,?,?,?,?)",
								PreparedStatement.RETURN_GENERATED_KEYS
								);
				stmt.setString(1, admin.getNombreUsuario());
				stmt.setString(2, admin.getPass() );
				stmt.setString(3, admin.getE_mail() );
				stmt.setString(4, admin.getNombre());
				stmt.setString(5, admin.getApellido());
				stmt.setString(6, admin.getRol());
				stmt.setDouble(7, admin.getSueldo());
				stmt.executeUpdate();
				keyResultSet=stmt.getGeneratedKeys();
	            if(keyResultSet!=null && keyResultSet.next()){
	                admin.setId(keyResultSet.getInt(1));
	            }

				
			}  catch (SQLException e) {
	            e.printStackTrace();
			} finally {
	            try {
	                if(keyResultSet!=null)keyResultSet.close();
	                if(stmt!=null)stmt.close();
	                DbConnector.getInstancia().releaseConn();
	            } catch (SQLException e) {
	            	e.printStackTrace();}
	            
			}}
		
		public void update(Administrador a) {
			
			PreparedStatement stmt= null;
			try {
				stmt=DbConnector.getInstancia().getConn().
						prepareStatement(
								"update usuario set nombreUsuario=?,password=?,e_mail=?,nombre=?,apellido=?,rol=?,sueldo=? where id=?"
								);
				stmt.setString(1, a.getNombreUsuario());
				stmt.setString(2, a.getPass() );
				stmt.setString(3, a.getE_mail() );
				stmt.setString(4, a.getNombre());
				stmt.setString(5, a.getApellido());
				stmt.setString(6, a.getRol());
				stmt.setDouble(7, a.getSueldo());
				stmt.setInt(8, a.getId());
				stmt.executeUpdate();
			}  catch (SQLException e) {
	            e.printStackTrace();
			} finally {
	            try {
	                if(stmt!=null)stmt.close();
	                DbConnector.getInstancia().releaseConn();
	            } catch (SQLException e) {
	            	e.printStackTrace();}
	            
			}}				

		public void delete(Administrador a) {
			
			PreparedStatement stmt= null;
			try {
				stmt=DbConnector.getInstancia().getConn().
						prepareStatement(
								"delete from usuario where id=?"
								);
				stmt.setInt(1, a.getId());
				stmt.executeUpdate();
			}  catch (SQLException e) {
	            e.printStackTrace();
			} finally {
	            try {
	                if(stmt!=null)stmt.close();
	                DbConnector.getInstancia().releaseConn();
	            } catch (SQLException e) {
	            	e.printStackTrace();}
	            
			}}	
		
}
