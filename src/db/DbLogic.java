package db;

public class DbLogic {

    DbAccesser database = new DbAccesser();
    /*
     * Create SQL statements here and use the DbAccesser to process them.
     * 
     * Connection con = database.connect();
     * ResultSet user = database.retrieve(con, ...);
     * 
     * And so on.
     * 
     * I think it'd be easier to use if the functions in here were all static.
     */

    public static ResultSet getSneezeSet() {
        Connection con = database.connect();
        String sql = "SELECT user, msg FROM messages ORDER BY RAND() LIMIT 12";
        ResultSet sneezes = database.retrieve(con, sql);
    }
}
