package reqres;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import db.DbLogic;

/**
 * Servlet implementation class Recover
 */
@WebServlet("/Recover")
public class Recover extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Recover() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String userMail = request.getParameter("mail");
        String from = "no-reply@mail.com";

        String host = "localhost";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userMail));

            String pass = DbLogic.getUserPass(userMail);

            if (pass != "") {
                message.setSubject("Sneeze Password");
                message.setText(pass);
                Transport.send(message);

                String title = "Send Email";
                String res = "E-Mail Sent...";
                String docType =
                    "<!doctype html public \"-//w3c//dtd html 4.0 " +
                    "transitional//en\">\n";
                out.println(docType +
                        "<html>\n" +
                        "<head><title>" + title + "</title></head>\n" +
                        "<body bgcolor=\"#f0f0f0\">\n" +
                        "<h1 align=\"center\">" + title + "</h1>\n" +
                        "<p align=\"center\">" + res + "</p>\n" +
                        "</body></html>");
            } else {
                String title = "Send Email";
                String res = "No account associated with this E-mail";
                String docType =
                    "<!doctype html public \"-//w3c//dtd html 4.0 " +
                    "transitional//en\">\n";
                out.println(docType +
                        "<html>\n" +
                        "<head><title>" + title + "</title></head>\n" +
                        "<body bgcolor=\"#f0f0f0\">\n" +
                        "<h1 align=\"center\">" + title + "</h1>\n" +
                        "<p align=\"center\">" + res + "</p>\n" +
                        "</body></html>");
            }
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
