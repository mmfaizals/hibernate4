package css.training.hibernate.domain.annotation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class Example11 {
	@Autowired
	private SessionFactory factory;

	@Test
	public void testL2Cache() throws Exception {
		try {
			readOrder(1900544);
			readOrder(1900544);
		} finally {
			factory.close();
		}
	}

	private void readOrder(int id) throws Exception {
		Session session = factory.openSession();
		try {
			session.beginTransaction();
			Order order = (Order) session.load(Order.class, id);
			order.getName();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}

	}
}
