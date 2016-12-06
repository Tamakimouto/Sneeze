package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import object.Sneeze;

/**
 * The logic of all the database related functions.
 *
 * @author  Anthony Zheng   <Anthony@fopen-dream.space>
 * @author  Robin Guice
 * @author  Zack Phan
 * @package db
 */
public class DbLogic {

    static DbAccesser database = new DbAccesser();

    /**
     * createSneeze
     *
     * Creates a sneeze entry in the database given a user id and message.
     *
     * @access  public
     * @static
     * @param   int     userId      The unique id corresponding the a user.
     * @param   String  msg         User entered message.
     * @return  boolean t/f         Whether or not the function succeeded.
     */
    public static boolean createSneeze(int userId, String msg) {
        Connection con = database.connect();
        String sql = "INSERT into messages(user_id, msg) VALUES (" + userId + ", \"" + msg + "\");";
        int result = database.update(con, sql);

        /* When result = 0, nothing is updated and createSneeze has failed */
        if (result == 0)
            return false;
        else
            return true;
    }

    /**
     * createSneeze
     *
     * Creates a sneeze entry in the database given a message.
     *
     * Used for anonymous Sneezes as the userId is hard set to 1,
     * being the Anonymous user account pre-registered in the 
     * database.
     *
     * @access  public
     * @static
     * @param   String  msg         User entered message.
     * @return  boolean t/f         Whether or not the function succeeded.
     */
    public static boolean createSneeze(String msg) {
        Connection con = database.connect();
        String sql = "INSERT into messages(user_id, msg) VALUES (" + 1 + ", \"" + msg + "\");";
        int result = database.update(con, sql);

        /* When result = 0, nothing is updated and createSneeze has failed */
        if (result == 0)
            return false;
        else
            return true;
    }

    /**
     * getSneezeSet
     *
     * Returns a map of up to 12 Sneezes.
     *
     * @access  public
     * @static
     * @return  ResultSet   sneezes     The map of sneeze entries.
     */
    public static ResultSet getSneezeSet() {
        Connection con = database.connect();
        String sql = "SELECT user_id, msg FROM messages ORDER BY RAND() LIMIT 12";
        ResultSet sneezes = database.retrieve(con, sql);
        return sneezes;
    }

    /**
     * getSneezes
     *
     * Returns an arraylist of up to 12 Sneezes.
     *
     * Used for Freemarker template models.
     *
     * @access  public
     * @static
     * @return  ArrayList   sneezes     The map of sneeze entries.
     */
    public static ArrayList<Sneeze> getSneezes() {
        ResultSet sneezeResults = getSneezeSet();
        ArrayList<Sneeze> sneezes = new ArrayList();

        try {
            while (sneezeResults.next())
                sneezes.add(new Sneeze(getUserById(sneezeResults.getInt("user_id")), sneezeResults.getString("msg")));
        } catch(Exception e) {
            e.printStackTrace();
        }

        return sneezes;
    }

    /**
     * validateCredentials
     *
     * Determines if a given username matches a password in the database.
     *
     * Also sets up the user session and keeps track of the currently logged in
     * user, to be accessed by sneeze creation functions.
     *
     * @access  public
     * @static
     * @param   String              user        The entered username.
     * @param   String              pass        The entered password.
     * @param   HttpServletRequest  request     Servlet Request.
     * @return  boolean             t/f         Whether or not a user to pass record was found.
     */
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

    /**
     * createUser
     *
     * Handles the registration form and logs a new user in the database.
     *
     * @access  public
     * @static
     * @param   String  user    The user entered username.
     * @param   String  pass    The user entered password.
     * @param   String  email   The User's e-mail address.
     * @return  boolean t/f     Whether or not the function succeeded.
     */
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

    /**
     * getUserById
     *
     * Returns a user name associated with a unique identifier.
     *
     * @access  public
     * @static
     * @param   int     id      The unique identifier.
     * @return  String  user    The matched username.
     */
    public static String getUserById(int id) {
        Connection con = database.connect();
        String sql = "SELECT username FROM users WHERE id=" + id;
        ResultSet result = database.retrieve(con, sql);
        String user = null;
        try {
            result.next();
            user = result.getString(1);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * userExists
     *
     * Returns true when a user exists in the database.
     *
     * @access  public
     * @static
     * @param   String  username    The username to search.
     * @return  boolean t/f         Whether or not the user exists.
     */
    public static boolean userExists(String username) {
        Connection con = database.connect();
        String sql  = "SELECT count(*) FROM users WHERE users.username=\"" + username + "\";";
        ResultSet users = database.retrieve(con, sql);
        int result = 0;

        try {
            users.next(); //get to our first entry
            result = users.getInt(1); //gets the result of the count(*) query
        } catch (Exception e) {
            e.printStackTrace();
        }

        //if there are no matching user names return false, else return true
        return result == 0 ? false : true;
    }

    /**
     * getUserPass
     *
     * Returns the password for a given email.
     *
     * Used for password recovery.
     *
     * @access  public
     * @static
     * @param   String  mail    The User email.
     * @return  String  word    The user's password.
     */
    public static String getUserPass(String mail) {
        Connection con = database.connect();
        String sql = "SELECT password FROM users WHERE email=\"" + mail + "\";";
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
