package com.nwice.barapp.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.nwice.barapp.manager.UserManager;

/**
 * @web.servlet
 *      name="InitServlet"
 *      load-on-startup="2"
 **/

public class InitServlet extends HttpServlet {
	
	protected static Logger log = Logger.getLogger(InitServlet.class);
	
	public void init() throws ServletException {
		
		try {
			
			UserManager userManager = new UserManager();
			log.debug("Size: "  + userManager.getAllUsers().length );
					 
			if ( userManager.getAllUsers() == null || userManager.getAllUsers().length == 0 ) {
				userManager.createUser("The", "Owner", "the", "owner", "admin", new Boolean("true"));
			}					
			
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	
	
}
