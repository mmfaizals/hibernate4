package css.training.hibernate.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.AfterClass;
import org.junit.Test;

public class Example04 {
	private static final SessionFactory factory = sessionFactory();

	@AfterClass
	public static void destroy() {
		if (factory != null) {
			factory.close();
		}
	}

	/**
	 * 
	 * One to One Association mapping with Primary on both side
	 * 
	 */
	@Test
	public void testManyToOne() {
		Session ses = factory.openSession();
		ses.beginTransaction();

		Person person = new Person();
		person.setFirstName("nobitha");
		person.setLastName("nobi");
		Passport passport = new Passport();
		passport.setPassportNumber("ALDYX193");
		person.setPassport(passport);
		passport.setPerson(person);
		ses.persist(person);
		ses.persist(passport);
		ses.getTransaction().commit();
		ses.close();
	}

	private static SessionFactory sessionFactory() {
		ServiceRegistryBuilder builder = new ServiceRegistryBuilder();
		Configuration configuration = new Configuration().configure();
		configuration.setNamingStrategy(ImprovedNamingStrategy.INSTANCE);
		builder.applySettings(configuration.getProperties());

		SessionFactory factory = configuration.buildSessionFactory(builder
				.buildServiceRegistry());
		return factory;

	}
}