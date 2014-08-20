package css.training.hibernate.domain.annotation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import css.training.hibernate.domain.annotation.ContractEmployee;
import css.training.hibernate.domain.annotation.Employee;
import css.training.hibernate.domain.annotation.PermanentEmployee;

public class Example06 {
	private static final SessionFactory factory = sessionFactory();

	@AfterClass
	public static void destroy() {
		if (factory != null) {
			factory.close();
		}
	}

	@Test
	public void testSingleTablePerHierarchy() throws Exception {
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			insertBatman();
			insertSuperman();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		}
	}

	@Test
	public void readPolymorphic() throws Exception {
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			Employee emp = excuteWithInTransaction();
			session.getTransaction().commit();
			Assert.assertTrue(emp instanceof ContractEmployee);
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		}
	}

	private Employee excuteWithInTransaction() {
		insertBatman();
		insertSuperman();
		return readEmployee();
	}

	private Employee readEmployee() {
		Session session = factory.getCurrentSession();
		Employee emp = (Employee) session.createQuery(
				"from Employee where firstName='Bat'").uniqueResult();
		return emp;
	}

	private void insertSuperman() {
		Session session = factory.getCurrentSession();
		PermanentEmployee pt = new PermanentEmployee();
		pt.setFirstName("Super");
		pt.setLastName("man");
		pt.setJoiningDate(new java.sql.Date(System.currentTimeMillis()));
		session.persist(pt);
	}

	private void insertBatman() {
		Session session = factory.getCurrentSession();
		ContractEmployee cte = new ContractEmployee();
		cte.setFirstName("Bat");
		cte.setLastName("man");
		cte.setJoiningDate(new java.sql.Date(System.currentTimeMillis()));
		session.persist(cte);
	}

	private static SessionFactory sessionFactory() {
		ServiceRegistryBuilder builder = new ServiceRegistryBuilder();
		Configuration configuration = new Configuration().configure();
		configuration.setNamingStrategy(ImprovedNamingStrategy.INSTANCE)
				.addAnnotatedClass(ContractEmployee.class)
				.addAnnotatedClass(PermanentEmployee.class);
		builder.applySettings(configuration.getProperties());
		SessionFactory factory = configuration.buildSessionFactory(builder
				.buildServiceRegistry());
		return factory;

	}

}
