package com.nwice.barapp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.nwice.barapp.manager.UserManager;
import com.nwice.barapp.model.BarappUser;

/**
 * @web.servlet
 *      name="UserServlet"
 * @web.servlet-mapping
 *      url-pattern="/admin/user.do"
 **/

public class UserServlet extends HttpServlet {
	
	protected static Logger log = Logger.getLogger(UserServlet.class);

	private UserManager userManager;

	public void init() throws ServletException {
		
		try {
			
			userManager = new UserManager();
			
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	public void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
    	try {
			BarappUser uo = null;

			if ( request.getParameter("userId") != null ) {
				log.debug("Editing User");
				uo = userManager.getUserById( new Integer(request.getParameter("userId")));
			} else {
				log.debug("Adding User");
				uo = new BarappUser();
			}
			
			uo.setUsername( request.getParameter("username") );
			uo.setFirstname( request.getParameter("firstname") );
			uo.setLastname( request.getParameter("lastname") );
			uo.setPassword( request.getParameter("password") );
			uo.setRole( request.getParameter("role") );
			if ( request.getParameter("active") != null ) {
				uo.setActive( new Boolean(true) );
			} else {
				uo.setActive( new Boolean(false) );
			}
			userManager.saveOrUpdateUser(uo);
    	} catch (Exception e) {
    		log.error(e);
        }
    	ServletContext sc = getServletContext(); 
    	RequestDispatcher rd = sc.getRequestDispatcher("/admin/index.jsp");
    	rd.include(request, response); 
    }
	
	
	
}
