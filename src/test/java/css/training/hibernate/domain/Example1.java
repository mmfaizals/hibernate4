package css.training.hibernate.domain;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Assert;
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

	@Test
	public void testRead() {
		SessionFactory factory = sessionFactory();
		Session ses = factory.openSession();
		try {
			ses.beginTransaction();
			List<Person> persons = ses.createQuery(
					"from Person where firstName='Nobita'").list();
			for (Person it : persons) {
				Assert.assertEquals(it.getFirstName(), "Nobita");
			}
			ses.getTransaction().commit();
			ses.close();
		} finally {
			factory.close();
		}
	}

	@Test
	public void testAddEmail() {
		SessionFactory factory = sessionFactory();
		Session ses = factory.openSession();
		ses.beginTransaction();
		List<Person> persons = ses.createQuery(
				"from Person where firstName='Nobita'").list();
		Person person = persons.get(0);
		Email email = new Email();
		email.setAddress(person.getFirstName() + "@csscorp.com");
		email.setPerson(person);
		person.getEmails().add(email);
		ses.persist(email);
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
