package css.training.hibernate.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.junit.AfterClass;
import org.junit.Test;

public class Example03 {
	private static final SessionFactory factory = sessionFactory();

	@AfterClass
	public static void destroy() {
		if (factory != null) {
			factory.close();
		}
	}

	/**
	 * 
	 * Many to One Association with foreign key owned by Person
	 * 
	 */
	@Test
	public void testManyToOne() {
		Session ses = factory.openSession();
		ses.beginTransaction();

		Person person = new Person();
		person.setFirstName("nobitha");
		person.setLastName("nobi");
		Country country = (Country) ses.createCriteria(Country.class)
				.add(Restrictions.eq("name", "Japan")).uniqueResult();
		person.setCountry(country);
		ses.persist(person);

		ses.getTransaction().commit();
		ses.close();
	}

	private static SessionFactory sessionFactory() {
		// return new
		// Configuration().configure().setNamingStrategy(ImprovedNamingStrategy.INSTANCE).buildSessionFactory();
		return new Configuration().configure().buildSessionFactory();
	}
}
