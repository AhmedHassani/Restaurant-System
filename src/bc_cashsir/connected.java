
package bc_cashsir;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class connected {
      private Connection conn = null;
      
      protected Connection con() throws SQLException{
      String url = "jdbc:sqlite:data//info";
      conn = DriverManager.getConnection(url);
      return conn;
      }

}
