package css.training.hibernate.spring.repository;

import css.training.hibernate.domain.annotation.Customer;

public interface CustomerRepository {

	Customer findOne(Integer id);

	void create(Customer cust);
}
