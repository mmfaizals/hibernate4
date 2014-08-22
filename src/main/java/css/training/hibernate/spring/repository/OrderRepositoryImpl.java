package css.training.hibernate.spring.repository;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import css.training.hibernate.domain.annotation.Customer;
import css.training.hibernate.domain.annotation.Order;

@Repository
class OrderRepositoryImpl implements OrderRepository {
	@Autowired
	private SessionFactory factory;

	public void create(Order order) {
		currentSession().save(order);
	}

	private Session currentSession() {
		return factory.getCurrentSession();
	}

	public Customer findCustomer(Order order) {
		Query query = currentSession()
				.createQuery(
						" select order.customer from Order order where order.id=:orderId");
		query.setInteger("orderId", order.getId());
		Customer customer = (Customer) query.uniqueResult();
		return customer;
	}

}
