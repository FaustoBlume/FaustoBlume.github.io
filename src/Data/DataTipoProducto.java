package Data;
import Entidades.*;

import java.sql.*;
import java.util.LinkedList;

public class DataTipoProducto {
		
		public LinkedList<Tipo_Producto> getAll(){
		
			Statement stmt=null;
			ResultSet rs=null;
			LinkedList<Tipo_Producto> tipos= new LinkedList<>();
			
			try {
				stmt= DbConnector.getInstancia().getConn().createStatement();
				rs= stmt.executeQuery("select id,descripcion from tipo_producto"
					);
				if(rs!=null) {
					while(rs.next()) {
						Tipo_Producto tp=new Tipo_Producto();
						tp.setId(rs.getInt("id"));
						tp.setDescripcion(rs.getString("descripcion"));
						
						tipos.add(tp);
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
			
			
			return tipos;
		}
		
		public Tipo_Producto getByTipoProducto(int id) {
			Tipo_Producto tp=null;
			PreparedStatement stmt=null;
			ResultSet rs=null;
			try {
				stmt=DbConnector.getInstancia().getConn().prepareStatement(
						"select id,descripcion from tipo_producto where id=?"
						);
				stmt.setInt(1, id);
				rs=stmt.executeQuery();
				if(rs!=null && rs.next()) {
					tp=new Tipo_Producto();
					tp.setId(rs.getInt("id"));
					tp.setDescripcion(rs.getString("descripcion"));
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
			
			return tp;
		}
		
		public String add(Tipo_Producto tp) {
			String alerta=null;
			PreparedStatement stmt= null;
			ResultSet keyResultSet=null;
			try {
				stmt=DbConnector.getInstancia().getConn().
						prepareStatement(
								"insert into tipo_producto(descripcion) values(?)",
								PreparedStatement.RETURN_GENERATED_KEYS
								);
				stmt.setString(1, tp.getDescripcion());
				stmt.executeUpdate();
				keyResultSet=stmt.getGeneratedKeys();
	            if(keyResultSet!=null && keyResultSet.next()){
	                tp.setId(keyResultSet.getInt(1));
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
		
		public Tipo_Producto search(Tipo_Producto tipo_prod) {
			Tipo_Producto tp=null;
			PreparedStatement stmt=null;
			ResultSet rs=null;
			try {
				stmt=DbConnector.getInstancia().getConn().prepareStatement(
						"select id,descripcion from tipo_producto where descripcion =?"
						);
				stmt.setString(1, tipo_prod.getDescripcion());
				rs=stmt.executeQuery();
				if(rs!=null && rs.next()) {
					tp=new Tipo_Producto();
					tp.setDescripcion(rs.getString("descripcion"));
					tp.setId(rs.getInt("id"));
					
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
			
			return tp;
		}
	
		public Tipo_Producto getByDescripcion(String desc) {
			Tipo_Producto tp=null;
			PreparedStatement stmt=null;
			ResultSet rs=null;
			try {
				stmt=DbConnector.getInstancia().getConn().prepareStatement(
						"select id,descripcion from tipo_producto where descripcion=?"
						);
				stmt.setString(1, desc);
				rs=stmt.executeQuery();
				if(rs!=null && rs.next()) {
					tp=new Tipo_Producto();
					tp.setId(rs.getInt("id"));
					tp.setDescripcion(rs.getString("descripcion"));
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
			
			return tp;
		}
			
		public String update(Tipo_Producto tp) {
				String alerta=null;
				PreparedStatement stmt= null;
				try {
					stmt=DbConnector.getInstancia().getConn().
							prepareStatement(
									"update tipo_producto set descripcion=? where id=?"
									);
					stmt.setString(1, tp.getDescripcion());
					stmt.setInt(2, tp.getId());
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
			
		public String delete(Tipo_Producto tp) {
			String alerta=null;
			PreparedStatement stmt= null;
			try {
				stmt=DbConnector.getInstancia().getConn().
						prepareStatement(
								"delete from tipo_producto where id=?"
								);
				stmt.setInt(1, tp.getId());
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
			}return alerta;}
		
		}