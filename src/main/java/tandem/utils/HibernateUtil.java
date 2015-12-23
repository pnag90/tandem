package tandem.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;

	static {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			sessionFactory = new Configuration().configure("config/hibernate.cfg.xml").buildSessionFactory();

			/*Configuration cfg = new Configuration();
	        cfg.configure("config/hibernate.cfg.xml");
	        cfg.addClass(OauthProvider.class).addResource("config/OauthProvider.hbm.xml");
	        cfg.addClass(Authorities.class).addResource("config/Authorities.hbm.xml");
	        cfg.addClass(Users.class).addResource("config/Users.hbm.xml");
	        cfg.addClass(Profile.class).addResource("config/Profile.hbm.xml");
	        StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder().applySettings( cfg.getProperties() );
	        sessionFactory = cfg.buildSessionFactory( registryBuilder.build() );*/
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		if(session!=null && !session.isOpen()){
			session = sessionFactory.openSession();
		}
		return session;
	}

}

