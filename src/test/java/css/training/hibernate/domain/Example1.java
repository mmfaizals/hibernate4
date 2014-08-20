package css.training.hibernate.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class Example1 {

	@Test
	public void testInsert() {
		SessionFactory factory = sessionFactory();
		Session ses = factory.openSession();
		ses.beginTransaction();
		Person person = new Person();
		person.setFirstName("Nobita");
		person.setLastName("Nobi");
		ses.persist(person);
		ses.getTransaction().commit();
		ses.close();
		factory.close();
	}

	private static SessionFactory sessionFactory() {
		// return new
		// Configuration().configure().setNamingStrategy(ImprovedNamingStrategy.INSTANCE).buildSessionFactory();
		return new Configuration().configure().buildSessionFactory();
	}
}
