package Data;

import java.sql.*;

public class DbConnector {

	private static DbConnector instancia;
	
	private String driver="com.mysql.cj.jdbc.Driver";
	private String host="localhost";
	private String port="3306";
	private String user="admin_java";
	private String password="tpjava";
	private String db="mercado_java";
	private int conectados=0;
	private Connection conn=null;
	
	private DbConnector() {
		try {
			Class.forName(driver);  // en el video lo acompaña el metodo "new.Instance()"
		} catch (ClassNotFoundException e) {  // hay mas exepciones en el video para atrapar
			e.printStackTrace();
		}
	}
	
	public static DbConnector getInstancia() {
		if (instancia == null) {
			instancia = new DbConnector();
		}
		return instancia;
	}
	
	public Connection getConn() {
		try {
			if(conn==null || conn.isClosed()) {
				conn=DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db, user, password);
				conectados=0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conectados++;
		return conn;
	}
	
	public void releaseConn() {
		conectados--;
		try {
			if (conectados<=0) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}