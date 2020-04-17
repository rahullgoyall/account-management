package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static db.Provider.*;

public class ConnectionProvider {
	private static Connection con=null;
	

    public static Connection getDBConnection(){
    	try{if(con == null){
            Class.forName(DRIVER);
            con= DriverManager.getConnection(CONNECTION_URL,USERNAME,PASSWORD);}
        }catch(Exception e){
           
        }
        return con;
    }

	public static void closeConnection() {
		try {
			if(con != null)
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		con = null;
	}
}
