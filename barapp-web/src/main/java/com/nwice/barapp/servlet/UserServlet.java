package com.nwice.barapp.servlet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nwice.barapp.manager.UserManager;
import com.nwice.barapp.model.BarappUser;

@Controller
public class UserServlet {
	
	protected static Logger log = Logger.getLogger(UserServlet.class);

	@Autowired
	private UserManager userManager;
	
	@RequestMapping(value="/admin/user_do", method = RequestMethod.POST)	
	public String userDo(@RequestParam("userId") Integer userId, 
						@RequestParam("username") String username,
						@RequestParam("firstname") String firstname,
						@RequestParam("lastname") String lastname,
						@RequestParam("password") String password,
						@RequestParam("role") String role,
						@RequestParam("active") Boolean active,
						Model model) {
		log.info("called userDo");
    	try {
			BarappUser uo = null;

			if ( userId != null ) {
				log.debug("Editing User");
				uo = userManager.getUserById( userId );
			} else {
				log.debug("Adding User");
				uo = new BarappUser();
			}
			
			uo.setUsername( username );
			uo.setFirstname( firstname );
			uo.setLastname( lastname );
			uo.setPassword( password );
			uo.setRole( role );
			uo.setActive( new Boolean(true) );			
			userManager.saveOrUpdateUser(uo);
    	} catch (Exception e) {
    		log.error(e);
        }
    	return "index.jsp";
    }
	
	
	
}
