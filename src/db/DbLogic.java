package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import object.Sneeze;


public class DbLogic {

    static DbAccesser database = new DbAccesser();
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
    public static boolean createSneeze(int user, String msg) {
    	Connection con = database.connect();
    	String sql = "INSERT into messages(user_id,messages) VALUES (" + user + ",'" + msg +"');";
    	int result = database.update(con, sql);

        /* When result = 0, nothing is updated and createSneeze has failed */
        if (result == 0)
            return false;
        else
            return true;
    }
    
    public static ResultSet getSneezeSet() {
        Connection con = database.connect();
        String sql = "SELECT user_id, msg FROM messages ORDER BY RAND() LIMIT 12";
        ResultSet sneezes = database.retrieve(con, sql);
        return sneezes;
    }

    public static ArrayList<Sneeze> getSneezes() {
        ResultSet sneezeResults = getSneezeSet();
        ArrayList<Sneeze> sneezes = new ArrayList();

        try {
            while (sneezeResults.next())
                sneezes.add(new Sneeze(sneezeResults.getString("user"), sneezeResults.getString("msg")));
        } catch(Exception e) {
            e.printStackTrace();
        }

        return sneezes;
    }

    public static boolean validateCredentials(String user, String pass, HttpServletRequest request) {
        Connection con = database.connect();
        String sql = "SELECT * from users WHERE username=\"" + user + "\" AND password=\"" + pass + "\"";
        ResultSet results = database.retrieve(con, sql);

        try {
            if (!results.next())
                return false; //no record found
            else {
            	HttpSession session = request.getSession(true);
            	session.setAttribute("user_id", results.getInt(1));
                return true; //record found
            }
        } catch (Exception e) {
            return false; //error -> no record found
        }
    }

    public static boolean createUser(String user, String pass, String email) {
        Connection con = database.connect();
        String sql = "INSERT INTO users (username, password, email) VALUES(\"" + user + "\",\"" + pass + "\",\"" + email + "\");";
        int result = database.update(con, sql);

        /* When result = 0, nothing is updated and createUser has failed */
        if (result == 0)
            return false;
        else
            return true;
    }

    /*
     * returns true when a user exists in the database
     */
    public static boolean userExists(String username) {
        Connection con = database.connect();
        String sql  = "SELECT count(*) FROM users WHERE users.username=" + username;
        ResultSet users = database.retrieve(con, sql);
        int result = 0;

        try {
            users.next(); //get to our first entry
            result = users.getInt(1); //gets the result of the count(*) query
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("RESULT: " + result);
        //if there are no matching user names return false, else return true
        return (result == 0);
    }

    public static String getUserPass(String mail) {
        Connection con = database.connect();
        String sql = "SELECT password FROM users WHERE email=" + mail;
        ResultSet pass = database.retrieve(con, sql);
        String word = "";

        try {
            pass.next();
            word = pass.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return word;
    }

}
