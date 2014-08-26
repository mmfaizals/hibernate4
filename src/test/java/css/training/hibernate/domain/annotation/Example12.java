package css.training.hibernate.domain.annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import css.training.hibernate.spring.service.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class Example12 {
	@Autowired
	private CustomerService service;

	@Test
	public void testComponentMapping() {
		Customer cust = new Customer();
		cust.setName("Yuna");
		Address address = new Address();
		address.setStreet("snsd");
		address.setCity("Seoul");
		address.setPincode(11200);
		cust.setAddress(address);
		service.create(cust);
	}

}
