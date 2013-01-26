package com.nwice.barapp.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.nwice.barapp.manager.UserManager;

public class InitServlet extends HttpServlet {
	
	protected static Logger log = Logger.getLogger(InitServlet.class);
	
	@Autowired
	private UserManager userManager;	
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	    
		log.info("init(ServletConfig config)" );
		
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());		
		
		try {			
			
			log.info("user size: "  + userManager.getAllUsers().length );
			
			if ( userManager.getAllUsers() == null || userManager.getAllUsers().length == 0 ) {
				
				log.info("Creating admin user");
				
				userManager.createUser("da", "owner", "admin", "pass", "ROLE_ADMIN", new Boolean("true"));
				userManager.createUser("bar", "tender", "bar", "pass", "ROLE_BARTENDER", new Boolean("true"));
				userManager.createUser("bar", "back", "barback", "pass", "ROLE_BARBACK", new Boolean("true"));
				
				log.info("new user size: "  + userManager.getAllUsers().length );
			}					
			
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	
	
}
