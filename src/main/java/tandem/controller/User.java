package tandem.controller;

import java.util.Date;
import java.util.TreeMap;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tandem.model.Users;
import tandem.security.LoginController;
import tandem.utils.HibernateUtil;

@Controller
@RequestMapping(value = "/tandem/user")
public class User {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@RequestMapping (value={"/teste"},  method = { RequestMethod.POST, RequestMethod.GET } )
	public @ResponseBody Date getData() {
		Date d = new Date();
		System.out.println("Estou a usar o controller user.java");
		return d;
	}

	@RequestMapping (value={"/{key}"},  method = { RequestMethod.POST, RequestMethod.GET }, produces="application/json")
	public @ResponseBody TreeMap<String, Object> getUserInstitutions(@PathVariable(value="key") String key) {
		TreeMap<String, Object> res = new TreeMap<String, Object>();
		Users user = null;
		try {		
			user = new Users();
			res.put("isError", false);
			res.put("error", "");
			res.put("result", user); 
		} catch (Exception e) {
			logger.error(e.toString());
			res.put("isError", true);
			res.put("error", e.toString());
			res.put("result", null); 
		}
		return res;
	}
}
