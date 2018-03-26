package edu.uga.cs.ratemyrecipe.presentation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uga.cs.ratemyrecipe.logic.LogicLayer;
import edu.uga.cs.ratemyrecipe.session.Session;
import edu.uga.cs.ratemyrecipe.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@WebServlet("/Upload")
public class Upload 
	extends HttpServlet {

		private static final long serialVersionUID = 1L;
		
		static String templateDir = "WEB-INF/templates";
		static String resultTemplateName = "upload.ftl";
		
		private Configuration cfg;
		
		public void init() {
			// Prepare the FreeMarker configuration;
	        // - Load templates from the WEB-INF/templates directory of the Web app.
	        //
	        cfg = new Configuration();
	        cfg.setServletContextForTemplateLoading(
	                getServletContext(), 
	                "WEB-INF/templates"
	                );
		}
		
		public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
			Template resultTemplate = null;
			BufferedWriter toClient = null;
			String         recipeName = null;
			String          recipeDescription = null;
			String 	          category = null;
			String 	          step1 = null;
			String 	          step2 = null;
			String 	          step3 = null;
			String 	          step4 = null;
			String 	          step5 = null;
			LogicLayer   logicLayer = null;
			HttpSession httpSession = null;
			String    		   name = null;
			Session         session = null;
			
			// Load templates from the WEB-INF/templates directory of the Web app.
	        //
	        try {
	            resultTemplate = cfg.getTemplate( resultTemplateName );
	        } 
	        catch (IOException e) {
	            throw new ServletException( "Upload.doPost: Can't load template in: " + templateDir + ": " + e.toString());
	        }
	        
	        // Prepare HTTP response
	        toClient = new BufferedWriter(
	        		new OutputStreamWriter(res.getOutputStream(), resultTemplate.getEncoding())
	        		);
	        
	        res.setContentType("text/html; charset=" + resultTemplate.getEncoding());
	        
	        if( session == null ) {
	            try {
	                session = SessionManager.createSession();
	            }
	            catch ( Exception e ) {
	            		RentError.error( cfg, toClient, e );
	                return;
	            }
	        }
	        
	        logicLayer = session.getLogicLayer();
	        
	        // Get the form parameters.
	        recipeName = req.getParameter("recipeName");
	        recipeDescription = req.getParameter("recipeDescription");
	        category = req.getParameter("category");
	        step1 = req.getParameter("step1");
	        step2 = req.getParameter("step2");
	        step3 = req.getParameter("step3");
	        step4 = req.getParameter("step4");
	        step5 = req.getParameter("step5");
	        // Validate the parameters
//	        if (username == null) {
//	        	RentError.error(cfg, toClient, "Unspecified Username");
//	        	return;
//	        }
//	        
//	        if (email == null) {
//	        	RentError.error(cfg, toClient, "Unspecified Email");
//	        	return;
//	        }
//	        
//	        if (address == null) {
//	        	RentError.error(cfg, toClient, "Unspecified Address");
//	        	return;
//	        }
	        
	        try {          
	            logicLayer.upload(recipeName, recipeDescription, category, step1, step2,step3,step4,step5);
	        } 
	        catch ( Exception e ) {
	            RentError.error( cfg, toClient, e + "hh");
	            return;
	        }
			
			// Set up the data model
			Map<String,Object> root = new HashMap<String,Object>();
	        		
			root.put( "recipeName", recipeName );
			
	        // Merge the data-model and the template
			try {
				resultTemplate.process(root, toClient);
				toClient.flush();
			} catch (TemplateException e) {
				throw new ServletException("Error while processing FreeMarker template", e);
			}	
			toClient.close();
		}
}
