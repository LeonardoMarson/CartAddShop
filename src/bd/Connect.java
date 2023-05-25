package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	
	private static String conect1 = "jdbc:mysql://localhost:3306/test";
	private static String conect2 = "root";
	private static String conect3 = "";
	
	
	private static Connection instance = null;
	
	private Connect() {
		try {
			 instance = DriverManager.getConnection(conect1, conect2, conect3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static Connection getInstance() {
		try {
			if(instance == null || instance.isClosed()) {
				 new Connect();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return instance;
	}
	
	
	public static void closeCon() {
		try {
			instance.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
