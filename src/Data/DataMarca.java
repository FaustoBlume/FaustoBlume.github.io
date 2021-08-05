package Data;
import Entidades.*;

import java.sql.*;
import java.util.LinkedList;

public class DataMarca {
		
		public LinkedList<Marca> getAll(){

			Statement stmt=null;
			ResultSet rs=null;
			LinkedList<Marca> marcas= new LinkedList<>();
			
			try {
				stmt= DbConnector.getInstancia().getConn().createStatement();
				rs= stmt.executeQuery("select id,descripcion from marca"
					);
				if(rs!=null) {
					while(rs.next()) {
						Marca m=new Marca();
						m.setId(rs.getInt("id"));
						m.setDescripcion(rs.getString("descripcion"));
						
						marcas.add(m);
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
			
			
			return marcas;
		}
		
		public Marca getByMarca(int id) {
			Marca m=null;
			PreparedStatement stmt=null;
			ResultSet rs=null;
			try {
				stmt=DbConnector.getInstancia().getConn().prepareStatement(
						"select id,descripcion from marca where id=?"
						);
				stmt.setInt(1, id);
				rs=stmt.executeQuery();
				if(rs!=null && rs.next()) {
					m=new Marca();
					m.setId(rs.getInt("id"));
					m.setDescripcion(rs.getString("descripcion"));
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
			
			return m;
		}

		public Marca getByDescripcion(String desc) {
			Marca m=null;
			PreparedStatement stmt=null;
			ResultSet rs=null;
			try {
				stmt=DbConnector.getInstancia().getConn().prepareStatement(
						"select id,descripcion from marca where descripcion=?"
						);
				stmt.setString(1, desc);
				rs=stmt.executeQuery();
				if(rs!=null && rs.next()) {
					m=new Marca();
					m.setId(rs.getInt("id"));
					m.setDescripcion(rs.getString("descripcion"));
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
			
			return m;
		}
			
		public String add(Marca m) {
			String alerta=null;
			PreparedStatement stmt= null;
			ResultSet keyResultSet=null;
			try {
				stmt=DbConnector.getInstancia().getConn().
						prepareStatement(
								"insert into marca(descripcion) values(?)",
								PreparedStatement.RETURN_GENERATED_KEYS
								);
				stmt.setString(1, m.getDescripcion());
				stmt.executeUpdate();
				keyResultSet=stmt.getGeneratedKeys();
	            if(keyResultSet!=null && keyResultSet.next()){
	                m.setId(keyResultSet.getInt(1));
	            }

				
			}  catch (SQLException e) {
				alerta = "add";
	            e.printStackTrace();
			} finally {
	            try {
	                if(keyResultSet!=null)keyResultSet.close();
	                if(stmt!=null)stmt.close();
	                DbConnector.getInstancia().releaseConn();
	            } catch (SQLException e) {
	            	e.printStackTrace();}
	            
			}return alerta;}
		
		public Marca search(Marca mar) {
			Marca m=null;
			PreparedStatement stmt=null;
			ResultSet rs=null;
			try {
				stmt=DbConnector.getInstancia().getConn().prepareStatement(
						"select id,descripcion from marca where descripcion =?"
						);
				stmt.setString(1, mar.getDescripcion());
				rs=stmt.executeQuery();
				if(rs!=null && rs.next()) {
					m=new Marca();
					m.setDescripcion(rs.getString("descripcion"));
					m.setId(rs.getInt("id"));
					
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
			
			return m;
		}

		public String update(Marca m) {
				String alerta=null;
				PreparedStatement stmt= null;
				try {
					stmt=DbConnector.getInstancia().getConn().
							prepareStatement(
									"update marca set descripcion=? where id=?"
									);
					stmt.setString(1, m.getDescripcion());
					stmt.setInt(2, m.getId());
					stmt.executeUpdate();
				}  catch (SQLException e) {
					alerta = "update";
		            e.printStackTrace();
				} finally {
		            try {
		                if(stmt!=null)stmt.close();
		                DbConnector.getInstancia().releaseConn();
		            } catch (SQLException e) {
		            	e.printStackTrace();}
		            
				}return alerta;}
			
		public String delete(Marca m) {		
			PreparedStatement stmt= null;
			String alerta=null;
			try {
				stmt=DbConnector.getInstancia().getConn().
						prepareStatement(
								"delete from marca where id=?"
								);
				stmt.setInt(1, m.getId());
				stmt.executeUpdate();
			}  catch (SQLException e) {
				alerta = "delete";
	            e.printStackTrace();
			} finally {
	            try {
	                if(stmt!=null)stmt.close();
	                DbConnector.getInstancia().releaseConn();
	            } catch (SQLException e) {
	            	e.printStackTrace();} 
			}
			return alerta;}
			
		}