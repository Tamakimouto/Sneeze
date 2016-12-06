package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * The class that accesses and makes the executions.
 *
 * @author      Anthony Zheng   <Anthony@fopen-dream.space>
 * @author      Robin Guice
 * @author      Zack Phan
 * @package     db
 */
public class DbAccesser extends DbSettings {

    /**
     * DbAccessor
     *
     * Constructor Function
     */
    public DbAccesser(){}

    /**
     * connect
     *
     * Creates and returns a database connection.
     *
     * @access public
     * @return  Connection  con     SQL Connection.
     */
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

    /**
     * disconnect
     *
     * Closes the SQL connection.
     *
     * @access  public
     * @param   Connection  con     The SQL connection to be closed.
     */
    public void disconnect(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * retrieve
     *
     * For getting things.
     *
     * @access  public
     * @param   Connection  con     A valid sql connection.
     * @param   String      query   The SQL Statement to be executed.
     * @return  ResultSet   rset    A map of matched results.
     */
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

    /**
     * update
     *
     * For creating, updating, and deleting things.
     *
     * @access  public
     * @param   Connection  con         A valid sql connection.
     * @param   String      query       The SQL Statement to be executed.
     * @return  int         numUpdates  The total number of updates made.
     */
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
