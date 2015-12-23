package tandem.security;

import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tandem.dao.UsersDAOImpl;
import tandem.model.OauthProvider;
import tandem.model.Users;
import tandem.utils.HibernateUtil;

@Controller
@RequestMapping(value = "/security/login")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	//@Autowired
	//private UsersDAO usersDao = new UsersDAOImpl();
	UsersDAOImpl usersDao = new UsersDAOImpl(sessionFactory);  

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

					if (!credentials.get("username").toString().isEmpty() && !credentials.get("password").toString().isEmpty()) {
						user = new Users();
						user.setUsername(credentials.get("username").toString());
						user.setPassword(credentials.get("password").toString());
						res.put("result", user);
						res.put("isError", false);
						res.put("error", "");
						System.out.println(res);
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

	@RequestMapping(value = {"/fbauth"}, method = RequestMethod.POST, produces="application/json")	
	public @ResponseBody TreeMap<String, Object> fbAuth(@RequestBody TreeMap<String, Object> postData) {
		TreeMap<String, Object> res = new TreeMap<String, Object>();
		Users user = new Users();
		try {		
			if(postData != null){
				logger.info("inside facebook login - postData: " + postData);
				/* fbData = {
                        fb_accessToken: postData.accessToken,
                        fb_userID: postData.userID,
                        fb_email: profileInfo.email,
                        fb_first_name: profileInfo.first_name,
                        fb_last_name: profileInfo.last_name,
                        fb_gender: profileInfo.gender,
                        fb_id: profileInfo.id,
                        fb_picture: profileInfo.picture.data.url
                 };*/
				if(postData.get("fb_userID")!=null){
					String userID = postData.get("fb_userID").toString();
					String accessToken = ""; //postData.get("accessToken").toString();

					int userFoundId = checkLogin(userID,accessToken,"FACEBOOK");
					System.out.println("User Not Found (userFoundId:"+userFoundId +")");
					if (userFoundId >= 0) {
						if (userFoundId > 0) {
							user.setUserId(userFoundId);

						}else{
							// criar utiizador
							user = createUser(postData,"FACEBOOK");
							if (user == null) {
								logger.error("get successful, no instance found");
								res.put("result", null);
								res.put("isError", true);
								res.put("error", "");
							} else {
								logger.debug("get successful, instance found");
								res.put("result", user);
								res.put("isError", false);
								res.put("error", "");
							}
						}
					}else{
						res.put("result", null);
						res.put("isError", true);
						res.put("error", "Erro BD");
						System.out.println("/security/login/fbauth : erro find user id");
					}
				}else{
					res.put("result", null);
					res.put("isError", true);
					res.put("error", "Faltam Parametros");
					System.out.println("/security/login/fbauth : um parametro null");
				}
			}else{
				res.put("result", null);
				res.put("isError", true);
				res.put("error", "Faltam Parametros");
				System.out.println("/security/login/fbauth : parametros null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
			res.put("isError", true);
			res.put("error", e.toString());
			res.put("result", null); 
		}
		return res;
	}

	public int checkLogin(String u, String pw, String Oauth){
		Session session=null;
		int userFound = 0;
		try{ 
			System.out.println("In checkLogin");
			//getting session object from session factory
			session = sessionFactory.openSession();
			//getting transaction object from session object  
			session.beginTransaction();  
			String SQL_QUERY = "select u.user_id from " + Users.class.getName()  + " as u";
			Query query = null;

			if(Oauth.equals("FACEBOOK") || Oauth.equals("GOOGLE") ){
				SQL_QUERY = SQL_QUERY + 
						", " + OauthProvider.class.getName() + " as ap " +
						"where ap.oauth_provider = " + Oauth +
						" and ap.user_fk = u.user_id " +
						"and ap.oauth_uid = " + u ;
				query = session.createQuery(SQL_QUERY);
				//query.setParameter("oap",Oauth);
				//query.setParameter("oai",u);
			}
			else{
				SQL_QUERY = SQL_QUERY + " where u.username='?' and u.password='?' ";
				query = session.createQuery(SQL_QUERY);
				query.setParameter(0,u);
				query.setParameter(1,pw);
			}
			List list = query.list();
			if ((list != null) && (list.size() == 1)) {
				userFound= (int) list.get(0);
			}
			//User u = (User) query.getSingleResult();
		}
		catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			userFound = -2;
		}
		finally {
			if(session !=null && session.isOpen()){
				session.close();
				//session=null;
			}
		}
		return userFound;
	}

	public Users createUser(TreeMap<String, Object> userData, String Oauth){
		Session session=null;
		Users newUser = null;
		int userID = 0;
		try{ 
			System.out.println("In createUser");

			session = sessionFactory.openSession();
			String SQL_QUERY = "";
			/*
			 FUNCTION `user_store_fb` (
				username_ char(45),
				password_ char(45),
				first_name_ char(55), 
				last_name_ char(55),
				email_ char(55),
			    photo_ mediumblob,
			    gender_ char(10),
			    fb_userID_ int(25)
			 )
			 */
			if(Oauth.equals("FACEBOOK")){
				SQL_QUERY = "select user_store_fb(?,?,?,?,?,?,?,?)";
			}
			Query query = null;
			/* fbData = {
		        fb_accessToken: postData.accessToken,
		        fb_userID: postData.userID,
		        fb_email: profileInfo.email,
		        fb_first_name: profileInfo.first_name,
		        fb_last_name: profileInfo.last_name,
		        fb_gender: profileInfo.gender,
		        fb_id: profileInfo.id,
		        fb_picture: profileInfo.picture.data.url
		 	};*/
			String uname = "";
			if(userData.get("fb_first_name")!=null)
				uname += userData.get("fb_first_name").toString() + " ";
			if(userData.get("fb_last_name")!=null)
				uname += userData.get("fb_last_name").toString();
			if(uname.isEmpty())
				uname += userData.get("id").toString();
			query = session.createSQLQuery(SQL_QUERY);
			query.setParameter(0,uname);							//username_
			query.setParameter(1,userData.get("fb_id").toString()+"pw");			//password_
			query.setParameter(2,userData.get("fb_first_name").toString());	//first_name_
			query.setParameter(3,userData.get("fb_last_name").toString());		//last_name_
			query.setParameter(4,userData.get("fb_email").toString());			//email_
			query.setParameter(5,userData.get("fb_picture").toString());		//photo_
			query.setParameter(6,userData.get("fb_gender").toString());		//gender_
			query.setParameter(7,userData.get("fb_userID").toString());		//fb_userID_
			userID = (Integer) query.uniqueResult();
			/*List list = query.list();
			if ((list != null) && (list.size() == 1)) {
				userID = (int) list.get(0);
				newUser = new Users();
				newUser.setUserId(userID);
			}*/
			System.out.println("Novo user criado: "+userID);
			//newUser = usersDAO.findById(userID);
			//newUser = (Users) session.get("Users", userID);
			//newUser = (Users) session.get(Users.class, userID);
			query = session.createQuery(" from " + Users.class.getName()  + " as o where o.user_id="+userID);
			//query.setParameter(0,userID);
			//newUser = (Users) query.uniqueResult();
			List<Users> list = usersDao.findById(userID+"");
			if ((list != null) && (list.size() == 1)) {
				newUser = list.get(0);
			}
		}
		catch(Exception e){
			logger.error(e.toString());
			e.printStackTrace();
		}
		finally {
			if(session !=null && session.isOpen()){
				session.close();
				//session=null;
			}
		}
		return newUser;  
	}
}