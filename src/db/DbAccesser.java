package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbAccesser extends DbSettings {

    public DbAccesser(){}

    public Connection connect() {
        Connection con = null;
        try {
            Class.forName(DB_DRIVE_NAME);
            con = DriverManager.getConnection(DB_CONNECTION_URL, DB_CONNECTION_USERNAME, DB_CONNECTION_PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void disconnect(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* For getting things */
    public ResultSet retrieve(Connection con, String query) {
        ResultSet rset = null;
        try {
            Statement stmt = con.createStatement();
            rset = stmt.executeQuery(query);
            return rset;
        } catch (SQLException e) {
            e.printStackTrace();
            return rset;
        }
    }

    /* For Creating, Updating, and Deleting things */
    public int update(Connection con, String query) {
        int numUpdates = 0;
        try {
            Statement st = con.createStatement();
            numUpdates = st.executeUpdate(query);
            return numUpdates;
        } catch (SQLException e) {
            e.printStackTrace();
            return numUpdates;
        }
    }
}
