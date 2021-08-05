package Data;
import Entidades.*;
import java.sql.*;

public class DataUsuario {
		
		public Usuario getByUser(Usuario usu) {
			Usuario u=null;
			PreparedStatement stmt=null;
			ResultSet rs=null;
			try {
				stmt=DbConnector.getInstancia().getConn().prepareStatement(
						"select id,nombreUsuario,password,nombre,apellido,e_mail,rol from usuario where e_mail=? and password=?"
						);
				stmt.setString(1, usu.getE_mail());
				stmt.setString(2, usu.getPass());
				rs=stmt.executeQuery();
				if(rs!=null && rs.next()) {
					u=new Usuario();
					u.setId(rs.getInt("id"));
					u.setNombreUsuario(rs.getString("nombreUsuario"));
					u.setPass(rs.getString("password"));
					u.setNombre(rs.getString("nombre"));
					u.setApellido(rs.getString("apellido"));
					u.setE_mail(rs.getString("e_mail"));
					u.setRol(rs.getString("rol"));
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
			
			return u;
}}
