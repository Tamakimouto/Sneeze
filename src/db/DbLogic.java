package db;

import java.sql.Connection;
import java.sql.ResultSet;

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

    public static ResultSet getSneezeSet() {
        Connection con = database.connect();
        String sql = "SELECT user, msg FROM messages ORDER BY RAND() LIMIT 12";
        ResultSet sneezes = database.retrieve(con, sql);
        return sneezes;
    }
    
    
    public static boolean validateCredentials(String user, String pass){
    	Connection con = database.connect();
    	String sql = "SELECT * from users WHERE username=\"" + user + "\" AND password=\"" + pass + "\"";
    	ResultSet results = database.retrieve(con, sql);
    	try{
    		if(!results.next()){
    			return false;//no record found
    		}else{
    			return true;//record found
    		}
    	}catch (Exception e){
    		return false;//error -> no record found
    	}
    }
    
    public static boolean createUser(String user, String pass, String email){
    	Connection con = database.connect();
        String sql = "INSERT INTO users (username, password, email) VALUES(\"" + user + "\",\"" + pass + "\",\"" + email + "\");";
        int result = database.update(con, sql);
        
        if(result == 0){//when result = 0, nothing was updated therefore createUser failed
        	return false;
        }else{
        	return true;
        }
    	
    }
    
    
    /*
     * returns true when a user exists in the database
     */
    public static boolean userExists(String username){
    	Connection con = database.connect();
    	String sql  = "Select count(*) from users where users.username=" + username;
    	ResultSet users = database.retrieve(con, sql);
    	int result = 0;
    	try{
    		users.next();//get to our first entry
    		result = users.getInt(1);//gets the result of the count(*) query
    	}catch( Exception e ){
    		e.printStackTrace();
    	}
    	
    	System.out.println("RESULT: " + result);
    	//if there are no matching user names return false, else return true
    	return result == 0 ? false : true;
    }
    
}
