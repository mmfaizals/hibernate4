package css.training.hibernate.spring.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import css.training.hibernate.domain.annotation.Customer;

@Repository
class CustomerRepositoryImpl implements CustomerRepository {
	@Autowired
	private SessionFactory factory;

	public Customer findOne(Integer id) {
		return (Customer) currentSession()
				.createCriteria(Customer.class).add(Restrictions.eq("id", id))
				.uniqueResult();
	}

	public void create(Customer customer) {
		currentSession().save(customer);
	}

	private Session currentSession() {
		return factory.getCurrentSession();
	}

}
