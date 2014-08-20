package css.training.hibernate.domain;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

public class Example05 {
	private static final SessionFactory factory = sessionFactory();

	@AfterClass
	public static void destroy() {
		if (factory != null) {
			factory.close();
		}
	}

	@Test
	public void testSelect() {
		setUpData();
		Session ses = factory.openSession();
		ses.beginTransaction();
		// make sure you dont have duplicate names
		Person person = (Person) ses.createQuery(
				"from Person where firstName='nobitha'").uniqueResult();
		Country country = person.getCountry();
		Passport pass = person.getPassport();
		ses.getTransaction().commit();
		ses.close();
		Assert.assertNotNull(country);
		Assert.assertNotNull(pass);
	}

	@Test
	public void testUpdate() {
		setUpData();
		Session ses = factory.openSession();
		ses.beginTransaction();
		// make sure you dont have duplicate names
		Person person = (Person) ses.createQuery(
				"from Person where firstName='nobitha'").uniqueResult();
		Country country = person.getCountry();
		Passport pass = person.getPassport();
		pass.setPassportNumber("Modified");
		Set<Transport> trans = person.getTransports();
		for (Transport it : trans) {
			it.setType("New Type1"); // !!! Note you are changing the master
										// data
		}
		ses.saveOrUpdate(person);
		ses.getTransaction().commit();
		ses.close();
	}

	@Test
	public void testDelete() {
		Session ses = factory.openSession();
		ses.beginTransaction();
		Person person = (Person) ses.createQuery("from Person where id=56")
				.uniqueResult();
		ses.delete(person);
		ses.getTransaction().commit();
		ses.close();
	}

	private void setUpData() {
		Session ses = factory.openSession();
		ses.beginTransaction();

		Person person = new Person();
		person.setFirstName("nobitha");
		person.setLastName("nobi");
		Passport passport = new Passport();
		passport.setPassportNumber("ALDYX193");
		person.setPassport(passport);
		passport.setPerson(person);
		Country country = (Country) ses.createCriteria(Country.class)
				.add(Restrictions.eq("name", "Japan")).uniqueResult();
		person.setCountry(country);
		Transport t1 = new Transport();
		t1.setType("Flying Machine");
		person.setTransports(new HashSet<Transport>());
		person.getTransports().add(t1);
		Transport t2 = new Transport();
		t2.setType("Time Machine");
		person.getTransports().add(t2);
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
