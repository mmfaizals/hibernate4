package css.training.hibernate.domain.annotation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.AfterClass;
import org.junit.Test;

public class Example09 {
	private static final SessionFactory factory = sessionFactory();

	@AfterClass
	public static void destroy() {
		if (factory != null) {
			factory.close();
		}
	}

	@Test
	public void testEagerLazy() throws Exception {
		Order order = create();
		readOrder(order);

	}

	private void readOrder(Order detached) throws Exception {
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			// execute one query to fetch order with fetch=FetchType.LAZY
			// execute join query to fetch order+ all lines with
			// fetch=FetchType.EAGER
			Order order = (Order) session.get(Order.class, detached.getId());
			List<LineItem> lines = order.getLines();
			// execute one query to fetch all lines with fetch=FetchType.LAZY
			lines.get(0);
			session.getTransaction().commit();

		} catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		}

	}

	private Order create() throws Exception {
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			Order order = createOrderAndLines();
			session.getTransaction().commit();
			return order;
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		}
	}

	private Order createOrderAndLines() {
		Order order = new Order();
		order.setName("order" + new Date());
		order.setLines(new ArrayList<LineItem>());
		for (int i = 1; i < 1000; i++) {
			LineItem li = new LineItem();
			li.setName("line" + i);
			li.setOrder(order);
			order.getLines().add(li);
		}
		factory.getCurrentSession().persist(order);
		return order;
	}

	private static SessionFactory sessionFactory() {
		ServiceRegistryBuilder builder = new ServiceRegistryBuilder();
		Configuration configuration = new Configuration().configure();
		configuration.setNamingStrategy(ImprovedNamingStrategy.INSTANCE)
				.addAnnotatedClass(Order.class)
				.addAnnotatedClass(LineItem.class);
		builder.applySettings(configuration.getProperties());
		SessionFactory factory = configuration.buildSessionFactory(builder
				.buildServiceRegistry());
		return factory;

	}
}
