
package backend.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
Class: DatabaseUtil
Type: Connection
Use: Make the connection with the databse
        
*/
public class DatabaseUtil {
    
     private static Connection con;

    public DatabaseUtil() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "password");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection getConnection() {
        return con;
    }
}
