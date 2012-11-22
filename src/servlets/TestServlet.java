package servlets;

//import java.io.IOException;
//import javax.servlet.http.*;
//
//public class TestServlet extends HttpServlet {
//    public void doGet(HttpServletRequest req, HttpServletResponse resp)
//            throws IOException {
//        resp.setContentType("text/plain");
//        resp.getWriter().println("Hello, world");
//    }
//}

import org.apache.velocity.Template;
import org.apache.velocity.servlet.VelocityServlet;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;
import javax.servlet.http.*;

public class TestServlet extends VelocityServlet {

	   public Template handleRequest( HttpServletRequest request,
               HttpServletResponse response,
               Context context ) {

				Template template = null;
				
				try {
				context.put("name", "Velocity Test");
				template = Velocity.getTemplate("hello.vm");
				
				
				} catch( Exception e ) {
				System.err.println("Exception caught: " + e.getMessage());
				}
				
				return template;
	    }
	   
}