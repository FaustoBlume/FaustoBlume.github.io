package Data;
	import Entidades.*;
	import java.sql.*;
	import java.util.LinkedList;

	public class DataProducto {	
			
			public LinkedList<Producto> getAll(){
			
				Statement stmt=null;
				ResultSet rs=null;
				LinkedList<Producto> productos= new LinkedList<>();
				
				try {
					stmt= DbConnector.getInstancia().getConn().createStatement();
					rs= stmt.executeQuery("select p.id,p.descripcion,p.precio,p.stock,m.id,p.id_tipo_producto,m.descripcion,p.foto, tp.descripcion"
							+ " from producto p inner join marca m on m.id = p.id_marca"
							+ " inner join tipo_producto tp on tp.id = p.id_tipo_producto"
							+ " order by p.id");
					if(rs!=null) {
						while(rs.next()) {
							Producto prod=new Producto();
							prod.setMarca(new Marca());
							prod.setTp(new Tipo_Producto());
							prod.setId(rs.getInt("p.id"));
							prod.setPrecio(rs.getDouble("p.precio"));
							prod.setDescripcion(rs.getString("p.descripcion"));
							prod.setStock(rs.getInt("p.stock"));
							prod.getMarca().setId(rs.getInt("m.id")); 
							prod.setFoto(rs.getString("p.foto"));
							prod.getMarca().setDescripcion(rs.getString("m.descripcion"));
							prod.getTp().setId(rs.getInt("p.id_tipo_producto"));
							prod.getTp().setDescripcion(rs.getString("tp.descripcion"));
							productos.add(prod);
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
				
				
				return productos;
			}
			
			public Producto getByProducto(int id) {
				
				Producto p=null;
				PreparedStatement stmt=null;
				ResultSet rs=null;
				try {
						stmt=DbConnector.getInstancia().getConn().prepareStatement(
								"select p.id,p.descripcion,p.precio,p.stock,m.id,p.id_tipo_producto,m.descripcion,p.foto, tp.descripcion"
										+ " from producto p inner join marca m on m.id = p.id_marca"
										+ " inner join tipo_producto tp on tp.id = p.id_tipo_producto where p.id=?"
						);
						stmt.setInt(1, id);
						rs=stmt.executeQuery();
						if(rs!=null && rs.next()) {
							p=new Producto();
							p.setMarca(new Marca());
							p.setTp(new Tipo_Producto());
							p.setId(rs.getInt("p.id"));
							p.setPrecio(rs.getDouble("p.precio"));
							p.setDescripcion(rs.getString("p.descripcion"));
							p.setStock(rs.getInt("p.stock"));
							p.getMarca().setId(rs.getInt("m.id")); 
							p.setFoto(rs.getString("p.foto"));
							p.getMarca().setDescripcion(rs.getString("m.descripcion"));
							p.getTp().setId(rs.getInt("p.id_tipo_producto"));
							p.getTp().setDescripcion(rs.getString("tp.descripcion"));
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
			
			return p;
		}
			
			public String add(Producto p) {
				String alerta=null;
				PreparedStatement stmt= null;
				ResultSet keyResultSet=null;
				try {
					stmt=DbConnector.getInstancia().getConn().
							prepareStatement(
									"insert into producto(descripcion, id_tipo_producto, stock, precio, id_marca, foto) values(?,?,?,?,?,?)",
									PreparedStatement.RETURN_GENERATED_KEYS
									);
					stmt.setString(1, p.getDescripcion());
					stmt.setInt(2,  p.getTp().getId());
					stmt.setInt(3,  p.getStock());
					stmt.setDouble(4, p.getPrecio());
					stmt.setInt(5,  p.getMarca().getId());
					stmt.setString(6,p.getFoto());
					stmt.executeUpdate();
					keyResultSet=stmt.getGeneratedKeys();
		            if(keyResultSet!=null && keyResultSet.next()){
		                p.setId(keyResultSet.getInt(1));
		            }
				}  catch (SQLException e) {
					alerta= "add";
		            e.printStackTrace();
				} finally {
		            try {
		                if(keyResultSet!=null)keyResultSet.close();
		                if(stmt!=null)stmt.close();
		                DbConnector.getInstancia().releaseConn();
		            } catch (SQLException e) {
		            	e.printStackTrace();}
		            
				}return alerta;}
			
			public String update(Producto p) {
					String alerta=null;
					PreparedStatement stmt= null;
					try {
						stmt=DbConnector.getInstancia().getConn().
								prepareStatement(
										"update producto set descripcion=?, id_tipo_producto=?, stock=?, precio=?, foto=?, id_marca=? where id=?"
										);
						stmt.setString(1, p.getDescripcion());
						stmt.setInt(2, p.getTp().getId());
						stmt.setInt(3, p.getStock());
						stmt.setDouble(4, p.getPrecio());
						stmt.setString(5, p.getFoto());
						stmt.setInt(6, p.getMarca().getId());
						stmt.setInt(7, p.getId());
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
				
			public void delete(Producto p) {
				
				PreparedStatement stmt= null;
				try {
					stmt=DbConnector.getInstancia().getConn().
							prepareStatement(
									"delete from producto where id=?"
									);
					stmt.setInt(1, p.getId());
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
	
			public LinkedList<Producto> search(String desc) {
				Producto p = null;
				PreparedStatement stmt=null;
				ResultSet rs=null;
				LinkedList<Producto> productos= new LinkedList<>();
				try {
						stmt=DbConnector.getInstancia().getConn().prepareStatement(
								"select p.id,p.descripcion,p.precio,p.stock,p.foto,m.id,p.id_tipo_producto,m.descripcion, tp.descripcion"  
								+ " from producto p "  
								+ " inner join marca m on m.id = p.id_marca"  
								+ " inner join tipo_producto tp on tp.id = p.id_tipo_producto"  
								+ " where (p.stock>0) and (p.descripcion like ?)"  
								+ " order by p.id"
						);
						stmt.setString(1, desc);
						rs=stmt.executeQuery();
						if(rs!=null) {
							while(rs.next()) {
							p=new Producto();
							p.setMarca(new Marca());
							p.setTp(new Tipo_Producto());
							p.setId(rs.getInt("p.id"));
							p.setPrecio(rs.getDouble("p.precio"));
							p.setDescripcion(rs.getString("p.descripcion"));
							p.setStock(rs.getInt("p.stock"));
							p.getMarca().setId(rs.getInt("m.id")); 
							p.setFoto(rs.getString("p.foto"));
							p.getMarca().setDescripcion(rs.getString("m.descripcion"));
							p.getTp().setId(rs.getInt("p.id_tipo_producto"));
							p.getTp().setDescripcion(rs.getString("tp.descripcion"));
							productos.add(p);
							}
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
			
			return productos;
		}
			
			public LinkedList<Producto> searchAlmacen() {
				Statement stmt=null;
				ResultSet rs=null;
				LinkedList<Producto> productos= new LinkedList<>();
				
				try {
					stmt= DbConnector.getInstancia().getConn().createStatement();
					rs= stmt.executeQuery("select p.id,p.descripcion,p.precio,p.foto,p.stock,m.id,p.id_tipo_producto,m.descripcion, tp.descripcion" 
							+ " from producto p "  
							+ " inner join marca m on m.id = p.id_marca"  
							+ " inner join tipo_producto tp on tp.id = p.id_tipo_producto"  
							+ " where (p.stock>0) and (tp.descripcion IN('ADEREZOS','GOLOSINAS', 'ENDULZANTES', 'CONDIMENTOS', 'SALSAS', 'CEREALES','LIMPIEZA DE BAÑO', 'LIMPIEZA DE COCINA'))"  
							+ " order by p.id");
					if(rs!=null) {
						while(rs.next()) {
							Producto prod=new Producto();
							prod.setMarca(new Marca());
							prod.setTp(new Tipo_Producto());
							prod.setId(rs.getInt("p.id"));
							prod.setPrecio(rs.getDouble("p.precio"));
							prod.setDescripcion(rs.getString("p.descripcion"));
							prod.setStock(rs.getInt("p.stock"));
							prod.getMarca().setId(rs.getInt("m.id")); 
							prod.setFoto(rs.getString("p.foto"));
							prod.getMarca().setDescripcion(rs.getString("m.descripcion"));
							prod.getTp().setId(rs.getInt("p.id_tipo_producto"));
							prod.getTp().setDescripcion(rs.getString("tp.descripcion"));
							productos.add(prod);
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
				
				
				return productos;
			}
			
			public LinkedList<Producto> searchBebidas() {
				Statement stmt=null;
				ResultSet rs=null;
				LinkedList<Producto> productos= new LinkedList<>();
				
				try {
					stmt= DbConnector.getInstancia().getConn().createStatement();
					rs= stmt.executeQuery("select p.id,p.descripcion,p.precio,p.foto,p.stock,m.id,p.id_tipo_producto,m.descripcion, tp.descripcion" + 
							" from producto p " + 
							" inner join marca m on m.id = p.id_marca" + 
							" inner join tipo_producto tp on tp.id = p.id_tipo_producto" + 
							" where (p.stock>0) and (tp.descripcion IN('GASEOSAS','JUGOS','VINOS'))" + 
							" order by p.id;");
					if(rs!=null) {
						while(rs.next()) {
							Producto prod=new Producto();
							prod.setMarca(new Marca());
							prod.setTp(new Tipo_Producto());
							prod.setId(rs.getInt("p.id"));
							prod.setPrecio(rs.getDouble("p.precio"));
							prod.setDescripcion(rs.getString("p.descripcion"));
							prod.setStock(rs.getInt("p.stock"));
							prod.getMarca().setId(rs.getInt("m.id")); 
							prod.setFoto(rs.getString("p.foto"));
							prod.getMarca().setDescripcion(rs.getString("m.descripcion"));
							prod.getTp().setId(rs.getInt("p.id_tipo_producto"));
							prod.getTp().setDescripcion(rs.getString("tp.descripcion"));
							productos.add(prod);
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
				
				
				return productos;
			}
			
			public LinkedList<Producto> searchElectro() {
				Statement stmt=null;
				ResultSet rs=null;
				LinkedList<Producto> productos= new LinkedList<>();
				
				try {
					stmt= DbConnector.getInstancia().getConn().createStatement();
					rs= stmt.executeQuery("select p.id,p.descripcion,p.precio,p.stock,p.foto,m.id,p.id_tipo_producto,m.descripcion, tp.descripcion" + 
							" from producto p " + 
							" inner join marca m on m.id = p.id_marca" + 
							" inner join tipo_producto tp on tp.id = p.id_tipo_producto" + 
							" where (p.stock>0) and (tp.descripcion IN('INFORMATICA','TELEFONIA','AUDIO'))" + 
							" order by p.id");
					if(rs!=null) {
						while(rs.next()) {
							Producto prod=new Producto();
							prod.setMarca(new Marca());
							prod.setTp(new Tipo_Producto());
							prod.setId(rs.getInt("p.id"));
							prod.setPrecio(rs.getDouble("p.precio"));
							prod.setDescripcion(rs.getString("p.descripcion"));
							prod.setStock(rs.getInt("p.stock"));
							prod.getMarca().setId(rs.getInt("m.id")); 
							prod.setFoto(rs.getString("p.foto"));
							prod.getMarca().setDescripcion(rs.getString("m.descripcion"));
							prod.getTp().setId(rs.getInt("p.id_tipo_producto"));
							prod.getTp().setDescripcion(rs.getString("tp.descripcion"));
							productos.add(prod);
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
				
				
				return productos;
			}
	
			public LinkedList<Producto> searchLimpieza() {
				Statement stmt=null;
				ResultSet rs=null;
				LinkedList<Producto> productos= new LinkedList<>();
				
				try {
					stmt= DbConnector.getInstancia().getConn().createStatement();
					rs= stmt.executeQuery("select p.id,p.descripcion,p.precio,p.stock,p.foto,m.id,p.id_tipo_producto,m.descripcion, tp.descripcion" + 
							" from producto p " + 
							" inner join marca m on m.id = p.id_marca" + 
							" inner join tipo_producto tp on tp.id = p.id_tipo_producto" + 
							" where (p.stock>0) and (tp.descripcion IN('LIMPIEZA DE COCINA','LIMPIEZA DE BAÑO','PAPELES'))" + 
							" order by p.id");
					if(rs!=null) {
						while(rs.next()) {
							Producto prod=new Producto();
							prod.setMarca(new Marca());
							prod.setTp(new Tipo_Producto());
							prod.setId(rs.getInt("p.id"));
							prod.setPrecio(rs.getDouble("p.precio"));
							prod.setDescripcion(rs.getString("p.descripcion"));
							prod.setStock(rs.getInt("p.stock"));
							prod.getMarca().setId(rs.getInt("m.id")); 
							prod.setFoto(rs.getString("p.foto"));
							prod.getMarca().setDescripcion(rs.getString("m.descripcion"));
							prod.getTp().setId(rs.getInt("p.id_tipo_producto"));
							prod.getTp().setDescripcion(rs.getString("tp.descripcion"));
							productos.add(prod);
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
				
				
				return productos;
			}
			
			public LinkedList<Producto> searchFrescos() {
				Statement stmt=null;
				ResultSet rs=null;
				LinkedList<Producto> productos= new LinkedList<>();
				
				try {
					stmt= DbConnector.getInstancia().getConn().createStatement();
					rs= stmt.executeQuery("select p.id,p.descripcion,p.precio,p.stock,p.foto,m.id,p.id_tipo_producto,m.descripcion, tp.descripcion" + 
							" from producto p" + 
							" inner join marca m on m.id = p.id_marca" + 
							" inner join tipo_producto tp on tp.id = p.id_tipo_producto" + 
							" where (p.stock>0) and (tp.descripcion IN('CARNES','FIAMBRES','LACTEOS'))" + 
							" order by p.id");
					if(rs!=null) {
						while(rs.next()) {
							Producto prod=new Producto();
							prod.setMarca(new Marca());
							prod.setTp(new Tipo_Producto());
							prod.setId(rs.getInt("p.id"));
							prod.setPrecio(rs.getDouble("p.precio"));
							prod.setDescripcion(rs.getString("p.descripcion"));
							prod.setStock(rs.getInt("p.stock"));
							prod.getMarca().setId(rs.getInt("m.id")); 
							prod.setFoto(rs.getString("p.foto"));
							prod.getMarca().setDescripcion(rs.getString("m.descripcion"));
							prod.getTp().setId(rs.getInt("p.id_tipo_producto"));
							prod.getTp().setDescripcion(rs.getString("tp.descripcion"));
							productos.add(prod);
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
				
				
				return productos;
			}
	
			public LinkedList<Producto> searchCategoria(String categoria) {
				Producto p = null;
				PreparedStatement stmt=null;
				ResultSet rs=null;
				LinkedList<Producto> productos= new LinkedList<>();
				try {
						stmt=DbConnector.getInstancia().getConn().prepareStatement(
								"select p.id,p.descripcion,p.precio,p.stock,p.foto,m.id,p.id_tipo_producto,m.descripcion, tp.descripcion" + 
								" from producto p " + 
								" inner join marca m on m.id = p.id_marca" + 
								" inner join tipo_producto tp on tp.id = p.id_tipo_producto" + 
								" where (p.stock>0) and (tp.descripcion=?)" + 
								" order by p.id"
						);
						stmt.setString(1, categoria);
						rs=stmt.executeQuery();
						if(rs!=null) {
							while(rs.next()) {
							p=new Producto();
							p.setMarca(new Marca());
							p.setTp(new Tipo_Producto());
							p.setId(rs.getInt("p.id"));
							p.setPrecio(rs.getDouble("p.precio"));
							p.setDescripcion(rs.getString("p.descripcion"));
							p.setStock(rs.getInt("p.stock"));
							p.getMarca().setId(rs.getInt("m.id")); 
							p.setFoto(rs.getString("p.foto"));
							p.getMarca().setDescripcion(rs.getString("m.descripcion"));
							p.getTp().setId(rs.getInt("p.id_tipo_producto"));
							p.getTp().setDescripcion(rs.getString("tp.descripcion"));
							productos.add(p);
							}
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
			
			return productos;
		}

			public LinkedList<Producto> searchMarca(String marca) {
				Producto p = null;
				PreparedStatement stmt=null;
				ResultSet rs=null;
				LinkedList<Producto> productos= new LinkedList<>();
				try {
						stmt=DbConnector.getInstancia().getConn().prepareStatement(
								"select p.id,p.descripcion,p.precio,p.stock,p.foto,m.id,p.id_tipo_producto,m.descripcion, tp.descripcion" + 
								" from producto p" + 
								" inner join marca m on m.id = p.id_marca" + 
								" inner join tipo_producto tp on tp.id = p.id_tipo_producto" + 
								" where (p.stock>0) and (m.descripcion=?)" + 
								" order by p.id"
						);
						stmt.setString(1, marca);
						rs=stmt.executeQuery();
						if(rs!=null) {
							while(rs.next()) {
							p=new Producto();
							p.setMarca(new Marca());
							p.setTp(new Tipo_Producto());
							p.setId(rs.getInt("p.id"));
							p.setPrecio(rs.getDouble("p.precio"));
							p.setDescripcion(rs.getString("p.descripcion"));
							p.setStock(rs.getInt("p.stock"));
							p.getMarca().setId(rs.getInt("m.id")); 
							p.setFoto(rs.getString("p.foto"));
							p.getMarca().setDescripcion(rs.getString("m.descripcion"));
							p.getTp().setId(rs.getInt("p.id_tipo_producto"));
							p.getTp().setDescripcion(rs.getString("tp.descripcion"));
							productos.add(p);
							}
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
			
			return productos;
		}
	
			public LinkedList<Producto> orderByAsc(){		
				Statement stmt=null;
				ResultSet rs=null;
				LinkedList<Producto> productos= new LinkedList<>();
				
				try {
					stmt= DbConnector.getInstancia().getConn().createStatement();
					rs= stmt.executeQuery("select p.id,p.descripcion,p.precio,p.stock,m.id,p.id_tipo_producto,m.descripcion,p.foto, tp.descripcion"
							+ " from producto p inner join marca m on m.id = p.id_marca"
							+ " inner join tipo_producto tp on tp.id = p.id_tipo_producto"
							+ " order by p.precio");
					if(rs!=null) {
						while(rs.next()) {
							Producto prod=new Producto();
							prod.setMarca(new Marca());
							prod.setTp(new Tipo_Producto());
							prod.setId(rs.getInt("p.id"));
							prod.setPrecio(rs.getDouble("p.precio"));
							prod.setDescripcion(rs.getString("p.descripcion"));
							prod.setStock(rs.getInt("p.stock"));
							prod.getMarca().setId(rs.getInt("m.id")); 
							prod.setFoto(rs.getString("p.foto"));
							prod.getMarca().setDescripcion(rs.getString("m.descripcion"));
							prod.getTp().setId(rs.getInt("p.id_tipo_producto"));
							prod.getTp().setDescripcion(rs.getString("tp.descripcion"));
							productos.add(prod);
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
				
				
				return productos;
			}
	
			public LinkedList<Producto> orderByDesc(){	
				Statement stmt=null;
				ResultSet rs=null;
				LinkedList<Producto> productos= new LinkedList<>();
				
				try {
					stmt= DbConnector.getInstancia().getConn().createStatement();
					rs= stmt.executeQuery("select p.id,p.descripcion,p.precio,p.stock,m.id,p.id_tipo_producto,m.descripcion,p.foto, tp.descripcion"
							+ " from producto p inner join marca m on m.id = p.id_marca"
							+ " inner join tipo_producto tp on tp.id = p.id_tipo_producto"
							+ " order by p.precio desc");
					if(rs!=null) {
						while(rs.next()) {
							Producto prod=new Producto();
							prod.setMarca(new Marca());
							prod.setTp(new Tipo_Producto());
							prod.setId(rs.getInt("p.id"));
							prod.setPrecio(rs.getDouble("p.precio"));
							prod.setDescripcion(rs.getString("p.descripcion"));
							prod.setStock(rs.getInt("p.stock"));
							prod.getMarca().setId(rs.getInt("m.id")); 
							prod.setFoto(rs.getString("p.foto"));
							prod.getMarca().setDescripcion(rs.getString("m.descripcion"));
							prod.getTp().setId(rs.getInt("p.id_tipo_producto"));
							prod.getTp().setDescripcion(rs.getString("tp.descripcion"));
							productos.add(prod);
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
				
				
				return productos;
			}
	}