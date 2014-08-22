package css.training.hibernate.spring.service;

import css.training.hibernate.domain.annotation.Customer;
import css.training.hibernate.domain.annotation.Order;

public interface CustomerService {
	void create(Customer customer);

	void addOrder(Customer customer, Order order);

	Customer findByOrder(Order order);
}
