
	package Data;
	import Entidades.*;

	import java.sql.*;
	import java.util.LinkedList;

	public class DataCliente {

		
			
			public LinkedList<Cliente> getAll(){
			
				Statement stmt=null;
				ResultSet rs=null;
				LinkedList<Cliente> clientes= new LinkedList<>();
				
				try {
					stmt= DbConnector.getInstancia().getConn().createStatement();
					rs= stmt.executeQuery("select id,nombre,apellido,nombreUsuario,password,e_mail,rol,telefono,edad,domicilio "
							+ "from usuario where rol = 'cliente'");
					if(rs!=null) {
						while(rs.next()) {
							Cliente c=new Cliente();
							c.setId(rs.getInt("id"));
							c.setNombre(rs.getString("nombre"));
							c.setApellido(rs.getString("apellido"));
							c.setE_mail(rs.getString("e_mail"));
							c.setNombreUsuario(rs.getString("nombreUsuario"));
							c.setPass(rs.getString("password"));
							c.setRol(rs.getString("rol"));
							c.setDomicilio(rs.getString("domicilio"));
							c.setTelefono(rs.getString("telefono"));
							c.setEdad(rs.getString("edad"));
							clientes.add(c);
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
				
				
				return clientes;
			}
			
			public Cliente getByUser(int id) {
				Cliente c=null;
				PreparedStatement stmt=null;
				ResultSet rs=null;
				try {
					stmt=DbConnector.getInstancia().getConn().prepareStatement(
							"select id,nombreUsuario,password,nombre,apellido,e_mail,rol,domicilio,edad,telefono from usuario where id=?"
							);
					stmt.setInt(1, id);
					rs=stmt.executeQuery();
					if(rs!=null && rs.next()) {
						c=new Cliente();
						c.setId(rs.getInt("id"));
						c.setPass(rs.getString("password"));
						c.setApellido(rs.getString("apellido"));
						c.setNombre(rs.getString("nombre"));
						c.setRol(rs.getString("rol"));
						c.setNombreUsuario(rs.getString("nombreUsuario"));
						c.setE_mail(rs.getString("e_mail"));
						c.setDomicilio(rs.getString("domicilio"));
						c.setTelefono(rs.getString("telefono"));
						c.setEdad(rs.getString("edad"));
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

			public void add(Cliente c) {
				PreparedStatement stmt= null;
				ResultSet keyResultSet=null;
				try {
					stmt=DbConnector.getInstancia().getConn().
							prepareStatement(
									"insert into usuario(nombre, apellido, password, edad, e_mail, domicilio, telefono, nombreUsuario,rol) values(?,?,?,?,?,?,?,?,?)",
									PreparedStatement.RETURN_GENERATED_KEYS
									);
					stmt.setString(1, c.getNombre());
					stmt.setString(2, c.getApellido());
					stmt.setString(3, c.getPass());
					stmt.setString(4, c.getEdad());
					stmt.setString(5, c.getE_mail());
					stmt.setString(6, c.getDomicilio());
					stmt.setString(7, c.getTelefono());
					stmt.setString(8, c.getNombreUsuario());
					stmt.setString(9, c.getRol());
					
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
		            	e.printStackTrace();}
		            
				}}
			
			public void update(Cliente c) {
				
				PreparedStatement stmt= null;
				try {
					stmt=DbConnector.getInstancia().getConn().
							prepareStatement(
									"update usuario set nombreUsuario=?,password=?,e_mail=?,nombre=?,apellido=?,rol=?,domicilio=?,edad=?,telefono=? where id=?"
									);
					stmt.setString(1, c.getNombreUsuario());
					stmt.setString(2, c.getPass() );
					stmt.setString(3, c.getE_mail() );
					stmt.setString(4, c.getNombre());
					stmt.setString(5, c.getApellido());
					stmt.setString(6, c.getRol());
					stmt.setString(7, c.getDomicilio());
					stmt.setString(8, c.getEdad());
					stmt.setString(9, c.getTelefono());
					stmt.setInt(10, c.getId());
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
	
			public void delete(Cliente c) {
				
				PreparedStatement stmt= null;
				try {
					stmt=DbConnector.getInstancia().getConn().
							prepareStatement(
									"delete from usuario where id=?"
									);
					stmt.setInt(1, c.getId());
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


