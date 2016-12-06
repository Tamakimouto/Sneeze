package reqres;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;

import object.Sneeze;
import db.DbLogic;


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
    public static void runTemplate(String template, SimpleHash tplModel, HttpServletResponse response) {
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

    public static void loadSneezes(HttpServletResponse response) {
        DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
        SimpleHash root = new SimpleHash(df.build());

        ArrayList<Sneeze> sneezes = DbLogic.getSneezes();
        root.put("sneezes", sneezes);
        runTemplate("home.ftl", root, response);
    }


    /**
     * @see HttpServlet#HttpServlet()
     */
    public Access() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("form"); //sign-in or sign-up
        String user = request.getParameter("user");
        String password = request.getParameter("pass");

        if (type.equals("sign-in")) {
            if (DbLogic.validateCredentials(user, password)) {
                //response.getWriter().append("Validated login: " + user);
                Cookie userCookie = new Cookie("sneezeUser", user);
                userCookie.setMaxAge(60*60*24*365);
                response.addCookie(userCookie);
                loadSneezes(response);
            } else {
                response.getWriter().append("Login failed Validation: " + user);
                // handle error validating credentials
            }
        } else if (type.equals("sign-up")) {
            String email = request.getParameter("mail");
            if (DbLogic.createUser(user, password, email)) {
                loadSneezes(response);
            } else {
                response.getWriter().append("Failed to add user: " + user);
                // handle error creating new user
            }
        }
    }
}

