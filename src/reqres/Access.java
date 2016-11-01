package reqres;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DbLogic;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;

/**
 * Servlet implementation class Access
 */
@WebServlet("/Access")
public class Access extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    static Configuration cfg = null;
    private String templateDir = "/WEB-INF/templates";
    
    public void init() {
        cfg = new Configuration(Configuration.VERSION_2_3_25);
        cfg.setServletContextForTemplateLoading(getServletContext(), templateDir);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
    }
    
    /**
     * runTemplate
     *
     * This function runs the freemarker template depending on
     * the given parameters.
     */
    public static void runTemplate(String template, Map<String, Object> tplModel, HttpServletResponse response) {
        Template tpl;
        try {
            tpl = cfg.getTemplate(template);
            response.setContentType("text/html");
            Writer out = response.getWriter();
            tpl.process(tplModel, out);
        } catch (TemplateNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedTemplateNameException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Access() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //response.getWriter();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String type = request.getParameter("form");		//sign-in or sign-up
    	
    	String user = request.getParameter("user");
    	String password = request.getParameter("pass");
    	String email = "";
    	
    	if(type.equals("sign-in")){
    		
    		if(DbLogic.validateCredentials(user, password)){
    			response.getWriter().append("Validated login: " + user);
    			//TODO: loadsneezes()
    		}else{
    			response.getWriter().append("Login failed Validation: " + user);
    			//TODO: handle error validating credentials
    		}
    		
    	}else if(type.equals("sign-up")){
    		
    		email = request.getParameter("mail");
    		
    		if(DbLogic.createUser(user, password, email)){
    			//TODO: loadsneezes();	
    			response.getWriter().append("Added New User: " + user);
    		}else{
    			response.getWriter().append("Failed to add user: " + user);
    			//TODO: handle error creating new user
    		}
    			
    	}
    	
    }

}