package tandem.security;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tandem.model.Users;
import tandem.utils.HibernateUtil;

import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/security/login")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@RequestMapping (value={"/teste"},  method = { RequestMethod.POST, RequestMethod.GET } )
	public @ResponseBody Date getData() {
		Date d = new Date();
		System.out.println("Estou a usar o cenas...");
		return d;
	}

	@RequestMapping(value = {"/auth"}, method = RequestMethod.POST)
	public @ResponseBody TreeMap<String, Object> auth(@RequestBody TreeMap<String, Object> credentials) {
		TreeMap<String, Object> res = new TreeMap<String, Object>();
		try {		
			if(credentials != null){
				logger.info("inside login - user: " + credentials);

				if (credentials.get("username")!=null && credentials.get("password")!=null) {
					Users user;
					Session session = HibernateUtil.getSessionFactory().getCurrentSession();

					if (!credentials.get("username").toString().isEmpty() && !credentials.get("password").toString().isEmpty()) {
						user = new Users();
						user.setUsername(credentials.get("username").toString());
						user.setPassword(credentials.get("password").toString());
						res.put("result", user);
						res.put("isError", false);
						res.put("error", "");
					}else{
						res.put("result", null);
						res.put("isError", true);
						res.put("error", "Erro java...");
						System.out.println("/security/login/auth : faltam parametros");
					}
				}else{
					res.put("result", null);
					res.put("isError", true);
					res.put("error", "Faltam Parametros");
					System.out.println("/security/login/auth : parametros null");
				}
				//response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}
		} catch (Exception e) {
			logger.error(e.toString());
			res.put("isError", true);
			res.put("error", e.toString());
			res.put("result", null); 
		}
		return res;
	}

	public boolean checkLogin(String userName, String userPassword){
		System.out.println("In Check login");
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		boolean userFound = false;

		//Query using Hibernate Query Language
		String SQL_QUERY =" from tandem.Users as o where o.username=? and o.password=?";
		Query query = session.createQuery(SQL_QUERY);
		query.setParameter(0,userName);
		query.setParameter(1,userPassword);
		List list = query.list();

		if ((list != null) && (list.size() > 0)) {
			userFound= true;
		}

		session.close();
		return userFound;              
	}
}