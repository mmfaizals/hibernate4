package css.training.hibernate.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import css.training.hibernate.domain.annotation.Customer;
import css.training.hibernate.domain.annotation.Order;
import css.training.hibernate.spring.repository.CustomerRepository;
import css.training.hibernate.spring.repository.OrderRepository;

@Service
class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private OrderRepository orderRepo;

	@Transactional
	public void create(Customer customer) {
		customerRepo.create(customer);
	}

	@Transactional
	public void addOrder(Customer customer, Order order) {
		order.setCustomer(customer);
		orderRepo.create(order);
	}

	@Transactional
	public Customer findByOrder(Order order) {
		return orderRepo.findCustomer(order);
	}
}
