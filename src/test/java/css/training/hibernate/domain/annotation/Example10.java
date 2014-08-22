package css.training.hibernate.domain.annotation;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import css.training.hibernate.spring.service.CustomerService;

/**
 * 
 * Using hibernate with Spring Transaction management
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class Example10 {

	@Autowired
	private CustomerService service;

	@Test
	public void testCreateOrder() {
		Customer customer = new Customer();
		customer.setName("Shizuka");
		Order order = new Order();
		order.setName("order" + new Date());
		service.create(customer);
		service.addOrder(customer, order);
		Assert.assertNotNull(customer.getId());
		Assert.assertNotNull(order.getId());
	}

	@Test
	public void testFindCustomerByOrder() {
		Integer orderId = 1736704;
		Order order = new Order();
		order.setId(orderId);
		Customer customer = service.findByOrder(order);
		Assert.assertNotNull(customer);
	}
}
