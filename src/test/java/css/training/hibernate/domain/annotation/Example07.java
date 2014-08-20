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

/**
 * 
 * Run Tests individually , always remember to delete records from tables
 * 
 * <pre>
 * DELETE FROM employee;
 * 	DELETE FROM contract_employee;
 * 	DELETE FROM permanent_employee;
 * </pre>
 */
public class Example07 {
	private static final SessionFactory factory = sessionFactory();

	@AfterClass
	public static void destroy() {
		if (factory != null) {
			factory.close();
		}
	}

	@Test
	public void testTablePerSubClass() throws Exception {
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
	public void readPolymorphicTablePerSubClass() throws Exception {
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
		PermanentEmployee perm = new PermanentEmployee();
		perm.setFirstName("Super");
		perm.setLastName("man");
		perm.setSalaryPerMonth(2.0);
		perm.setBonus(1.0);
		perm.setJoiningDate(new java.sql.Date(System.currentTimeMillis()));
		session.persist(perm);
	}

	private void insertBatman() {
		Session session = factory.getCurrentSession();
		ContractEmployee contract = new ContractEmployee();
		contract.setFirstName("Bat");
		contract.setLastName("man");
		contract.setSalaryPerHour(3.0);
		contract.setJoiningDate(new java.sql.Date(System.currentTimeMillis()));
		session.persist(contract);
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
