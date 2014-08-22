package css.training.hibernate.spring.repository;

import css.training.hibernate.domain.annotation.Customer;
import css.training.hibernate.domain.annotation.Order;

public interface OrderRepository {
	void create(Order order);

	Customer findCustomer(Order order);
}
